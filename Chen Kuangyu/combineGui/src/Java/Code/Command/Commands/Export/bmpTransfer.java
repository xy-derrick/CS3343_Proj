package Code.Command.Commands.Export;

import Code.Software.imgProcessor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import Code.exportException.*;
import javax.imageio.ImageIO;
import java.awt.Color;

public class bmpTransfer extends typeTransfer {
  private String path;

  public bmpTransfer(imgProcessor receiver, String path) {
    super(receiver);
    this.path = path;
  }

  public bmpTransfer(imgProcessor receiver, ArrayList<Object> args) {
    super(receiver);
    this.path = (String) args.get(0);
  }

  @Override
  public void transfer() throws nameNotFoundException {
    try {

      // 读入图片以及路径
      BufferedImage imag = iProcessor.getImg();
      String localPath = iProcessor.getPath();
      String name = getName(localPath);

      if (name.isEmpty() || name == "") {
        throw new nameNotFoundException();
      }

      // 创建一个空的RGB，与图片拥有相同的高宽，白色底
      BufferedImage newBufferedImage = new BufferedImage(imag.getWidth(),
          imag.getHeight(), BufferedImage.TYPE_INT_RGB);

      // TYPE_INT_RGB:创建一个RBG图像，24位深度
      newBufferedImage.createGraphics().drawImage(imag, 0, 0, Color.WHITE, null);

      // 写入bmp文件
      ImageIO.write(newBufferedImage, "bmp", new File(path + "\\" + String.valueOf(newName) + name + ".bmp"));

      System.out.println("Transfer to bmp successfully");

    } catch (FileNotFoundException e) {
      System.out.println("Invalid file path! Please check and input again!");
    } catch (NullPointerException e) {
      System.out.println("Input path is null! Please input again!");
    } catch (IOException e) {
      System.out.println("Unknown errors happended when write to bmp file");
    } catch (IllegalArgumentException e) {
      System.out.println("Can find imag from the imag processor. Please check!");
    }

  }

}
