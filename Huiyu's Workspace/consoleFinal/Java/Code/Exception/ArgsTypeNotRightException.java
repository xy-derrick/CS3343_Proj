package Code.Exception;

public class ArgsTypeNotRightException extends Exception{
    public ArgsTypeNotRightException()
    {
        String msg = "Your input args is not match the args in need !";
        System.out.println(msg);
    }
}
