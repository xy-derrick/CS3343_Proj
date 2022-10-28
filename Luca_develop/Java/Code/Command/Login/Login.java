package Code.Command.Login;


import Code.Command.Base.Command;
import Code.Software.imgProcessor;
import JDBCUtils.*;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class Login extends Command{

    public Login(imgProcessor receiver, int id) throws ClassNotFoundException, SQLException {
        super(receiver);
        try (Scanner scanner = new Scanner(System.in)) {
 			System.out.print("Input your user name: ");
 			String username = scanner.next();
 			System.out.print("Input your your password:：");
 			String password = scanner.next();

 			boolean flag = new jdbc_test().login(username, password);
 			while(!flag) {
 			    System.out.print("Login fail,Please reinput the usename：");
 			    username = scanner.next();
 			    System.out.print("Please inpit your password：");
 			    password = scanner.next();
 			    flag = new jdbc_test().login(username, password);
 			}
 			id =  new jdbc_test().getId(username);
 		}
         System.out.println("Sucessful login");
        
        
    }
    
    @Override
    public void execute() {
       
    }

    @Override
    public void undo() {

    }
    
 
}
