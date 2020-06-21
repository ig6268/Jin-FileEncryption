package security;

import lock.start;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;


public class CryptoUtils {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";

 
    public static void encrypt(String key, File inputFile, File outputFile)
            throws CryptoException {
        doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
    }

 
    public static void decrypt(String key, File inputFile, File outputFile)
            throws CryptoException {
        doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile);
    }


    /**
     * 암호화 알고리즘
     *
     * @param cipherMode
     * @param key
     * @param inputFile
     * @param outputFile
     * @throws CryptoException
     */
    private static void doCrypto(int cipherMode, String key, File inputFile,
                                 File outputFile) throws CryptoException {
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(cipherMode, secretKey);

            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();

        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException | IOException ex) {
            throw new CryptoException("Error encrypting/decrypting file", ex);
        }
    }

    /**
     * 리스트 암호화 메소드
     *
     * @param files
     * @param key
     */
    public static void encrypt(ArrayList<File> files, String key) {
        for (File file : files) {
            try {
                System.out.print(file.getPath()+"...");
                start.txt_area.append(file.getPath()+"...");
                File output = new File(file.getPath() + ".encrypt");
                CryptoUtils.encrypt(key, file, output);
                file.delete();
                System.out.println("Done");	// 어떤 파일을 암호화하고 있는지 화면에 출력
                start.txt_area.append("완료\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 리스트 복호화 메소드
     *
     * @param files
     * @param key
     */
    public static void decrypt(ArrayList<File> files, String key) {
        for (File file : files) {
            try {
                System.out.print(file.getPath()+"...");
                start.txt_area.append(file.getPath()+"...");
                String file_name = file.getPath().split(".encrypt")[0];
                File output = new File(file_name);
                CryptoUtils.decrypt(key, file, output);
                file.delete();
                System.out.println("Done"); //어떤 파일을 복호화 하고있는지 화면에 출력
                start.txt_area.append("완료\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 파일 제거 메소드
     * 
     * @param files
     * @param key
     */
    public static void delteion(ArrayList<File> files, String key) {
        for (File file : files) {
            try {
                file.delete();
                System.out.println(file+" delete");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }
    
    /**
	 * AES 256 으로 문자를 암호화 한다.
	 * @param msg
	 * @param key
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidParameterSpecException
	 * @throws UnsupportedEncodingException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
    
    public static String encryptAES256(String msg, String key) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidParameterSpecException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[20];
		random.nextBytes(bytes);
		byte[] saltBytes = bytes;
		
		// Password-Based Key Derivation function 2
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		// 70000번 해시하여 256 bit 길이의 키를 만든다.
		PBEKeySpec spec = new PBEKeySpec(key.toCharArray(), saltBytes, 70000, 256);
		
		SecretKey secretKey = factory.generateSecret(spec);
		SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");
		
		// 알고리즘/모드/패딩
		// CBC : Cipher Block Chaining Mode
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secret);
		AlgorithmParameters params = cipher.getParameters();
		// Initial Vector(1단계 암호화 블록용)
		byte[] ivBytes = params.getParameterSpec(IvParameterSpec.class).getIV();
		
		byte[] encryptedTextBytes = cipher.doFinal(msg.getBytes("UTF-8"));
		
		byte[] buffer = new byte[saltBytes.length + ivBytes.length + encryptedTextBytes.length];
		System.arraycopy(saltBytes, 0, buffer, 0, saltBytes.length);
        System.arraycopy(ivBytes, 0, buffer, saltBytes.length, ivBytes.length);
	    System.arraycopy(encryptedTextBytes, 0, buffer, saltBytes.length + ivBytes.length, encryptedTextBytes.length);
	    
		return Base64.getEncoder().encodeToString(buffer);
	}
	
	/**
	 * 위에서 암호화된 내용을 복호화 한다.
	 * @param msg
	 * @param key
	 * @return
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static String decryptAES256(String msg, String key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		ByteBuffer buffer = ByteBuffer.wrap(Base64.getDecoder().decode(msg));
		
		byte[] saltBytes = new byte[20];
		buffer.get(saltBytes, 0, saltBytes.length);
		byte[] ivBytes = new byte[cipher.getBlockSize()];
		buffer.get(ivBytes, 0, ivBytes.length);
		byte[] encryoptedTextBytes = new byte[buffer.capacity() - saltBytes.length - ivBytes.length];
		buffer.get(encryoptedTextBytes);
		
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		PBEKeySpec spec = new PBEKeySpec(key.toCharArray(), saltBytes, 70000, 256);
		
		SecretKey secretKey = factory.generateSecret(spec);
		SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");
		
		cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(ivBytes));
		
		byte[] decryptedTextBytes = cipher.doFinal(encryoptedTextBytes);
		//byte[] decryptedTextBytes = cipher.doFinal( Base64.getDecoder().decode(msg.getBytes()) );
		return new String(decryptedTextBytes);
	}
}