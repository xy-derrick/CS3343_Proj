package testing;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import java.sql.*;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

import JDBCUtils.jdbc_test;
import Java.Code.Command.Login.loginController;
import Java.Code.Software.imgProcessor;

import static org.junit.jupiter.api.Assertions.*;
public class test_login {
	private String absolutePath = System.getProperty("user.dir");
	@Test
	public void test_login_01() throws SQLException
	{
		String username = "xy";
		String password = "123";
		boolean flag = new jdbc_test().login(username, password);
		assertEquals(true, flag);
	}
	@Test
	public void test_login_02() throws SQLException
	{
		String username = null;
		String password =null;
		boolean flag = new jdbc_test().login(username, password);
		assertEquals(false, flag);
	}
	@Test
	public void test_login_03() throws SQLException
	{
		String username = "11213123111jxx";
		String password = "221231222";
		boolean flag = new jdbc_test().login(username, password);
		assertEquals(false, flag);
	}
	@Test
	public void test_login_04() throws SQLException
	{
		String username = "xy";
		String password =null;
		boolean flag = new jdbc_test().login(username, password);
		assertEquals(false, flag);
	}
	
	@Test
	public void test_register_03() throws SQLException
	{
		Random r = new Random();
		int number = r.nextInt(100000,7999999);
		String username = Integer.toString(number);
		String password = "2222";
		boolean flag = new jdbc_test().register(username, password);
		assertEquals(true, flag);
	}
	@Test
	public void test_register_05() throws SQLException
	{
		String username = "xy";
		String password = "123";
		boolean flag = new jdbc_test().register(username, password);
		assertEquals(false, flag);
	}
	@Test
	public void test_register_06() throws SQLException
	{
		String username = "xy";
		String password = null;
		boolean flag = new jdbc_test().register(username, password);
		assertEquals(false, flag);
	}
	@Test
	public void test_register_04() throws SQLException
	{
		String username = null;
		String password = null;
		boolean flag = new jdbc_test().register(username, password);
		assertEquals(false, flag);
	}
	@Test
	public void test_getid_05() throws SQLException
	{
		String username = "xy";
		int result = new jdbc_test().getId(username);
		assertEquals(result, 4);
	}
	
	@Test
	public void test_login_05() throws SQLException, IOException
	{
		
		String username = "xy";
		String password = "123";
		BufferedImage imag = null;
		String imagPath= absolutePath+"\\testcase\\test\\test1.jpg";
		imag = ImageIO.read(new File(imagPath));
		imgProcessor imgprocesser1 = new imgProcessor();
		imgprocesser1.setImg(imag);
		int result = loginController.login(username, password);
		loginController.save(imag);
		BufferedImage imag02 = null;
		imag02 = loginController.readLastData();
		int id01 = loginController.getLatestImgId();
		assertEquals(1, result);
	}
	@Test
	public void test_register_07() throws SQLException
	{
		Random r = new Random();
		int number = r.nextInt(100000,7999999);
		String username = Integer.toString(number);
		String password = "123222";
		int result = loginController.register(username, password);
		assertEquals(1, result);
	}
	@Test
	public void test_register_08() throws SQLException
	{
		String username = "xy";
		String password = "123";
		int result = loginController.register(username, password);
		assertEquals(0, result);
	}
	@Test
	public void test_register_09() throws SQLException
	{
		String username = null;
		String password = null;
		int result = loginController.register(username, password);
		assertEquals(0, result);
	}
	@Test
	public void test_login_10() throws SQLException
	{
		String username = "";
		String password = "";
		int result = loginController.login(username, password);
		assertEquals(0, result);
	}
	@Test
	public void test_login_11() throws SQLException, IOException
	{
		String username = "x";
		String password ="";
		BufferedImage imag = null;
		String imagPath= absolutePath+"\\testcase\\test\\test1.jpg";
		imag = ImageIO.read(new File(imagPath));
		imgProcessor imgprocesser1 = new imgProcessor();
		imgprocesser1.setImg(imag);
		int result = loginController.login(username, password);
		BufferedImage imag02 = null;
		imag02 = loginController.readLastData();
		int id01 = loginController.getLatestImgId();
		assertEquals(0, result);
	}
	@Test
	public void test_register_11() throws SQLException
	{
		String username = "xy";
		String password = null;
		int result = loginController.register(username, password);
		assertEquals(0, result);
	}


}
