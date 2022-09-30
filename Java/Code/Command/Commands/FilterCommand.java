package Code.Command.Commands;

import Code.Command.Base.Command;
import Code.Command.Base.CommandInterface;
import Code.Software.imgProcessor;

public class FilterCommand extends Command implements CommandInterface {
    protected imgProcessor iProcessor;

    public FilterCommand(imgProcessor receiver) {
        super(receiver);
    }

    public void execute() {

    }

    public void undo() {

    }
}
