package Code.Command.Commands.HuiyuBAI;

import java.util.ArrayList;

import Code.Command.Base.Command;
import Code.Command.Base.CommandNoncancelabe;
import Code.Software.Software;
import Code.Software.imgProcessor;
import Code.Software.ipState;

public class batchExecute extends Command implements CommandNoncancelabe{
    batchProcessor bp;

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
        bp.reset();
        System.out.println("BatchExecute runs successfully.");
        
    }
    @Override
    public void undo() {
      
 
    }



}
