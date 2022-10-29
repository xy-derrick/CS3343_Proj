package Java.Code.Command.EditDecorator;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import Java.Code.Command.Base.Command;
import Java.Code.Software.imgProcessor;

public class EditDecorator extends Command {
	protected Command wrappee;
	private BufferedImage imgcopy;
    public EditDecorator(Command wrappee) {
    	super(wrappee.getIP());
        this.wrappee = wrappee;
        this.imgcopy=copyImage(this.wrappee.getIP().getImg());
    }

    @Override
    public void execute() {
    	System.out.println("filter adding!");
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
    	imgProcessor ip=this.wrappee.getIP();
    	ip.setImg(this.imgcopy);
    	System.out.println("Filter has been removed!");
    }
    
    public static BufferedImage copyImage(BufferedImage source){
	    BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
	    Graphics g = b.getGraphics();
	    g.drawImage(source, 0, 0, null);
	    g.dispose();
	    return b;
	}
}
