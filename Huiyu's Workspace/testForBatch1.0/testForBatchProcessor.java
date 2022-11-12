package testForBatch;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

import Java.Code.Batch.batchAdd;
import Java.Code.Batch.batchExecute;
import Java.Code.Batch.batchProcessor;
import Java.Code.Batch.transCommand;
import Java.Code.Command.Commands.Common.closeAllImgProcessors;
import Java.Code.Command.Commands.Common.readImgFromLocal;
import Java.Code.Exception.ArgsInvalidException;
import Java.Code.Software.Software;
import Java.Code.Software.imgProcessor;

class testForBatchProcessor {
	Software mainSoftware = Software.getInstance();
	ArrayList<String> imgs = new ArrayList<>();
	ArrayList<Object> batchImgs = new ArrayList<>();
	ArrayList<Object> batchCommands = new ArrayList<>();

	@Test
	public void testForGetInstance() {
		boolean res = batchProcessor.getInstance() instanceof batchProcessor;
		assertEquals(true, res);
	}
	
	@Test
	public void testForgetImgBuffer() {
		boolean res = batchProcessor.getInstance().getImgBuffer() instanceof ArrayList<Integer>;  ;
		assertEquals(true, res);
	}
	
	
	@Test
	public void testForAddElement1() {
		 batchProcessor bp = batchProcessor.getInstance();
		 
		ArrayList<Object> tailorPara = new ArrayList<>();
		tailorPara.add(10);
		tailorPara.add(1);
		tailorPara.add(5);
		tailorPara.add(10);
		
		bp.addElement(new transCommand("Tailor", tailorPara));
		
		assertEquals(1,bp.getCmdBuffer().size());
		bp.getCmdBuffer().clear();
		batchCommands.clear();
	}
	
	@Test
	public void testForAddElement2() {
		 batchProcessor bp = batchProcessor.getInstance();
		 
		
		
		bp.addElement(1);
		
		assertEquals(1,bp.getImgBuffer().size());
		bp.getImgBuffer().clear();
		batchCommands.clear();
	}

	@Test
	public void testForDeleteElement1() {
		
		batchProcessor bp = batchProcessor.getInstance();
		 
		ArrayList<Object> tailorPara = new ArrayList<>();
		tailorPara.add(10);
		tailorPara.add(1);
		tailorPara.add(5);
		tailorPara.add(10);
		
		bp.addElement(new transCommand("Tailor", tailorPara));
		
		bp.deleteElement(new transCommand("Tailor", tailorPara));
		
		assertEquals(0,bp.getCmdBuffer().size());
	}
	
	@Test
	public void testForDeleteElement2() {
		batchProcessor bp = batchProcessor.getInstance();
		System.out.println("Here it is: "+bp.getImgBuffer().size());
		bp.addElement(0);
		System.out.println("Here it is: "+bp.getImgBuffer().size());

		bp.deleteElement(0);
		System.out.println("Here it is: "+bp.getImgBuffer().size());

		assertEquals(0,bp.getImgBuffer().size());
		
	}
	@Test
	public void testForPreview() {
		batchProcessor bp = batchProcessor.getInstance();
		class StubImgProcessor extends imgProcessor{
			StubImgProcessor(){
			File imgFile = new File(".\\gui_temp\\src\\testForBatch\\imgsFortest\\img4.png");
			super.setPath(".\\gui_temp\\src\\testForBatch\\imgsFortest\\img4.png");
			try{
				super.setImg(ImageIO.read(imgFile));
			} catch (IOException e) {
			}
			}
		}
		StubImgProcessor ip = new StubImgProcessor();
		mainSoftware.getImgProcessorList().add(ip);
		bp.getImgBuffer().add(0);

		ArrayList<Object> tailorPara = new ArrayList<>();
		tailorPara.add(10);
		tailorPara.add(1);
		tailorPara.add(5);
		tailorPara.add(10);
		bp.addElement(new transCommand("Tailor", tailorPara));
		
		bp.addElement(new transCommand("localSave",new ArrayList<>()));
		try {
			int res = bp.preview().getHeight();
			assertEquals(9,res);

		} catch (ArgsInvalidException e) {
		}
		mainSoftware.getImgProcessorList().clear();
		
		bp.getImgBuffer().clear();
		bp.getCmdBuffer().clear();
		mainSoftware.getImgProcessorList().clear();
		
	}
	
