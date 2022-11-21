package testing;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.junit.Test;

import Java.Code.Command.Base.Command;
import Java.Code.Command.Commands.showOperationHint;
import Java.Code.Command.Commands.Common.changeImgProcessor;
import Java.Code.Command.Commands.Common.closeAllImgProcessors;
import Java.Code.Command.Commands.Common.closeImgProcessor;
import Java.Code.Command.Commands.Common.readImgFromLocal;
import Java.Code.Command.EditDecorator.Rotate180Degrees;
import Java.Code.Command.EditDecorator.Tailoring_bat;
import Java.Code.Command.EditDecorator.Zoom;
import Java.Code.Software.ArgsReader;
import Java.Code.Software.Software;
import Java.Code.Software.imgProcessor;
import Java.Code.Software.ipState;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;

public class testcase_argrd {
	
	String path = System.getProperty("user.dir") + "\\testcase\\src\\test.png";
	@Test
	public void test_software_01(){
		
		ArgsReader ar1 = ArgsReader.getInstance();
		ArgsReader ar2 = ArgsReader.getInstance();
		ar2.hint(null);
		ar2.hint("");
		assertEquals(ar1,ar2);
	}
	@Test
	public void test_software_02(){
		
		ArgsReader ar1 = ArgsReader.getInstance();
		Class commond_name = closeImgProcessor.class;
        ArrayList<Object>args_object = ArgsReader.getInstance().read(commond_name, false, 0, null);
        assertEquals(args_object.size(),0);
	}
	@Test
	public void test_software_03(){
		
		ArgsReader ar1 = ArgsReader.getInstance();
		Class commond_name = changeImgProcessor.class;
		String input = "0";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ArrayList<Object>args_object = ArgsReader.getInstance().read(commond_name, false, 0, null);
        assertEquals(args_object.get(0),0);
	}
	
	@Test
	public void test_software_04(){
		
		ArgsReader ar1 = ArgsReader.getInstance();
		Class commond_name = Zoom.class;
		String input = "1.0";
	    System.setIn(new ByteArrayInputStream(input.getBytes()));
	    ArrayList<Object>args_object = ArgsReader.getInstance().read(commond_name, false, 0, null);
	    System.out.println("-----------------------");
	    System.out.println(args_object);
	    assertEquals(args_object.get(0),1.0);
	}
	
	@Test
	public void test_software_05(){
		
		ArgsReader ar1 = ArgsReader.getInstance();
		Class commond_name = readImgFromLocal.class;
		String input = path+" "+path;
	    System.setIn(new ByteArrayInputStream(input.getBytes()));
	    ArrayList<Object>args_object = ArgsReader.getInstance().read(commond_name, false, 0, null);
	    assertEquals(args_object.get(0),path);
	}
	
	@Test
	public void test_software_06(){
		
		class stubCommand extends Command{
			public stubCommand(imgProcessor receiver, Boolean b) {
				super(receiver);
			}

			@Override
			public void execute() {
				// TODO Auto-generated method stub
				
			}
		}

		ArgsReader ar1 = ArgsReader.getInstance();
		Class commond_name = stubCommand.class;
		stubCommand sc = new stubCommand(null,true);
	    String input = "true";
	    System.setIn(new ByteArrayInputStream(input.getBytes()));
	    ArrayList<Object>args_object = ArgsReader.getInstance().read(commond_name, false, 0, null);
	    System.out.println("-----------------------");
	    System.out.println(args_object);
	    assertEquals(args_object.get(0),true);
	}
	
	@Test
	public void test_software_07(){
		ArgsReader ar1 = ArgsReader.getInstance();
		Class commond_name = Tailoring_bat.class;
		String input = "1 2 3 4";
	    System.setIn(new ByteArrayInputStream(input.getBytes()));
	    ArrayList<Object>args_object = ArgsReader.getInstance().read(commond_name, false, 0, null);
	    assertEquals(args_object.get(0),1);
	    assertEquals(args_object.get(1),2);
	    assertEquals(args_object.get(2),3);
	    assertEquals(args_object.get(3),4);
	}
	
	@Test
	public void test_software_08(){
		
		ArgsReader ar1 = ArgsReader.getInstance();
		Class commond_name = Rotate180Degrees.class;
	    ArrayList<Object>args_object = ArgsReader.getInstance().read(commond_name, false, 0, null);
	    assertEquals(args_object.size(),0);
	}
	
}

