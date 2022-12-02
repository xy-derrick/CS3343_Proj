package testing;

import static org.junit.Assert.*;

import javax.imageio.ImageIO;

import org.junit.Test;

import Java.Code.Command.Base.Command;
import Java.Code.Software.imgProcessor;



public class testcase_command {

	@Test
	public void test_command_02(){
		//确保全部关闭
		imgProcessor ip1 = new imgProcessor();
		imgProcessor ip2 = new imgProcessor();
		class stubCommand extends Command{

			public stubCommand(imgProcessor receiver) {
				super(receiver);
				// TODO Auto-generated constructor stub
			}

			@Override
			public void execute() {
				// TODO Auto-generated method stub
				
			}
		}
		stubCommand c = new stubCommand(ip1);
		c.execute();
		c.setIP(ip2);
		imgProcessor ip3 = c.getIP();
		assertEquals(ip2,ip3);
	}
}