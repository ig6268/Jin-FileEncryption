package lock;
import security.CryptoUtils;
import javax.swing.*;

import file.getfile;

import java.awt.event.*;
import java.awt.*;
import java.io.*;

import java.util.StringTokenizer;

public class decryptpw extends JFrame implements ActionListener {
	private Container con;
	public static EncryptionController encryptionController;
	private JTextField tf1 = new JPasswordField(10);  //비밀번호 입력 텍스트필드
	private JButton bt1 = new JButton("확인");
	
	//File f2 = new File("C:\\login.bak");
	String s,s1,s2,s3,a1;
	
	 public decryptpw() {
		 this.init();
		 this.setTitle("암호확인");
		 this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		 this.setSize(300,120);
		 this.setLocation(900,400);
		 this.setVisible(true);
	 	}
	 
	 public void init() {
			
			con = this.getContentPane();
			
			JPanel pn1 = new JPanel(new FlowLayout());

			con.setLayout(new GridLayout(2,1));
			con.add(pn1);

			
			pn1.add(new JLabel("pw :"));
			pn1.add(tf1);
			pn1.add(bt1);	

			bt1.addActionListener(new ActionListener() { 
				//@Override
				public void actionPerformed(ActionEvent aa) {
					String depw = tf1.getText();
					encryptionController = new EncryptionController("ABCDEFGHIJKLMNOP");
					File f = new File("C:\\login.txt");
					String key = "secret key";
					
					
					
					try { // 복호화시 비밀번호 확인 함수

						BufferedReader in = new BufferedReader(new FileReader("c:\\login.txt"));

						
						while((a1 = in.readLine()) != null) {
							if(depw.equals(CryptoUtils.decryptAES256(a1, key))){ // 비밀번호를 복호화하여 값을 확인
							
							encryptionController.decryption();	
							in.close();
							break;
						
						}

						/*
						else if(!a1.equals(depw)){
							in.close(); // 닫기
							//out.close();
							//in.close();
							JOptionPane.showMessageDialog(null, "틀린 비밀번호입니다.",

								      "경고", JOptionPane.WARNING_MESSAGE);	
						}*/
							//in.close();
							//out.close();
						} 
					}
				catch (Exception e) {
					e.printStackTrace();
					
					}
					
					dispose();
				}
				
			});
	 }
	 
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}	 

	public static void main() {
		// TODO Auto-generated method stub
		decryptpw bbbbb = new decryptpw();
	}



}
