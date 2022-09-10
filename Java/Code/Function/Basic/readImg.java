package Code.Function.Basic;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class readImg extends SampleProcessor{

    public void Process(Object img, String text) {
        System.out.println("111111");
        File sourceimage = new File(text); 
        try {
            BufferedImage image = ImageIO.read(sourceimage);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
