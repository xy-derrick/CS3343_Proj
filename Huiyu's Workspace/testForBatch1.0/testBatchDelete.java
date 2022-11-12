package testForBatch;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

import Java.Code.Batch.batchAdd;
import Java.Code.Batch.batchDelete;
import Java.Code.Batch.batchProcessor;
import Java.Code.Batch.transCommand;
import Java.Code.Command.Commands.Common.readImgFromLocal;
import Java.Code.Software.Software;
import Java.Code.Software.imgProcessor;

class testBatchDelete {
	Software mainSoftware = Software.getInstance();
	ArrayList<String> imgs = new ArrayList<>();
    ArrayList<Object>batchImgs = new ArrayList<>();
    ArrayList<Object>batchCommands = new ArrayList<>();
    batchProcessor bp = batchProcessor.getInstance();
    
	@Test
	public void testImgsDelete1() {
		imgs.add("./gui_temp/src/testForBatch/imgsFortest/img1.png");
        mainSoftware.setCommand(new readImgFromLocal(null,imgs.get(0),"title"));
        mainSoftware.execute();
        
        batchImgs.add(0);
        mainSoftware.setCommand(new batchAdd(null,batchImgs));
        mainSoftware.execute();
        imgs.clear();
        
        mainSoftware.setCommand(new batchDelete(null,batchImgs));
        mainSoftware.execute();

        assertEquals(0,bp.getImgBuffer().size());
        
        
        batchImgs.clear();
        mainSoftware.undo();
        assertEquals(1,bp.getImgBuffer().size());
     
        mainSoftware.undo();

	}
	@Test
	public void testImgsDelete2() {
		imgs.add("./gui_temp/src/testForBatch/imgsFortest/img2.png");
		imgs.add("./gui_temp/src/testForBatch/imgsFortest/img3.png");
		imgs.add("./gui_temp/src/testForBatch/imgsFortest/img4.png");
		imgs.add("./gui_temp/src/testForBatch/imgsFortest/img5.png");
		for(String imgPath: imgs){
            mainSoftware.setCommand(new readImgFromLocal(null,imgPath,"title"));
            mainSoftware.execute();
        }
		batchImgs.add(1);
		batchImgs.add(2);
		batchImgs.add(3);
		batchImgs.add(4);
		
		mainSoftware.setCommand(new batchAdd(null,batchImgs));
        mainSoftware.execute();
        
        mainSoftware.setCommand(new batchDelete(null,batchImgs));
        mainSoftware.execute();
		
        assertEquals(0,bp.getImgBuffer().size());

        mainSoftware.undo();
        assertEquals(4,bp.getImgBuffer().size());

        mainSoftware.redo();
        batchImgs.clear();
		imgs.clear();
	}
	
	@Test
	public void testCmdsDelete1() {
		batchCommands.add(new transCommand("Gray", null));
        mainSoftware.setCommand(new batchAdd(null,batchCommands));
        mainSoftware.execute();

        mainSoftware.setCommand(new batchDelete(null,batchCommands));
        mainSoftware.execute();
        assertEquals(0,bp.getImgBuffer().size());
        
        mainSoftware.undo();
        System.out.println("The answer is"+bp.getImgBuffer().size());
        
        assertEquals(1,bp.getCmdBuffer().size());
        
        mainSoftware.undo();
        batchCommands.clear();
	}
	
	@Test
	public void TestcmdsDelete2() {
		 batchCommands.add(new transCommand("Gray", null));
	     batchCommands.add(new transCommand("FlipVertical", null));

	     ArrayList<Object> tailorPara= new ArrayList<>();
	        tailorPara.add(20);
	        tailorPara.add(30);
	        tailorPara.add(60);
	        tailorPara.add(10);
	        batchCommands.add(new transCommand("Tailor", tailorPara));

	        mainSoftware.setCommand(new batchAdd(null,batchCommands));
	        mainSoftware.execute();

	        mainSoftware.setCommand(new batchDelete(null,batchCommands));
	        mainSoftware.execute();
	        assertEquals(0,bp.getImgBuffer().size());
	        
	        mainSoftware.undo();
	        System.out.println("The answer is"+bp.getImgBuffer().size());
	        assertEquals(3,bp.getCmdBuffer().size());

	        batchCommands.clear();
	        mainSoftware.undo();        
	}

	@Test
	public void TestcmdsDelete3() {
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
		new batchDelete(new StubImgProcessor(),new ArrayList<>()).execute();
		bp.getImgBuffer().add(0);
		batchImgs.add(4);
		new batchDelete(new StubImgProcessor(),batchImgs).execute();
		
		 ArrayList<Object> tailorPara= new ArrayList<>();
	        tailorPara.add(20);
	        tailorPara.add(30);
	        tailorPara.add(60);
	        tailorPara.add(10);
	        batchCommands.add(new transCommand("Tailor", tailorPara));
		new batchDelete(new StubImgProcessor(),batchCommands).execute();
		batchCommands.clear();
		batchImgs.clear();
		batchImgs.add(3.5);
		new batchDelete(new StubImgProcessor(),batchImgs).execute();

	}
	

}
