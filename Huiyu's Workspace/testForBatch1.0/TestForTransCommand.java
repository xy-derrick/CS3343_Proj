package testForBatch;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

import Java.Code.Batch.notifyIP;
import Java.Code.Batch.transCommand;
import Java.Code.Exception.ArgsInvalidException;
import Java.Code.Software.imgProcessor;

class TestForTransCommand {

	@Test
	public void testForCheckPreviewable1() {
		ArrayList<Object> argList = new ArrayList<>();
		boolean result = transCommand.checkPreviewable(new transCommand("Combine",new ArrayList<Object>()));
		assertEquals(true,result);
	}
	@Test
	public void testForCheckPreviewable2() {
		ArrayList<Object> argList = new ArrayList<>();
		boolean result = transCommand.checkPreviewable(new transCommand("localSave",new ArrayList<Object>()));
		assertEquals(false,result);
	}
	@Test
	public void testForEquals() {
		transCommand tc1 = new transCommand("AntiColor",null);
		ArrayList<Object> tailorPara= new ArrayList<>();
        tailorPara.add(20);
        tailorPara.add(30);
        tailorPara.add(60);
        tailorPara.add(10);
		transCommand tc2 = new transCommand("Tailor", tailorPara);
		
		ArrayList<Object> tailorPara1= new ArrayList<>();
        tailorPara1.add(20);
        tailorPara1.add(30);
        tailorPara1.add(60);
        tailorPara1.add(20);
		transCommand tc3 = new transCommand("Tailor", tailorPara1);
		
		ArrayList<Object> tailorPara2= new ArrayList<>();
        tailorPara2.add(20);
        tailorPara2.add(30);
        tailorPara2.add(60);
        tailorPara2.add(20);
        tailorPara2.add(20);
		transCommand tc4 = new transCommand("Tailor", tailorPara2);
		
		assertEquals(false, tc1.equals(new ArrayList()));
		assertEquals(false, tc1.equals(new transCommand("Zoom", null)));
		assertEquals(true, tc1.equals(new transCommand("AntiColor",null)));
		assertEquals(false, tc1.equals(new transCommand("AntiColor",new ArrayList())));
		assertEquals(false, new transCommand("AntiColor",new ArrayList()).equals(tc1));
		assertEquals(false, tc2.equals(tc4));
		assertEquals(true, tc2.equals(tc2));
		assertEquals(false,tc2.equals(tc3));	
	}
	@Test
	public void testForToString() {
		transCommand tc1 = new transCommand("AntiColor",new ArrayList<>());
		
		ArrayList<Object> tailorPara= new ArrayList<>();
        tailorPara.add(20);
        tailorPara.add(30);
        tailorPara.add(60);
        tailorPara.add(10);
		transCommand tc2 = new transCommand("Tailor", tailorPara);
		
		System.out.println(tc1.toString());
		String testRes1 = " AntiColor ";
		String testRes2 = " Tailor 20 30 60 10 ";

		assertEquals(testRes1,tc1.toString());
		assertEquals(testRes2,tc2.toString());

		
	}
	@Test
	public void testForTransfer() {
		class StubImgProcessor extends imgProcessor{
			StubImgProcessor(){
			File imgFile = new File("./gui_temp/src/testForBatch/imgsFortest/img1.png");
			super.setPath("/gui_temp/src/testForBatch/imgsFortest/img1.png");
			try{
				super.setImg(ImageIO.read(imgFile));
			} catch (IOException e) {
			}
			}
		}
			
		
		class notifyIPStub extends notifyIP {

			public notifyIPStub(imgProcessor ip) {
				super(ip);
			}
			
		}
		
		ArrayList<Object> path = new ArrayList<>();
		path.add("Path");
		transCommand tc1 = new transCommand("localSave",path);
		try {

			String className = tc1.transfer(new notifyIPStub(new StubImgProcessor())).getClass().toString();
			assertEquals("class Java.Code.Command.Export.localSave",className);
		} catch (ArgsInvalidException e) {
			// TODO Auto-generated catch block
		}
}
	
	
}
