package Java.Code.Command.Login;

import java.util.ArrayList;
import java.util.Date;
import java.util.TimerTask;

import Java.Code.Software.Software;
import Java.Code.Software.imgProcessor;
//import Java.Gui.guiMain;

public class autoSaveTask extends TimerTask {
	private boolean first = true;

	public void run() {
		if (first) {
//			guiMain.writeLog("Log in success and auto save enabled\n");
			first = false;
		} else {
//			guiMain.writeLog("Auto Save:\n");
			ArrayList<imgProcessor> iplist = Software.getInstance().getImgProcessorList();
			if (iplist.size() != 0) {
				for (int i = 0; i < iplist.size(); i++) {
//					guiMain.writeLog("Image " + String.valueOf(i) + " saved.\n");
					loginController.save(iplist.get(i).getImg());
				}
			}
		}
	}
}