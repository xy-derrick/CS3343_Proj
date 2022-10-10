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
    private ArrayList<notifyIP> imgBuffer = new ArrayList<notifyIP>();
    private ArrayList<stringCommand> cmdBuffer= new ArrayList<stringCommand>();
    private Integer cmdSize = 0;
    private imgProcessor mainIp;
    // private ArrayList<Command> fullCmdList = new ArrayList<Command>();
    public void addElement(stringCommand c) {
        cmdBuffer.add(c);
        System.out.printf("add command(s) successfully, now %d command(s) in processor.\n",cmdBuffer.size());
        info();
    }

    public void addElement(notifyIP ip) {
        imgBuffer.add(ip);
        System.out.printf("add img(s) successfully, now %d img(s) in processor.\n",imgBuffer.size());
        info();
    }

    public void deleteElement(stringCommand c) {
        cmdBuffer.remove(c);
        System.out.printf("delete cmd(s) successfully, now %d cmd(s) in processor.\n",cmdBuffer.size());
        info();
    }

    public void deleteElement(notifyIP ip) {
        imgBuffer.remove(ip);
        System.out.printf("delete img(s) successfully, now %d cmd(s) in processor.\n",imgBuffer.size());
        info();
    }
    public void notifyAlls(){
        cmdSize = cmdBuffer.size()*imgBuffer.size();
        System.out.printf("Now we will execute %d commands...\n",cmdSize);

        mainIp = Software.getInstance().getMain_ip();

        for(notifyIP p: imgBuffer){
            p.update();
        }

        Software.getInstance().setMain_ip(mainIp);
    }
    public void unotifyAlls() {
        
        System.out.printf("Now we will undo %d commands\n",cmdSize);
        for(int i = 0;i<cmdSize;i++){
            Main.switchCommand("common",3);
        }
        Software.getInstance().setMain_ip(mainIp);
    }
    public void reset() {

        imgBuffer = new ArrayList<notifyIP>();;
        cmdBuffer = new ArrayList<stringCommand>();;
        cmdSize = 0;
        mainIp = null;

        info();
    }
    private void info(){
        System.out.printf("imgBuffer has %d elements,\ncmdBuffer has %d elements\n",imgBuffer.size(),cmdBuffer.size());

    }
    public ArrayList<stringCommand> getStringCommandList() {
        return cmdBuffer;
    }
}
    


