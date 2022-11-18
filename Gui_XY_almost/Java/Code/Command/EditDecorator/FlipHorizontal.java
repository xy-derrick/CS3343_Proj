package Java.Code.Command.EditDecorator;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Java.Code.Command.Commands.EditCommand;
import Java.Code.Software.imgProcessor;
import Java.Gui.guiMain;

public class FlipHorizontal extends EditDecorator {
	protected BufferedImage imgcopy;

	public FlipHorizontal(EditCommand wrappee) {
		super(wrappee);

	}

	public FlipHorizontal(EditCommand wrappee, ArrayList<Object> args) {
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
				nimg.setRGB(img.getWidth() - i - 1, j, img.getRGB(i, j));
		ip.setImg(nimg);

	}

	@Override
	public void undo() {
		super.undo();
		guiMain.writeLog("Filp horizaontally has been removed.");
	}

}
