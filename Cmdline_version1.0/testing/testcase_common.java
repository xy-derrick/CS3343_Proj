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
import Java.Code.Command.Commands.Common.displayImg;
import Java.Code.Command.Commands.showOperationHint;
import Java.Code.Command.Commands.Common.changeImgProcessor;
import Java.Code.Command.Commands.Common.closeAllImgProcessors;
import Java.Code.Command.Commands.Common.closeImgProcessor;
import Java.Code.Command.Commands.Common.createCopy;
import Java.Code.Command.Commands.Common.readFromBuffer;
import Java.Code.Command.Commands.Common.readImgFromLocal;
import Java.Code.Software.Software;
import Java.Code.Software.imgProcessor;
import Java.Code.Software.ipState;


public class testcase_common {
	String path = System.getProperty("user.dir") + "\\testcase\\src\\test.png";
	@Test
	public void test_change_02(){
		//确保全部关闭
		Software s = Software.getInstance();
		s.setImgProcessorList(new ArrayList<>());
		imgProcessor ip1= new imgProcessor();
		imgProcessor ip2= new imgProcessor();
		changeImgProcessor c = new changeImgProcessor(null, 1);
		c.execute();
		c = new changeImgProcessor(null, 0);
		c.execute();
		assertEquals(s.getMain_ip(),ip1);
		c.undo();
		assertEquals(s.getMain_ip(),ip2);
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
		
		c.undo();
		assertEquals(ip1,null);
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
		
		s.undo();
		assertEquals(s.getUndoCommand().size(),cnt);
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
	
		c.undo();
		assertEquals(ip1.getImg(),null);
		c.execute();
		assertEquals(Software.getInstance().getMain_ip().getImg().getClass().toString(),"class java.awt.image.BufferedImage");
	}
	
	@Test
	public void test_readLocal_06() throws IOException{
		imgProcessor ip1= new imgProcessor();
		imgProcessor ip_o = Software.getInstance().getMain_ip();
		readImgFromLocal c = new readImgFromLocal(ip1,path,"t");
		c.execute();
		assertEquals(Software.getInstance().getMain_ip().getImg().getClass().toString(),"class java.awt.image.BufferedImage");
	
		c.undo();
		assertEquals(Software.getInstance().getMain_ip(),ip_o);
		c.execute();
		assertEquals(Software.getInstance().getMain_ip().getImg().getClass().toString(),"class java.awt.image.BufferedImage");
	}
	
	@Test
	public void test_close_07() throws IOException{
		imgProcessor ip_0 = Software.getInstance().getMain_ip();
		imgProcessor ip1= new imgProcessor();
		closeImgProcessor c = new closeImgProcessor(ip1);
		c.execute();
		assertEquals(Software.getInstance().getMain_ip(),null);
		
		c.undo();
		assertEquals(Software.getInstance().getMain_ip(),ip_0);
		c.execute();
		assertEquals(Software.getInstance().getMain_ip(),null);
	}
	
	@Test
	public void test_closeAll_01() throws IOException{
		imgProcessor ip1= new imgProcessor();
		imgProcessor ip2= new imgProcessor();
		imgProcessor ip3= new imgProcessor();
		closeAllImgProcessors c = new closeAllImgProcessors(null);
		c.execute();
		assertEquals(Software.getInstance().getImgProcessorList().size(),0);
		ip1= new imgProcessor();
		ip2= new imgProcessor();
		ip3= new imgProcessor();
		c = new closeAllImgProcessors(null);
		c.execute();
		c.undo();
		assertEquals(Software.getInstance().getImgProcessorList().size(),3);
		c.execute();
		assertEquals(Software.getInstance().getImgProcessorList().size(),0);
	}
}