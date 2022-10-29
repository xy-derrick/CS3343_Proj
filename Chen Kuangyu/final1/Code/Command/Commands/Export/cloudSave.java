package Code.Command.Commands.Export;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

import Code.Command.Base.Command;
import Code.Command.Base.CommandNoncancelabe;
import Code.Software.imgProcessor;
import ImgUtil.ImgUtil;

public class cloudSave extends Command implements CommandNoncancelabe{
    private String path;

    public cloudSave(imgProcessor receiver, String path) {
        super(receiver);
        this.path=path;
    }

    public cloudSave(imgProcessor receiver, ArrayList<Object> args) {
        super(receiver);
        this.path=(String)args.get(0);
    }

    public void save(String path) throws FileNotFoundException, SQLException
    {
		ImgUtil.saveImginDb("2","command1",path);
    }

    @Override
    public void execute() {
        try {
            save(path);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find the imag file, Please check and try again!");
        }catch(SQLException e)
        {
            System.out.println("Unable to connect to the cloud server!");
        }
        
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
    }
    
}
