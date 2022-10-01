package Code.Command.FilterDecorator;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

import Code.Command.Base.Command;
import Code.Software.imgProcessor;

public class GrayFilter extends FilterDecorator {
	protected BufferedImage imgcopy;
    public GrayFilter(Command wrappee) {
        super(wrappee);
        // TODO Auto-generated constructor stub
        this.imgcopy=this.wrappee.getIP().getImg();
    }
    @Override
    public void execute() {
        // TODO Auto-generated method stub
    	imgProcessor ip=this.wrappee.getIP();
    	ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY); 
        ColorConvertOp op = new ColorConvertOp(cs, null);
        ip.setImg(op.filter(ip.getImg(),null));
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
    	imgProcessor ip=this.wrappee.getIP();
    	ip.setImg(this.imgcopy);
    	System.out.println("Gray filter has been removed!");
    }
}
