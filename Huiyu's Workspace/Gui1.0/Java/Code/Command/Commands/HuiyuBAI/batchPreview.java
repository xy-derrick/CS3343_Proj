package Code.Command.Commands.HuiyuBAI;


import java.awt.image.BufferedImage;
import java.util.ArrayList;



public class batchPreview {
    private batchProcessor bp = batchProcessor.getInstance();
    public BufferedImage execute(){
        // TODO Auto-generated method stub
        String i = new ArrayList<String>().get(0);
        return bp.preview();
    }
}
