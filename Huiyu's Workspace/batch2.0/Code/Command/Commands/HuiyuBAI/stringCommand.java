package Code.Command.Commands.HuiyuBAI;

public class stringCommand {
    private String cmdType;
    private Integer cmdNum;
    private String parameters;
    public stringCommand(String cmdType, Integer cmdNum, String parameters) {
        this.cmdType = cmdType.trim();
        this.cmdNum = cmdNum;
        if(parameters == null) this.parameters= "";
        else this.parameters = parameters.trim();
        System.out.printf("New stringCommand add in: "+cmdType+" %d "+parameters,this.cmdNum);
    }
    public String getCmdType() {
        return cmdType;
    }
    public Integer getCmdNum() {
        return cmdNum;
    }
    public String getParameters() {
        return parameters;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        else if(obj instanceof stringCommand){
            stringCommand theObj = (stringCommand) obj;
            if( cmdType.equals(theObj.cmdType)&& cmdNum.equals(theObj.cmdNum)&& parameters.equals(theObj.parameters)){
                return true;
        }
        }
        return false;
    }
}
