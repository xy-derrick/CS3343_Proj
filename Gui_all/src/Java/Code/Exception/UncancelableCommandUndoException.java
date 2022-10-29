package Java.Code.Exception;

public class UncancelableCommandUndoException extends Exception {
    public UncancelableCommandUndoException(Object command)
    {
        String msg = "Command "+command.getClass().getName()+"is uncancelable command !";
        System.out.println(msg);
    }
}