package Code.Command.Commands.HuiyuBAI;
import java.util.ArrayList;

public class batchableCommandDict {
    private static ArrayList<stringCommand> batchableList ;
    private static batchableCommandDict instance = new batchableCommandDict();
    private batchableCommandDict(){
        batchableList = new ArrayList<stringCommand>();
        batchableList.add(new stringCommand("common",1,null));
        batchableList.add(new stringCommand("common",5,null));
        batchableList.add(new stringCommand("common",8,null));
        batchableList.add(new stringCommand("common",10,null));

        
    }
    public static batchableCommandDict getInstance(){
        return instance;
    }

    public Boolean checkBatchable(String cmdType,Integer cmdNum){
       if( batchableList.indexOf(new stringCommand(cmdType, cmdNum, null))==-1)
            return false;
        return true;
    }
}
