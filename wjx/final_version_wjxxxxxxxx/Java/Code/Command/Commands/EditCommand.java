package Code.Command.Commands;

import java.util.ArrayList;
import Code.Command.Base.Command;
import Code.Software.imgProcessor;

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
