import java.util.ArrayList;

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

    public VariousArgsSampleCommand(imgProcessor receiver,ArrayList<Object> args) {
        super(receiver);
        this.f = (Float)args.get(0);
        this.i = (Integer)args.get(1);
        this.s = (String)args.get(2);
        this.b = ( Boolean)args.get(3);
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
