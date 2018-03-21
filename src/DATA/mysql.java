package DATA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;


public class mysql {
	 // 驱动程序名
    static String driver = "com.mysql.jdbc.Driver";
    // URL指向要访问的数据库名scutcs
    static String url = "jdbc:mysql://127.0.0.1:3306/wechat?characterEncoding=utf8&useSSL=false";
    // MySQL配置时的用户名
    static String user = "root"; 
    // MySQL配置时的密码
    static String password = "123456";   
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<article> al=getall();
	    for(int i=0;i<al.size();i++) {
	    	System.out.println(al.get(i).id);
	    	System.out.println(al.get(i).author);
	    	System.out.println(al.get(i).title);
	    	System.out.println(al.get(i).summary);
	    	System.out.println(al.get(i).publish_time);
	    	System.out.println(al.get(i).get_time);
	    }
	}
	//获取全部信息
	public static ArrayList<article> getall() {
			ArrayList<article> all=new ArrayList<article>(); 
			try { 
		         // 加载驱动程序
		         Class.forName(driver);

		         // 连续数据库
		         Connection conn = DriverManager.getConnection(url, user, password);

		         if(!conn.isClosed()) {
		          System.out.println("Succeeded connecting to the Database!");
		         }
		         Statement statement = conn.createStatement();
		            //要执行的SQL语句
		            String sql = "select * from article where is_fail='0' order by id DESC ";
		            //3.ResultSet类，用来存放获取的结果集！！
		            ResultSet rs = statement.executeQuery(sql);
		            int id;
		            String author;
		            String title;
		            String summary;
		            String publish_time;
		            String get_time;
		            while(rs.next()){      
		                id = rs.getInt("id");
		                author = rs.getString("author");
	                    title = rs.getString("title");
	                    summary=rs.getString("summary");
	                    publish_time=rs.getString("publish_time");
	                    get_time=rs.getString("get_time");
	                    article a=new article(id,author,title,summary,publish_time,get_time);
	                    all.add(a);
		            }
		       
		         conn.close();
		         System.out.println("读取数据完成!");
		        } catch(ClassNotFoundException e) {


		         System.out.println("Sorry,can`t find the Driver!"); 
		         e.printStackTrace();


		        } catch(SQLException e) {


		         e.printStackTrace();


		        } catch(Exception e) {


		         e.printStackTrace();


		        }
			return all; 
		}
	
	
	public static void update(article ar) {
		try { 
	         // 加载驱动程序
	         Class.forName(driver);

	         // 连续数据库
	         Connection conn = DriverManager.getConnection(url, user, password);

	         if(!conn.isClosed()) {
	          //System.out.println("Succeeded connecting to the Database!");
	         }
	         Statement statement = conn.createStatement();
	            //要执行的SQL语句
	            String sql = "UPDATE article set is_fail='1' where get_time='"+ar.getGet_time()+"'";
	            //3.ResultSet类，用来存放获取的结果集！！
	           statement.executeUpdate(sql);
	           
	       
	         conn.close();
	         System.out.println("更新数据完成!");
	        } catch(ClassNotFoundException e) {


	         System.out.println("Sorry,can`t find the Driver!"); 
	         e.printStackTrace();


	        } catch(SQLException e) {


	         e.printStackTrace();


	        } catch(Exception e) {


	         e.printStackTrace();


	        }
	}
}
