package Code.Command.Commands.HuiyuBAI;

import java.util.ArrayList;

import Code.Command.Base.Command;
import Code.Exception.ImgProcessorNotFindInListException;
import Code.Software.Software;
import Code.Software.imgProcessor;

public class batchAdd extends Command {
    //private ArrayList<notifyIP> imgList = new ArrayList<notifyIP>();
    private ArrayList<Integer> imgList = new ArrayList<Integer>();
    private ArrayList<stringCommand> cmdList = new ArrayList<stringCommand>();
    private batchProcessor bp = batchProcessor.getInstance();
    // public batchAdd(imgProcessor ip,ArrayList<?> list) { //强制设为null?
    //     super(ip);
    //     //TODO Auto-generated constructor stub
    //     if(list.size()!=0 && list.get(0) instanceof imgProcessor){
    //         for(imgProcessor i: (ArrayList<imgProcessor>)list){
    //             imgList.add(new notifyIP(i));
    //         }
    //     }
    //     else if(list.size()!=0&&list.get(0) instanceof stringCommand)
    //     //------------------------
    //     cmdList = (ArrayList<stringCommand>)list;
    //     //--------------------------
    // }
    public batchAdd(imgProcessor ip,ArrayList<?> list) throws ExImgHaveExist, ImgProcessorNotFindInListException, ExStringCommandNotExist{ //强制设为null?
        super(null);
        //TODO Auto-generated constructor stub
        if(list.size()!=0 && list.get(0) instanceof Integer){
            for(Integer i: (ArrayList<Integer>)list){
                if(Software.getInstance().getImgProcessorList().size()<i) throw new ImgProcessorNotFindInListException(i);
                else if(bp.getImgBuffer().indexOf(i)!=-1) throw new ExImgHaveExist(i);
                else{
                    imgList.add(i);
                }
                
            }
        }
        else if(list.size()!=0&&list.get(0) instanceof stringCommand){
        //------------------------
        for(stringCommand i: (ArrayList<stringCommand>)list){

        
        cmdList = (ArrayList<stringCommand>)list;
        
        }
    }
        //--------------------------
    }
  
	// @Override
    // public void execute() {
    //     // TODO Auto-generated method stub  
        
    //     for(stringCommand c:cmdList) bp.addElement(c);
    //     for(notifyIP ip: imgList) bp.addElement(ip);
    //     System.out.println("BatchAdd runs compeleted.");
    // }
    // @Override
    // public void undo() {
    //     // TODO Auto-generated method stub
    //     for(stringCommand c:cmdList) bp.deleteElement(c);
    //     for(notifyIP ip: imgList) bp.deleteElement(ip);
    //     System.out.println("BatchAdd undos compeleted.");

    // }
    @Override
    public void execute() {
        // TODO Auto-generated method stub  
        
        for(stringCommand c:cmdList) bp.addElement(c);
        for(Integer i: imgList) bp.addElement(i);
        bp.info();
        System.out.println("BatchAdd runs compeleted.");
    }
    @Override
    public void undo() {
        // TODO Auto-generated method stub
        for(stringCommand c:cmdList) bp.deleteElement(c);
        for(Integer i: imgList) bp.deleteElement(i);
        bp.info();
        System.out.println("BatchAdd undos compeleted.");

    }
   
    


}
