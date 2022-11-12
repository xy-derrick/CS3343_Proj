package testForBatch;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

import Java.Code.Batch.batchAdd;
import Java.Code.Batch.batchExecute;
import Java.Code.Batch.batchProcessor;
import Java.Code.Batch.notifyIP;
import Java.Code.Batch.transCommand;
import Java.Code.Command.Commands.Common.closeAllImgProcessors;
import Java.Code.Command.Commands.Common.readImgFromLocal;
import Java.Code.Exception.ArgsInvalidException;
import Java.Code.Software.Software;
import Java.Code.Software.imgProcessor;

public class TestForNotifyIP {
	Software mainSoftware = Software.getInstance();
	ArrayList<String> imgs = new ArrayList<>();
	ArrayList<Object> batchImgs = new ArrayList<>();
	ArrayList<Object> batchCommands = new ArrayList<>();


	
	@Test
	public void testForUpdate() {
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
		


		ArrayList<Object> tailorPara = new ArrayList<>();
		tailorPara.add(10);
		tailorPara.add(1);
		tailorPara.add(5);
		tailorPara.add(10);
		batchCommands.add(new transCommand("Tailor", tailorPara));

		ArrayList<Object> path = new ArrayList<>();
		path.add("D:\\Users\\huiyu\\eclipse-workspace2\\CS3343_Proj\\testImgFolder");
		batchCommands.add(new transCommand("localSave", path));
		String resultPath = "D:\\Users\\huiyu\\eclipse-workspace2\\CS3343_Proj\\testImgFolder\\1img4.png";

		mainSoftware.setCommand(new batchAdd(null, batchCommands));
		mainSoftware.execute();

		try {
			notifyIP ip = new notifyIP(new StubImgProcessor());
			ip.update();
			assertEquals(9,ip.getImg().getHeight());
			boolean result = true;
			File file = new File(resultPath);
			if (!file.exists()) {
				result = false;
			}
			assertEquals(true, result);
		} catch (ArgsInvalidException e) {
			// TODO Auto-generated catch block
		};

		batchImgs.clear();
		batchCommands.clear();
		mainSoftware.setCommand(new closeAllImgProcessors(null));
	}

	@Test
	public void testForPreviewDisplay() {
		ArrayList<transCommand> batchTransCommand  = new ArrayList<>();
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
		


		ArrayList<Object> tailorPara = new ArrayList<>();
		tailorPara.add(10);
		tailorPara.add(1);
		tailorPara.add(5);
		tailorPara.add(10);
		batchTransCommand.add(new transCommand("Tailor", tailorPara));

		

		

		try {
			notifyIP ip = new notifyIP(new StubImgProcessor());
			ip.previewDisplay(batchTransCommand);
			assertEquals(9,ip.getImg().getHeight());
			
		} catch (ArgsInvalidException e) {
			// TODO Auto-generated catch block

		};

		batchImgs.clear();
		batchCommands.clear();
		mainSoftware.setCommand(new closeAllImgProcessors(null));

	}


}
