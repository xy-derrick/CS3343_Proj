package Code.Command.Commands.HuiyuBAI;

import java.util.ArrayList;

import Code.Software.Software;
import Code.Software.imgProcessor;
import Main.Main;

public class batchProcessor implements Observable{
    private static batchProcessor bp = new batchProcessor();
    private batchProcessor(){
    }
    public static batchProcessor getInstance(){return bp;}
    private ArrayList<Integer> imgBuffer = new ArrayList<Integer>();
    private ArrayList<stringCommand> cmdBuffer= new ArrayList<stringCommand>();
    private Integer cmdSize = 0;
    private imgProcessor mainIp;
    // private ArrayList<notifyIP> imgBuffer = new ArrayList<notifyIP>();
    ArrayList<Integer> getImgBuffer() {
        return imgBuffer;
    }
    // private ArrayList<Command> fullCmdList = new ArrayList<Command>();
    public void addElement(stringCommand c) {
        cmdBuffer.add(c);

        System.out.printf("add command(s) successfully, now %d command(s) in processor.\n",cmdBuffer.size());
    }

    

    // public void addElement(notifyIP ip) {
    //     imgBuffer.add(ip);
    //     System.out.printf("add img(s) successfully, now %d img(s) in processor.\n",imgBuffer.size());
    //     info();
    // }
    public void addElement(Integer ip) {
        
            imgBuffer.add(ip);
            System.out.printf("add img(s) successfully, now %d img(s) in processor.\n",imgBuffer.size());     
    }
    public void deleteElement(stringCommand c)  {
       
            cmdBuffer.remove(c);
            System.out.printf("delete cmd(s) successfully, now %d cmd(s) in processor.\n",cmdBuffer.size());
        
        
    }

    // public void deleteElement(notifyIP ip) throws imgNotExistException {
    //     if(imgBuffer.indexOf(ip)== -1) throw new imgNotExistException(ip);
    //     else{
    //         imgBuffer.remove(ip);
    //         System.out.printf("delete img(s) successfully, now %d cmd(s) in processor.\n",imgBuffer.size());
    //         info();
    //     }
        
    // }
     
    public void deleteElement(Integer i)  {
        
        
            imgBuffer.remove(i);
            System.out.printf("delete img(s) successfully, now %d cmd(s) in processor.\n",imgBuffer.size());
    
        
    }
    public void notifyAlls(){
        cmdSize = cmdBuffer.size()*imgBuffer.size();
        System.out.printf("Now we will execute %d commands...\n",cmdSize);

        mainIp = Software.getInstance().getMain_ip();

        for(Integer i: imgBuffer){
            imgProcessor ip = Software.getInstance().getImgProcessorList().get(i);
            notifyIP p = new notifyIP(ip);
            p.update();
        }

        Software.getInstance().setMain_ip(mainIp);
    }
    public void preview()  {
        ArrayList<stringCommand> temp = new ArrayList<stringCommand>();
        for(stringCommand i: cmdBuffer){
            if(stringCommand.checkPreviewable(i)) temp.add(i);
        }
        try{
        if(temp.size()==0) throw new ExNoCmdForPreview();
        else{
            temp.add(new stringCommand("common", 7, null));
        }
        
        mainIp = Software.getInstance().getMain_ip();
        imgProcessor ip = Software.getInstance().getImgProcessorList().get(0);
        notifyIP p = new notifyIP(ip);
        p.previewDisplay(temp);

        Software.getInstance().setMain_ip(mainIp);
    }catch (Exception e){
        System.out.println(e.getMessage());
    }
        //选第一个，先最后加上common 7 命令，执行后从buffer去掉
    }
    // public void unotifyAlls() {
        
    //     System.out.printf("Now we will undo %d commands\n",cmdSize);
    //     for(int i = 0;i<cmdSize;i++){
    //         Main.switchCommand("common",3);
    //     }
    //     Software.getInstance().setMain_ip(mainIp);
    // }



    public void reset() {

        imgBuffer = new ArrayList<Integer>();;
        cmdBuffer = new ArrayList<stringCommand>();;
        cmdSize = 0;
        mainIp = null;

        info();
    }
    void info(){
        System.out.printf("imgBuffer has %d elements,\ncmdBuffer has %d elements\n",imgBuffer.size(),cmdBuffer.size());

    }
    public ArrayList<stringCommand> getCmdBuffer() {
        return cmdBuffer;
    }

    
    public String ImgBufferToStr() {
        String buffer = "";
        for(Integer img:imgBuffer){
            buffer+=img+", ";
        }
        return buffer.substring(0,buffer.length()-2);
    }
   
    public String cmdBufferToStr() {
        String buffer = "";
        for(stringCommand sc:cmdBuffer){
            buffer+=sc.toString()+", ";
        }

        return buffer.substring(0,buffer.length()-2);
    }
    
   
    
}
    


