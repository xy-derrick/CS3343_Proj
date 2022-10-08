package Java.Code.Command.Commands;

<<<<<<< HEAD
import Code.Command.Base.Command;
import Code.Software.imgProcessor;
import java.util.ArrayList;
=======
import Java.Code.Command.Base.Command;
import Java.Code.Software.imgProcessor;
>>>>>>> branch 'main' of https://github.com/xy-derrick/CS3343_Proj.git

public class EditCommand extends Command{
    protected imgProcessor iProcessor;
    public EditCommand(imgProcessor receiver) {
        super(receiver);
    }
    @Override
    public void execute() {
    	System.out.println("Edit operation!");
    }

    public void undo() {

    }
}
