package Code.Command.FilterDecorator;

import Code.Command.Base.CommandInterface;

public class FilterDecorator implements CommandInterface {
    protected CommandInterface wrappee;

    public FilterDecorator(CommandInterface wrappee) {
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
