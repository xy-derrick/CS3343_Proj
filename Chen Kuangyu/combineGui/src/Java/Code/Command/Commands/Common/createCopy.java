package Code.Command.Commands.Common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import Code.Command.Base.Command;
import Code.Command.EditDecorator.EditDecorator;
import Code.Software.imgProcessor;
import Code.Software.Software;
import Code.Software.ipState;

public class createCopy extends Command {
    BufferedImage ori = null;
    ipState state_last = null;
    ipState state_curr = null;

    public createCopy(imgProcessor receiver, BufferedImage ori) {
        super(receiver);
        this.ori = EditDecorator.copyImage(ori);
    }

    @Override
    public void execute() {
        // ignore receiver
        state_last = Software.getInstance().getState();
        // first
        if (state_curr == null) {
            iProcessor = new imgProcessor();
            iProcessor.setImg(this.ori);
            Software.getInstance().setMain_ip(iProcessor);
            state_curr = Software.getInstance().getState();
        } else {
            Software.getInstance().setState(state_curr);
        }
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        try {
            Software.getInstance().setState(state_last);
            System.out.println("open commond undo successfully !");
        } catch (Exception e) {
            System.out.println("open commond undo failed !");
        }
    }
}
