package Code.Command.Commands;

import Code.Command.Base.Command;
import Code.Software.imgProcessor;

public class StringReadSampleCommand extends Command{
    String text = null;

    public StringReadSampleCommand(imgProcessor receiver,String text) {
        super(receiver);
        this.text = text;
    }
    
    @Override
    public void execute() {
        // ignore receiver
        System.out.println(text);
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        
    }
}
