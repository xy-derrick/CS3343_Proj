package Code.Command.Commands.HuiyuBAI;
import Main.Main;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import Code.Software.Software;
import Code.Software.imgProcessor;
public class notifyIP extends imgProcessor implements Observer{

    public notifyIP(imgProcessor ip){
        super(0);
        setImg(ip.getImg());
        setPath(ip.getPath());
    }
    @Override
    public void update() {
        ArrayList<stringCommand> cmdBuffer = batchProcessor.getInstance().getCmdBuffer();

        Software.getInstance().setMain_ip(this);

        for(stringCommand i:cmdBuffer){
            InputStream ps = System.in;
            System.setIn(new ByteArrayInputStream(i.getParameters().getBytes()));
            Main.switchCommand(i.getCmdType(),i.getCmdNum());
            System.setIn(ps);
        }
       
    }
    public void previewDisplay(ArrayList<stringCommand> temp) {
        Software.getInstance().setMain_ip(this);

        for(stringCommand i:temp){
            InputStream ps = System.in;
            System.setIn(new ByteArrayInputStream(i.getParameters().getBytes()));
            Main.switchCommand(i.getCmdType(),i.getCmdNum());
            System.setIn(ps);
        }
        

    }

    // @Override
    // public boolean equals(Object obj) {
    //     // TODO Auto-generated method stub
    //     if(obj == this) return true;
    //     if(obj instanceof notifyIP) {
    //         notifyIP theObj = (notifyIP)obj;
    //         if (theObj.getImg() == this.getImg()) return true;
    //     }
    //     return false;
    // }
    
}
    



