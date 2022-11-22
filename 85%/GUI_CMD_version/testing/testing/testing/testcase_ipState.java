package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Java.Code.Software.Software;
import Java.Code.Software.imgProcessor;
import Java.Code.Software.ipState;


public class testcase_ipState {
	@Test
	public void test_software_01(){
		
		ipState is = new ipState(new ArrayList<>(),null);
		ArrayList sp_list = new ArrayList<>();
		imgProcessor ip = new imgProcessor();
		is.setImgProcessorList(sp_list);
		is.setMain_ip(ip);
		assertEquals(is.getMain_ip(),ip);
		assertEquals(is.getImgProcessorList(),sp_list);
	}
	
	
	
}
