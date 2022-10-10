package Code.Command.Commands.HuiyuBAI;

import java.util.ArrayList;

import Code.Command.Base.Command;
import Code.Software.imgProcessor;

public class batchAdd extends Command {
    private ArrayList<notifyIP> imgList = new ArrayList<notifyIP>();
    private ArrayList<stringCommand> cmdList = new ArrayList<stringCommand>();
    private batchProcessor bp = batchProcessor.getInstance();
    public batchAdd(imgProcessor ip,ArrayList<?> list) { //强制设为null?
        super(null);
        //TODO Auto-generated constructor stub
        if(list.size()!=0 && list.get(0) instanceof imgProcessor){
            for(imgProcessor i: (ArrayList<imgProcessor>)list){
                imgList.add(new notifyIP(i));
            }
        }
        else if(list.size()!=0&&list.get(0) instanceof stringCommand)
        //------------------------
        cmdList = (ArrayList<stringCommand>)list;
        //--------------------------
    }
  
	@Override
    public void execute() {
        // TODO Auto-generated method stub  
        
        for(stringCommand c:cmdList) bp.addElement(c);
        for(notifyIP ip: imgList) bp.addElement(ip);
        System.out.println("BatchAdd runs compeleted.");
    }
    @Override
    public void undo() {
        // TODO Auto-generated method stub
        for(stringCommand c:cmdList) bp.deleteElement(c);
        for(notifyIP ip: imgList) bp.deleteElement(ip);
        System.out.println("BatchAdd undos compeleted.");

    }
  
   
    


}
