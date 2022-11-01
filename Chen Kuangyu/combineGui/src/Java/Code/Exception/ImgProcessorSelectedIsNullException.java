package Code.Exception;

public class ImgProcessorSelectedIsNullException extends Exception {
    public ImgProcessorSelectedIsNullException() {
        String msg = "The current image processor is not exsit, please open/select a valid processor";
        System.out.println(msg);
    }
}
