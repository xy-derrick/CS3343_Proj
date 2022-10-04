package Java.Code.Command.Commands;

import java.awt.Graphics;
import java.awt.Image;

import java.awt.image.BufferedImage;

import Code.Command.Base.Command;
import Code.Enum.ScaleMode;
import Code.Software.imgProcessor;

public class scaleImg extends Command{
    int h = -1;
    int w = -1;
    ScaleMode s = null;


    public scaleImg(imgProcessor receiver,int height,int width,ScaleMode sMode) {
        super(receiver);
        h = height;
        w = width;
        s = sMode;
    }
    
    @Override
    public void execute() {
        Image img_scaled = iProcessor.getImg().getScaledInstance(w, h,s.ordinal());
        iProcessor.setImg(toBufferedImage(img_scaled));
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        
    }

    public BufferedImage toBufferedImage(Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);

        BufferedImage bufImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bufImg.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return bufImg;
        }
}
