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
	private JTextField tf1 = new JPasswordField(10);  //��й�ȣ �Է� �ؽ�Ʈ�ʵ�
	private JButton bt1 = new JButton("Ȯ��");
	
	//File f2 = new File("C:\\login.bak");
	String s,s1,s2,s3,a1;
	
	 public decryptpw() {
		 this.init();
		 this.setTitle("��ȣȮ��");
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
					
					
					
					try { // ��ȣȭ�� ��й�ȣ Ȯ�� �Լ�

						BufferedReader in = new BufferedReader(new FileReader("c:\\login.txt"));

						
						while((a1 = in.readLine()) != null) {
							if(depw.equals(CryptoUtils.decryptAES256(a1, key))){ // ��й�ȣ�� ��ȣȭ�Ͽ� ���� Ȯ��
							
							encryptionController.decryption();	
							in.close();
							break;
						
						}

						/*
						else if(!a1.equals(depw)){
							in.close(); // �ݱ�
							//out.close();
							//in.close();
							JOptionPane.showMessageDialog(null, "Ʋ�� ��й�ȣ�Դϴ�.",

								      "���", JOptionPane.WARNING_MESSAGE);	
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
