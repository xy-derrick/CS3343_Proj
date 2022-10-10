package Code.Command.Commands.HuiyuBAI;

import java.util.ArrayList;

import Code.Command.Base.Command;
import Code.Software.Software;
import Code.Software.imgProcessor;
import Code.Software.ipState;

public class batchExecute extends Command{
    batchProcessor bp;
    //不确定是否符合总体框架，想去掉ob

    public batchExecute(imgProcessor ip) {
        super(ip);
        bp = batchProcessor.getInstance();
    }
    public batchExecute(imgProcessor ip,ArrayList<?> obs) {
        super(ip);
        bp = batchProcessor.getInstance();
    }
    @Override
    public void execute() {


        bp.notifyAlls();
        System.out.println("BatchExecute runs successfully.");

        
    }
    @Override
    public void undo() {
        bp.unotifyAlls();
        System.out.println("BatchExecute undo successfully.");
 
    }



}
