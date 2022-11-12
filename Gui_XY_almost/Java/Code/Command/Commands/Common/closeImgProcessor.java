package Java.Code.Command.Commands.Common;

import java.util.ArrayList;
import Java.Code.Command.Base.Command;
import Java.Code.Exception.OrderImageNotMovedException;
import Java.Code.Software.Software;
import Java.Code.Software.imgProcessor;
import Java.Code.Software.ipState;

public class closeImgProcessor extends Command {
	ipState state_last = null;
	ipState state_curr = null;

	public closeImgProcessor(imgProcessor receiver) {
		super(receiver);
	}

	public closeImgProcessor(imgProcessor receiver, ArrayList<Object> args) {
		super(receiver);
	}

	@Override
	public void execute() {
		// ignore receiver
		try {
			state_last = Software.getInstance().getState();
			if (state_curr == null) {
				Software.getInstance().getImgProcessorList().remove(Software.getInstance().getMain_ip());
				Software.getInstance().setMain_ip(null);
				System.out.println("image processor now changed");

				state_curr = Software.getInstance().getState();
			} else {
				Software.getInstance().setState(state_curr);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		try {
			Software.getInstance().setState(state_last);
			System.out.println("close commond undo successfully !");
		} catch (Exception e) {
			System.out.println("close commond undo failed !");
		}
	}
}
