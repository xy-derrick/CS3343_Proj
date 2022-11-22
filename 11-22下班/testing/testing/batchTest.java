package testing;

import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import Java.Code.Command.Batch.batchPreview;
import Java.Code.Command.Batch.batchProcessor;
import Java.Main.Main_cmd;

@FixMethodOrder(value = MethodSorters.JVM)

class batchTest {
	private String absolutePath = System.getProperty("user.dir");
	String test1Path = absolutePath+"\\testcase\\test\\test.png";
	String test2Path = absolutePath+"\\testcase\\test\\test1.jpg";
	String test3Path = absolutePath+"\\testcase\\test\\testlocal.png";
	batchProcessor bp = batchProcessor.getInstance();

	//----
	private static boolean diffIntSecondArr(int[][] paramInt,int[][] diffInt){
        List<Integer> ResultList = new ArrayList<>();
        List<Integer> DiffList = new ArrayList<>();
        for(int[] npdArr : paramInt){
            for(Integer children : npdArr){
                ResultList.add(children);
            }
        }
        for(int[] dpdArr : diffInt){
            for(Integer children : dpdArr){
                DiffList.add(children);
            }
        }
 
        for(int i = 0;i<ResultList.size();i++){
            if(!ResultList.get(i).equals(DiffList.get(i))){
                return false;
            }
        }
        return true;
    }
	private static int[][]  getRGB(BufferedImage image) {
		 int height = image.getHeight();
	     int width = image.getWidth();
	     int[][] result = null;
	     result = new int[width][height];
	     for (int i = 0; i < width; i++) {
	        for (int j = 0; j < height; j++) {
	        	result[i][j] = image.getRGB(i, j) & 0xFFFFFF;
	        }
	     }
	     return result;
	}
	//--------------------------------
	private boolean testSystem(String resultPath)
	{
		File file = new File(resultPath);
	    if(!file.exists())
	    {
	    	return false;
	    }
	    
	    return true;
	}
	 @Test
	 void test01(){
		 String[] args = {"0","common","1",test1Path,"common","1",test2Path,
				 "batch","1","img>>0,1","common","3","common","4",
				 "batch","2","img>>0","common","3","exist"};
		 System.out.println(args.length);
		 Main_cmd.main(args);
		 assertEquals(bp.getImgBuffer().size(),2);

	}
 
	 @Test
	 void test02(){
		 String[] args = {"0","batch","1","cmd>>edit 4, filter 5 4, edit 6 2 4 3 7, export 1 "+absolutePath+"\\testcase\\batchRes\\",
				 "common","3","common","4",
				 "batch","2","cmd>>filter 5 4","common","3","exist"};
		 Main_cmd.main(args);
		 assertEquals(bp.getCmdBuffer().size(),4);

	 }
	
	 @Test
	 void test03() throws IOException {
		 String savePath1=absolutePath+"\\testcase\\batchRes\\batch_1.jpg";
		 String savePath2 = absolutePath+"\\testcase\\batchRes\\common_2.jpg";
		 String samplePath=absolutePath+"\\testcase\\batchRes\\Sample1.jpg";
			
		 String[] args = {"0","batch","4","","exist"};
		 batchPreview bp = batchPreview.getInstance();
		 assertEquals(true,bp.checkPreviewable());
		 
		 BufferedImage previewImg = bp.execute();
		 System.out.println(samplePath);
		 BufferedImage sampleImage = ImageIO.read(new File(samplePath));
		 boolean result = diffIntSecondArr(getRGB(sampleImage),getRGB(previewImg));	
		 
		 Main_cmd.main(args);
		 result=testSystem(savePath1)&&testSystem(savePath2) ;
		
		 assertEquals(false,batchPreview.getInstance().checkPreviewable());
	     assertEquals(true,result);
	     result = diffIntSecondArr(getRGB(sampleImage),getRGB(ImageIO.read(new File(savePath1))));
	     assertEquals(true,result);

	 }
	 
	 

}