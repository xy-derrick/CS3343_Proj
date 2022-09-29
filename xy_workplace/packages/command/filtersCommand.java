package xy_workplace.packages.command;

import xy_workplace.packages.command.Command;
import xy_workplace.packages.Software.imgProcessor;

public class filtersCommand extends Command {
    protected imgProcessor iProcessor;

    public filtersCommand(imgProcessor receiver) {
        super(receiver);
    }

    public void execute() {

    }

    public void undo() {

    }
}
