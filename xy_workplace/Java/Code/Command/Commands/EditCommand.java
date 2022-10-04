package Java.Code.Command.Commands;

import Code.Command.Base.Command;
import Code.Software.imgProcessor;

public class EditCommand extends Command{
    protected imgProcessor iProcessor;

    public EditCommand(imgProcessor receiver) {
        super(receiver);
    }
    @Override
    public void execute() {
    	System.out.println("Adding filter!");
    }

    public void undo() {

    }
}
