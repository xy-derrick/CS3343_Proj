package exportTest;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Code.Command.Base.Command;
import Code.Command.Export.*;
import Code.Software.imgProcessor;
import Code.exportException.nameNotFoundException;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
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
	
	//unit test
	private boolean testTransfer(typeTransfer test,String resultPath)
	{
		try {
        	test.transfer();
		} catch (nameNotFoundException e) {
			return false;
		}
        File file = new File(resultPath);
        if(!file.exists())
        {
        	return false;
        }
        
        return true;
	}
	
	
	private boolean testLocal(localSave test,String resultPath)
	{
		try {
        	test.save();
		} catch (nameNotFoundException e) {
			return false;
		}
        File file = new File(resultPath);
        if(!file.exists())
        {
        	return false;
        }
        
        return true;
	}
	
	private boolean testCompress(imagCompress test,String resultPath)
	{
		try {
        	test.compress();
		} catch (nameNotFoundException e) {
			return false;
		}
        File file = new File(resultPath);
        if(!file.exists())
        {
        	return false;
        }
        
        return true;
	}
	
	@Test
	public void a01_test_bmp_1() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1test.bmp";
        imgProcessor iProcessor = read(imagPath);
        bmpTransfer test=new bmpTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void a02_test_bmp_2() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1test.png";
        imgProcessor iProcessor = read(imagPath);
        bmpTransfer test=new bmpTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a03_test_bmp_3() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1test.bmp";
        imgProcessor iProcessor = read(imagPath);
        bmpTransfer test=new bmpTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a04_test_bmp_4() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result1";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\ioresult\\1test.png";
        imgProcessor iProcessor = read(imagPath);
        bmpTransfer test=new bmpTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}

	@Test
	public void a05_test_gif_1() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1test.gif";
        imgProcessor iProcessor = read(imagPath);
        gifTransfer test=new gifTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void a06_test_gif_2() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1test.png";
        imgProcessor iProcessor = read(imagPath);
        gifTransfer test=new gifTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a07_test_gif_3() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1test.gif";
        imgProcessor iProcessor = read(imagPath);
        gifTransfer test=new gifTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a08_test_gif_4() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result1";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\ioresult\\1test.png";
        imgProcessor iProcessor = read(imagPath);
        gifTransfer test=new gifTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}

	@Test
	public void a09_test_jpg_1() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1test.jpg";
        imgProcessor iProcessor = read(imagPath);
        jpgTransfer test=new jpgTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void a10_test_jpg_2() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1test.png";
        imgProcessor iProcessor = read(imagPath);
        jpgTransfer test=new jpgTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a11_test_jpg_3() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1test.jpg";
        imgProcessor iProcessor = read(imagPath);
        jpgTransfer test=new jpgTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a12_test_jpg_4() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result1";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\ioresult\\1test.png";
        imgProcessor iProcessor = read(imagPath);
        jpgTransfer test=new jpgTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}

	@Test
	public void a13_test_png_1() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test1.jpg";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1test1.png";
        imgProcessor iProcessor = read(imagPath);
        pngTransfer test=new pngTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void a14_test_png_2() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test1.jpg";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1test1.jpg";
        imgProcessor iProcessor = read(imagPath);
        pngTransfer test=new pngTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a15_test_png_3() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\.jpg";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1test1.png";
        imgProcessor iProcessor = read(imagPath);
        pngTransfer test=new pngTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a16_test_png_4() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test1.jpg";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result1";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\ioresult\\1test1.png";
        imgProcessor iProcessor = read(imagPath);
        pngTransfer test=new pngTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a17_test_tiff_1() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1test.tiff";
        imgProcessor iProcessor = read(imagPath);
        tiffTransfer test=new tiffTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void a18_test_tiff_2() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1test.png";
        imgProcessor iProcessor = read(imagPath);
        tiffTransfer test=new tiffTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a19_test_tiff_3() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1.tiff";
        imgProcessor iProcessor = read(imagPath);
        tiffTransfer test=new tiffTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a20_test_tiff_4() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result1";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\ioresult\\1test.tiff";
        imgProcessor iProcessor = read(imagPath);
        tiffTransfer test=new tiffTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a21_test_local_1() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\testlocal.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1testlocal.png";
        imgProcessor iProcessor = read(imagPath);
        localSave test=new localSave(iProcessor,savePath);
        boolean result=testLocal(test,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void a22_test_local_2() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\testlocal.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result1";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\ioresult\\2testlocal.png";
        imgProcessor iProcessor = read(imagPath);
        localSave test=new localSave(iProcessor,savePath);
        boolean result=testLocal(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a23_test_local_3() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\2testlocal.png";
        imgProcessor iProcessor = read(imagPath);
        localSave test=new localSave(iProcessor,savePath);
        boolean result=testLocal(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a24_test_local_4() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\.tiff";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\2testlocal.png";
        imgProcessor iProcessor = read(imagPath);
        localSave test=new localSave(iProcessor,savePath);
        boolean result=testLocal(test,resultPath);
        assertEquals(false, result);
	}
	

	
	@Test
	public void a25_test_zip_1() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\testlocal.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\testlocal2.zip";
        imgProcessor iProcessor = read(imagPath);
        imagCompress test=new imagCompress(iProcessor,savePath);
        boolean result=testCompress(test,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void a26_test_zip_2() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\testlocal.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result1";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\ioresult\\testlocal3.zip";
        imgProcessor iProcessor = read(imagPath);
        imagCompress test=new imagCompress(iProcessor,savePath);
        boolean result=testCompress(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a27_test_zip_3() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\testlocal3.zip";
        imgProcessor iProcessor = read(imagPath);
        imagCompress test=new imagCompress(iProcessor,savePath);
        boolean result=testCompress(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a28_test_zip_4() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\.tiff";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\testlocal3.zip";
        imgProcessor iProcessor = read(imagPath);
        imagCompress test=new imagCompress(iProcessor,savePath);
        boolean result=testCompress(test,resultPath);
        assertEquals(false, result);
	}

	//integration test
	
	private boolean testCommandTransfer(Command test,String resultPath)
	{
        test.execute();
        File file = new File(resultPath);
        if(!file.exists())
        {
        	return false;
        }
        
        return true;
	}
	
	@Test
	public void b01_test_bmp_integration_1() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\integrationResult";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\integrationResult\\1test.bmp";
        imgProcessor iProcessor = read(imagPath);
        Command test=new bmpTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void b02_test_bmp_integration_2() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\integrationResult";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\integrationResult\\2test.png";
        imgProcessor iProcessor = read(imagPath);
        Command test=new bmpTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void b03_test_bmp_integration_3() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\integrationResult";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\integrationResult\\3test.bmp";
        imgProcessor iProcessor = read(imagPath);
        Command test=new bmpTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void b04_test_bmp_integration_4() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result1";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\ioresult\\1test.png";
        imgProcessor iProcessor = read(imagPath);
        Command test=new bmpTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}

	@Test
	public void b05_test_gif_integration_1() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\integrationResult";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\integrationResult\\5test.gif";
        imgProcessor iProcessor = read(imagPath);
        Command test=new gifTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void b06_test_gif_integration_2() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\integrationResult";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\integrationResult\\6test.png";
        imgProcessor iProcessor = read(imagPath);
        Command test=new gifTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void b07_test_gif_integration_3() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\integrationResult";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\integrationResult\\1test.gif";
        imgProcessor iProcessor = read(imagPath);
        Command test=new gifTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void b08_test_gif_integration_4() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result1";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\ioresult\\5test.png";
        imgProcessor iProcessor = read(imagPath);
        Command test=new gifTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}

	@Test
	public void b09_test_jpg_integration_1() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\integrationResult";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\integrationResult\\9test.jpg";
        imgProcessor iProcessor = read(imagPath);
        Command test=new jpgTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void b10_test_jpg_integration_2() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\integrationResult";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\integrationResult\\10test.png";
        imgProcessor iProcessor = read(imagPath);
        Command test=new jpgTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void b11_test_jpg_integration_3() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\integrationResult";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\integrationResult\\11test.jpg";
        imgProcessor iProcessor = read(imagPath);
        Command test=new jpgTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void b12_test_jpg_integration_4() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result1";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\ioresult\\12test.png";
        imgProcessor iProcessor = read(imagPath);
        Command test=new jpgTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}

	@Test
	public void b13_test_png_integration_1() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test1.jpg";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\integrationResult";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\integrationResult\\13test1.png";
        imgProcessor iProcessor = read(imagPath);
        Command test=new pngTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void b14_test_png_integration_2() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test1.jpg";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\integrationResult";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\integrationResult\\14test1.jpg";
        imgProcessor iProcessor = read(imagPath);
        pngTransfer test=new pngTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void b15_test_png_integration_3() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\.jpg";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\integrationResult";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\integrationResult\\15test1.png";
        imgProcessor iProcessor = read(imagPath);
        Command test=new pngTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void b16_test_png_integration_4() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test1.jpg";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result1";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\ioresult\\1test1.png";
        imgProcessor iProcessor = read(imagPath);
        Command test=new pngTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void b17_test_tiff_integration_1() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\integrationResult";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\integrationResult\\16test.tiff";
        imgProcessor iProcessor = read(imagPath);
        Command test=new tiffTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void b18_test_tiff_integration_2() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\integrationResult";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\integrationResult\\17test.png";
        imgProcessor iProcessor = read(imagPath);
        Command test=new tiffTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void b19_test_tiff_integration_3() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\integrationResult";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\integrationResult\\18.tiff";
        imgProcessor iProcessor = read(imagPath);
        Command test=new tiffTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void b20_test_tiff_integration_4() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result1";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\ioresult\\1test.tiff";
        imgProcessor iProcessor = read(imagPath);
        Command test=new tiffTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}
