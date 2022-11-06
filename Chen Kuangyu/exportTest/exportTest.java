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
		String imagPath="C:\\Users\\chenkuangyu\\Desktop\\test.png";
		String savePath="C:\\Users\\chenkuangyu\\Desktop";
		String resultPath="C:\\Users\\chenkuangyu\\Desktop\\1test.bmp";
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
		String imagPath="C:\\Users\\chenkuangyu\\Desktop\\test.png";
		String savePath="C:\\Users\\chenkuangyu\\Desktop";
		String resultPath="C:\\Users\\chenkuangyu\\Desktop\\1test.png";
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

	}

	@Test
	public void test_gif_2() {

	}

	@Test
	public void test_jpg_1() {

	}

	@Test
	public void test_jpg_2() {

	}

	@Test
	public void test_png_1() {

	}

	@Test
	public void test_png_2() {

	}
	
	@Test
	public void test_tiff_1() {

	}

	@Test
	public void test_tiff_2() {

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
