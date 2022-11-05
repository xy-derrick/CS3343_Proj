package Code.Exception;



public class ArgsInvalidException extends BaseException {

    public ArgsInvalidException(String msg)
    {
        super(msg);
        System.out.println(msg);
    }
}
