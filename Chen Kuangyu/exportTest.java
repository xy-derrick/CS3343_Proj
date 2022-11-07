package exportTest;

import Code.Command.Commands.Export.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import Code.Software.imgProcessor;
import Code.exportException.nameNotFoundException;

public class exportTest {
	
	private imgProcessor read(String path)
	{
		File imgFile = new File(path);
		BufferedImage image = null;
		try {
			image = ImageIO.read(imgFile);
		} catch (IOException e) {
			System.out.println("fail");
		}
        imgProcessor iProcessor = new imgProcessor();
        iProcessor.setImg(image); 
        iProcessor.setPath(path); 
        return iProcessor;
	}
	
	@Test
	public void test_bmp_1() {
		String imagPath="C:\\Users\\chenkuangyu\\Desktop\\pro-3343\\testcase\\test\\test.png";
		String savePath="C:\\Users\\chenkuangyu\\Desktop\\pro-3343\\testcase\\result";
		String resultPath="C:\\Users\\chenkuangyu\\Desktop\\pro-3343\\testcase\\result\\1test.bmp";
        imgProcessor iProcessor = read(imagPath);
        bmpTransfer test=new bmpTransfer(iProcessor,savePath);
        boolean result=true;
        try {
        	test.transfer();
		} catch (nameNotFoundException e) {
			result=false;
		}
        File file = new File(resultPath);
        if(!file.exists())
        {
        	result=false;
        }
        assertEquals(true, result);
	}

	@Test
	public void test_bmp_2() {
		String imagPath="C:\\Users\\chenkuangyu\\Desktop\\pro-3343\\testcase\\test\\test.png";
		String savePath="C:\\Users\\chenkuangyu\\Desktop\\pro-3343\\testcase\\result";
		String resultPath="C:\\Users\\chenkuangyu\\Desktop\\pro-3343\\testcase\\result\\1test.png";
        imgProcessor iProcessor = read(imagPath);
        bmpTransfer test=new bmpTransfer(iProcessor,savePath);
        boolean result=true;
        try {
        	test.transfer();
		} catch (nameNotFoundException e) {
			result=false;
		}
        File file = new File(resultPath);
        if(!file.exists())
        {
        	result=false;
        }
        assertEquals(false, result);
	}

	@Test
	public void test_gif_1() {
		String imagPath="C:\\Users\\chenkuangyu\\Desktop\\pro-3343\\testcase\\test\\test.png";
		String savePath="C:\\Users\\chenkuangyu\\Desktop\\pro-3343\\testcase\\result";
		String resultPath="C:\\Users\\chenkuangyu\\Desktop\\pro-3343\\testcase\\result\\1test.gif";
        imgProcessor iProcessor = read(imagPath);
        gifTransfer test=new gifTransfer(iProcessor,savePath);
        boolean result=true;
        try {
        	test.transfer();
		} catch (nameNotFoundException e) {
			result=false;
		}
        File file = new File(resultPath);
        if(!file.exists())
        {
        	result=false;
        }
        assertEquals(true, result);
	}

	@Test
	public void test_gif_2() {
		String imagPath="C:\\Users\\chenkuangyu\\Desktop\\pro-3343\\testcase\\test\\test.png";
		String savePath="C:\\Users\\chenkuangyu\\Desktop\\pro-3343\\testcase\\result";
		String resultPath="C:\\Users\\chenkuangyu\\Desktop\\pro-3343\\testcase\\result\\1test.png";
        imgProcessor iProcessor = read(imagPath);
        gifTransfer test=new gifTransfer(iProcessor,savePath);
        boolean result=true;
        try {
        	test.transfer();
		} catch (nameNotFoundException e) {
			result=false;
		}
        File file = new File(resultPath);
        if(!file.exists())
        {
        	result=false;
        }
        assertEquals(false, result);
	}

	@Test
	public void test_jpg_1() {
		String imagPath="C:\\Users\\chenkuangyu\\Desktop\\pro-3343\\testcase\\test\\test.png";
		String savePath="C:\\Users\\chenkuangyu\\Desktop\\pro-3343\\testcase\\result";
		String resultPath="C:\\Users\\chenkuangyu\\Desktop\\pro-3343\\testcase\\result\\1test.jpg";
        imgProcessor iProcessor = read(imagPath);
        jpgTransfer test=new jpgTransfer(iProcessor,savePath);
        boolean result=true;
        try {
        	test.transfer();
		} catch (nameNotFoundException e) {
			result=false;
		}
        File file = new File(resultPath);
        if(!file.exists())
        {
        	result=false;
        }
        assertEquals(true, result);
	}

