package testForBatch;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

import Java.Code.Batch.batchAdd;
import Java.Code.Batch.batchPreview;
import Java.Code.Batch.batchProcessor;
import Java.Code.Batch.transCommand;
import Java.Code.Command.Commands.Common.readImgFromLocal;
import Java.Code.Software.Software;
import Java.Code.Software.imgProcessor;

class testForBatchPreview {
	Software mainSoftware = Software.getInstance();
	ArrayList<String> imgs = new ArrayList<>();
	ArrayList<Object> batchImgs = new ArrayList<>();
	ArrayList<Object> batchCommands = new ArrayList<>();
	batchProcessor bp = batchProcessor.getInstance();
	
	@Test
	public void testForCheckPreviewable() {
		
		boolean res = batchPreview.checkPreviewable();
		assertEquals(false,res);
		
	}
	
	@Test
	public void testForExecute() {
		ArrayList<Object> tailorPara = new ArrayList<>();
		tailorPara.add(10);
		tailorPara.add(1);
		tailorPara.add(5);
		tailorPara.add(10);
		batchCommands.add(new transCommand("Tailor", tailorPara));
		
		mainSoftware.setCommand(new batchAdd(null, batchCommands));
		mainSoftware.execute();
		
		imgs.add(".\\gui_temp\\src\\testForBatch\\imgsFortest\\img4.png");
        mainSoftware.setCommand(new readImgFromLocal(null,imgs.get(0),"title"));
        mainSoftware.execute();
        
        batchImgs.add(0);
        mainSoftware.setCommand(new batchAdd(null, batchImgs));
		mainSoftware.execute();
		
		BufferedImage res =  batchPreview.getInstance().execute();
		assertEquals(9,res.getHeight());
		bp.reset();
		
		
	}
	@Test
	public void testForExecute2() {
		ArrayList<Object> tailorPara = new ArrayList<>();
		tailorPara.add(1000);
		tailorPara.add(-1000);
		tailorPara.add(5);
		tailorPara.add(10);
		batchCommands.add(new transCommand("Tailor", tailorPara));
		
		mainSoftware.setCommand(new batchAdd(null, batchCommands));
		mainSoftware.execute();
		
		imgs.add(".\\gui_temp\\src\\testForBatch\\imgsFortest\\img4.png");
        mainSoftware.setCommand(new readImgFromLocal(null,imgs.get(0),"title"));
        mainSoftware.execute();
        
        batchImgs.add(0);
        mainSoftware.setCommand(new batchAdd(null, batchImgs));
		mainSoftware.execute();
		
		BufferedImage res =  batchPreview.getInstance().execute();
		bp.reset();
		
		
	}
}
