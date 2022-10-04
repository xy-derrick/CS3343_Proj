package Java.Code.Software;

import java.awt.image.BufferedImage;

public class imgProcessor {
    BufferedImage img = null;

    int imgHeight= -1;
    int imgWidth= -1;

    public imgProcessor()
    {
        Software.getInstance().addImgProcessor(this);
    }

    public void setImg(BufferedImage bufImage)
    {
        img = bufImage;

        //init
        System.out.println("img file init...");  
        imgHeight = bufImage.getHeight();
        imgWidth = bufImage.getWidth();
    }

    public BufferedImage getImg()
    {
        return img;
    }
}
