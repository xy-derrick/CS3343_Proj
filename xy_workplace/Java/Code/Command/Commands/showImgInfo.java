package Java.Code.Command.Commands;

import java.awt.image.BufferedImage;

import Code.Command.Base.Command;
import Code.Software.imgProcessor;

public class showImgInfo extends Command{

    public showImgInfo(imgProcessor receiver) {
        super(receiver);
    }
    
    @Override
    public void execute() {
        // example for reading info from img
        BufferedImage img = iProcessor.getImg();
        
        System.out.println("Img infomation is as following:    \n"+ 
                            "height: 100px    width:   100px    \n"+
                            "light:  30degree                   \n"+
                            "filter applied: [...,...,...,...]   \n");
        //1st default info--  2nd self-defined info ---  3rd all the filter user applied info ----
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        System.out.println("nothing to undo");
    }
}
