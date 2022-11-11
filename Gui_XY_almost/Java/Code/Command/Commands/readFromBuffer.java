package Java.Code.Command.Commands;

import java.awt.image.BufferedImage;
import Java.Code.Command.Base.Command;
import Java.Code.Software.imgProcessor;

public class readFromBuffer extends Command{
    BufferedImage img=null;

    public readFromBuffer(imgProcessor receiver,BufferedImage img) {
        super(receiver);
        this.img=img;
    }
    
    
    @Override
    public void execute() {
        // ignore receiver
        iProcessor.setImg(this.img); 
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        
    }
}
