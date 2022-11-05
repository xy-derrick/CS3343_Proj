package Code.Command.Commands.HuiyuBAI;

public class ExUnbatchableCommand extends Exception{

    private String cmdType;
    private Integer cmdNum;
    public ExUnbatchableCommand(String cmdType, Integer cmdNum) {
        this.cmdNum = cmdNum;
        this.cmdType = cmdType;
    }
    @Override
    public String getMessage() {
        // TODO Auto-generated method stub
        return "The command " +cmdType+" "+cmdNum+" can not used in batch.";
    }
}
