package Java.Code.Command.Commands.Common;

import java.util.ArrayList;
import Java.Code.Command.Base.Command;
import Java.Code.Command.Base.CommandCancelable;
import Java.Code.Command.Base.CommandNoncancelable_gui;
import Java.Code.Software.imgProcessor;

public class existSoftware extends Command implements CommandCancelable  {

	public existSoftware(imgProcessor receiver) {
		super(receiver);
	}

	public existSoftware(imgProcessor receiver, ArrayList<Object> args) {
		super(receiver);
	}

	@Override
	public void execute() {
		System.exit(1);
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		System.out.println("nothing to undo");
	}
}
