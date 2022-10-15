package Code.eportException;

public class typeNotFoundException extends Exception {
    public typeNotFoundException()
    {
        String msg = "Can't find imag type. Please check and try again!";
        System.out.println(msg);
    }
}