package Java.Code.Command.EditDecorator;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Java.Code.Command.Commands.EditCommand;
import Java.Code.Exception.ArgsInvalidException;
import Java.Code.Software.imgProcessor;
//import Java.Gui.guiMain;

public class Zoom extends EditDecorator {
	private float targetWidth;

	public Zoom(EditCommand wrappee, ArrayList<Object> args) throws ArgsInvalidException {
		super(wrappee);
		this.targetWidth = (Float) args.get(0);
		if ((targetWidth > 10) || (targetWidth <= 0)) {
			throw new ArgsInvalidException("TargetWidth must be (0,10].");
		}
	}

	public Zoom(EditCommand wrappee, Float targetWidth) throws ArgsInvalidException {
		super(wrappee);
		this.targetWidth = targetWidth;
		if ((targetWidth > 10) || (targetWidth <= 0)) {
			throw new ArgsInvalidException("TargetWidth must be (0,10].");
		}
	}

	@Override
	public void execute() {
		super.wrappee.execute();
		imgProcessor ip = super.wrappee.getIP();
		BufferedImage img = ip.getImg();
		int toWidth = (int) (targetWidth * img.getWidth());
		int toHeight = (int) (targetWidth * img.getHeight());
		BufferedImage nimg;
		nimg = new BufferedImage(toWidth, toHeight, BufferedImage.TYPE_INT_RGB);
		nimg.getGraphics().drawImage(img.getScaledInstance(toWidth, toHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
		ip.setImg(nimg);
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		super.undo();
//		guiMain.writeLog("Zoom recovered.");
	}

}
