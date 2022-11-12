package testForBatch;




import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Java.Code.Batch.batchAdd;
import Java.Code.Batch.batchProcessor;
import Java.Code.Batch.transCommand;
import Java.Code.Command.Commands.Common.readImgFromLocal;
import Java.Code.Software.Software;
import Java.Code.Software.imgProcessor;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testBatchAdd {
	Software mainSoftware = Software.getInstance();
	ArrayList<String> imgs = new ArrayList<>();
    ArrayList<Object>batchImgs = new ArrayList<>();
    ArrayList<Object>batchCommands = new ArrayList<>();
    batchProcessor bp = batchProcessor.getInstance();
    Integer ans;

	@Test 
	public void TestImgsAdd1() {
		imgs.add("./gui_temp/src/testForBatch/imgsFortest/img1.png");
        mainSoftware.setCommand(new readImgFromLocal(null,imgs.get(0),"title"));
        mainSoftware.execute();
        
        batchImgs.add(0);
        mainSoftware.setCommand(new batchAdd(null,batchImgs));
        mainSoftware.execute();
        
        imgs.clear();
        batchImgs.clear();
        assertEquals(1,bp.getImgBuffer().size());
        
        mainSoftware.undo();
        assertEquals(0,bp.getImgBuffer().size());
	}
	@Test
	public void TestImgsAdd2() {
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
        
        
		
		int imgNum = bp.getImgBuffer().size();
		imgs.clear();
		batchImgs.clear();
        assertEquals(4 ,imgNum);
        
        mainSoftware.undo();

        assertEquals(0,bp.getImgBuffer().size());
	}

	@Test
	public void TestcmdsAdd1() {
		batchCommands.add(new transCommand("Gray", null));
        mainSoftware.setCommand(new batchAdd(null,batchCommands));
        mainSoftware.execute();

        int cmdNum = bp.getCmdBuffer().size();
        assertEquals(cmdNum, 1);

       
        System.out.println("The size is "+bp.getCmdBuffer().size());
        assertEquals(1,bp.getCmdBuffer().size());

        mainSoftware.undo();
        cmdNum = bp.getCmdBuffer().size();
        batchCommands.clear();
        assertEquals(cmdNum, 0);
	        
	}
	
	@Test
	public void TestcmdsAdd2() {
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

	        int cmdNum = bp.getCmdBuffer().size();
	        assertEquals(3,cmdNum);

	        batchCommands.clear();
	        mainSoftware.undo();

	        
	        cmdNum =bp.getCmdBuffer().size();
	        assertEquals(0,cmdNum);
	        
	        
	}
	
	@Test
	public void testException1() {
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
		
		new batchAdd(new StubImgProcessor(),new ArrayList<>()).execute();;
		
		batchImgs.add(4);
		new batchAdd(new StubImgProcessor(),batchImgs).execute();
		batchImgs.clear();
		
		batchImgs.add(1);
		Software.getInstance().getImgProcessorList().add(new StubImgProcessor());
		new batchAdd(new StubImgProcessor(),batchImgs).execute();
		new batchAdd(new StubImgProcessor(),batchImgs).execute();
		batchImgs.clear();

		batchImgs.add(3.7);
		new batchAdd(new StubImgProcessor(),batchImgs);
		batchImgs.clear();

		Software.getInstance().getImgProcessorList().clear();
		bp.getCmdBuffer().clear();
		bp.getImgBuffer().clear();
	}
}