//	
//	@Test
//	public void test_local_integration_1() {
//		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\testlocal.png";
//		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
//		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1testlocal.png";
//        imgProcessor iProcessor = read(imagPath);
//        localSave test=new localSave(iProcessor,savePath);
//        boolean result=testLocal(test,resultPath);
//        assertEquals(true, result);
//	}
//
//	@Test
//	public void test_local_integration_2() {
//		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\testlocal.png";
//		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result1";
//		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\ioresult\\2testlocal.png";
//        imgProcessor iProcessor = read(imagPath);
//        localSave test=new localSave(iProcessor,savePath);
//        boolean result=testLocal(test,resultPath);
//        assertEquals(false, result);
//	}
//	
//	@Test
//	public void test_local_integration_3() {
//		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\.png";
//		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
//		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\2testlocal.png";
//        imgProcessor iProcessor = read(imagPath);
//        localSave test=new localSave(iProcessor,savePath);
//        boolean result=testLocal(test,resultPath);
//        assertEquals(false, result);
//	}
//	
//	@Test
//	public void test_local_integration_4() {
//		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\.tiff";
//		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
//		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\2testlocal.png";
//        imgProcessor iProcessor = read(imagPath);
//        localSave test=new localSave(iProcessor,savePath);
//        boolean result=testLocal(test,resultPath);
//        assertEquals(false, result);
//	}
//	
//
//	
//	@Test
//	public void test_zip_integration_1() {
//		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\testlocal.png";
//		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
//		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\testlocal2.zip";
//        imgProcessor iProcessor = read(imagPath);
//        imagCompress test=new imagCompress(iProcessor,savePath);
//        boolean result=testCompress(test,resultPath);
//        assertEquals(true, result);
//	}
//
//	@Test
//	public void test_zip_integration_2() {
//		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\testlocal.png";
//		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result1";
//		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\ioresult\\testlocal3.zip";
//        imgProcessor iProcessor = read(imagPath);
//        imagCompress test=new imagCompress(iProcessor,savePath);
//        boolean result=testCompress(test,resultPath);
//        assertEquals(false, result);
//	}
//	
//	@Test
//	public void test_zip_integration_3() {
//		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\.png";
//		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
//		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\testlocal3.zip";
//        imgProcessor iProcessor = read(imagPath);
//        imagCompress test=new imagCompress(iProcessor,savePath);
//        boolean result=testCompress(test,resultPath);
//        assertEquals(false, result);
//	}
//	
//	@Test
//	public void test_zip_integration_4() {
//		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\.tiff";
//		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
//		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\testlocal3.zip";
//        imgProcessor iProcessor = read(imagPath);
//        imagCompress test=new imagCompress(iProcessor,savePath);
//        boolean result=testCompress(test,resultPath);
//        assertEquals(false, result);
//	}
	

}
