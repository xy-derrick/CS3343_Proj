package Code.Command.Commands.Sample;

import Code.Command.Base.Command;
import Code.Enum.Action;
import Code.Software.imgProcessor;

public class FloatReadSampleCommand extends Command{
    Float value = null;
    Action a = null;

    public FloatReadSampleCommand(imgProcessor receiver,Float value,Action a) {
        super(receiver);
        this.value = value;
        this.a = a;
    }
    
    @Override
    public void execute() {
        
        // iProcessor.setLightDegree(value)
        switch(a)
        {
            case up:
                upLightDegree();
            case down:
                downLightDegree();
        }
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        
    }

    //-------------------------self-----------------------------------------
    public void setLightDegree()
    {

    }

    public void upLightDegree()
    {
        
    }
    public void downLightDegree()
    {
        
    }
}
