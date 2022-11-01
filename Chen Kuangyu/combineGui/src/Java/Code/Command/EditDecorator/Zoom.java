package Code.Command.EditDecorator;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Code.Command.Commands.EditCommand;
import Code.Exception.ArgsInvalidException;
import Code.Software.imgProcessor;

public class Zoom extends EditDecorator {
    private float targetWidth;

    public Zoom(EditCommand wrappee, ArrayList<Object> args) throws ArgsInvalidException {
        super(wrappee);
        // TODO Auto-generated constructor stub
        this.targetWidth = (Float) args.get(0);
        if ((targetWidth > 10) || (targetWidth <= 0)) {
            throw new ArgsInvalidException("TargetWidth must be (0,10].");
        }
    }

    public Zoom(EditCommand wrappee, Float targetWidth) {
        super(wrappee);
        // TODO Auto-generated constructor stub
        this.targetWidth = targetWidth;
    }

    @Override
    public void execute() {
        super.wrappee.execute();
        imgProcessor ip = super.wrappee.getIP();
        BufferedImage img = ip.getImg();
        // double resizeTimes = (double)targetWidth /(double) img.getWidth();
        /* 调整后的图片的宽度和高度 - 按照压缩比例计算出新的宽度和高度 */
        // int toWidth = (int) (img.getWidth() * resizeTimes);
        // int toHeight = (int) (img.getHeight()* resizeTimes);
        int toWidth = (int) (targetWidth * img.getWidth());
        int toHeight = (int) (targetWidth * img.getHeight());
        BufferedImage nimg;
        nimg = new BufferedImage(toWidth, toHeight, BufferedImage.TYPE_INT_RGB);
        nimg.getGraphics().drawImage(img.getScaledInstance(toWidth, toHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
        ip.setImg(nimg);
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        super.undo();
        System.out.println("Zoom has been removed!");
    }

}
