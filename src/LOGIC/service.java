package LOGIC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import DATA.article;
import DATA.mysql;
import DATA.txt;

public class service {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
    
	//读取全部数据
	public static ArrayList<article> getall() {
		return mysql.getall();
		
	}
	
	//获取文章的txt文件
	public static String getArticle(String time) throws IOException {
		String result=txt.readtxt(time);
		return result;
	}
	
	//处理需要上传的文章
	public static void upload(ArrayList<article> result) throws IOException {
		for(int i=0;i<result.size();i++) {
			mysql.update(result.get(i));
			copyDir("G:\\img\\"+result.get(i).getGet_time(),"G:\\News\\src\\main\\webapp\\images\\"+result.get(i).getGet_time());
			System.out.println("图片文件夹复制成功!");
		}
		
		//图片加入images
	}
	
	//复制文件夹
	public static void copyDir(String oldPath, String newPath) throws IOException {
	        File file = new File(oldPath);
	        String[] filePath = file.list();
	        
	        if (!(new File(newPath)).exists()) {
	            (new File(newPath)).mkdir();
	        }
	        
	        for (int i = 0; i < filePath.length; i++) {          
	            if (new File( oldPath  + file.separator + filePath[i]).isFile()) {
	                copyFile(oldPath + file.separator + filePath[i], newPath + file.separator + filePath[i]);
	            }
	            
	        }
	}
		
	public static void copyFile(String oldPath, String newPath) throws IOException {
	        File oldFile = new File(oldPath);
	        File file = new File(newPath);
	        FileInputStream in = new FileInputStream(oldFile);
	        FileOutputStream out = new FileOutputStream(file);;

	        byte[] buffer=new byte[2097152];
	        
	        while((in.read(buffer)) != -1){
	            out.write(buffer);
	        }
	    
	        
	 }
}
