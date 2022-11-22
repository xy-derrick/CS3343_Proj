package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

import Java.Code.Command.Base.Command;
import Java.Code.Command.Commands.EditCommand;
import Java.Code.Command.EditDecorator.Anticolor;
import Java.Code.Command.EditDecorator.CombineFilter;
import Java.Code.Command.EditDecorator.CreateBorder;
import Java.Code.Command.EditDecorator.EditDecorator;
import Java.Code.Command.EditDecorator.FlipHorizontal;
import Java.Code.Command.EditDecorator.FlipVertical;
import Java.Code.Command.EditDecorator.HighContrastFilter;
import Java.Code.Command.EditDecorator.MosaicFilter;
import Java.Code.Command.EditDecorator.PaintFilter;
import Java.Code.Command.EditDecorator.Rotate180Degrees;
import Java.Code.Command.EditDecorator.Rotate90DegreesClockwise;
import Java.Code.Command.EditDecorator.Rotate90DegreesCounterclockwise;
import Java.Code.Command.EditDecorator.Tailoring;
import Java.Code.Command.EditDecorator.Tailoring_bat;
import Java.Code.Command.EditDecorator.VintageFilter;
import Java.Code.Command.EditDecorator.Zoom;
import Java.Code.Exception.ArgsInvalidException;
import Java.Code.Software.Software;
import Java.Code.Software.imgProcessor;

public class testForEditDecorator {
	//height 376
		//width  432
		private static String absolutePath = System.getProperty("user.dir");

		
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
	 
