package testing;
import java.sql.*;

import org.junit.jupiter.api.Test;

import JDBCUtils.jdbc_test;

import static org.junit.jupiter.api.Assertions.*;
public class test_login {

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
		String username = "1121312311";
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
		String username = "test_16";
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
}
