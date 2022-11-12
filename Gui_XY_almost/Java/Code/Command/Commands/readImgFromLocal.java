package Java.Code.Command.Commands;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Java.Code.Command.Base.Command;
import Java.Code.Software.imgProcessor;

public class readImgFromLocal extends Command {
	String text = null;

	public readImgFromLocal(imgProcessor receiver, String text) {
		super(receiver);
		this.text = text;
	}

	@Override
	public void execute() {
		// ignore receiver
		System.out.println("try to read img file from " + text);
		try {
			File imgFile = new File(text);
			BufferedImage bufImage = ImageIO.read(imgFile);
			iProcessor.setImg(bufImage);
			System.out.println("successfully read img file from " + text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}
}
