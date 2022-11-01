package Java.Code.Command.Export;

import Java.Code.Software.imgProcessor;
import Java.Code.exportException.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.Color;

public class gifTransfer extends typeTransfer {
  private String path;

  public gifTransfer(imgProcessor receiver, String path) {
    super(receiver);
    this.path = path;
  }

  public gifTransfer(imgProcessor receiver, ArrayList<Object> args) {
    super(receiver);
    this.path = (String) args.get(0);
  }

  @Override
  public void transfer() throws nameNotFoundException {
    try {

      /// 读入图片以及路径
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

      // 写入gif文件
      ImageIO.write(newBufferedImage, "gif", new File(path + "\\" + String.valueOf(newName) + name + ".gif"));

      System.out.println("Transfer to gif successfully");
    } catch (FileNotFoundException e) {
      System.out.println("Invalid file path! Please check and input again!");
    } catch (NullPointerException e) {
      System.out.println("Input path is null! Please input again!");
    } catch (IOException e) {
      System.out.println("Unknown errors happended when write to gif file");
    } catch (IllegalArgumentException e) {
      System.out.println("Can find imag from the imag processor. Please check!");
    }

  }

}
