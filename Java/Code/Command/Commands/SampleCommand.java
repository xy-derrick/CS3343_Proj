package Code.Command.Commands;

import Code.Command.Base.Command;
import Code.Software.Software;

public class SampleCommand extends Command{
    public SampleCommand(Software receiver) {
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
