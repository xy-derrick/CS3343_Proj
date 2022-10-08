package Code.Command.Commands.Export;

import Code.Software.imgProcessor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import java.awt.Color;

public class tiffTransfer extends typeTransfer {
    private  String path;

    public tiffTransfer(imgProcessor receiver, String path) {
        super(receiver);
        this.path=path;
    }
    public tiffTransfer(imgProcessor receiver, ArrayList<Object> args) {
      super(receiver);
      this.path=(String)args.get(0);
  }


    @Override
    public void transfer(){
        try {

            //读入图片以及路径
            BufferedImage  imag= iProcessor.getImg();
            String localPath=iProcessor.getPath();
            String name=getName(localPath);
      
            // 创建一个空的RGB，与图片拥有相同的高宽，白色底
            BufferedImage newBufferedImage = new BufferedImage(imag.getWidth(),
                  imag.getHeight(), BufferedImage.TYPE_INT_RGB);
      
           //TYPE_INT_RGB:创建一个RBG图像，24位深度
      
            newBufferedImage.createGraphics().drawImage(imag, 0, 0, Color.WHITE, null);
      
            //写入tiff文件
            ImageIO.write(newBufferedImage, "tiff", new File(path+"\\"+String.valueOf(newName)+name+".tiff"));
            
      
            System.out.println("Transfer to tiff successfully");
      
          } catch (IOException e) {
            System.out.println("Invalid path");
          }
      
         }
        
    }

