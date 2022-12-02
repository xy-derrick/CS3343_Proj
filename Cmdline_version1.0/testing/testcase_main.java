package testing;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Java.Main.Main_cmd;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class testcase_main {

	@Test
	public void a01_system_login() {
		String [] args= {"1","name","password","exist"};
        Main_cmd.main(args);
	}
	
	@Test
	public void a02_system_login() {
		System.out.println("-----------------------------");
		String [] args= {"2","name","password","exist"};
        Main_cmd.main(args);
	}
	
	@Test
	public void a04_system_commonCommand() {
		System.out.println("----------------------------------------------");
		String [] args= {"0","common","2","9999","exist"};
        Main_cmd.main(args);
	}
	
	@Test
	public void a05_system_commonCommand() {
		String [] args= {"0","common","5","","exist"};
        Main_cmd.main(args);
	}
	
	@Test
	public void a06_system_commonCommand() {
		String [] args= {"0","common","6","","exist"};
        Main_cmd.main(args);
	}
	
	@Test
	public void a07_system_commonCommand() {
		String [] args= {"0","common","7","","exist"};
        Main_cmd.main(args);
	}
	
	@Test
	public void a08_system_commonCommand() {
		String [] args= {"0","common","9","exist"};
        Main_cmd.main(args);
	}
	
	@Test
	public void a09_system_commonCommand() {
		String [] args= {"0","edit","2","0","exist"};
        Main_cmd.main(args);
	}
	
	@Test
	public void a10_system_commonCommand() {
		String [] args= {"0","edit","3","0","exist"};
        Main_cmd.main(args);
	}
	
	@Test
	public void a11_system_commonCommand() {
		String [] args= {"0","edit","4","0","exist"};
        Main_cmd.main(args);
	}
	
	@Test
	public void a12_system_commonCommand() {
		String [] args= {"0","edit","5","0","exist"};
        Main_cmd.main(args);
	}
	
	@Test
	public void a13_system_commonCommand() {
		String [] args= {"0","edit","6","0","0","0","0","exist"};
        Main_cmd.main(args);
	}
	
	@Test
	public void a14_system_commonCommand() {
		String [] args= {"0","edit","7","0","exist"};
        Main_cmd.main(args);
	}
}
