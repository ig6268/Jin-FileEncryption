package lock;

import file.getfile;
//import javafx.concurrent.Task;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import javax.swing.JOptionPane;

public class start extends JFrame implements ActionListener {
		public static EncryptionController encryptionController;
		
		private Container con;
		
		public static JTextArea txt_area;
		
		private JTextField tf1 = new JTextField(27); 
		
		private JButton bt1 = new JButton("...");
		private JButton bt3 = new JButton("��ȣȭ");
		private JButton bt4 = new JButton("��ȣȭ");


public start() {
	
	this.init();
	this.setTitle("AES�Ϻ�ȣȭ");
	this.setSize(450,250);
	this.setLocation(700,400);
	
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
	
}

public void init() {
	
	con = this.getContentPane();
	
	JPanel pn1 = new JPanel(new FlowLayout());
	//JPanel pn2 = new JPanel(new FlowLayout());		

	con.setLayout(new GridLayout(2,1));
	con.add(pn1);

	txt_area = new JTextArea();
	JScrollPane jp = new JScrollPane(txt_area);
	jp.setEnabled(false);	
	
	pn1.add(new JLabel("���ϰ��"));
	pn1.add(tf1);
	pn1.add(bt1);	

	add(jp);

	pn1.add(bt3);
	pn1.add(bt4);
	
	bt1.addActionListener(new ActionListener() { 
		//@Override
		public void actionPerformed(ActionEvent aa) {
			//getfile gf = new getfile();
			
			getfile.main(null);
			tf1.setText(getfile.getf);
		}
	});
	
	//��ȣȭ ��ư�� ��������
	bt3.addActionListener(new ActionListener() { 
		//@Override
		public void actionPerformed(ActionEvent aa) {
			//String check=tf1.getText();
			//check = tf1.getText();
			
			encryptpw.main();
			
			txt_area.setText("");
			
	
			
			try
	        {
	            (new Serial()).connect("COM6"); //�Է��� ��Ʈ�� ����
	        }
	        catch ( Exception e )
	        {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        
	 
			
			/*
			//����������� ��ȣȭ�ϱ� ���� �̱���
				encryptionController = new EncryptionController("ABCDEFGHIJKLMNOP");
				
	            encryptionController.encryption();
			*/	
		}
	});
	
	//��ȣȭ ��ư�� ��������
	bt4.addActionListener(new ActionListener() { 
		//@Override
		public void actionPerformed(ActionEvent aa) {
			
			decryptpw.main();
			txt_area.setText("");
			
			try
	        {
	            (new Serial()).connect("COM6"); //�Է��� ��Ʈ�� ����
	        }
	        catch ( Exception e )
	        {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
			/*
			String check=tf1.getSelectedText();
			
			txt_area.setText("");
			
			if(check == null) {
				JOptionPane.showMessageDialog(null, "��θ� ���������ʾҽ��ϴ�."
						, "���", JOptionPane.WARNING_MESSAGE);

					    return;
			
			} 
			else if(check != null){
			encryptionController = new EncryptionController("ABCDEFGHIJKLMNOP");
			
			encryptionController.decryption();
			}*/
		}
	});
}	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	
	public static void main(String[] args) throws Exception {
		
	start st = new start();

	}



}
