package Code.Command.Commands.HuiyuBAI;

import java.util.ArrayList;

import Code.Command.Base.Command;
import Code.Software.imgProcessor;

public class batchAdd extends Command {
    private ArrayList<notifyIP> imgList = new ArrayList<notifyIP>();
    private ArrayList<Command> cmdList = new ArrayList<Command>();
    private batchProcessor bp;
    public batchAdd(batchProcessor receiver,ArrayList<?> list) {
        super(null);
        //TODO Auto-generated constructor stub
        if(list.size()!=0 && list.get(0) instanceof imgProcessor){
            for(imgProcessor i: (ArrayList<imgProcessor>)list){
                imgList.add(new notifyIP(i));
            }
        }
        else if(list.size()!=0&&list.get(0) instanceof Command)
        //------------------------
        cmdList = (ArrayList<Command>)list;
        //--------------------------
        bp = receiver;
    }
    
    @Override
    public void execute() {
        // TODO Auto-generated method stub  
        
        notify1(bp);
        System.out.println("BatchAdd runs correctly.");
    }
    @Override
    public void undo() {
        // TODO Auto-generated method stub
        unotify1(bp);
        System.out.println("BatchAdd undos correctly.");

    }
  
    public void notify1(batchProcessor bp){
        for(Command c:cmdList) bp.addElement(c);
        for(notifyIP ip: imgList) bp.addElement(ip);
    }
    public void unotify1(batchProcessor bp){
        for(Command c:cmdList) bp.deleteElement(c);
        for(notifyIP ip: imgList) bp.deleteElement(ip);
    }
    


}
