package Code.Command.Commands.Export;

import Code.Software.imgProcessor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.Color;

public class bmpTransfer extends typeTransfer {
    private  String path;

    public bmpTransfer(imgProcessor receiver, String path) {
        super(receiver);
        this.path=path;
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
      
            // write to bmp file
            ImageIO.write(newBufferedImage, "bmp", new File(path+"\\newImag.bmp"));
            
      
            System.out.println("Transfer to bmp successfully");
      
          } catch (IOException e) {
            System.out.println("Invalid path");
          }
      
         }
        
    }

