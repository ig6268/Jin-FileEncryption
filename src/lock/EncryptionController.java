package lock;

import security.CryptoUtils;
import security.SearchDirectory;

import java.io.File;
import java.util.ArrayList;


/**
 * Encrypt&Decrypt �������� �ʿ��� �޼ҵ带 �ڵ����� ȣ�� �� ó��
 */

public class EncryptionController {
    /**
     * SearchDirectory : ���� �˻��� �ʿ��� ��ü
     * isEncrypted : ��ȣȭ �� ���¸� boolean ������ ��Ÿ��
     * key : ����ȣȭ�� �ʿ��� Ű
     */
    private SearchDirectory searchDirectory;
    private boolean isEncrypted = false;
    private String key;

    /**
     * constructor
     *
     * @param key
     */
    public EncryptionController(String key) {
        this.searchDirectory = new SearchDirectory();
        this.key = key;
       // this.isEncrypted = searchDirectory.isEncrypted();
    }

    /**
     * isEncrypte get
     *
     * @return
     */
    public boolean isEncrypted() {
        return this.isEncrypted;
    }

    /**
     * ��ȣȭ �Ѱ� �޼ҵ�
     */
    public void encryption() {
        ArrayList<File> files = searchDirectory.allFileSearch();
      //  searchDirectory.setEncrypted();
        System.out.println("��ȣȭ ����");
        start.txt_area.append("��ȣȭ ����\n");
        CryptoUtils.encrypt(files, key);
        System.out.println(files.size() + "���� ���� ��ȣȭ ����");
        start.txt_area.append(files.size() + "���� ���� ��ȣȭ ����\n");
        searchDirectory.clearFiles();
    }

    /**
     * ��ȣȭ �Ѱ� �޼ҵ�
     */
    public void decryption() {
        ArrayList<File> encrypted_files = searchDirectory.decryptSearch();
        System.out.println("��ȣȭ ����");
        start.txt_area.append("��ȣȭ ����\n");
        CryptoUtils.decrypt(encrypted_files, key);
        System.out.println(encrypted_files.size() + "�� ���� ��ȣȭ ����");
        start.txt_area.append(encrypted_files.size() + "�� ���� ��ȣȭ ����\n");
       // searchDirectory.setDecrypted();
        searchDirectory.clearFiles();
    }
    public void deletion() {
        ArrayList<File> encrypted_files = searchDirectory.decryptSearch();
        System.out.println("���ϻ��� ����");
       // searchDirectory.setDecrypted();
        CryptoUtils.delteion(encrypted_files, key);
      
    }
}