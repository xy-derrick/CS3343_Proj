package Command;

import xy_workplace.packages.Command.Command;
import xy_workplace.packages.Software.imgProcessor;
import xy_workplace.packages.Dec­orator.FilterInterface;

public class FilterCommand extends Command implements FilterInterface {
    protected imgProcessor iProcessor;

    public FilterCommand(imgProcessor receiver) {
        super(receiver);
    }

    public void execute() {

    }

    public void undo() {

    }
}
