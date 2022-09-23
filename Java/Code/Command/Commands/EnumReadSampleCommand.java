package Code.Command.Commands;

import Code.Command.Base.Command;
import Code.Enum.Degree;
import Code.Software.imgProcessor;

public class EnumReadSampleCommand extends Command{
    Degree d = null;

    public EnumReadSampleCommand(imgProcessor receiver,Degree d) {
        super(receiver);
        this.d = d;
    }
    
    @Override
    public void execute() {
        // ignore receiver
        switch(d)
        {
            case high:
            case mid:
            case low:
        }
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        
    }
}
