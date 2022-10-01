package Code.Command.FilterDecorator;

import java.awt.color.ColorSpace;
import java.awt.image.ColorConvertOp;

import Code.Command.Base.Command;
import Code.Software.imgProcessor;

public class GrayFilter extends FilterDecorator {
	
    public GrayFilter(Command wrappee) {
        super(wrappee);
    }
    @Override
    public void execute() {
    	imgProcessor ip=super.wrappee.getIP();
    	ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY); 
        ColorConvertOp op = new ColorConvertOp(cs, null);
        ip.setImg(op.filter(ip.getImg(),null));
        super.execute();
    }

    @Override
    public void undo() {
    	super.undo();
    }
}
