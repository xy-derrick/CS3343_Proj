package Java.Code.Command.EditDecorator;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;

import Java.Code.Command.Base.Command;
import Java.Code.Command.Base.CommandCancelable;
import Java.Code.Exception.ArgsInvalidException;
import Java.Code.Software.imgProcessor;
//import Java.Gui.guiMain;

public class EditDecorator extends Command implements CommandCancelable {
	protected Command wrappee;
	private BufferedImage imgcopy;

	public EditDecorator(Command wrappee) {
		super(wrappee.getIP());
		this.wrappee = wrappee;
		this.imgcopy = copyImage(this.wrappee.getIP().getImg());
	}

	@Override
	public void execute() {
		System.out.println("filter adding!");
	}

	@Override
	public void undo() {
		imgProcessor ip = this.wrappee.getIP();
		ip.setImg(this.imgcopy);
	}

	public static BufferedImage copyImage(BufferedImage source) {
		BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
		Graphics g = b.getGraphics();
		g.drawImage(source, 0, 0, null);
		g.dispose();
		return b;
	}
}
