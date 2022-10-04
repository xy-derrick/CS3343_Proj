package Code.Command.Commands.Export;

import Code.Software.imgProcessor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import java.awt.Color;

public class jpgTransfer extends typeTransfer {
    private  String path;

    public jpgTransfer(imgProcessor receiver, String type) {
        super(receiver);
        this.path=type;
    }

    public jpgTransfer(imgProcessor receiver, ArrayList<Object> args) {
      super(receiver);
      this.path=(String)args.get(0);
  }

    @Override
    public void transfer(){
        try {

            //read image file
            BufferedImage  imag= iProcessor.getImg();
      
            // create a blank, RGB, same width and height, and a white background
            BufferedImage newBufferedImage = new BufferedImage(imag.getWidth(),
                  imag.getHeight(), BufferedImage.TYPE_INT_RGB);
      
           //TYPE_INT_RGB:创建一个RBG图像，24位深度
      
            newBufferedImage.createGraphics().drawImage(imag, 0, 0, Color.WHITE, null);
      
            // write to jpeg file
            ImageIO.write(newBufferedImage, "jpg", new File(path+"\\newImag.jpg"));
            
      
            System.out.println("Transfer to jpg successfully");
      
          } catch (IOException e) {
            System.out.println("Invalid path");
          }
      
         }
        
    }

