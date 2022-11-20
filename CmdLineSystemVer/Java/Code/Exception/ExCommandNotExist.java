package Java.Code.Exception;

import Java.Code.Command.Batch.transCommand;

public class ExCommandNotExist extends Exception {
	transCommand sc;

	public ExCommandNotExist(transCommand c) {
		sc = c;
		String cmd = "The Command(s)" + sc.toString() + " is not in the batch commond list. " + "Please check again.";
		System.out.println(cmd);
		;
	}
}