	@Test
	public void test_jpg_2() {
		String imagPath="C:\\Users\\chenkuangyu\\Desktop\\pro-3343\\testcase\\test\\test.png";
		String savePath="C:\\Users\\chenkuangyu\\Desktop\\pro-3343\\testcase\\result";
		String resultPath="C:\\Users\\chenkuangyu\\Desktop\\pro-3343\\testcase\\result\\1test.png";
        imgProcessor iProcessor = read(imagPath);
        jpgTransfer test=new jpgTransfer(iProcessor,savePath);
        boolean result=true;
        try {
        	test.transfer();
		} catch (nameNotFoundException e) {
			result=false;
		}
        File file = new File(resultPath);
        if(!file.exists())
        {
        	result=false;
        }
        assertEquals(false, result);
	}

	@Test
	public void test_png_1() {
		String imagPath="C:\\Users\\chenkuangyu\\Desktop\\pro-3343\\testcase\\test\\test1.jpg";
		String savePath="C:\\Users\\chenkuangyu\\Desktop\\pro-3343\\testcase\\result";
		String resultPath="C:\\Users\\chenkuangyu\\Desktop\\pro-3343\\testcase\\result\\1test1.png";
        imgProcessor iProcessor = read(imagPath);
        pngTransfer test=new pngTransfer(iProcessor,savePath);
        boolean result=true;
        try {
        	test.transfer();
		} catch (nameNotFoundException e) {
			result=false;
		}
        File file = new File(resultPath);
        if(!file.exists())
        {
        	result=false;
        }
        assertEquals(true, result);
	}

	@Test
	public void test_png_2() {
		String imagPath="C:\\Users\\chenkuangyu\\Desktop\\pro-3343\\testcase\\test\\test1.jpg";
		String savePath="C:\\Users\\chenkuangyu\\Desktop\\pro-3343\\testcase\\result";
		String resultPath="C:\\Users\\chenkuangyu\\Desktop\\pro-3343\\testcase\\result\\1test1.jpg";
        imgProcessor iProcessor = read(imagPath);
        pngTransfer test=new pngTransfer(iProcessor,savePath);
        boolean result=true;
        try {
        	test.transfer();
		} catch (nameNotFoundException e) {
			result=false;
		}
        File file = new File(resultPath);
        if(!file.exists())
        {
        	result=false;
        }
        assertEquals(false, result);
	}
	
	@Test
	public void test_tiff_1() {
		String imagPath="C:\\Users\\chenkuangyu\\Desktop\\pro-3343\\testcase\\test\\test.png";
		String savePath="C:\\Users\\chenkuangyu\\Desktop\\pro-3343\\testcase\\result";
		String resultPath="C:\\Users\\chenkuangyu\\Desktop\\pro-3343\\testcase\\result\\1test.tiff";
        imgProcessor iProcessor = read(imagPath);
        tiffTransfer test=new tiffTransfer(iProcessor,savePath);
        boolean result=true;
        try {
        	test.transfer();
		} catch (nameNotFoundException e) {
			result=false;
		}
        File file = new File(resultPath);
        if(!file.exists())
        {
        	result=false;
        }
        assertEquals(true, result);
	}

	@Test
	public void test_tiff_2() {
		String imagPath="C:\\Users\\chenkuangyu\\Desktop\\pro-3343\\testcase\\test\\test.png";
		String savePath="C:\\Users\\chenkuangyu\\Desktop\\pro-3343\\testcase\\result";
		String resultPath="C:\\Users\\chenkuangyu\\Desktop\\pro-3343\\testcase\\result\\1test.png";
        imgProcessor iProcessor = read(imagPath);
        tiffTransfer test=new tiffTransfer(iProcessor,savePath);
        boolean result=true;
        try {
        	test.transfer();
		} catch (nameNotFoundException e) {
			result=false;
		}
        File file = new File(resultPath);
        if(!file.exists())
        {
        	result=false;
        }
        assertEquals(false, result);
	}
	
	@Test
	public void test_local_1() {

	}

	@Test
	public void test_local_2() {

	}
	
	@Test
	public void test_zip_1() {

	}

	@Test
	public void test_zip_2() {

	}

}
