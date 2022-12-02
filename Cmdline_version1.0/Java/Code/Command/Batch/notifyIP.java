package Java.Code.Command.Batch;

import java.util.ArrayList;
import Java.Code.Exception.ArgsInvalidException;
import Java.Code.Software.Software;
import Java.Code.Software.imgProcessor;

public class notifyIP extends imgProcessor implements Observer {

	public notifyIP(imgProcessor ip) {
		super(0);
		setImg(ip.getImg());
		setPath(ip.getPath());
		setName(ip.getName());
		
	}

	@Override
	public void update() throws ArgsInvalidException {
		ArrayList<transCommand> cmdBuffer = batchProcessor.getInstance().getCmdBuffer();
		Software mainSoftware = Software.getInstance();
		for (transCommand i : cmdBuffer) {
			// InputStream ps = System.in;
			// System.setIn(new ByteArrayInputStream(i.getParameters().getBytes()));
			// Main.switchCommand(i.getCmdType(),i.getCmdNum());
			// System.setIn(ps);

			mainSoftware.setCommand(i.transfer(this));

			mainSoftware.execute();

		}

	}

	public void previewDisplay(ArrayList<transCommand> temp) throws ArgsInvalidException {
		imgProcessor mainIp = Software.getInstance().getMain_ip();

		Software.getInstance().setMain_ip(this);

		for (transCommand i : temp) {
			Software mainSoftware = Software.getInstance();
			mainSoftware.setCommand(i.transfer(this));
			mainSoftware.execute();
		}
		//

		//
		Software.getInstance().setMain_ip(mainIp);

	}

	// @Override
	// public boolean equals(Object obj) {
	// // TODO Auto-generated method stub
	// if(obj == this) return true;
	// if(obj instanceof notifyIP) {
	// notifyIP theObj = (notifyIP)obj;
	// if (theObj.getImg() == this.getImg()) return true;
	// }
	// return false;
	// }

}