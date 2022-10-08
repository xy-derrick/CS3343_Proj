package Java.Code.Command.Commands;

import Code.Command.Base.Command;
import Code.Software.imgProcessor;
import java.util.ArrayList;

public class EditCommand extends Command{
    protected imgProcessor iProcessor;
    public EditCommand(imgProcessor receiver) {
        super(receiver);
    }
    @Override
    public void execute() {
    	System.out.println("Edit operation!");
    }

    public void undo() {

    }
}
