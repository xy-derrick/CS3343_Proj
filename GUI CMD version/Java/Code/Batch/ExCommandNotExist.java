package Java.Code.Batch;

public class ExCommandNotExist extends Exception {
	transCommand sc;

	public ExCommandNotExist(transCommand c) {
		sc = c;
		String cmd = "The Command(s)" + sc.toString() + " is not in the batch commond list. " + "Please check again.";
		System.out.println(cmd);
		;
	}
}