	        if(ResultList.size()!=DiffList.size()){
	            return false;
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
		
		private int testZoom(imgProcessor imgprocesser1,EditCommand editCommand1,boolean whetherUndo,float input)
		{
			Zoom zoom=null;
			try {
				zoom = new Zoom(editCommand1,input);
				zoom.execute();      
				if(whetherUndo)
				{
					zoom.undo();
				}
		        
			} catch (ArgsInvalidException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				assertTrue(e instanceof ArgsInvalidException);
				return 0;
			}
			BufferedImage image2 =imgprocesser1.getImg();
	        return image2.getHeight();
		}

		@Test
		void test_zoom1() throws IOException, ArgsInvalidException  {
			BufferedImage image = null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
	        imgProcessor imgprocesser1 = new imgProcessor();
	        EditCommand editCommand1 = new EditCommand(imgprocesser1);      
	        imgprocesser1.setImg(image);
	        int result = testZoom(imgprocesser1,editCommand1,false,0.5f);
	        assertEquals(188,result);
		}
		@Test
		void test_zoom2() throws IOException, ArgsInvalidException  {
			BufferedImage image = null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
	        imgProcessor imgprocesser1 = new imgProcessor();
	        EditCommand editCommand1 = new EditCommand(imgprocesser1);      
	        imgprocesser1.setImg(image);
	        int result = testZoom(imgprocesser1,editCommand1,true,0.5f);
	        assertEquals(376,result);
	        
	        
		}
		@Test
		void test_zoom3() throws IOException, ArgsInvalidException {
			BufferedImage image = null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
	        imgProcessor imgprocesser1 = new imgProcessor();
	        EditCommand editCommand1 = new EditCommand(imgprocesser1);      
	        imgprocesser1.setImg(image);
	        int result = testZoom(imgprocesser1,editCommand1,false,14f);
	        assertEquals(0,result);
		}
		
		private int testTai(imgProcessor imgprocesser1,EditCommand editCommand1,ArrayList<Object> myList,boolean whetherUndo)
		{
			Tailoring tailoring;
			try {
				tailoring = new Tailoring(editCommand1,myList);
				tailoring.execute();   
				if(whetherUndo)
				{
					tailoring.undo();
				}
		        
			} catch (ArgsInvalidException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				assertTrue(e instanceof ArgsInvalidException);
				return 0;
			}
			BufferedImage image2 =imgprocesser1.getImg();
	        return image2.getHeight();
		}
		
		
		@Test
		void test_tailoring1() throws IOException, ArgsInvalidException {
			BufferedImage image = null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
			ArrayList<Object> myList = new ArrayList<>();
	        myList.add(180);
	        myList.add(40);
	        myList.add(50);
	        myList.add(50);
	        imgProcessor imgprocesser1 = new imgProcessor();
	        EditCommand editCommand1 = new EditCommand(imgprocesser1);      
	        imgprocesser1.setImg(image);
	        int result=testTai(imgprocesser1,editCommand1,myList,false);
	        assertEquals(10,result);
	    
		}
		@Test
		void test_tailoring2() throws IOException, ArgsInvalidException {
			BufferedImage image = null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
			ArrayList<Object> myList = new ArrayList<>();
	        myList.add(180);
	        myList.add(40);
	        myList.add(50);
	        myList.add(50);
	        imgProcessor imgprocesser1 = new imgProcessor();
	        EditCommand editCommand1 = new EditCommand(imgprocesser1);      
	        imgprocesser1.setImg(image);
	        int result=testTai(imgprocesser1,editCommand1,myList,true);
	        assertEquals(376,result);

	    
		}
		
		@Test
		void test_tailoring3() throws IOException, ArgsInvalidException {
			BufferedImage image = null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
			ArrayList<Object> myList = new ArrayList<>();
	        myList.add(-1);
	        myList.add(40);
	        myList.add(50);
	        myList.add(50);
	        imgProcessor imgprocesser1 = new imgProcessor();
	        EditCommand editCommand1 = new EditCommand(imgprocesser1);      
	        imgprocesser1.setImg(image);
	        int result=testTai(imgprocesser1,editCommand1,myList,false);
	        assertEquals(0,result);
		}
		
		@Test
		void test_tailoring4() throws IOException, ArgsInvalidException {
			BufferedImage image = null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
			ArrayList<Object> myList = new ArrayList<>();
	        myList.add(180);
	        myList.add(40);
	        myList.add(50);
	        myList.add(40);
	        imgProcessor imgprocesser1 = new imgProcessor();
	        EditCommand editCommand1 = new EditCommand(imgprocesser1);      
	        imgprocesser1.setImg(image);
	        int result=testTai(imgprocesser1,editCommand1,myList,false);
	        assertEquals(0,result);

		}
		
		@Test
		void test_tailoring5() throws IOException, ArgsInvalidException {
			BufferedImage image = null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
			ArrayList<Object> myList = new ArrayList<>();
	        myList.add(800);
	        myList.add(40);
	        myList.add(50);
	        myList.add(40);
	        imgProcessor imgprocesser1 = new imgProcessor();
	        EditCommand editCommand1 = new EditCommand(imgprocesser1);      
	        imgprocesser1.setImg(image);
	        int result=testTai(imgprocesser1,editCommand1,myList,false);
	        assertEquals(0,result);

	    
		}
		
		@Test
		void test_tailoring6() throws IOException, ArgsInvalidException {
			BufferedImage image = null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
			ArrayList<Object> myList = new ArrayList<>();
	        myList.add(100);
	        myList.add(4000);
	        myList.add(50);
	        myList.add(40);
	        imgProcessor imgprocesser1 = new imgProcessor();
	        EditCommand editCommand1 = new EditCommand(imgprocesser1);      
	        imgprocesser1.setImg(image);
	        int result=testTai(imgprocesser1,editCommand1,myList,false);
	        assertEquals(0,result);
	    
		}
		
		private int testTaiBatch(imgProcessor imgprocesser1,EditCommand editCommand1,ArrayList<Object> myList,boolean whetherUndo)
		{
			Tailoring_bat tailoringForBatch;
			try {
				tailoringForBatch = new Tailoring_bat(editCommand1,myList);
				tailoringForBatch.execute();    
				if(whetherUndo)
				{
					tailoringForBatch.undo();
				}
		        
			} catch (ArgsInvalidException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				assertTrue(e instanceof ArgsInvalidException);
				return 0;
			}
			BufferedImage image2 =imgprocesser1.getImg();
	        return image2.getHeight();
		}
		
		@Test
		void test_tailoringForBatch1() throws IOException, ArgsInvalidException {
			BufferedImage image = null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
			ArrayList<Object> myList = new ArrayList<>();
	        myList.add(30);
	        myList.add(30);
	        myList.add(50);
	        myList.add(50);
	        imgProcessor imgprocesser1 = new imgProcessor();
	        EditCommand editCommand1 = new EditCommand(imgprocesser1);      
	        imgprocesser1.setImg(image);
	        int result=testTaiBatch(imgprocesser1,editCommand1,myList,false);
	        assertEquals(50,result);

	    
		}
		@Test
		void test_tailoringForBatch2() throws IOException, ArgsInvalidException {
			BufferedImage image = null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
			ArrayList<Object> myList = new ArrayList<>();
	        myList.add(30);
	        myList.add(30);
	        myList.add(50);
	        myList.add(50);
	        imgProcessor imgprocesser1 = new imgProcessor();
	        EditCommand editCommand1 = new EditCommand(imgprocesser1);      
	        imgprocesser1.setImg(image);
	        int result=testTaiBatch(imgprocesser1,editCommand1,myList,true);
	        assertEquals(376,result);
		}
		@Test
		void test_tailoringForBatch3() throws IOException, ArgsInvalidException {
			BufferedImage image = null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
			ArrayList<Object> myList = new ArrayList<>();
		    myList.add(30);
		    myList.add(-1);
		    myList.add(30);
		    myList.add(30);
		    imgProcessor imgprocesser1 = new imgProcessor();
		    EditCommand editCommand1 = new EditCommand(imgprocesser1);      
		    imgprocesser1.setImg(image);
		    int result=testTaiBatch(imgprocesser1,editCommand1,myList,false);
	        assertEquals(0,result);
			}

		@Test
		void test_tailoringForBatch4() throws IOException, ArgsInvalidException {
			BufferedImage image = null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
			ArrayList<Object> myList = new ArrayList<>();
		    myList.add(30);
		    myList.add(30);
		    myList.add(80);
		    myList.add(30);
		    imgProcessor imgprocesser1 = new imgProcessor();
		    EditCommand editCommand1 = new EditCommand(imgprocesser1);      
		    imgprocesser1.setImg(image);
		    int result=testTaiBatch(imgprocesser1,editCommand1,myList,false);
	        assertEquals(0,result);

	        
		}
		@Test
		void test_tailoringForBatch5() throws IOException, ArgsInvalidException {
			BufferedImage image = null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
			ArrayList<Object> myList = new ArrayList<>();
		    myList.add(30);
		    myList.add(30);
		    myList.add(30);
		    myList.add(80);
		    imgProcessor imgprocesser1 = new imgProcessor();
		    EditCommand editCommand1 = new EditCommand(imgprocesser1);      
		    imgprocesser1.setImg(image);
		    int result=testTaiBatch(imgprocesser1,editCommand1,myList,false);
	        assertEquals(0,result);

	        
		}
		@Test
		void test_rotate90DegreesClockwise1() throws IOException{
			BufferedImage image = null;
			BufferedImage image_want= null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
			String image_wantPath= absolutePath+"\\testcase\\test\\design_clockwise.png";
			image_want = ImageIO.read(new File(image_wantPath));
	        imgProcessor imgprocesser1 = new imgProcessor();
	        EditCommand editCommand1 = new EditCommand(imgprocesser1);      
	        imgprocesser1.setImg(image);
	        ArrayList<Object> myList = new ArrayList<>();
	        Rotate90DegreesClockwise rotate90DegreesClockwise = new Rotate90DegreesClockwise(editCommand1,myList);
	        rotate90DegreesClockwise.execute(); 
			BufferedImage image2 =imgprocesser1.getImg();
			boolean result = diffIntSecondArr(getRGB(image2),getRGB(image_want));
	        assertEquals(true,result);
	       

	        
		}
		@Test
		void test_rotate90DegreesClockwise2() throws IOException{
			BufferedImage image = null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
	        imgProcessor imgprocesser1 = new imgProcessor();
	        EditCommand editCommand1 = new EditCommand(imgprocesser1);      
	        imgprocesser1.setImg(image);
	        ArrayList<Object> myList = new ArrayList<>();
	        Rotate90DegreesClockwise rotate90DegreesClockwise = new Rotate90DegreesClockwise(editCommand1,myList);
	        rotate90DegreesClockwise.execute(); 
	        rotate90DegreesClockwise.undo();
			BufferedImage image2 =imgprocesser1.getImg();
			boolean result = diffIntSecondArr(getRGB(image2),getRGB(image));
	        assertEquals(true,result);
	       

	    
		}
		
		@Test
		void test_rotate90DegreesCounterclockwise1() throws IOException{
			BufferedImage image = null;
			BufferedImage image_want= null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
			String image_wantPath= absolutePath+"\\testcase\\test\\design_counterclockwise.png";
			image_want = ImageIO.read(new File(image_wantPath));
	        imgProcessor imgprocesser1 = new imgProcessor();
	        EditCommand editCommand1 = new EditCommand(imgprocesser1);      
	        imgprocesser1.setImg(image);
	        ArrayList<Object> myList = new ArrayList<>();
	        Rotate90DegreesCounterclockwise rotate90DegreesCounterclockwise = new Rotate90DegreesCounterclockwise(editCommand1,myList);
	        rotate90DegreesCounterclockwise.execute(); 
			BufferedImage image2 =imgprocesser1.getImg();
	        boolean result = diffIntSecondArr(getRGB(image2),getRGB(image_want));
	        assertEquals(true,result);
	    
		}
		
		@Test
		void test_rotate90DegreesCounterclockwise2() throws IOException{
			BufferedImage image = null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));		
	        imgProcessor imgprocesser1 = new imgProcessor();
	        EditCommand editCommand1 = new EditCommand(imgprocesser1);      
	        imgprocesser1.setImg(image);
	        ArrayList<Object> myList = new ArrayList<>();
	        Rotate90DegreesCounterclockwise rotate90DegreesCounterclockwise = new Rotate90DegreesCounterclockwise(editCommand1,myList);
	        rotate90DegreesCounterclockwise.execute(); 
	        rotate90DegreesCounterclockwise.undo(); 
			BufferedImage image2 =imgprocesser1.getImg();
	        boolean result = diffIntSecondArr(getRGB(image2),getRGB(image));
	        assertEquals(true,result);
	    
		}
		
		@Test
		void test_rotate180Degrees1() throws IOException{
			BufferedImage image = null;
			BufferedImage image_want= null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
			String image_wantPath= absolutePath+"\\testcase\\test\\design_180.png";
			image_want = ImageIO.read(new File(image_wantPath));
	        imgProcessor imgprocesser1 = new imgProcessor();
	        EditCommand editCommand1 = new EditCommand(imgprocesser1);      
	        imgprocesser1.setImg(image);
	        ArrayList<Object> myList = new ArrayList<>();
	        Rotate180Degrees rotate180Degrees = new Rotate180Degrees(editCommand1,myList);
	        rotate180Degrees.execute(); 
			BufferedImage image2 =imgprocesser1.getImg();
	        boolean result = diffIntSecondArr(getRGB(image2),getRGB(image_want));
	        assertEquals(true,result);
		}
		@Test
		void test_rotate180Degrees2() throws IOException{
			BufferedImage image = null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
	        imgProcessor imgprocesser1 = new imgProcessor();
	        EditCommand editCommand1 = new EditCommand(imgprocesser1);      
	        imgprocesser1.setImg(image);
	        ArrayList<Object> myList = new ArrayList<>();
	        Rotate180Degrees rotate180Degrees = new Rotate180Degrees(editCommand1,myList);
	        rotate180Degrees.execute(); 
	        rotate180Degrees.undo();
			BufferedImage image2 =imgprocesser1.getImg();
	        boolean result = diffIntSecondArr(getRGB(image2),getRGB(image));
	        assertEquals(true,result);

	    
		}
		
		@Test
		void test_flipHorizontal1() throws IOException{
			BufferedImage image = null;
			BufferedImage image_want= null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
			String image_wantPath= absolutePath+"\\testcase\\test\\design_filp_horizontal.png";
			image_want = ImageIO.read(new File(image_wantPath));
	        imgProcessor imgprocesser1 = new imgProcessor();
	        EditCommand editCommand1 = new EditCommand(imgprocesser1);      
	        imgprocesser1.setImg(image);
	        ArrayList<Object> myList = new ArrayList<>();
	        FlipHorizontal flipHorizontal = new FlipHorizontal(editCommand1,myList);
	        flipHorizontal.execute(); 
			BufferedImage image2 =imgprocesser1.getImg();
	        boolean result = diffIntSecondArr(getRGB(image2),getRGB(image_want));
	        assertEquals(true,result);

	    
		}
		@Test
		void test_flipHorizontal2() throws IOException{
			BufferedImage image = null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
	        imgProcessor imgprocesser1 = new imgProcessor();
	        EditCommand editCommand1 = new EditCommand(imgprocesser1);      
	        imgprocesser1.setImg(image);
	        ArrayList<Object> myList = new ArrayList<>();
	        FlipHorizontal flipHorizontal = new FlipHorizontal(editCommand1,myList);
	        flipHorizontal.execute(); 
	        flipHorizontal.undo();
			BufferedImage image2 =imgprocesser1.getImg();
	        boolean result = diffIntSecondArr(getRGB(image2),getRGB(image));
	        assertEquals(true,result);

	    
		}
		
		@Test
		void test_flipVertical1() throws IOException{
			BufferedImage image = null;
			BufferedImage image_want= null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
			String image_wantPath= absolutePath+"\\testcase\\test\\design_filp_vertical.png";
			image_want = ImageIO.read(new File(image_wantPath));
	        imgProcessor imgprocesser1 = new imgProcessor();
	        EditCommand editCommand1 = new EditCommand(imgprocesser1);      
	        imgprocesser1.setImg(image);
	        ArrayList<Object> myList = new ArrayList<>();
	        FlipVertical flipVertical = new FlipVertical(editCommand1,myList);
	        flipVertical.execute(); 
			BufferedImage image2 =imgprocesser1.getImg();
	        boolean result = diffIntSecondArr(getRGB(image2),getRGB(image_want));
	        assertEquals(true,result);
	    
		}
		
		@Test
		void test_flipVertical2() throws IOException{
			BufferedImage image = null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
	        imgProcessor imgprocesser1 = new imgProcessor();
	        EditCommand editCommand1 = new EditCommand(imgprocesser1);      
	        imgprocesser1.setImg(image);
	        ArrayList<Object> myList = new ArrayList<>();
	        FlipVertical flipVertical = new FlipVertical(editCommand1,myList);
	        flipVertical.execute(); 
	        flipVertical.undo(); 
			BufferedImage image2 =imgprocesser1.getImg();
	        boolean result = diffIntSecondArr(getRGB(image2),getRGB(image));
	        assertEquals(true,result);

	    
		}
		
		
		@Test
		void test_createBorder1() throws IOException, ArgsInvalidException {
			BufferedImage image = null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
			ArrayList<Object> myList = new ArrayList<>();
		    myList.add(-1);
		    myList.add(-1);

		    imgProcessor imgprocesser1 = new imgProcessor();
		    EditCommand editCommand1 = new EditCommand(imgprocesser1);      
		    imgprocesser1.setImg(image);
		    CreateBorder createBorder;
			try {
				createBorder = new CreateBorder(editCommand1,myList);
				createBorder.execute();        
			        
			} catch (ArgsInvalidException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				assertTrue(e instanceof ArgsInvalidException);
			}

	        
		}
		@Test
		void test_createBorder2() throws IOException, ArgsInvalidException {
			BufferedImage image = null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
			ArrayList<Object> myList = new ArrayList<>();
		    myList.add(800);
		    myList.add(-1);

		    imgProcessor imgprocesser1 = new imgProcessor();
		    EditCommand editCommand1 = new EditCommand(imgprocesser1);      
		    imgprocesser1.setImg(image);
		    CreateBorder createBorder;
			try {
				createBorder = new CreateBorder(editCommand1,myList);
				createBorder.execute();        
			        
			} catch (ArgsInvalidException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				assertTrue(e instanceof ArgsInvalidException);
			}

	        
		}
		@Test
		void test_highContrastFilter() throws IOException, ArgsInvalidException {
			BufferedImage image = null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
	        imgProcessor imgprocesser1 = new imgProcessor();
	        EditCommand editCommand1 = new EditCommand(imgprocesser1);      
	        imgprocesser1.setImg(image);
	        HighContrastFilter highContrastFilter;
			try {
				highContrastFilter = new HighContrastFilter(editCommand1,-2);
				highContrastFilter.execute();        
		        
			} catch (ArgsInvalidException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				assertTrue(e instanceof ArgsInvalidException);
			}
		}
		@Test
		void test_mosaicFilter() throws IOException, ArgsInvalidException {
			BufferedImage image = null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
	        imgProcessor imgprocesser1 = new imgProcessor();
	        EditCommand editCommand1 = new EditCommand(imgprocesser1);      
	        imgprocesser1.setImg(image);
	        MosaicFilter mosaicFilter;
			try {
				mosaicFilter = new MosaicFilter(editCommand1,-2);
				mosaicFilter.execute();        
		        
			} catch (ArgsInvalidException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				assertTrue(e instanceof ArgsInvalidException);
			}
		}
		@Test
		void test_paintFilter() throws IOException, ArgsInvalidException {
			BufferedImage image = null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
	        imgProcessor imgprocesser1 = new imgProcessor();
	        EditCommand editCommand1 = new EditCommand(imgprocesser1);      
	        imgprocesser1.setImg(image);
	        PaintFilter paintFilter;
			try {
				paintFilter = new PaintFilter(editCommand1,-2);
				paintFilter.execute();        
		        
			} catch (ArgsInvalidException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				assertTrue(e instanceof ArgsInvalidException);
			}
		}
		@Test
		void test_vintageFilter() throws IOException, ArgsInvalidException {
			BufferedImage image = null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
			ArrayList<Object> myList = new ArrayList<>();
		    myList.add(-1f);

		    imgProcessor imgprocesser1 = new imgProcessor();
		    EditCommand editCommand1 = new EditCommand(imgprocesser1);      
		    imgprocesser1.setImg(image);
		    VintageFilter vintageFilter;
			try {
				vintageFilter = new VintageFilter(editCommand1,myList);
				vintageFilter.execute();        
			        
			} catch (ArgsInvalidException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				assertTrue(e instanceof ArgsInvalidException);
			}

	        
		}
		@Test
		void test_anticolor1() throws IOException {
			BufferedImage image = null;
			BufferedImage image_want= null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
			String image_wantPath= absolutePath+"\\testcase\\test\\design_anti.png";
			image_want = ImageIO.read(new File(image_wantPath));
		
		
		    imgProcessor imgprocesser1 = new imgProcessor();
		    EditCommand editCommand1 = new EditCommand(imgprocesser1);      
		    imgprocesser1.setImg(image);
		    ArrayList<Object> myList = new ArrayList<>();
		    Anticolor anticolor = new Anticolor(editCommand1,myList);
		    anticolor.execute(); 
			BufferedImage image2 =imgprocesser1.getImg();
		    boolean result = diffIntSecondArr(getRGB(image2),getRGB(image_want));
		    assertEquals(true,result);
		
		    
		}
		
		@Test
		void test_anticolor2() throws IOException {
			BufferedImage image = null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
		    imgProcessor imgprocesser1 = new imgProcessor();
		    EditCommand editCommand1 = new EditCommand(imgprocesser1);      
		    imgprocesser1.setImg(image);
		    ArrayList<Object> myList = new ArrayList<>();
		    Anticolor anticolor = new Anticolor(editCommand1,myList);
		    anticolor.execute(); 
		    anticolor.undo(); 
			BufferedImage image2 =imgprocesser1.getImg();
	        boolean result = diffIntSecondArr(getRGB(image2),getRGB(image));
	        assertEquals(true,result);

	        
		}

		@Test
		void test_combineFilter1() throws IOException, ArgsInvalidException {
			BufferedImage image = null;
			BufferedImage image2 = null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
			String imagPath2= absolutePath+"\\testcase\\test\\design_anti.png";
			image2 = ImageIO.read(new File(imagPath2));
	        imgProcessor imgprocesser1 = new imgProcessor();
	        EditCommand editCommand1 = new EditCommand(imgprocesser1);      
	        imgprocesser1.setImg(image);
	        imgProcessor imgprocesser2 = new imgProcessor();    
	        imgprocesser2.setImg(image2);
	        ArrayList<Object> myList = new ArrayList<>();
	        myList.add(300);
	        myList.add(11.72f);
	        CombineFilter combineFilter;
			try {
				combineFilter = new CombineFilter(editCommand1,myList);
				combineFilter.execute();        
		        
			} catch (ArgsInvalidException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				assertTrue(e instanceof ArgsInvalidException);
			}
		}
		
		@Test
		void test_combineFilter2() throws IOException, ArgsInvalidException {
			BufferedImage image = null;
			BufferedImage image2 = null;
			String imagPath= absolutePath+"\\testcase\\test\\design.png";
			image = ImageIO.read(new File(imagPath));
			String imagPath2= absolutePath+"\\testcase\\test\\design_anti.png";
			image2 = ImageIO.read(new File(imagPath2));
	        imgProcessor imgprocesser1 = new imgProcessor();
	        EditCommand editCommand1 = new EditCommand(imgprocesser1);      
	        imgprocesser1.setImg(image);
	        imgProcessor imgprocesser2 = new imgProcessor();    
	        imgprocesser2.setImg(image2);
	        ArrayList<Object> myList = new ArrayList<>();
	        myList.add(1);
	        myList.add(111.72f);
	        CombineFilter combineFilter;
			try {
				combineFilter = new CombineFilter(editCommand1,myList);
				combineFilter.execute();        
		        
			} catch (ArgsInvalidException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				assertTrue(e instanceof ArgsInvalidException);
			}
		}

		
}
