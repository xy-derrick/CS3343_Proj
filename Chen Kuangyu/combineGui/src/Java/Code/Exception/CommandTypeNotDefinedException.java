package Code.Exception;

public class CommandTypeNotDefinedException extends Exception {
    public CommandTypeNotDefinedException(String commandType) {
        String msg = "There is no such command type as '" + commandType + "'";
        System.out.println(msg);
    }
}
