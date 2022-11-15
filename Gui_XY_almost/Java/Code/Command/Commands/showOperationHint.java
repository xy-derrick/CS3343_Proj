package Java.Code.Command.Commands;

import Java.Code.Command.Base.Command;
import Java.Code.Command.Base.CommandNoncancelable_gui;
import Java.Code.Software.Software;
import Java.Code.Software.imgProcessor;

public class showOperationHint extends Command implements CommandNoncancelable_gui {

	public showOperationHint(imgProcessor receiver) {
		super(receiver);
	}

	@Override
	public void execute() {
		String ipList = "";
		String ipMain = "";
		if (Software.getInstance().getImgProcessorList() != null) {
			for (var ip : Software.getInstance().getImgProcessorList()) {
				int id = Software.getInstance().getImgProcessorList().indexOf(ip);
				boolean hasImg = (ip.getImg() != null);
				if (ipList == null) {
					ipList = " id-" + id + ":" + (hasImg ? "Image " : "None ");
				} else {
					ipList += " id-" + id + ":" + (hasImg ? "Image " : "None ");
				}
			}
			;
		}
		;

		ipMain = (Software.getInstance().getMain_ip() == null) ? ""
				: ("id-" + Software.getInstance().getImgProcessorList().indexOf(Software.getInstance().getMain_ip())
						+ " processor");

		System.out.println("\nimage processor list : [" + ipList + "]\n" + "selected image processor : [" + ipMain
				+ "]\n" + "undo commond list : [" + Software.getInstance().getUndoCommand().size()
				+ "]  redo commond list : [" + Software.getInstance().getRedoCommand().size() + "]\n"
//                            "common: 1.open 2.change 3.undo 4.redo 5.close 6.closeAll 7.display 8.info 9.exist\n"+
//                            "edit: 1.Flip Horizontal 2.Flip Vertical 3.Rotate 90 Degrees Clockwise 4.Rotate 90 Degrees Counterclockwise\n"+
//                            "5.Rotate 180 Degrees 6.Tailoring 7.Zoom\n"+
//                            "export:\n"+
//                            "filter: 1.Gary 2.ContrastChange 3.Vintage 4.Combine 5.Mosaic 6.Paint 7.border\n"+
//                            "Command format should be 'title +(space) + number', like 'common 1' \n\n"
		);
	}

}
