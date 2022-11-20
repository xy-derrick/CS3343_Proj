package Java.Code.Command.Commands.Common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import Java.Code.Command.Base.Command;
import Java.Code.Command.Base.CommandCancelable;
import Java.Code.Command.Base.CommandNoncancelable_gui;
import Java.Code.Command.EditDecorator.EditDecorator;
import Java.Code.Software.imgProcessor;
import Java.Code.Software.Software;
import Java.Code.Software.ipState;

public class createCopy extends Command implements CommandCancelable,CommandNoncancelable_gui  {
	BufferedImage ori = null;
	ipState state_last = null;
	ipState state_curr = null;
	String name=null;

	public createCopy(imgProcessor receiver, BufferedImage ori, String name) {
		super(receiver);
		this.ori = EditDecorator.copyImage(ori);
	}

	@Override
	public void execute() {
		// ignore receiver
		state_last = Software.getInstance().getState();
		// first
		if (state_curr == null) {
			iProcessor = new imgProcessor(name);
			iProcessor.setImg(this.ori);
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
