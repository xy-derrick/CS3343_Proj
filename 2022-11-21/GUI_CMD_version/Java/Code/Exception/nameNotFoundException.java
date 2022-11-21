package Java.Code.Exception;

public class nameNotFoundException extends Exception {
	public nameNotFoundException() {
		String msg = "Can't find the imag name.";
		System.out.println(msg);
	}
}