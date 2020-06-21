package lock;
import security.CryptoUtils;
import javax.swing.*;

import file.getfile;

import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class encryptpw extends JFrame implements ActionListener {
	private Container con;
	public static EncryptionController encryptionController;
	private JTextField tf1 = new JPasswordField(10);  //비밀번호 입력 텍스트필드
	private JButton bt1 = new JButton("확인");
	
	 public encryptpw() {
		 this.init();
		 this.setTitle("암호등록");
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
					String enpw = tf1.getText();
					String str;
					String key = "secret key";
					
					try { // txt 파일 출력 함수
						BufferedWriter out = new BufferedWriter(new FileWriter("c:\\login.txt",true)); 
						out.write(CryptoUtils.encryptAES256(enpw, key));
						out.newLine(); 
						out.close(); // 닫기
						dispose();
					} 
				catch (Exception e) {
					}
					
					encryptionController = new EncryptionController("ABCDEFGHIJKLMNOP");
					
		            encryptionController.encryption();

				}
			});
	 }
	 
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}	 

	public static void main() {
		// TODO Auto-generated method stub
		encryptpw aaaaa = new encryptpw();
	}



}
