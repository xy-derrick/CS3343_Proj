package Java.Code.Command.Commands.Common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import Java.Code.Command.Base.Command;
import Java.Code.Command.Base.CommandCancelable;
import Java.Code.Command.Base.CommandNoncancelable_gui;
import Java.Code.Software.imgProcessor;
import Java.Code.Software.Software;
import Java.Code.Software.ipState;

public class readFromBuffer extends Command implements CommandCancelable {
	String text = null;
	String name = null;
	BufferedImage img = null;
	ipState state_last = null;
	ipState state_curr = null;

	public readFromBuffer(imgProcessor receiver, BufferedImage img, String name) {
		super(receiver);
		this.img = img;
		this.name = name;
	}

	public readFromBuffer(imgProcessor receiver, ArrayList<Object> args) {
		super(receiver);
		this.text = (String) args.get(0);
	}

	@Override
	public void execute() {
		state_last = Software.getInstance().getState();
		// first
		if (state_curr == null) {
			iProcessor = new imgProcessor(this.name);
			iProcessor.setImg(this.img);
			Software.getInstance().setMain_ip(iProcessor);
			state_curr = Software.getInstance().getState();
		} else {
			Software.getInstance().setState(state_curr);
		}
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		try {
			Software.getInstance().setState(state_last);
			System.out.println("open commond undo successfully !");
		} catch (Exception e) {
			System.out.println("open commond undo failed !");
		}
	}
}
