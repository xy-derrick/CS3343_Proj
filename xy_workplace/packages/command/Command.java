package xy_workplace.packages.command;

import xy_workplace.packages.Software.imgProcessor;

abstract public class Command implements CommandInterface {
    protected imgProcessor iProcessor;

    public Command(imgProcessor receiver) {
        this.iProcessor = receiver;
    }

    public abstract void execute();

    public abstract void undo();

}
