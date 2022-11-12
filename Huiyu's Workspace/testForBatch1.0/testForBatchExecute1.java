package testForBatch;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import Java.Code.Batch.batchAdd;
import Java.Code.Batch.batchExecute;
import Java.Code.Batch.transCommand;
import Java.Code.Command.Commands.Common.readImgFromLocal;
import Java.Code.Software.Software;

public class testForBatchExecute1 {
	Software mainSoftware = Software.getInstance();
	ArrayList<String> imgs = new ArrayList<>();
    ArrayList<Object>batchImgs = new ArrayList<>();
    ArrayList<Object>batchCommands = new ArrayList<>();

    
	
	@Test
	public void testBatchExecute(){
		imgs.add(".\\gui_temp\\src\\testForBatch\\imgsFortest\\img4.png");
        mainSoftware.setCommand(new readImgFromLocal(null,imgs.get(0),"title"));
        mainSoftware.execute();
        String resultPath = "D:\\Users\\huiyu\\eclipse-workspace2\\CS3343_Proj\\testImgFolder\\img41.zip";
        
        batchImgs.add(0);
        mainSoftware.setCommand(new batchAdd(null,batchImgs));
        mainSoftware.execute();
		
        ArrayList<Object> path= new ArrayList<>();
        path.add("D:\\Users\\huiyu\\eclipse-workspace2\\CS3343_Proj\\testImgFolder");
        
        
        batchCommands.add(new transCommand("imagCompress", path));
        mainSoftware.setCommand(new batchAdd(null,batchCommands));
        mainSoftware.execute();
        
		mainSoftware.setCommand(new batchExecute(null));
		mainSoftware.execute();
		
		boolean result = true;
		File file = new File(resultPath);
        if(!file.exists())
        {
        	result=false;
        }
        
        assertEquals(true, result);
        
        new batchExecute(null).execute();
        new batchExecute(null).undo();

	}
	
}