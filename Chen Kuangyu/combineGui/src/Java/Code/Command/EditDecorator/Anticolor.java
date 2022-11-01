package Code.Command.EditDecorator;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Code.Command.Commands.EditCommand;
import Code.Software.imgProcessor;

public class Anticolor extends EditDecorator {
    public Anticolor(EditCommand wrappee) {
        super(wrappee);
    }

    public Anticolor(EditCommand wrappee, ArrayList<Object> args) {
        super(wrappee);
    }

    private int colorToRGB(int red, int green, int blue) {
        int newPixel = 0;
        newPixel = newPixel << 8;
        newPixel += red;
        newPixel = newPixel << 8;
        newPixel += green;
        newPixel = newPixel << 8;
        newPixel += blue;
        return newPixel;
    }

    private BufferedImage filter(BufferedImage img) {

        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage imageBuffer = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int rgb = img.getRGB(x, y);
                int R = (rgb & 0xff0000) >> 16;
                int G = (rgb & 0x00ff00) >> 8;
                int B = rgb & 0x0000ff;
                int newPixel = colorToRGB(255 - R, 255 - G, 255 - B);
                imageBuffer.setRGB(x, y, newPixel);
            }
        }
        return imageBuffer;
    }

    @Override
    public void execute() {
        // super.wrappee.execute();
        imgProcessor ip = super.wrappee.getIP();
        BufferedImage img = ip.getImg();
        ip.setImg(filter(img));

    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        super.undo();
        System.out.println("Anticolor has been removed!");
    }

}
