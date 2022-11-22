package Java.Code.Command.Batch;

import java.util.ArrayList;

public class batchableCommandDict {
    private static ArrayList<stringCommand> batchableList ;
    private static batchableCommandDict instance = new batchableCommandDict();
    
    private batchableCommandDict(){
        batchableList = new ArrayList<stringCommand>();
//        batchableList.add(new stringCommand("common",1,null));
//        batchableList.add(new stringCommand("common",5,null));
//        batchableList.add(new stringCommand("common",8,null));
//        batchableList.add(new stringCommand("common",7,null));

        batchableList.add(new stringCommand("edit",1,null));
        batchableList.add(new stringCommand("edit",2,null));
        batchableList.add(new stringCommand("edit",3,null));
        batchableList.add(new stringCommand("edit",4,null));
        batchableList.add(new stringCommand("edit",5,null));
        batchableList.add(new stringCommand("edit",6,null));
        batchableList.add(new stringCommand("edit",7,null));

        batchableList.add(new stringCommand("filter",1,null));
        batchableList.add(new stringCommand("filter",2,null));
        batchableList.add(new stringCommand("filter",3,null));
        batchableList.add(new stringCommand("filter",4,null));
        batchableList.add(new stringCommand("filter",5,null));
        batchableList.add(new stringCommand("filter",6,null));
        batchableList.add(new stringCommand("filter",7,null));
        
        batchableList.add(new stringCommand("export",1,null));
        batchableList.add(new stringCommand("export",2,null));
        batchableList.add(new stringCommand("export",3,null));
        batchableList.add(new stringCommand("export",4,null));
        batchableList.add(new stringCommand("export",5,null));
        batchableList.add(new stringCommand("export",6,null));
        batchableList.add(new stringCommand("export",7,null));
    }
    
    public static batchableCommandDict getInstance(){
        return instance;
    }

    public Boolean checkBatchable(String cmdType,Integer cmdNum){
       if( batchableList.indexOf(new stringCommand(cmdType, cmdNum, null))==-1)
            return false;
        return true;
    }

	public String getMethodName() {
		// TODO Auto-generated method stub
		return null;
	}
}