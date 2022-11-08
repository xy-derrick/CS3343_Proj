package Java.Code.Command.Commands.Common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import Java.Code.Command.Base.Command;
import Java.Code.Software.imgProcessor;
import Java.Code.Software.Software;
import Java.Code.Software.ipState;

public class readImgFromLocal extends Command {
    String text = null;
    String name = null;
    ipState state_last = null;
    ipState state_curr = null;

    public readImgFromLocal(imgProcessor receiver, String text, String name) {
        super(receiver);
        this.text = text;
        this.name = name;
    }

    public readImgFromLocal(imgProcessor receiver, ArrayList<Object> args) {
        super(receiver);
        this.text = (String) args.get(0);
    }

    @Override
    public void execute() {
        // ignore receiver
        System.out.println("try to read img file from " + text);
        try {
            state_last = Software.getInstance().getState();

            // first
            if (state_curr == null) {
                iProcessor = new imgProcessor(this.name);
                File imgFile = new File(text);
                BufferedImage bufImage = ImageIO.read(imgFile);
                iProcessor.setImg(bufImage);
                iProcessor.setPath(text);
                Software.getInstance().setMain_ip(iProcessor);
                state_curr = Software.getInstance().getState();
            } else {
                Software.getInstance().setState(state_curr);
            }
            System.out.println("successfully read img file from " + text);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Can't read such file, please check the address and file type !");
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
