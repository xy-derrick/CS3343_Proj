package Java.Code.Command.Commands.Common;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Java.Code.Command.Base.Command;
import Java.Code.Command.Base.CommandNoncancelabe;
import Java.Code.Software.Software;
import Java.Code.Software.imgProcessor;

public class showImgInfo extends Command implements CommandNoncancelabe {

	public showImgInfo(imgProcessor receiver) {
		super(receiver);
	}

	public showImgInfo(imgProcessor receiver, ArrayList<Object> args) {
		super(receiver);
	}

	@Override
	public void execute() {
		// example for reading info from img
		BufferedImage img = iProcessor.getImg();

		System.out.println("Img infomation is as following:    \n" + "id:"
				+ Software.getInstance().getImgProcessorList().indexOf(iProcessor) + "\n"
				+ "height: 100px    width:   100px    \n" + "light:  30degree                   \n"
				+ "filter applied: [...,...,...,...]   \n");
		// 1st default info-- 2nd self-defined info --- 3rd all the filter user applied
		// info ----
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		System.out.println("nothing to undo");
	}
}
