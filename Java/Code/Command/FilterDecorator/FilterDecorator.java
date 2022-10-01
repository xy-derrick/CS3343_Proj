package Code.Command.FilterDecorator;

import Code.Command.Base.Command;

public class FilterDecorator extends Command {
    protected Command wrappee;

    public FilterDecorator(Command wrappee) {
    	super(wrappee.getIP());
        this.wrappee = wrappee;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub

    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }
}
