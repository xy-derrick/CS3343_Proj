package testing;

import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Java.Code.Software.imgProcessor;
import Java.Main.Main_cmd;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class editTest {
	private String absolutePath = System.getProperty("user.dir");

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
	//System test

	
	@Test
	public void d01_test_edit1_system_1() throws IOException {
		BufferedImage image = null;
		BufferedImage image_want = null;
		String imagPathresult= absolutePath+"\\testcase\\test\\design.png";
		image_want = ImageIO.read(new File(imagPathresult));
		
		String imagPath=absolutePath+"\\testcase\\test\\design.png";
		String savePath=absolutePath+"\\testcase\\editResult";
		String resultPath= absolutePath+"\\testcase\\editResult\\edit_3.png";
		String [] args= {"0","common","1",imagPath,"edit","1","common","3","edit","2","common","3",
				"edit","3","common","3","edit","4","common","3","edit","5","common","3","edit","6","20","10","30","40",
				"common","3","edit","7","0.5","common","3",
				"export","2",savePath,"exist"};
        Main_cmd.main(args);
        image = ImageIO.read(new File(resultPath));
		imgProcessor imgprocesser1 = new imgProcessor();  
	    imgprocesser1.setImg(image);	   
        boolean result = diffIntSecondArr(getRGB(image),getRGB(image_want));

        assertEquals(true,result);
	}
	
	@Test
	public void d02_test_filter1_system_1() throws IOException {
		BufferedImage image = null;
		BufferedImage image_want = null;
		String imagPathresult= absolutePath+"\\testcase\\test\\design.png";
		image_want = ImageIO.read(new File(imagPathresult));
		
		String imagPath=absolutePath+"\\testcase\\test\\design.png";
		String savePath=absolutePath+"\\testcase\\editResult";
		String resultPath= absolutePath+"\\testcase\\editResult\\edit_3.png";
		String [] args= {"0","common","1",imagPath,"filter","1","common","3","filter","2","10","common","3",
				"filter","3","10","common","3","filter","5","20","common","3","filter","6","20",
				"common","3","filter","7","20","2","common","3",
				"export","2",savePath,"exist"};
        Main_cmd.main(args);
		image = ImageIO.read(new File(resultPath));
		imgProcessor imgprocesser1 = new imgProcessor();  
	    imgprocesser1.setImg(image);	   
        boolean result = diffIntSecondArr(getRGB(image),getRGB(image_want));

        assertEquals(true,result);
	}
	@Test
	public void d03_test_filter2_system_1() throws IOException {
		BufferedImage image = null;
		BufferedImage image_want = null;
		String imagPathresult= absolutePath+"\\testcase\\test\\design.png";
		image_want = ImageIO.read(new File(imagPathresult));
		
		String imagPath=absolutePath+"\\testcase\\test\\design.png";
		String imagPath2=absolutePath+"\\testcase\\test\\design_anti.png";
		String imagPath3=absolutePath+"\\testcase\\test\\design_180.png";
		String savePath=absolutePath+"\\testcase\\editResult";
		String resultPath= absolutePath+"\\testcase\\editResult\\edit_3.png";
		String [] args= {"0","common","1",imagPath,"common","1",imagPath2,"common","1",imagPath3,"filter","4","1","10","common","3",
				"filter","7","1","10","common","3","filter","7","1","1","common","3",
				"export","2",savePath,"exist"};
        Main_cmd.main(args);
		image = ImageIO.read(new File(resultPath));
		imgProcessor imgprocesser1 = new imgProcessor();  
	    imgprocesser1.setImg(image);	   
        boolean result = diffIntSecondArr(getRGB(image),getRGB(image_want));

        assertEquals(true,result);
	}
	
	
}

