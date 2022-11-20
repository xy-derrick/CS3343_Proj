package testing;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

import ImgUtil.ImgUtil;
import JDBCUtils.jdbc_test;
import Java.Code.Software.imgProcessor;
public class test_ImgUtil {
	private String absolutePath = System.getProperty("user.dir");
	@Test
	public void test01() throws SQLException, IOException
	{
		BufferedImage imag = null;
		String imagPath= absolutePath+"\\testcase\\test\\test1.jpg";
		imag = ImageIO.read(new File(imagPath));
		imgProcessor imgprocesser1 = new imgProcessor();
		imgprocesser1.setImg(imag);
		String result= ImgUtil.generateBase64(imag, "jpg");
		
	}
	@Test
	public void test02() throws SQLException, IOException
	{
		BufferedImage imag = null;
		String imagPath= absolutePath+"\\testcase\\test\\test1.jpg";
		imag = ImageIO.read(new File(imagPath));
		imgProcessor imgprocesser1 = new imgProcessor();
		imgprocesser1.setImg(imag);
		String result= ImgUtil.generateBase64(imag, "jpg");
		BufferedImage imag02 = null;
		imag02 = ImgUtil.GenerateImage(result);
	}
	@Test
	public void test03() throws SQLException, IOException
	{
		
		String result= null;
		BufferedImage imag02 = null;
		imag02 = ImgUtil.GenerateImage(result);
	}
	@Test
	public void test04() throws SQLException, IOException
	{
		
		BufferedImage imag = null;
		String imagPath= absolutePath+"\\testcase\\test\\test1.jpg";
		imag = ImageIO.read(new File(imagPath));
		imgProcessor imgprocesser1 = new imgProcessor();
		imgprocesser1.setImg(imag);
		boolean result= ImgUtil.saveImginDb("4", imag, "jpg");
		assertEquals(result, true);

	}
	@Test
	public void test05() throws SQLException, IOException
	{
		
		BufferedImage imag = null;
		String imagPath= absolutePath+"\\testcase\\test\\test1.jpg";
		imag = ImageIO.read(new File(imagPath));
		imgProcessor imgprocesser1 = new imgProcessor();
		imgprocesser1.setImg(imag);
		BufferedImage imag02 = null;
		imag02= ImgUtil.getImgfromDb("4","72");
	}
	@Test
	public void test06() throws SQLException, IOException
	{
		
		BufferedImage imag = null;
		String imagPath= absolutePath+"\\testcase\\test\\test1.jpg";
		imag = ImageIO.read(new File(imagPath));
		imgProcessor imgprocesser1 = new imgProcessor();
		imgprocesser1.setImg(imag);
		BufferedImage imag02 = null;
		imag02= ImgUtil.getImgfromDb("5","72");
	}
}
