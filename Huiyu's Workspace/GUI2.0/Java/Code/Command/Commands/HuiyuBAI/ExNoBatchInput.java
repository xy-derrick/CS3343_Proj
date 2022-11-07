package Code.Command.Commands.HuiyuBAI;

public class ExNoBatchInput extends Exception{
    public ExNoBatchInput(){
        String msg;
        msg = "Please input imgs or commands for batch command!";
        System.out.println(msg);
    }

}
