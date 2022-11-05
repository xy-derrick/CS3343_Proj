package Code.Command.Commands.HuiyuBAI;

import java.util.ArrayList;

public class batchReader {
    
    
    static private batchReader reader = null;
    
    private batchReader(){};

    static public batchReader getInstance(){
        if(reader==null)
        {
            reader = new batchReader();
            return reader;
        }
        else
        {
            return reader;
        }
    }
    public ArrayList<Object> parseString(String str) throws ExUnbatchableCommand, ExWrongBatchType {
        String rawString = str;
        ArrayList args = new ArrayList<>();
        String[] batchStrings = rawString.split(">>");
        String[] argLists = batchStrings[1].trim().split(",");
        String batchType = batchStrings[0].trim();

        if(batchType.equals("img")){//判断是imgProcessor
            for (String imgOrder:argLists){
                args.add(Integer.parseInt(imgOrder.trim()));

            }
        }

        else if (batchType.equals("cmd")){//判断是Command
            for (String cmd:argLists){
                cmd = cmd.trim();
                String[] cmdParts = cmd.split(" ");

                String cmdType = cmdParts[0].trim();
                Integer cmdNum = Integer.parseInt(cmdParts[1].trim());

                if(!batchableCommandDict.getInstance().checkBatchable(cmdType,cmdNum))  
                throw new ExUnbatchableCommand(cmdType,cmdNum);
                
                int temp = cmdParts[0].length()+cmdParts[1].length()+2;
                if(temp>=cmd.length()) 
                args.add(new stringCommand(cmdType, cmdNum, null));
                else
                args.add(new stringCommand(cmdType, cmdNum, cmd.substring(temp))); 
                
            }
            
        //没有正确输入img<< 或 cmd<<
        } 
        else{ 
            throw new ExWrongBatchType();
        } 
        return args;

    }       
};
