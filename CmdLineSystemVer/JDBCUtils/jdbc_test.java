package JDBCUtils;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class jdbc_test {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner scanner_1 = new Scanner(System.in);
		System.out.print("Register or Login:(If register, input 1. If login, input 2.): ");
		String decision = scanner_1.next();
		switch (decision) {
		case "1":
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
				}
			}
			System.out.println("Sucessful register1");
			break;
		case "2":
			try (Scanner scanner = new Scanner(System.in)) {
				System.out.print("Input your user name: ");
				String username = scanner.next();
				System.out.print("Input your your password:：");
				String password = scanner.next();

				boolean flag = new jdbc_test().login(username, password);
				while (!flag) {
					System.out.print("Login fail,Please reinput the usename：");
					username = scanner.next();
					System.out.print("Please inpit your password：");
					password = scanner.next();
					flag = new jdbc_test().login(username, password);
				}
			}
			System.out.println("Sucessful login");
			break;

		}
	}

	// login method
	public boolean login(String username, String password) throws SQLException {
		if (username == null || password == null) {
			return false;
		}
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getCounection();
			// define sql
			String sql = "select * from user where name='" + username + "' and password ='" + password + "' ";
			// get the executed sql object
			stmt = conn.createStatement();
			// query
			rs = stmt.executeQuery(sql);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, stmt, conn);
		}
		return false;
	}

	// register method
	public boolean register(String username, String password) throws SQLException {
		if (username == null || password == null) {
			return false;
		}
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtils.getCounection();
			// define sql
			String sql = "select * from user where name='" + username + "'";
			// get the executed sql object
			stmt = conn.createStatement();
			// query
			rs = stmt.executeQuery(sql);
			if (rs.next())
				return false; // If the account is exist, the refuse the register
			// define sql, insert the new member
			sql = "INSERT into user(name,password) value ('" + username + "','" + password + "')";
			// query
			int count = stmt.executeUpdate(sql);
			return count != 0;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// close the database.
			JDBCUtils.close(rs, stmt, conn);
		}
		return false;

	}

	public int getId(String username) throws SQLException {
		int id = 0;
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getCounection();
			String sql = "select id from user where name='" + username + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				list.add(rs.getString(1));
			}
			// get the ID
			id = Integer.parseInt(list.get(0));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// close the database.
			JDBCUtils.close(rs, stmt, conn);
		}
		return id;
	}

	public void appendRecord(int id, String operation) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtils.getCounection();
			// define sql
			String sql = "INSERT into user_opeartion(user_id, operation) value ('" + id + "','" + operation + "')";
			// get the executed sql object
			stmt = conn.createStatement();
			// query
			int count = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// close the database.
			JDBCUtils.close(rs, stmt, conn);
		}
	}
}
