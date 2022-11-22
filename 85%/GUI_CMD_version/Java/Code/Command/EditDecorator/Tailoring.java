package Java.Code.Command.EditDecorator;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Java.Code.Command.Commands.EditCommand;
import Java.Code.Exception.ArgsInvalidException;
import Java.Code.Software.imgProcessor;
//import Java.Gui.guiMain;

public class Tailoring extends EditDecorator {
	protected BufferedImage imgcopy;
	private int x1, y1, x2, y2;

	public Tailoring(EditCommand wrappee, ArrayList<Object> args) throws ArgsInvalidException {
		super(wrappee);
		imgProcessor ip = super.wrappee.getIP();
		BufferedImage img = ip.getImg();
		this.x1 = (int) args.get(0);
		this.y1 = (int) args.get(1);
		this.x2 = (int) args.get(2);
		this.y2 = (int) args.get(3);

		if ((this.x1 > img.getWidth()) || (this.x2 > img.getWidth())) {
			throw new ArgsInvalidException("The input x cannot be larger than the width of the picture.");
		}
		if ((this.y1 > img.getHeight()) || (this.y2 > img.getHeight())) {
			throw new ArgsInvalidException("The input y cannot be larger than the height of the picture.");
		}
		if ((this.x1 < 0) || (this.x2 < 0) || (this.y1 < 0) || (this.y2 < 0)) {
			throw new ArgsInvalidException("The entered coordinate value cannot be negative!");
		}
		if ((this.x1 == this.x2) || (this.y1 == this.y2)) {
			throw new ArgsInvalidException("The two x cannot be equal, and the two y cannot be equal.");
		}

	}

	public Tailoring(EditCommand wrappee, Integer x1, Integer y1, Integer x2, Integer y2) {
		super(wrappee);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	@Override
	public void execute() {
		super.wrappee.execute();
		imgProcessor ip = super.wrappee.getIP();
		BufferedImage img = ip.getImg();
		ip.setImg(img.getSubimage(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2)));
	}

	@Override
	public void undo() {
		super.undo();
//		guiMain.writeLog("Tailoring recoverd.");
	}
}
