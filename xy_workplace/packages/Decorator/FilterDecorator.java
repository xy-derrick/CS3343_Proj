package xy_workplace.packages.Decorator;

public class FilterDecorator implements FilterInterface {
    protected FilterInterface wrappee;

    public FilterDecorator(FilterInterface wrappee) {
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
