package ImgUtil;

import java.io.IOException;

import JDBCUtils.JDBCUtils;
import java.util.Base64;

import javax.imageio.ImageIO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.image.BufferedImage;

public class ImgUtil {
	static Base64.Encoder encoder = Base64.getEncoder();
	static Base64.Decoder decoder = Base64.getDecoder();

	public static String generateBase64(BufferedImage imag, String type) {
		Base64.Encoder utilEncoder = Base64.getEncoder();
		byte[] buffer = null;
		FileInputStream fileInputStream = null;
		try {
			try {
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				ImageIO.write(imag, type, os);
				ByteArrayInputStream file = new ByteArrayInputStream(os.toByteArray());
				buffer = new byte[file.available()];
				file.read(buffer);
				return utilEncoder.encodeToString(buffer);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} finally {
			if (null != fileInputStream) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static BufferedImage GenerateImage(String imgData) throws IOException {
		if (imgData == null)
			return null;
		BufferedImage imag = null;
		try {
			Base64.Decoder utilDecoder = Base64.getDecoder();
			byte[] b = utilDecoder.decode(imgData);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			ByteArrayInputStream file = new ByteArrayInputStream(b);
			imag = ImageIO.read(file);
			return imag;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imag;
	}

	public static boolean saveImginDb(String user_id, BufferedImage imag, String type)
			throws SQLException, FileNotFoundException {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getCounection();
			stmt = conn.createStatement();
			String sql = "INSERT into user_operation(user_id,image) value ('" + user_id + "','"
					+ generateBase64(imag, type) + "')";
			System.out.println(sql);
			int count = stmt.executeUpdate(sql);
			return count != 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, stmt, conn);
		}
		return false;
	}

	public static BufferedImage getImgfromDb(String user_id, String id) throws SQLException, IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		BufferedImage imag = null;
		try {
			conn = JDBCUtils.getCounection();
			stmt = conn.createStatement();
			String sql = "select image from user_operation where user_id='" + user_id + "' and id ='" + id + "' ";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String photo = rs.getString("image");
				imag = ImgUtil.GenerateImage(photo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, stmt, conn);
			return imag;
		}
	}

}