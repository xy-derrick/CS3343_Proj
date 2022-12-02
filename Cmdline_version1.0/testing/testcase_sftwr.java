package testing;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.junit.Test;

import Java.Code.Command.Base.Command;
import Java.Code.Command.Commands.showOperationHint;
import Java.Code.Command.Commands.Common.closeAllImgProcessors;
import Java.Code.Software.Software;
import Java.Code.Software.imgProcessor;
import Java.Code.Software.ipState;


public class testcase_sftwr {
	@Test
	public void test_software_01(){
		
		
		
		Software s = Software.getInstance();
		Command c = new showOperationHint(null);
		
		int c1 = s.getUndoCommand().size();
		int c2 = s.getRedoCommand().size();
		s.setCommand(c);
		s.execute();
		s.undo();
		s.redo();
		int r = (c2-1)<0?0:c2-1;
		assertEquals(s.getUndoCommand().size(),c1);
		assertEquals(s.getRedoCommand().size(),r);
	}
	@Test
	public void test_software_05(){
		Software s = Software.getInstance();
		closeAllImgProcessors c = new closeAllImgProcessors(null);
		
		int c1 = s.getUndoCommand().size();
		int c2 = s.getRedoCommand().size();
		
		s.setCommand(c);
		s.execute();
		c = new closeAllImgProcessors(null);
		s.setCommand(c);
		s.execute();
		s.undo();
		s.undo();
		s.redo();
		s.redo();
		s.undo();
		assertEquals(s.getUndoCommand().size(),c1);
		assertEquals(s.getRedoCommand().size(),c2+1);
	}
	@Test
	public void test_software_02(){
		
		Software s = Software.getInstance();
		imgProcessor ip = new imgProcessor();
		s.addImgProcessor(ip);
		s.setImgProcessorList(null);
		assertEquals(s.getImgProcessorList(),null);
	}
	@Test
	public void test_software_03(){
		
		Software s = Software.getInstance();
		imgProcessor ip = new imgProcessor(1);
		s.setMain_ip(ip);
		assertEquals(s.getMain_ip(),ip);
	}
	@Test
	public void test_software_04(){
		
		Software s = Software.getInstance();
		ipState is = new ipState(new ArrayList<>(),null);
		s.setState(is);
		assertEquals(s.getState().getMain_ip(),is.getMain_ip());
	}
	
}