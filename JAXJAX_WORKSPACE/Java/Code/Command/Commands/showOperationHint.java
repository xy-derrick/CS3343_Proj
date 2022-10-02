package Code.Command.Commands;

import java.awt.image.BufferedImage;

import Code.Command.Base.Command;
import Code.Software.imgProcessor;

public class showOperationHint extends Command{

    public showOperationHint(imgProcessor receiver) {
        super(receiver);
    }
    
    @Override
    public void execute() {
        
        System.out.println("Welcome to Img Process Software !\n"+ 
                            "please select operation from the following list :\n"+
                            "Common: 1.openNew 2.undo 3.redo 4.close 5.changeTo 6.closeAll 7.showImg 8.showInfo 9.exist\n"+
                            "Edit:\n"+
                            "Export:\n"+
                            "Filter\n"+
                            "Command format should be 'title +(space) + number', like 'Common 1' "
                            );

    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        System.out.println("nothing to undo");
    }
}
