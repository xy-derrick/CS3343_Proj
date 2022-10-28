package jdbc_demo;
import java.sql.*;  
    public class demo{  
      public static void main(String args[]) {  
        try {  
          Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序     
          //Class.forName("org.gjt.mm.mysql.Driver");  
         System.out.println("Success loading Mysql Driver!");  
        }  
        catch (Exception e) {  
          System.out.print("Error loading Mysql Driver!");  
          e.printStackTrace();  
        }  
        try {  
          Connection connect = DriverManager.getConnection(  
              "jdbc:mysql://localhost:3306/dm","cs3343_tester","123456");  
               //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码 必须自定义  
      
          System.out.println("Success connect MySql server!");  
          Statement stmt = connect.createStatement();  
          ResultSet rs = stmt.executeQuery("select * from user");  
                                                                  //user 为你表的名称  
          while (rs.next()) {  
            System.out.println(rs.getString("name"));  
            System.out.println(rs.getString("password"));
    }  
        }  
        catch (Exception e) {  
          System.out.print("get data error!");  
          e.printStackTrace();  
        }  
      }  
    }  