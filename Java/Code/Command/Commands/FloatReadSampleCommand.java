package Code.Command.Commands;

import Code.Command.Base.Command;
import Code.Software.imgProcessor;

public class FloatReadSampleCommand extends Command{
    Float value = null;

    public FloatReadSampleCommand(imgProcessor receiver,Float value) {
        super(receiver);
        this.value = value;
    }
    
    @Override
    public void execute() {
        // ignore receiver
        System.out.println("Do some other work in AnotherCommand");
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        
    }
}
