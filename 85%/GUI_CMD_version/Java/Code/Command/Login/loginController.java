package Java.Code.Command.Login;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Java.Code.Software.imgProcessor;
import ImgUtil.ImgUtil;
import JDBCUtils.JDBCUtils;
import JDBCUtils.jdbc_test;

public class loginController {
	static int uid;
	static String username;
	private static int latest_img_id = 0;

	static public int login(String username, String password) throws SQLException {
		if (username.isEmpty() || password.isEmpty()) {
			return 0;
		}
		boolean flag = new jdbc_test().login(username, password);
		uid = new jdbc_test().getId(username);
		if (flag == false) {
			return 0;
		}
		return 1;
	}

	static public int register(String username, String password) throws SQLException {
		boolean flag = new jdbc_test().register(username, password);
		return flag == true ? 1 : 0;
	}

	static public void save(BufferedImage imag) {
		String uid_s = getUid() + "";
		try {
			ImgUtil.saveImginDb(uid_s, imag, "jpg");
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static BufferedImage readLastData() throws SQLException, IOException {
		if (getLatestImgId() == 0) {
			System.out.print("No latest image.");
			return null;
		} else {
			String img_id = getLatestImgId() + "";
			String uid_s = getUid() + "";
			System.out.print("The latest image id is: " + latest_img_id + "\n");
			return ImgUtil.getImgfromDb(uid_s, img_id);
		}
	}

	public static int getLatestImgId() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtils.getCounection();
			String sql = "select id from user_operation where id = (select max(id) from user_operation where user_id="
					+ uid + ")";
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);
			if (rs.next()) {

				String latest_img_id_s = rs.getString(1);
				if (latest_img_id_s == null) {
					latest_img_id = 0;
				} else {
					latest_img_id = Integer.parseInt(latest_img_id_s);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCUtils.close(rs, stmt, conn);
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return latest_img_id;
	}

	public static int getUid() {
		return uid;
	}

	public String getUsername() {
		return username;
	}

}