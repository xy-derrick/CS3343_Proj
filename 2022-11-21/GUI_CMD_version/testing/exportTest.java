package testing;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import Java.Code.Command.Base.Command;
import Java.Code.Command.Export.*;
import Java.Code.Exception.nameNotFoundException;
import Java.Code.Software.Software;
import Java.Code.Software.imgProcessor;
import Java.Main.Main_cmd;
import Java.Code.Command.Export.*;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class exportTest {
	private String absolutePath = System.getProperty("user.dir");
	
	private String getName(String localPath) {
		String fName = localPath.trim();
		String name = fName.substring(fName.lastIndexOf("\\") + 1);
		return name.substring(0, name.lastIndexOf("."));
	}
	
	
	private imgProcessor read(String path)
	{
		File imgFile = new File(path);
		BufferedImage image = null;
		try {
			image = ImageIO.read(imgFile);
		} catch (IOException e) {
			System.out.println("fail");
		}
        imgProcessor iProcessor = new imgProcessor(getName(path));
        iProcessor.setImg(image); 
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
	
	private imgProcessor readLocal(String path)
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
	
	private imgProcessor readZip(String path)
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
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\result";
		String resultPath=absolutePath+"\\testcase\\result\\test_1.bmp";
		System.out.println("-------------------------------------------------------------------------");
		System.out.println(imagPath);
        imgProcessor iProcessor = read(imagPath);
        bmpTransfer test=new bmpTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void a02_test_bmp_2() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\result";
		String resultPath=absolutePath+"\\testcase\\result\\test_1.png";
        imgProcessor iProcessor = read(imagPath);
        bmpTransfer test=new bmpTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a03_test_bmp_3() {
		String imagPath=absolutePath+"\\testcase\\test\\.png";
		String savePath=absolutePath+"\\testcase\\result";
		String resultPath=absolutePath+"\\testcase\\result\\_1.bmp";
        imgProcessor iProcessor = read(imagPath);
        bmpTransfer test=new bmpTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a04_test_bmp_4() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase1\\ioresult";
		String resultPath=absolutePath+"\\testcase1\\ioresult\\test_1.bmp";
        imgProcessor iProcessor = read(imagPath);
        bmpTransfer test=new bmpTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}

	@Test
	public void a05_test_gif_1() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\result";
		String resultPath=absolutePath+"\\testcase\\result\\test_1.gif";
        imgProcessor iProcessor = read(imagPath);
        gifTransfer test=new gifTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void a06_test_gif_2() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\result";
		String resultPath=absolutePath+"\\testcase\\result\\test_1.png";
        imgProcessor iProcessor = read(imagPath);
        gifTransfer test=new gifTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a07_test_gif_3() {
		String imagPath=absolutePath+"\\testcase\\test\\.png";
		String savePath=absolutePath+"\\testcase\\result";
		String resultPath=absolutePath+"\\testcase\\result\\_1.gif";
        imgProcessor iProcessor = read(imagPath);
        gifTransfer test=new gifTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a08_test_gif_4() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase1\\ioresult";
		String resultPath=absolutePath+"\\testcase1\\ioresult\\test_1.gif";
        imgProcessor iProcessor = read(imagPath);
        gifTransfer test=new gifTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}

	@Test
	public void a09_test_jpg_1() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\result";
		String resultPath=absolutePath+"\\testcase\\result\\test_1.jpg";
        imgProcessor iProcessor = read(imagPath);
        jpgTransfer test=new jpgTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void a10_test_jpg_2() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\result";
		String resultPath=absolutePath+"\\testcase\\result\\test_1.png";
        imgProcessor iProcessor = read(imagPath);
        jpgTransfer test=new jpgTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a11_test_jpg_3() {
		String imagPath=absolutePath+"\\testcase\\test\\.png";
		String savePath=absolutePath+"\\testcase\\result";
		String resultPath=absolutePath+"\\testcase\\result\\_1.jpg";
        imgProcessor iProcessor = read(imagPath);
        jpgTransfer test=new jpgTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a12_test_jpg_4() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase1\\ioresult";
		String resultPath=absolutePath+"\\testcase1\\ioresult\\test_1.bmp";
        imgProcessor iProcessor = read(imagPath);
        jpgTransfer test=new jpgTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}

	@Test
	public void a13_test_png_1() {
		String imagPath=absolutePath+"\\testcase\\test\\test1.jpg";
		String savePath=absolutePath+"\\testcase\\result";
		String resultPath=absolutePath+"\\testcase\\result\\test1_1.png";
        imgProcessor iProcessor = read(imagPath);
        pngTransfer test=new pngTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void a14_test_png_2() {
		String imagPath=absolutePath+"\\testcase\\test\\test1.jpg";
		String savePath=absolutePath+"\\testcase\\result";
		String resultPath=absolutePath+"\\testcase\\result\\test1_1.jpg";
        imgProcessor iProcessor = read(imagPath);
        pngTransfer test=new pngTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a15_test_png_3() {
		String imagPath=absolutePath+"\\testcase\\test\\.jpg";
		String savePath=absolutePath+"\\testcase\\result";
		String resultPath=absolutePath+"\\testcase\\result\\_1.png";
        imgProcessor iProcessor = read(imagPath);
        pngTransfer test=new pngTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a16_test_png_4() {
		String imagPath=absolutePath+"\\testcase\\test\\test1.jpg";
		String savePath=absolutePath+"\\testcase1\\ioresult";
		String resultPath=absolutePath+"\\testcase1\\ioresult\\test1_1.png";
        imgProcessor iProcessor = read(imagPath);
        pngTransfer test=new pngTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a17_test_tiff_1() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\result";
		String resultPath=absolutePath+"\\testcase\\result\\test_1.tiff";
        imgProcessor iProcessor = read(imagPath);
        tiffTransfer test=new tiffTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void a18_test_tiff_2() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\result";
		String resultPath=absolutePath+"\\testcase\\result\\test_1.png";
        imgProcessor iProcessor = read(imagPath);
        tiffTransfer test=new tiffTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a19_test_tiff_3() {
		String imagPath=absolutePath+"\\testcase\\test\\.png";
		String savePath=absolutePath+"\\testcase\\result";
		String resultPath=absolutePath+"\\testcase\\result\\_1.tiff";
        imgProcessor iProcessor = read(imagPath);
        tiffTransfer test=new tiffTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a20_test_tiff_4() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase1\\ioresult";
		String resultPath=absolutePath+"\\testcase1\\ioresult\\test_1.tiff";
        imgProcessor iProcessor = read(imagPath);
        tiffTransfer test=new tiffTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a21_test_local_1() {
		String imagPath=absolutePath+"\\testcase\\test\\testlocal.png";
		String savePath=absolutePath+"\\testcase\\result";
		String resultPath=absolutePath+"\\testcase\\result\\1_testlocal.png";
        imgProcessor iProcessor = readLocal(imagPath);
        localSave test=new localSave(iProcessor,savePath);
        boolean result=testLocal(test,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void a22_test_local_2() {
		String imagPath=absolutePath+"\\testcase\\test\\testlocal.png";
		String savePath=absolutePath+"\\testcase1\\ioresult";
		String resultPath=absolutePath+"\\testcase1\\ioresult\\1_testlocal.png";
        imgProcessor iProcessor = readLocal(imagPath);
        localSave test=new localSave(iProcessor,savePath);
        boolean result=testLocal(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a23_test_local_3() {
		String imagPath=absolutePath+"\\testcase\\test\\.png";
		String savePath=absolutePath+"\\testcase\\result";
		String resultPath=absolutePath+"\\testcase\\result\\1_.png";
        imgProcessor iProcessor = readLocal(imagPath);
        localSave test=new localSave(iProcessor,savePath);
        boolean result=testLocal(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a24_test_local_4() {
		String imagPath=absolutePath+"\\testcase\\test\\.tiff";
		String savePath=absolutePath+"\\testcase\\result";
		String resultPath=absolutePath+"\\testcase\\result\\1_.tiff";
        imgProcessor iProcessor = readLocal(imagPath);
        localSave test=new localSave(iProcessor,savePath);
        boolean result=testLocal(test,resultPath);
        assertEquals(false, result);
	}
	

	
	@Test
	public void a25_test_zip_1() {
		String imagPath=absolutePath+"\\testcase\\test\\testlocal.png";
		String savePath=absolutePath+"\\testcase\\result";
		String resultPath=absolutePath+"\\testcase\\result\\testlocal_1.zip";
        imgProcessor iProcessor = readZip(imagPath);
        imagCompress test=new imagCompress(iProcessor,savePath);
        boolean result=testCompress(test,resultPath);
        assertEquals(true, result);
	}
	
	@Test
	public void a25_test_zip_2() {
		String imagPath=absolutePath+"\\testcase\\test\\test1.jpg";
		String savePath=absolutePath+"\\testcase\\pathEmptyResult";
		String resultPath=absolutePath+"\\testcase\\pathEmptyResult\\test1_1.zip";
        imgProcessor iProcessor = read(imagPath);
        imagCompress test=new imagCompress(iProcessor,savePath);
        boolean result=testCompress(test,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void a26_test_zip_3() {
		String imagPath=absolutePath+"\\testcase\\test\\testlocal.png";
		String savePath=absolutePath+"\\testcase1\\result";
		String resultPath=absolutePath+"\\testcase1\\result\\testlocal_1.zip";
        imgProcessor iProcessor = readZip(imagPath);
        imagCompress test=new imagCompress(iProcessor,savePath);
        boolean result=testCompress(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a27_test_zip_4() {
		String imagPath=absolutePath+"\\testcase\\test\\.png";
		String savePath=absolutePath+"\\testcase\\result";
		String resultPath=absolutePath+"\\testcase\\result\\_1.zip";
        imgProcessor iProcessor = readZip(imagPath);
        imagCompress test=new imagCompress(iProcessor,savePath);
        boolean result=testCompress(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void a28_test_zip_5() {
		String imagPath=absolutePath+"\\testcase\\test\\.tiff";
		String savePath=absolutePath+"\\testcase\\result";
		String resultPath=absolutePath+"\\testcase\\result\\_1.tiff";
        imgProcessor iProcessor = readZip(imagPath);
        imagCompress test=new imagCompress(iProcessor,savePath);
        boolean result=testCompress(test,resultPath);
        assertEquals(false, result);
	}

	//integration test level 1
	
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
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\integrationResult";
		String resultPath=absolutePath+"\\testcase\\integrationResult\\test_1.bmp";
        imgProcessor iProcessor = read(imagPath);
        Command test=new bmpTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void b02_test_bmp_integration_2() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\integrationResult";
		String resultPath=absolutePath+"\\testcase\\integrationResult\\test_2.png";
        imgProcessor iProcessor = read(imagPath);
        Command test=new bmpTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void b03_test_bmp_integration_3() {
		String imagPath=absolutePath+"\\testcase\\test\\.png";
		String savePath=absolutePath+"\\testcase\\integrationResult";
		String resultPath=absolutePath+"\\testcase\\integrationResult\\_3.bmp";
        imgProcessor iProcessor = read(imagPath);
        Command test=new bmpTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void b04_test_bmp_integration_4() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase1\\ioresult";
		String resultPath=absolutePath+"\\testcase1\\ioresult\\test_4.bmp";
        imgProcessor iProcessor = read(imagPath);
        Command test=new bmpTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}

	@Test
	public void b05_test_gif_integration_1() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\integrationResult";
		String resultPath=absolutePath+"\\testcase\\integrationResult\\test_5.gif";
        imgProcessor iProcessor = read(imagPath);
        Command test=new gifTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void b06_test_gif_integration_2() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\integrationResult";
		String resultPath=absolutePath+"\\testcase\\integrationResult\\test_6.png";
        imgProcessor iProcessor = read(imagPath);
        Command test=new gifTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void b07_test_gif_integration_3() {
		String imagPath=absolutePath+"\\testcase\\test\\.png";
		String savePath=absolutePath+"\\testcase\\integrationResult";
		String resultPath=absolutePath+"\\testcase\\integrationResult\\_7.gif";
        imgProcessor iProcessor = read(imagPath);
        Command test=new gifTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void b08_test_gif_integration_4() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase1\\ioresult";
		String resultPath=absolutePath+"\\testcase1\\ioresult\\test_8.gif";
        imgProcessor iProcessor = read(imagPath);
        Command test=new gifTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}

	@Test
	public void b09_test_jpg_integration_1() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\integrationResult";
		String resultPath=absolutePath+"\\testcase\\integrationResult\\test_9.jpg";
        imgProcessor iProcessor = read(imagPath);
        Command test=new jpgTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void b10_test_jpg_integration_2() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\integrationResult";
		String resultPath=absolutePath+"\\testcase\\integrationResult\\test_10.png";
        imgProcessor iProcessor = read(imagPath);
        Command test=new jpgTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void b11_test_jpg_integration_3() {
		String imagPath=absolutePath+"\\testcase\\test\\.png";
		String savePath=absolutePath+"\\testcase\\integrationResult";
		String resultPath=absolutePath+"\\testcase\\integrationResult\\_11.jpg";
        imgProcessor iProcessor = read(imagPath);
        Command test=new jpgTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void b12_test_jpg_integration_4() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase1\\integrationResult";
		String resultPath=absolutePath+"\\testcase1\\integrationResult\\test_12.jpg";
        imgProcessor iProcessor = read(imagPath);
        Command test=new jpgTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}

	@Test
	public void b13_test_png_integration_1() {
		String imagPath=absolutePath+"\\testcase\\test\\test1.jpg";
		String savePath=absolutePath+"\\testcase\\integrationResult";
		String resultPath=absolutePath+"\\testcase\\integrationResult\\test1_13.png";
        imgProcessor iProcessor = read(imagPath);
        Command test=new pngTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void b14_test_png_integration_2() {
		String imagPath=absolutePath+"\\testcase\\test\\test1.jpg";
		String savePath=absolutePath+"\\testcase\\integrationResult";
		String resultPath=absolutePath+"\\testcase\\integrationResult\\test1_14.jpg";
        imgProcessor iProcessor = read(imagPath);
        pngTransfer test=new pngTransfer(iProcessor,savePath);
        boolean result=testTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void b15_test_png_integration_3() {
		String imagPath=absolutePath+"\\testcase\\test\\.jpg";
		String savePath=absolutePath+"\\testcase\\integrationResult";
		String resultPath=absolutePath+"\\testcase\\integrationResult\\_15.png";
        imgProcessor iProcessor = read(imagPath);
        Command test=new pngTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void b16_test_png_integration_4() {
		String imagPath=absolutePath+"\\testcase\\test\\test1.jpg";
		String savePath=absolutePath+"\\testcase1\\integrationResult";
		String resultPath=absolutePath+"\\testcase1\\integrationResult\\test1_16.png";
        imgProcessor iProcessor = read(imagPath);
        Command test=new pngTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void b17_test_tiff_integration_1() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\integrationResult";
		String resultPath=absolutePath+"\\testcase\\integrationResult\\test_16.tiff";
        imgProcessor iProcessor = read(imagPath);
        Command test=new tiffTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void b18_test_tiff_integration_2() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\integrationResult";
		String resultPath=absolutePath+"\\testcase\\integrationResult\\test_17.png";
        imgProcessor iProcessor = read(imagPath);
        Command test=new tiffTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void b19_test_tiff_integration_3() {
		String imagPath=absolutePath+"\\testcase\\test\\.png";
		String savePath=absolutePath+"\\testcase\\integrationResult";
		String resultPath=absolutePath+"\\testcase\\integrationResult\\_18.tiff";
        imgProcessor iProcessor = read(imagPath);
        Command test=new tiffTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void b20_test_tiff_integration_4() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase1\\integrationResult";
		String resultPath=absolutePath+"\\testcase1\\integrationResult\\test_19.tiff";
        imgProcessor iProcessor = read(imagPath);
        Command test=new tiffTransfer(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void b21_test_local_integration_1() {
		String imagPath=absolutePath+"\\testcase\\test\\testlocal.png";
		String savePath=absolutePath+"\\testcase\\integrationResult";
		String resultPath=absolutePath+"\\testcase\\integrationResult\\1_testlocal.png";
        imgProcessor iProcessor = readLocal(imagPath);
        Command test=new localSave(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void b22_test_local_integration_2() {
		String imagPath=absolutePath+"\\testcase\\test\\testlocal.png";
		String savePath=absolutePath+"\\testcase1\\ioresult";
		String resultPath=absolutePath+"\\testcase1\\ioresult\\2_testlocal.png";
        imgProcessor iProcessor = readLocal(imagPath);
        Command test=new localSave(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void b23_test_local_integration_3() {
		String imagPath=absolutePath+"\\testcase\\test\\.png";
		String savePath=absolutePath+"\\testcase\\integrationResult";
		String resultPath=absolutePath+"\\testcase\\integrationResult\\3_.png";
        imgProcessor iProcessor = readLocal(imagPath);
        Command test=new localSave(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void b24_test_local_integration_4() {
		String imagPath=absolutePath+"\\testcase\\test\\.tiff";
		String savePath=absolutePath+"\\testcase\\integrationResult";
		String resultPath=absolutePath+"\\testcase\\integrationResult\\4_.tiff";
        imgProcessor iProcessor = readLocal(imagPath);
        Command test=new localSave(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}
	

	
	@Test
	public void b25_test_zip_integration_1() {
		String imagPath=absolutePath+"\\testcase\\test\\testlocal.png";
		String savePath=absolutePath+"\\testcase\\integrationResult";
		String resultPath=absolutePath+"\\testcase\\integrationResult\\testlocal_3.zip";
        imgProcessor iProcessor = readZip(imagPath);
        Command test=new imagCompress(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void b26_test_zip_integration_2() {
		String imagPath=absolutePath+"\\testcase\\test\\test1.jpg";
		String savePath=absolutePath+"\\testcase\\pathEmptyResult";
		String resultPath=absolutePath+"\\testcase\\pathEmptyResult\\test1_4.zip";
        imgProcessor iProcessor = read(imagPath);
        Command test=new imagCompress(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(true, result);
	}
	
	@Test
	public void b27_test_zip_integration_3() {
		String imagPath=absolutePath+"\\testcase\\test\\testlocal.png";
		String savePath=absolutePath+"\\testcase1\\ioresult";
		String resultPath=absolutePath+"\\testcase1\\ioresult\\testlocal_5.zip";
        imgProcessor iProcessor = readZip(imagPath);
        Command test=new imagCompress(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void b28_test_zip_integration_4() {
		String imagPath=absolutePath+"\\testcase\\test\\.png";
		String savePath=absolutePath+"\\testcase\\integrationResult";
		String resultPath=absolutePath+"\\testcase\\integrationResult\\_5.zip";
        imgProcessor iProcessor = readZip(imagPath);
        Command test=new imagCompress(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void b29_test_zip_integration_5() {
		String imagPath=absolutePath+"\\testcase\\test\\.tiff";
		String savePath=absolutePath+"\\testcase\\integrationResult";
		String resultPath=absolutePath+"\\testcase\\integrationResult\\_6.zip";
        imgProcessor iProcessor = readZip(imagPath);
        Command test=new imagCompress(iProcessor,savePath);
        boolean result=testCommandTransfer(test,resultPath);
        assertEquals(false, result);
	}
	
	//integration test level 2
	private Software test_software = Software.getInstance();
	private boolean testSystem(Software test,String resultPath)
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
	public void c01_test_bmp_integration_1() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\systemResult";
		String resultPath=absolutePath+"\\testcase\\systemResult\\test_20.bmp";
        imgProcessor iProcessor = read(imagPath);
        test_software.setMain_ip(iProcessor);
        test_software.setCommand(new bmpTransfer(test_software.getMain_ip(),savePath));
        boolean result=testSystem(test_software,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void c02_test_bmp_integration_2() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\systemResult";
		String resultPath=absolutePath+"\\testcase\\systemResult\\test_21.png";
        imgProcessor iProcessor = read(imagPath);
        test_software.setMain_ip(iProcessor);
        test_software.setCommand(new bmpTransfer(test_software.getMain_ip(),savePath));
        boolean result=testSystem(test_software,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void c03_test_bmp_integration_3() {
		String imagPath=absolutePath+"\\testcase\\test\\.png";
		String savePath=absolutePath+"\\testcase\\systemResult";
		String resultPath=absolutePath+"\\testcase\\systemResult\\_22.png";
        imgProcessor iProcessor = read(imagPath);
        test_software.setMain_ip(iProcessor);
        test_software.setCommand(new bmpTransfer(test_software.getMain_ip(),savePath));
        boolean result=testSystem(test_software,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void c04_test_bmp_integration_4() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase1\\ioresult";
		String resultPath=absolutePath+"\\testcase1\\ioresult\\test_23.bmp";
        imgProcessor iProcessor = read(imagPath);
        test_software.setMain_ip(iProcessor);
        test_software.setCommand(new bmpTransfer(test_software.getMain_ip(),savePath));
        boolean result=testSystem(test_software,resultPath);
        assertEquals(false, result);
	}

	@Test
	public void c05_test_gif_integration_1() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\systemResult";
		String resultPath=absolutePath+"\\testcase\\systemResult\\test_24.gif";
        imgProcessor iProcessor = read(imagPath);
        test_software.setMain_ip(iProcessor);
        test_software.setCommand(new gifTransfer(test_software.getMain_ip(),savePath));
        boolean result=testSystem(test_software,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void c06_test_gif_integration_2() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\systemResult";
		String resultPath=absolutePath+"\\testcase\\systemResult\\test_25.png";
        imgProcessor iProcessor = read(imagPath);
        test_software.setMain_ip(iProcessor);
        test_software.setCommand(new gifTransfer(test_software.getMain_ip(),savePath));
        boolean result=testSystem(test_software,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void c07_test_gif_integration_3() {
		String imagPath=absolutePath+"\\testcase\\test\\.png";
		String savePath=absolutePath+"\\testcase\\systemResult";
		String resultPath=absolutePath+"\\testcase\\systemResult\\_26.gif";
        imgProcessor iProcessor = read(imagPath);
        test_software.setMain_ip(iProcessor);
        test_software.setCommand(new gifTransfer(test_software.getMain_ip(),savePath));
        boolean result=testSystem(test_software,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void c08_test_gif_integration_4() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase1\\ioresult";
		String resultPath=absolutePath+"\\testcase1\\ioresult\\test_27.gif";
        imgProcessor iProcessor = read(imagPath);
        test_software.setMain_ip(iProcessor);
        test_software.setCommand(new gifTransfer(test_software.getMain_ip(),savePath));
        boolean result=testSystem(test_software,resultPath);
        assertEquals(false, result);
	}

	@Test
	public void c09_test_jpg_integration_1() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\systemResult";
		String resultPath=absolutePath+"\\testcase\\systemResult\\test_28.jpg";
        imgProcessor iProcessor = read(imagPath);
        test_software.setMain_ip(iProcessor);
        test_software.setCommand(new jpgTransfer(test_software.getMain_ip(),savePath));
        boolean result=testSystem(test_software,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void c10_test_jpg_integration_2() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\systemResult";
		String resultPath=absolutePath+"\\testcase\\systemResult\\test_29.png";
        imgProcessor iProcessor = read(imagPath);
        test_software.setMain_ip(iProcessor);
        test_software.setCommand(new jpgTransfer(test_software.getMain_ip(),savePath));
        boolean result=testSystem(test_software,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void c11_test_jpg_integration_3() {
		String imagPath=absolutePath+"\\testcase\\test\\.png";
		String savePath=absolutePath+"\\testcase\\systemResult";
		String resultPath=absolutePath+"\\testcase\\systemResult\\_30.jpg";
        imgProcessor iProcessor = read(imagPath);
        test_software.setMain_ip(iProcessor);
        test_software.setCommand(new jpgTransfer(test_software.getMain_ip(),savePath));
        boolean result=testSystem(test_software,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void c12_test_jpg_integration_4() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase1\\ioresult";
		String resultPath=absolutePath+"\\testcase1\\ioresult\\test_31.jpg";
        imgProcessor iProcessor = read(imagPath);
        test_software.setMain_ip(iProcessor);
        test_software.setCommand(new jpgTransfer(test_software.getMain_ip(),savePath));
        boolean result=testSystem(test_software,resultPath);
        assertEquals(false, result);
	}

	@Test
	public void c13_test_png_integration_1() {
		String imagPath=absolutePath+"\\testcase\\test\\test1.jpg";
		String savePath=absolutePath+"\\testcase\\systemResult";
		String resultPath=absolutePath+"\\testcase\\systemResult\\test1_32.png";
        imgProcessor iProcessor = read(imagPath);
        test_software.setMain_ip(iProcessor);
        test_software.setCommand(new pngTransfer(test_software.getMain_ip(),savePath));
        boolean result=testSystem(test_software,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void c14_test_png_integration_2() {
		String imagPath=absolutePath+"\\testcase\\test\\test1.jpg";
		String savePath=absolutePath+"\\testcase\\systemResult";
		String resultPath=absolutePath+"\\testcase\\systemResult\\test1_33.jpg";
        imgProcessor iProcessor = read(imagPath);
        test_software.setMain_ip(iProcessor);
        test_software.setCommand(new pngTransfer(test_software.getMain_ip(),savePath));
        boolean result=testSystem(test_software,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void c15_test_png_integration_3() {
		String imagPath=absolutePath+"\\testcase\\test\\.jpg";
		String savePath=absolutePath+"\\testcase\\systemResult";
		String resultPath=absolutePath+"\\testcase\\systemResult\\_34.jpg";
        imgProcessor iProcessor = read(imagPath);
        test_software.setMain_ip(iProcessor);
        test_software.setCommand(new pngTransfer(test_software.getMain_ip(),savePath));
        boolean result=testSystem(test_software,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void c16_test_png_integration_4() {
		String imagPath=absolutePath+"\\testcase\\test\\test1.jpg";
		String savePath=absolutePath+"\\testcase1\\ioresult";
		String resultPath=absolutePath+"\\testcase1\\ioresult\\test1_35.jpg";
        imgProcessor iProcessor = read(imagPath);
        test_software.setMain_ip(iProcessor);
        test_software.setCommand(new pngTransfer(test_software.getMain_ip(),savePath));
        boolean result=testSystem(test_software,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void c17_test_tiff_integration_1() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\systemResult";
		String resultPath=absolutePath+"\\testcase\\systemResult\\test_36.tiff";
        imgProcessor iProcessor = read(imagPath);
        test_software.setMain_ip(iProcessor);
        test_software.setCommand(new tiffTransfer(test_software.getMain_ip(),savePath));
        boolean result=testSystem(test_software,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void c18_test_tiff_integration_2() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\systemResult";
		String resultPath=absolutePath+"\\testcase\\systemResult\\test_37.png";
        imgProcessor iProcessor = read(imagPath);
        test_software.setMain_ip(iProcessor);
        test_software.setCommand(new tiffTransfer(test_software.getMain_ip(),savePath));
        boolean result=testSystem(test_software,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void c19_test_tiff_integration_3() {
		String imagPath=absolutePath+"\\testcase\\test\\.png";
		String savePath=absolutePath+"\\testcase\\systemResult";
		String resultPath=absolutePath+"\\testcase\\systemResult\\_38.png";
        imgProcessor iProcessor = read(imagPath);
        test_software.setMain_ip(iProcessor);
        test_software.setCommand(new tiffTransfer(test_software.getMain_ip(),savePath));
        boolean result=testSystem(test_software,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void c20_test_tiff_integration_4() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase1\\ioresult";
		String resultPath=absolutePath+"\\testcase1\\ioresult\\test_37.png";
        imgProcessor iProcessor = read(imagPath);
        test_software.setMain_ip(iProcessor);
        test_software.setCommand(new tiffTransfer(test_software.getMain_ip(),savePath));
        boolean result=testSystem(test_software,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void c21_test_local_integration_1() {
		String imagPath=absolutePath+"\\testcase\\test\\testlocal.png";
		String savePath=absolutePath+"\\testcase\\systemResult";
		String resultPath=absolutePath+"\\testcase\\systemResult\\6_testlocal.png";
        imgProcessor iProcessor = readLocal(imagPath);
        test_software.setMain_ip(iProcessor);
        test_software.setCommand(new localSave(test_software.getMain_ip(),savePath));
        boolean result=testSystem(test_software,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void c22_test_local_integration_2() {
		String imagPath=absolutePath+"\\testcase\\test\\testlocal.png";
		String savePath=absolutePath+"\\testcase1\\ioresult";
		String resultPath=absolutePath+"\\testcase1\\ioresult\\7_testlocal.png";
        imgProcessor iProcessor = readLocal(imagPath);
        test_software.setMain_ip(iProcessor);
        test_software.setCommand(new localSave(test_software.getMain_ip(),savePath));
        boolean result=testSystem(test_software,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void c23_test_local_integration_3() {
		String imagPath=absolutePath+"\\testcase\\test\\.png";
		String savePath=absolutePath+"\\testcase\\systemResult";
		String resultPath=absolutePath+"\\testcase\\systemResult\\8_.png";
        imgProcessor iProcessor = readLocal(imagPath);
        test_software.setMain_ip(iProcessor);
        test_software.setCommand(new localSave(test_software.getMain_ip(),savePath));
        boolean result=testSystem(test_software,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void c24_test_local_integration_4() {
		String imagPath=absolutePath+"\\testcase\\test\\.tiff";
		String savePath=absolutePath+"\\testcase\\systemResult";
		String resultPath=absolutePath+"\\testcase\\systemResult\\9_.tiff";
        imgProcessor iProcessor = readLocal(imagPath);
        test_software.setMain_ip(iProcessor);
        test_software.setCommand(new localSave(test_software.getMain_ip(),savePath));
        boolean result=testSystem(test_software,resultPath);
        assertEquals(false, result);
	}
	

	
	@Test
	public void c25_test_zip_integration_1() {
		String imagPath=absolutePath+"\\testcase\\test\\testlocal.png";
		String savePath=absolutePath+"\\testcase\\systemResult";
		String resultPath=absolutePath+"\\testcase\\systemResult\\testlocal_8.zip";
        imgProcessor iProcessor = readZip(imagPath);
        test_software.setMain_ip(iProcessor);
        test_software.setCommand(new imagCompress(test_software.getMain_ip(),savePath));
        boolean result=testSystem(test_software,resultPath);
        assertEquals(true, result);
	}

	@Test
	public void c26_test_zip_integration_2() {
		String imagPath=absolutePath+"\\testcase\\test\\test1.jpg";
		String savePath=absolutePath+"\\testcase\\pathEmptyResult";
		String resultPath=absolutePath+"\\testcase\\pathEmptyResult\\test1_9.zip";
        imgProcessor iProcessor = read(imagPath);
        test_software.setMain_ip(iProcessor);
        test_software.setCommand(new imagCompress(test_software.getMain_ip(),savePath));
        boolean result=testSystem(test_software,resultPath);
        assertEquals(true, result);
	}
	
	@Test
	public void c27_test_zip_integration_3() {
		String imagPath=absolutePath+"\\testcase\\test\\testlocal.png";
		String savePath=absolutePath+"\\testcase1\\ioresult";
		String resultPath=absolutePath+"\\testcase1\\ioresult\\testlocal_10.zip";
        imgProcessor iProcessor = readZip(imagPath);
        test_software.setMain_ip(iProcessor);
        test_software.setCommand(new imagCompress(test_software.getMain_ip(),savePath));
        boolean result=testSystem(test_software,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void c28_test_zip_integration_4() {
		String imagPath=absolutePath+"\\testcase\\test\\.png";
		String savePath=absolutePath+"\\testcase\\systemResult";
		String resultPath=absolutePath+"\\testcase\\systemResult\\_11.zip";
        imgProcessor iProcessor = readZip(imagPath);
        test_software.setMain_ip(iProcessor);
        test_software.setCommand(new imagCompress(test_software.getMain_ip(),savePath));
        boolean result=testSystem(test_software,resultPath);
        assertEquals(false, result);
	}
	
	@Test
	public void c29_test_zip_integration_5() {
		String imagPath=absolutePath+"\\testcase\\test\\.tiff";
		String savePath=absolutePath+"\\testcase\\systemResult";
		String resultPath=absolutePath+"\\testcase\\systemResult\\_12.zip";
        imgProcessor iProcessor = readZip(imagPath);
        test_software.setMain_ip(iProcessor);
        test_software.setCommand(new imagCompress(test_software.getMain_ip(),savePath));
        boolean result=testSystem(test_software,resultPath);
        assertEquals(false, result);
	}
	
	//System test
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
	public void d01_test_jpg_system_1() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\system";
		String resultPath=absolutePath+"\\testcase\\system\\test_40.jpg";
		String [] args= {"0","common","1",imagPath,"export","1",savePath,"exist"};
        Main_cmd.main(args);
        boolean result=testSystem(resultPath);
        assertEquals(true,result);
	}
	
	@Test
	public void d02_test_jpg_system_2() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\system";
		String resultPath=absolutePath+"\\testcase\\system\\test_41.png";
		String [] args= {"0","common","1",imagPath,"export","1",savePath,"exist"};
        Main_cmd.main(args);
        boolean result=testSystem(resultPath);
        assertEquals(false,result);
	}
	
	@Test
	public void d03_test_jpg_system_3() {
		String imagPath=absolutePath+"\\testcase\\test\\.png";
		String savePath=absolutePath+"\\testcase\\system";
		String resultPath=absolutePath+"\\testcase\\system\\test_42.jpg";
		String [] args= {"0","common","1",imagPath,"export","1",savePath,"exist"};
        Main_cmd.main(args);
        boolean result=testSystem(resultPath);
        assertEquals(false,result);
	}
	
	@Test
	public void d04_test_png_system_1() {
		String imagPath=absolutePath+"\\testcase\\test\\test1.jpg";
		String savePath=absolutePath+"\\testcase\\system";
		String resultPath=absolutePath+"\\testcase\\system\\test1_43.png";
		String [] args= {"0","common","1",imagPath,"export","2",savePath,"exist"};
        Main_cmd.main(args);
        boolean result=testSystem(resultPath);
        assertEquals(true,result);
	}
	
	@Test
	public void d05_test_gif_system_1() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\system";
		String resultPath=absolutePath+"\\testcase\\system\\test_44.gif";
		String [] args= {"0","common","1",imagPath,"export","3",savePath,"exist"};
        Main_cmd.main(args);
        boolean result=testSystem(resultPath);
        assertEquals(true,result);
	}
	
	@Test
	public void d06_test_bmp_system_1() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\system";
		String resultPath=absolutePath+"\\testcase\\system\\test_45.bmp";
		String [] args= {"0","common","1",imagPath,"export","4",savePath,"exist"};
        Main_cmd.main(args);
        boolean result=testSystem(resultPath);
        assertEquals(true,result);
	}
	
	@Test
	public void d07_test_tiff_system_1() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\system";
		String resultPath=absolutePath+"\\testcase\\system\\test_46.tiff";
		String [] args= {"0","common","1",imagPath,"export","5",savePath,"exist"};
        Main_cmd.main(args);
        boolean result=testSystem(resultPath);
        assertEquals(true,result);
	}
	
	@Test
	public void d08_test_zip_system_1() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\system";
		String resultPath=absolutePath+"\\testcase\\system\\test_11.zip";
		String [] args= {"0","common","1",imagPath,"export","6",savePath,"exist"};
        Main_cmd.main(args);
        boolean result=testSystem(resultPath);
        assertEquals(true,result);
	}
	
	@Test
	public void d09_test_local_system_1() {
		String imagPath=absolutePath+"\\testcase\\test\\test.png";
		String savePath=absolutePath+"\\testcase\\system";
		String resultPath=absolutePath+"\\testcase\\system\\12_test.png";
		String [] args= {"0","common","1",imagPath,"export","7",savePath,"exist"};
        Main_cmd.main(args);
        boolean result=testSystem(resultPath);
		System.out.println("-------------------------------------------------------------------------");
        assertEquals(true,result);
	}

}
