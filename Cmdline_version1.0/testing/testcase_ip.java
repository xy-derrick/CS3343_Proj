package testing;

import static org.junit.Assert.*;
import org.junit.Test;

import Java.Code.Software.Software;
import Java.Code.Software.imgProcessor;


public class testcase_ip {
	@Test
	// Sample Test Case
	public void test_ImgProcessor_01(){
		imgProcessor ip = new imgProcessor("01");
		ip.setName("01");
		assertEquals(ip.getName(),"01");
	}
	@Test
	public void test_ImgProcessor_02(){
		imgProcessor ip = new imgProcessor(1);
		ip.setImg(null);
		assertEquals(ip.getImg(),null);
		
	}
	@Test
	public void test_ImgProcessor_03(){
		imgProcessor ip = new imgProcessor();
		ip.setPath("4010");
		assertEquals(ip.getPath(),"4010");
	}
	
	
	
}
