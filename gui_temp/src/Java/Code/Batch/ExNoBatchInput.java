package Java.Code.Batch;

public class ExNoBatchInput extends Exception{
    public ExNoBatchInput(){
        String msg;
        msg = "Please input imgs or commands for batch command!";
        System.out.println(msg);
    }

}
