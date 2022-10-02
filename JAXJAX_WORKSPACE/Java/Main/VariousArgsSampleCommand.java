import Code.Command.Base.Command;
import Code.Software.imgProcessor;

public class VariousArgsSampleCommand extends Command{
    Float f = null;
    Integer i = null;
    String s = null;
    Boolean b = null;

    public VariousArgsSampleCommand(imgProcessor receiver,Float f,Integer i, String s,Boolean b) {
        super(receiver);
        this.f = f;
        this.i = i;
        this.s = s;
        this.b = b;
    }
    
    @Override
    public void execute() {
        System.out.printf("Class %s arg %f received \n",(f.getClass()),f);
        System.out.printf("Class %s arg %d received \n",(i.getClass()),i);
        System.out.printf("Class %s arg %s received \n",(s.getClass()),s);
        System.out.printf("Class %s arg %b received \n",(b.getClass()),b);
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        
    }
}
