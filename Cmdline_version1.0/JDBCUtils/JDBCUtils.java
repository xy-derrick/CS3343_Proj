package JDBCUtils;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private static String url;
    private static String user;
    private static String psssword;
    private static String driver;

    //Read the file and get the values just once, using static code blocks
    static {

            //1.Create the Properties collection class
           // Properties pro=new Properties();
            //Get the file ————>ClassLoader in the SRC path
            //ClassLoader classLoader=JDBCUtils.class.getClassLoader();
            //URL res=classLoader.getResource("jdbc.properties");
            //String path = res.getPath();
            //System.out.println(path);

            //2.Load file
            //pro.load(new FileReader(path));

            //3.Get data and assign values
            url = "jdbc:mysql://localhost:3306/dm";
            user = "cs3343_tester";
            psssword = "123456";
            driver = "com.mysql.cj.jdbc.Driver";

            //4.Registration drive
            try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


    }

    //Connection
    public static Connection getCounection() throws SQLException{
        return DriverManager.getConnection(url,user,psssword);
    }

    //Release the source
    public static void close(ResultSet rs,Statement stmt,Connection conn) throws SQLException{  //ResultSet 结果集
        if(rs != null){
            rs.close();
            stmt.close();
        }
        if (stmt != null){
            stmt.close();
        }
        if(conn != null){
            conn.close();
        }
    }

}
