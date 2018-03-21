package DATA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class txt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
     
	public static String readtxt(String time) throws IOException {
		 ArrayList<String>  al=getFileName();
		 for(int i=0;i<al.size();i++) {
			 if(al.get(i).contains(time)) {
				 time=al.get(i);
				 break;
			 }
		 }
		 String result="";
		 String pathname = "g:/txt/"+time; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径  
         File filename = new File(pathname); // 要读取以上路径的input。txt文件  
         InputStreamReader reader = new InputStreamReader(  
                 new FileInputStream(filename)); // 建立一个输入流对象reader  
         BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
         String line = "";  
         line = br.readLine();  
         while (line != null) {
        	 result+=line;
             line = br.readLine(); // 一次读入一行数据  
         }  
		return result;
	}
	
	public static ArrayList<String> getFileName() {
	      String path = "g:/txt"; // 路径
	      File f = new File(path);
	     /* if (!f.exists()) {
	            System.out.println(path + " not exists");
	            return;
	       }*/

	       File fa[] = f.listFiles();
	       ArrayList<String>  files=new ArrayList<String>();
	       for (int i = 0; i < fa.length; i++) {
	           File fs = fa[i];
	           if (fs.isDirectory()) {
	              // System.out.println(fs.getName() + " [目录]");
	           } else {
	              // System.out.println(fs.getName());
	        	   files.add(fs.getName());
	           }
	        }
	       return files;
	 }
}
