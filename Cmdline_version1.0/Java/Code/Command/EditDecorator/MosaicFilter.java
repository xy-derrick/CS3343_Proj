package Java.Code.Command.EditDecorator;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Java.Code.Command.Commands.EditCommand;
import Java.Code.Exception.ArgsInvalidException;
import Java.Code.Software.imgProcessor;
//import Java.Gui.guiMain;

public class MosaicFilter extends EditDecorator {
	private int mosaicSize = 1;

	public MosaicFilter(EditCommand wrappee, ArrayList<Object> args) {
		super(wrappee);
		this.mosaicSize = (Integer) args.get(0);
	}

	public MosaicFilter(EditCommand wrappee, Integer size) throws ArgsInvalidException {
		super(wrappee);
	}

	private BufferedImage filter(BufferedImage img) {
		int pixel = img.getRGB(0, 0);
		for (int i = 0; i < img.getHeight(); i++) {
			for (int j = 0; j < img.getWidth(); j++) {
				if (i % this.mosaicSize == 0 || j % this.mosaicSize == 0) {
					pixel = img.getRGB(j, i);
				}
				img.setRGB(j, i, pixel);
			}
		}
		return img;
	}

	@Override
	public void execute() {
		super.wrappee.execute();
		imgProcessor ip = super.wrappee.getIP();
		BufferedImage img = ip.getImg();
		ip.setImg(filter(img));
	}

	@Override
	public void undo() {
		super.undo();
//		guiMain.writeLog("Mosaic filter removed.");
	}
}
