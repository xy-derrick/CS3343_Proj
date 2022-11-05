package Code.Command.Commands.HuiyuBAI;

public class ExStringCommandNotExist extends Exception{
    stringCommand sc;
    public ExStringCommandNotExist(stringCommand c) {
        sc = c;
    }
    @Override
    public String getMessage() {

        String cmd = "The Command" + sc.toString()+" is not in the batch commond list. "+
        //"The command(s) in batch list are "+ batchProcessor.getInstance().cmdBufferToStr()+"\n"+
        "Please check again.";
        return cmd;
    }
}
