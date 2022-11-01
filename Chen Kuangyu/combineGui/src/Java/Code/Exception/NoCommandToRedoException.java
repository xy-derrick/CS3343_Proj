package Code.Exception;

public class NoCommandToRedoException extends Exception {
    public NoCommandToRedoException() {
        String msg = "There is no command to redo !";
        System.out.println(msg);
    }
}
