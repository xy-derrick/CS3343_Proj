package Java.Code.Exception;

public class CommandIndexOverLimit extends Exception {
	public CommandIndexOverLimit(int input) {
		String msg = "Your input " + input + " is over the limit";
		System.out.println(msg);
	}
}
