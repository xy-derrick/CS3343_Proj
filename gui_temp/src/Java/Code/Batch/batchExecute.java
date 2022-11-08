package Java.Code.Batch;

import java.util.ArrayList;

import Java.Code.Command.Base.Command;
import Java.Code.Command.Base.CommandNoncancelabe;
import Java.Code.Software.imgProcessor;

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
        try {
            if(bp.getCmdSize()==0) throw new ExLackBatchElement();
            bp.notifyAlls();
            bp.reset();
            System.out.println("BatchExecute runs successfully.");
        
        } catch (Exception e) {
        }
        
    }
    @Override
    public void undo() {
    }
}
