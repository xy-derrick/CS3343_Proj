package Code.Command.Commands.HuiyuBAI;

import java.util.ArrayList;

import Code.Command.Base.Command;
import Code.Command.Base.CommandNoncancelabe;
import Code.Software.imgProcessor;

public class batchPreview extends Command implements CommandNoncancelabe{
    private batchProcessor bp = batchProcessor.getInstance();
    public batchPreview(imgProcessor receiver) {
        super(receiver);
        //TODO Auto-generated constructor stub
    }
    
    public batchPreview(imgProcessor receiver,ArrayList<Object> args) {
        super(receiver);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void execute(){
        // TODO Auto-generated method stub
        bp.preview();
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        
    }
    
}
