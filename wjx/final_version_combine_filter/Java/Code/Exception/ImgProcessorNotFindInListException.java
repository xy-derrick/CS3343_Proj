package Code.Exception;

public class ImgProcessorNotFindInListException extends Exception{
    public ImgProcessorNotFindInListException(int index)
    {
        String msg = "The "+index+"-nt image processor is not valid/exsit in processor list !";
        System.out.println(msg);
    }
}