package Code.eportException;

public class nameNotFoundException extends Exception {
    public nameNotFoundException()
    {
        String msg = "Can't find imag name. Please rename your imag file and try again";
        System.out.println(msg);
    }
}