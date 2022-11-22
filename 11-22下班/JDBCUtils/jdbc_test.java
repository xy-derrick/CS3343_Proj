package JDBCUtils;

import java.sql.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class jdbc_test {
    
    //login method
    public boolean login(String username,String password) throws SQLException {
        if (username == null || password == null) {
            return false;
        }
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        conn = JDBCUtils.getCounection();
        //define sql
        String sql = "select * from user where name='" + username + "' and password ='" + password + "' ";
        //get the executed sql object 
        stmt = conn.createStatement();
        //query
        rs = stmt.executeQuery(sql);
        boolean flag = rs.next(); 
        JDBCUtils.close(rs, stmt, conn);
        return flag;
    }

    //register method
    public boolean register(String username,String password) throws SQLException {
        if (username == null || password == null) {
            return false;
        }
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        conn = JDBCUtils.getCounection();
        //define sql
        String sql = "select * from user where name='" + username + "'";
      //get the executed sql object 
        stmt = conn.createStatement();
      //query
        rs = stmt.executeQuery(sql);
        if (rs.next()) return false;     //If the account is exist, the refuse the register
        //define sql, insert the new member
        sql = "INSERT into user(name,password,latest_img_id) value ('" + username + "','" + password +"','" + "0"+ "')";
        //query
        int count = stmt.executeUpdate(sql);
        JDBCUtils.close(rs, stmt, conn);
        return count != 0;
           
    }
    
    public int getId(String username) throws SQLException
    {
    	int id = 0;
    	List<String> list=new ArrayList<String>();
    	Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        conn = JDBCUtils.getCounection();
        String sql = "select id from user where name='" + username + "'";
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        
        while (rs.next()) {
            list.add(rs.getString(1));
           }
        //get the ID
        id = Integer.parseInt(list.get(0));
 
        JDBCUtils.close(rs, stmt, conn);
     
    	return id;
    }
}   


