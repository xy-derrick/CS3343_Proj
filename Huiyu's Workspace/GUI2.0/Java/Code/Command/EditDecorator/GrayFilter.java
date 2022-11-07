package Code.Command.EditDecorator;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Code.Command.Commands.EditCommand;
import Code.Software.imgProcessor;

public class GrayFilter extends EditDecorator {
	private float[] radios = new float[]{0.4f,0.6f,0.4f,0.6f,0.2f,0.8f};
    public GrayFilter(EditCommand wrappee) {
        super(wrappee);
    }
    public GrayFilter(EditCommand wrappee,ArrayList<Object> args) {
        super(wrappee);
    }
    @Override
    public void execute() {
    	super.wrappee.execute();
    	imgProcessor ip=super.wrappee.getIP();
    	BufferedImage image = ip.getImg();
    	int width = image.getWidth();   //获取位图的宽
        int height = image.getHeight();  //获取位图的高
        int alpha = 0;
        int r = 0;
        int g = 0;
        int b = 0;
        int max = 0;
        int min = 0;
        int mid = 0;
        int gray = 0;
        float radioMax = 0;
        float radioMaxMid = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
              gray = image.getRGB(i, j);
                alpha = gray >>> 24;
                r = (gray>>16) & 0x000000ff;
                g = (gray >> 8) & 0x000000ff;
                b = gray & 0x000000ff;
                if (r >= g && r>=b) {
                    max = r;
                    radioMax = radios[0];
                }
                if (g>= r && g>=b) {
                    max = g;
                    radioMax = radios[2]; 
                }
                if (b >= r && b>=g) {
                    max = b;
                    radioMax = radios[4];
                }
                if (r<=g && r<=b) { // g+ b = cyan 青色
                    min = r;
                    radioMaxMid = radios[3];
                }
                if (b <= r && b<=g) {//r+g = yellow 黄色
                    min = b;
                    radioMaxMid = radios[1];
                }
                if (g <= r && g<=b) {//r+b = m 洋红
                    min = g;
                    radioMaxMid = radios[5];
                }
                mid = r + g + b-max -min;
                gray = (int) ((max - mid) * radioMax + (mid - min) * radioMaxMid + min);
                gray = (alpha << 24) | (gray << 16) | (gray << 8) | gray;
                image.setRGB(i, j, gray);
            }
        }
        ip.setImg(image);
    }
    @Override
    public void undo() {
    	super.undo();
    }
}
