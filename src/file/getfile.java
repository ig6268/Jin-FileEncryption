package file;

import java.io.InputStream;
import java.io.File;
import javax.swing.JFileChooser;

import javax.swing.JOptionPane;

 

public class getfile {
	public static String getf;
	

  public static void main(String[] args){
	  

   JFileChooser chooser = new JFileChooser(); //��ü ����
   chooser.setFileSelectionMode(chooser.DIRECTORIES_ONLY);//���丮�� ���ð���
    
    
   int ret = chooser.showOpenDialog(null);  //����â ����
  
 

   if (ret != JFileChooser.APPROVE_OPTION) {

    JOptionPane.showMessageDialog(null, "��θ� ���������ʾҽ��ϴ�.",

      "���", JOptionPane.WARNING_MESSAGE);

    return;

   }

 

 
   String filePath = chooser.getSelectedFile().getPath();  //���ϰ�θ� ������

   getf = filePath;
   System.out.println(filePath);  //���

  }

}
