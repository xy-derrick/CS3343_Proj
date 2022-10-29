package ImgUtil;

import java.awt.image.BufferedImage;  
import java.io.ByteArrayInputStream;  
import java.io.ByteArrayOutputStream;  
import java.io.File;  
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import com.mysql.cj.jdbc.Blob;

import JDBCUtils.JDBCUtils;
import JDBCUtils.jdbc_test;
import java.util.Base64;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ImgUtil {  
    static Base64.Encoder encoder = Base64.getEncoder();  
    static Base64.Decoder decoder = Base64.getDecoder();  
  
    public static void main(String args[])throws IOException, SQLException
    {
    	String a="C:/Users/DELL/Desktop/photo.jpg";
    	new ImgUtil();
		//String result=ImgUtil.generateBase64(a);
		//System.out.print(result);
		System.out.print("-------------------------");
		//ImgUtil.GenerateImage(result, "C:/Users/DELL/Desktop/photo_file/photo.jpg");
		ImgUtil.saveImginDb("2","command1","C:/Users/DELL/Desktop/photo_file/photo.jpg");
		ImgUtil.getImgfromDb("2","command1","28");
		//System.out.print(result);
    }
    /**
	 * 自动将图片生成base64
	 * @param imageFileName 需要生成字符串的图片路径
	 */
	public static String generateBase64(String imageFileName) {
		Base64.Encoder utilEncoder = Base64.getEncoder();
		byte[] imageBytes = null;
		FileInputStream fileInputStream = null; //文件输入流
		try {
			fileInputStream = new FileInputStream(imageFileName);
			try {
				imageBytes = new byte[fileInputStream.available()];
				fileInputStream.read(imageBytes);
				return utilEncoder.encodeToString(imageBytes);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			if (null != fileInputStream){
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	 public static boolean GenerateImage(String imgData, String imgFilePath) throws IOException { // 对字节数组字符串进行Base64解码并生成图片
	        if (imgData == null) // 图像数据为空
	            return false;
	        OutputStream out = null;
	        try {
	            out = new FileOutputStream(imgFilePath);
	            // Base64解码
	            Base64.Decoder utilDecoder = Base64.getDecoder();
				byte[] b = utilDecoder.decode(imgData);
	            for (int i = 0; i < b.length; ++i) {
	                if (b[i] < 0) {// 调整异常数据
	                    b[i] += 256;
	                }
	            }
	            out.write(b);
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } finally {
	            out.flush();
	            out.close();
	            return true;
	        }
	    }
	 public static boolean  saveImginDb(String user_id, String command, String imagePath) throws SQLException, FileNotFoundException
	 {
		 	Connection conn = null;
	        Statement stmt = null;
	        ResultSet rs = null;
	        InputStream is = null;
	        try {
			 	conn = JDBCUtils.getCounection();
	            //define sql
	           
	          //get the executed sql object 
	            stmt = conn.createStatement();
	            //define sql, insert the new member
	            is=new FileInputStream(imagePath);
	            String sql = "INSERT into user_operation(user_id,operation,image) value ('" + user_id + "','" + command + "','"+generateBase64(imagePath)+"')";
	            int count = stmt.executeUpdate(sql);
	            return count != 0;

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            JDBCUtils.close(rs, stmt, conn);
	        }
	        return false;
	 }
	 public static void getImgfromDb(String user_id, String command, String id) throws SQLException, IOException
	 {
		 	Connection conn = null;
	        Statement stmt = null;
	        ResultSet rs = null;
	        InputStream is = null;
	        FileOutputStream fos = null;
	        try {
			 	conn = JDBCUtils.getCounection();

	          //get the executed sql object 
	            stmt = conn.createStatement();
	            //define sql, insert the new member
	            String sql = "select image from user_operation where user_id='" + user_id + "' and operation ='" + command + "' and id ='" + id+"' ";
	            rs = stmt.executeQuery(sql);
	            if (rs.next()) {
	            	String photo =  rs.getString("image");
	               // is = photo.getBinaryStream();
					
					 fos = new FileOutputStream("C:/Users/DELL/Desktop/photo_file/photo1.jpg");
					  //byte[] buffer = new byte[1024*1024*1024];
					  //int len;
					  //len = is.read(buffer);
					  Base64.Decoder utilDecoder = Base64.getDecoder();
					  byte[] b = utilDecoder.decode(photo);
					  ImgUtil.GenerateImage(photo,"C:/Users/DELL/Desktop/photo_file/photo2.jpg") ;
						/*
						 * for (int i = 0; i < b.length; ++i) { if (b[i] < 0) {// 调整异常数据 b[i] += 256; }
						 * } fos.write(b); fos.flush();
						 */ 
					  
					 /// System.out.print(buffer);
					 
	            }
	           
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            JDBCUtils.close(rs, stmt, conn);
	        }
	 }
    
  
}  