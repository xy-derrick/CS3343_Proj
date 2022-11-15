package Java.Code.Command.Login;

import Java.Code.Command.Base.Command;
import Java.Code.Software.imgProcessor;
import JDBCUtils.*;
import java.sql.SQLException;
import java.util.Scanner;

public class Register extends Command {

	public Register(imgProcessor receiver, int id) throws ClassNotFoundException, SQLException {
		super(receiver);
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Input your user name:");
			String username = scanner.next();
			System.out.print("Input your your password:：");
			String password = scanner.next();

			boolean flag = new jdbc_test().register(username, password);
			while (!flag) {
				System.out.print("Register fail,Please reinput the usename：");
				username = scanner.next();
				System.out.print("Please inpit your password：");
				password = scanner.next();
				flag = new jdbc_test().register(username, password);
				id = new jdbc_test().getId(username);
			}
		}
		System.out.println("Sucessful register1");

	}

	@Override
	public void execute() {

	}

}
