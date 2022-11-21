package Java.Code.Command.EditDecorator;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Java.Code.Command.Commands.EditCommand;
import Java.Code.Software.imgProcessor;
import Java.Gui.guiMain;

public class Rotate180Degrees extends EditDecorator {
	protected BufferedImage imgcopy;

	public Rotate180Degrees(EditCommand wrappee) {
		super(wrappee);

	}

	public Rotate180Degrees(EditCommand wrappee, ArrayList<Object> args) {
		super(wrappee);

	}

	@Override
	public void execute() {
		super.wrappee.execute();
		imgProcessor ip = this.wrappee.getIP();
		BufferedImage img = ip.getImg();
		BufferedImage nimg = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
		for (int i = 0; i < img.getWidth(); i++)
			for (int j = 0; j < img.getHeight(); j++)
				nimg.setRGB(img.getWidth() - i - 1, img.getHeight() - j - 1, img.getRGB(i, j));
		ip.setImg(nimg);

	}

	@Override
	public void undo() {
		super.undo();
		guiMain.writeLog("180 degree rotation removed.");
	}

}
