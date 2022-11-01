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

    // Read the file and get the values just once, using static code blocks
    static {
        try {
            // 1.Create the Properties collection class
            Properties pro = new Properties();
            // Get the file ————>ClassLoader in the SRC path
            ClassLoader classLoader = JDBCUtils.class.getClassLoader();
            URL res = classLoader.getResource("jdbc.properties");
            String path = res.getPath();
            System.out.println(path);

            // 2.Load file
            pro.load(new FileReader(path));

            // 3.Get data and assign values
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            psssword = pro.getProperty("password");
            driver = pro.getProperty("driver");

            // 4.Registration drive
            Class.forName(driver);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    // Connection
    public static Connection getCounection() throws SQLException {
        return DriverManager.getConnection(url, user, psssword);
    }

    // Release the source
    public static void close(ResultSet rs, Statement stmt, Connection conn) throws SQLException { // ResultSet 结果集
        if (rs != null) {
            rs.close();
            stmt.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public static void close(Statement stmt, Connection conn) throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
}
