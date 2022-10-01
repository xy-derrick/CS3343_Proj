package Code.Command.Commands.Export;


import Code.Command.Base.Command;
import Code.Software.imgProcessor;

public abstract class typeTransfer extends Command{


    public typeTransfer(imgProcessor receiver) {
        super(receiver);
    }
    
    /*
     * 总共为5种格式互相转化png，jpg，tiff，gif，bmp
     */
    public abstract void transfer();


    
    @Override
    public void execute() {
        // ignore receiver
 
        transfer();
    
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        
    }
}