	@Test
	public void testForNotifyAlls() {
		ArrayList<transCommand> transList = new ArrayList<>();
		batchProcessor bp = batchProcessor.getInstance();
		imgs.add(".\\gui_temp\\src\\testForBatch\\imgsFortest\\img4.png");
        mainSoftware.setCommand(new readImgFromLocal(null,imgs.get(0),"title"));
        mainSoftware.execute();
        
        String resultPath = "D:\\Users\\huiyu\\eclipse-workspace2\\CS3343_Proj\\testImgFolder\\1img4.png";
        
        bp.getImgBuffer().add(0);
		
        ArrayList<Object> path= new ArrayList<>();
        path.add("D:\\Users\\huiyu\\eclipse-workspace2\\CS3343_Proj\\testImgFolder");
        bp.getCmdBuffer().add(new transCommand("localSave", path));
        
        try {
			bp.notifyAlls();
		} catch (ArgsInvalidException e) {
			// TODO Auto-generated catch blockc
		}
		
		
		boolean result = true;
		File file = new File(resultPath);
        if(!file.exists())
        {
        	result=false;
        }
        assertEquals(true,result);
        mainSoftware.setCommand(new closeAllImgProcessors(null));
        imgs.clear();
        bp.getImgBuffer().clear();
        bp.getCmdBuffer().clear();
	}
	@Test
	public void testForCheckPreviewable1() {
		batchProcessor bp = batchProcessor.getInstance(); 
		assertEquals(false,bp.checkPreviewable());
	 }
	@Test
	public void testForCheckPreviewable2() {
		batchProcessor bp = batchProcessor.getInstance(); 
		assertEquals(false,bp.checkPreviewable());
	 }
	@Test
	public void testForCheckPreviewable3() {
		 batchProcessor bp = batchProcessor.getInstance();
		 ArrayList<Object> tailorPara = new ArrayList<>();
			tailorPara.add(10);
			tailorPara.add(1);
			tailorPara.add(5);
			tailorPara.add(10);
		bp.addElement(new transCommand("Tailor", tailorPara));
		
		ArrayList<Object> path= new ArrayList<>();
        path.add("D:\\Users\\huiyu\\eclipse-workspace2\\CS3343_Proj\\testImgFolder");
        
        
        bp.addElement(new transCommand("imagCompress", path));
		assertEquals(true,bp.checkPreviewable());
		bp.getCmdBuffer().clear();
		
	}
			
	
	@Test
	public void testForReset() {
		
		batchProcessor bp = batchProcessor.getInstance();
		 bp.reset();
		 boolean res = (bp.getCmdBuffer().size()+bp.getImgBuffer().size())==0;
		 
		assertEquals(true, res);
	}
	
	
	
	@Test 
	public void testForGetCmdBuffer() {
		 batchProcessor bp = batchProcessor.getInstance();
		assertEquals(true, bp.getCmdBuffer()instanceof ArrayList<transCommand>);
	}
	@Test
	public void testForImgBufferToStr() {
		 batchProcessor bp = batchProcessor.getInstance();
		 bp.getImgBuffer().add(0);
		 String res = bp.ImgBufferToStr();
		 String ans = "0";
		assertEquals(true, ans.equals(res));
		 bp.getImgBuffer().clear();

		 
	}
	@Test
	public void testForCmdBufferToStr() {
		batchProcessor bp = batchProcessor.getInstance();
		bp.getCmdBuffer().add(new transCommand("AntiColor",new ArrayList<Object>()));
		String ans = " AntiColor ";
		String res = bp.cmdBufferToStr();
		assertEquals(true, ans.equals(res));
		bp.getCmdBuffer().clear();
		 
	}
	
}
