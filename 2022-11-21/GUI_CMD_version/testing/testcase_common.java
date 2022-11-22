package testing;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.junit.Test;

import Java.Code.Command.Base.Command;
import Java.Code.Command.Commands.displayImg;
import Java.Code.Command.Commands.showOperationHint;
import Java.Code.Command.Commands.Common.changeImgProcessor;
import Java.Code.Command.Commands.Common.closeAllImgProcessors;
import Java.Code.Command.Commands.Common.createCopy;
import Java.Code.Command.Commands.Common.readFromBuffer;
import Java.Code.Command.Commands.Common.readImgFromLocal;
import Java.Code.Software.Software;
import Java.Code.Software.imgProcessor;
import Java.Code.Software.ipState;


public class testcase_common {
	String path = System.getProperty("user.dir") + "\\GUI_CMD_version\\testcase\\src\\test.png";
	@Test
	public void test_closeAll_01(){
		//确保全部关闭
		Software s = Software.getInstance();
		s.setImgProcessorList(new ArrayList<>());
		imgProcessor ip1= new imgProcessor();
		imgProcessor ip2= new imgProcessor();
//		closeAllImgProcessors c = new closeAllImgProcessors(null);
//		c.execute();
		assertEquals(s.getImgProcessorList().size(),2);
	}
	@Test
	public void test_change_02(){
		//确保全部关闭
		Software s = Software.getInstance();
		s.setImgProcessorList(new ArrayList<>());
		imgProcessor ip1= new imgProcessor();
		imgProcessor ip2= new imgProcessor();
		changeImgProcessor c = new changeImgProcessor(null, 0);
		c.execute();
		assertEquals(s.getMain_ip(),ip1);
	}
	@Test
	public void test_copy_03() throws IOException{
		Software s = Software.getInstance();
		s.setImgProcessorList(new ArrayList<>());
		imgProcessor ip1= null;
		File imgFile = new File(path);
		System.out.println(path);
		BufferedImage bufImage = ImageIO.read(imgFile);
		createCopy c = new createCopy(ip1, bufImage, "test");
		c.execute();

		assertEquals(s.getMain_ip().getImg().getClass().toString(),"class java.awt.image.BufferedImage");
	}
	@Test
	public void test_display_04() throws IOException{
		Software s = Software.getInstance();
		int cnt = s.getUndoCommand().size();
		imgProcessor ip1= new imgProcessor();
		File imgFile = new File(path);
		BufferedImage bufImage = ImageIO.read(imgFile);
		readImgFromLocal cc = new readImgFromLocal(ip1, path, "t");
		cc.execute();
		s.setCommand(new displayImg(ip1));
		s.execute_cmd();
		assertEquals(s.getUndoCommand().size(),cnt+1);
	}
	@Test
	public void test_readBuffer_05() throws IOException{
		File imgFile = new File(path);
		BufferedImage bufImage = ImageIO.read(imgFile);
		imgProcessor ip1= new imgProcessor();
		readFromBuffer c = new readFromBuffer(ip1,bufImage,"t");
		c.execute();
		assertEquals(Software.getInstance().getMain_ip().getImg().getClass().toString(),"class java.awt.image.BufferedImage");
	}
	
	@Test
	public void test_readLocal_06() throws IOException{
		imgProcessor ip1= new imgProcessor();
		readImgFromLocal c = new readImgFromLocal(ip1,path,"t");
		c.execute();
		assertEquals(Software.getInstance().getMain_ip().getImg().getClass().toString(),"class java.awt.image.BufferedImage");
	}
}
