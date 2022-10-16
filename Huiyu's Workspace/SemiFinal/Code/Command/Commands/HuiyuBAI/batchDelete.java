package Code.Command.Commands.HuiyuBAI;

import java.util.ArrayList;

import Code.Command.Base.Command;
import Code.Software.imgProcessor;

public class batchDelete extends Command {
    private ArrayList<Integer> imgList = new ArrayList<Integer>();
    private ArrayList<stringCommand> cmdList= new ArrayList<stringCommand>();
    private batchProcessor bp = batchProcessor.getInstance();
    public batchDelete(imgProcessor ip,ArrayList<?> list) throws ExImgNotExist, ExStringCommandNotExist  {
        super(ip);
        if(list.size()!=0 && list.get(0) instanceof Integer){
            for(Integer i: (ArrayList<Integer>)list){
                if(bp.getImgBuffer().indexOf(i)== -1) throw new ExImgNotExist(i);
                else
                imgList.add(i);
            }
        }
        else if(list.size()!=0&&list.get(0) instanceof stringCommand)
        //------------------------
        {
            for(stringCommand i: (ArrayList<stringCommand>)list){
                if(bp.getCmdBuffer().indexOf(i)==-1){
                    throw new ExStringCommandNotExist(i);
                }                
                else{
                    cmdList = (ArrayList<stringCommand>)list;
                }
            }
            
        }
       
        //--------------------------
    }
    
    @Override
    public void execute()  {
        // TODO Auto-generated method stub  
        for(stringCommand c:cmdList) bp.deleteElement(c);
        for(Integer ip: imgList) bp.deleteElement(ip);
        System.out.println("BatchDelete runs compeleted.");
       
    }
    @Override
    public void undo()  {
        // TODO Auto-generated method stub
        for(stringCommand c:cmdList) bp.addElement(c);
        for(Integer ip: imgList) bp.addElement(ip);
        System.out.println("BatchDelete undo compeleted.");

    }
   
    
    


}
