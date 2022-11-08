package Java.Code.Software;

import java.awt.image.BufferedImage;

public class imgProcessor {
    BufferedImage img = null;
    String name;
    int imgHeight = -1;
    int imgWidth = -1;
    private String localPath;

    public imgProcessor() {
        Software.getInstance().addImgProcessor(this);
    }

    public imgProcessor(String name) {
        this.name = name;
        Software.getInstance().addImgProcessor(this);
    }
    
    public imgProcessor(int temp) {
    }

    public void setImg(BufferedImage bufImage) {
        img = bufImage;

        // init
        System.out.println("img file init...");
        imgHeight = bufImage.getHeight();
        imgWidth = bufImage.getWidth();
    }

    public String getName() {
        return this.name;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setPath(String text) {
        this.localPath = text;
    }

    public String getPath() {
        return localPath;
    }
}
