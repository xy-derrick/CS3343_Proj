package Code.Command.Commands;

import Code.Command.Base.Command;
import Code.Software.imgProcessor;

public class FilterCommand extends Command{
    protected imgProcessor iProcessor;

    public FilterCommand(imgProcessor receiver) {
        super(receiver);
    }
    @Override
    public void execute() {
    	System.out.print("Adding filter!");
    }

    public void undo() {

    }
}
