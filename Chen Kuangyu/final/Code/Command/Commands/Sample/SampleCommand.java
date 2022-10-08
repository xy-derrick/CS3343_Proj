package Code.Command.Commands.Sample;

import Code.Command.Base.Command;
import Code.Software.imgProcessor;

public class SampleCommand extends Command{
    public SampleCommand(imgProcessor receiver) {
        super(receiver);
    }
    
    private void doSomeOtherWork() {
        System.out.println("Do some other work in AnotherCommand");
    }
    
    @Override
    public void execute() {
        // ignore receiver
        doSomeOtherWork();
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        
    }
}
