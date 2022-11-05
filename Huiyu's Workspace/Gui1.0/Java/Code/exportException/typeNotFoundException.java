package Code.exportException;

public class typeNotFoundException extends Exception {
    public typeNotFoundException()
    {
        String msg = "Can't find imag type!";
        System.out.println(msg);
    }
}