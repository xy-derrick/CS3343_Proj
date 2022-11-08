package exportTest;

import Code.Command.Commands.Export.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import Code.Software.imgProcessor;
import Code.exportException.nameNotFoundException;
import Code.exportException.typeNotFoundException;

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
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1test.bmp";
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
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1test.png";
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
	public void test_bmp_3() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1test.bmp";
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
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1test.gif";
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
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1test.png";
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
	public void test_gif_3() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1test.gif";
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
	public void test_jpg_1() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1test.jpg";
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
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1test.png";
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
	public void test_jpg_3() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1test.jpg";
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
	public void test_png_1() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test1.jpg";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1test1.png";
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
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test1.jpg";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1test1.jpg";
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
	public void test_png_3() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\.jpg";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1test1.png";
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
	public void test_tiff_1() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1test.tiff";
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
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\test.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1test.png";
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
	public void test_tiff_3() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1.tiff";
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
	public void test_local_1() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\testlocal.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\1testlocal.png";
        imgProcessor iProcessor = read(imagPath);
        localSave test=new localSave(iProcessor,savePath);
        boolean result=true;
        try {
        	test.save();
		} catch (nameNotFoundException e) {
			result=false;
		} catch (typeNotFoundException e) {
			// TODO Auto-generated catch block
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
	public void test_local_2() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\testlocal.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result1";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\2testlocal.png";
        imgProcessor iProcessor = read(imagPath);
        localSave test=new localSave(iProcessor,savePath);
        boolean result=true;
        try {
        	test.save();
		} catch (nameNotFoundException e) {
			result=false;
		} catch (typeNotFoundException e) {
			// TODO Auto-generated catch block
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
	public void test_zip_1() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\testlocal.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\testlocal2.zip";
        imgProcessor iProcessor = read(imagPath);
        imagCompress test=new imagCompress(iProcessor,savePath);
        boolean result=true;
        try {
        	test.compress();
		} catch (nameNotFoundException e) {
			result=false;
		} catch (typeNotFoundException e) {
			// TODO Auto-generated catch block
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
	public void test_zip_2() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\testlocal.png";
		String savePath="C:\\Users\\aenkychen\\Desktop\\testExport\\result1";
		String resultPath="C:\\Users\\aenkychen\\Desktop\\testExport\\result\\testlocal3.zip";
        imgProcessor iProcessor = read(imagPath);
        imagCompress test=new imagCompress(iProcessor,savePath);
        boolean result=true;
        try {
        	test.compress();
		} catch (nameNotFoundException e) {
			result=false;
		} catch (typeNotFoundException e) {
			// TODO Auto-generated catch block
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
	public void test_cloud_1() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\testlocal.png";
        imgProcessor iProcessor = read(imagPath);
        cloudSave test=new cloudSave(iProcessor,"2");
        boolean result=true;
        try {
        	result=test.save();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			result=false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result=false;
		}
        assertEquals(true, result);
	}

	@Test
	public void test_cloud_2() {
		String imagPath="C:\\Users\\aenkychen\\Desktop\\testExport\\test\\testlocal.png";
        imgProcessor iProcessor = read(imagPath);
        cloudSave test=new cloudSave(iProcessor,"2");
        boolean result=true;
        try {
        	result=test.save();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			result=false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result=false;
		}
        assertEquals(false, result);
	}

}
