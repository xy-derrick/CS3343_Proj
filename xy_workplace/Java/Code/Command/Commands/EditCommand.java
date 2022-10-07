package Java.Code.Command.Commands;

import Java.Code.Command.Base.Command;
import Java.Code.Software.imgProcessor;

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
