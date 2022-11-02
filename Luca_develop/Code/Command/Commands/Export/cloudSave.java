package Code.Command.Commands.Export;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import Code.Command.Base.Command;
import Code.Command.Base.CommandNoncancelabe;
import Code.Software.imgProcessor;
import ImgUtil.ImgUtil;

public class cloudSave extends Command implements CommandNoncancelabe {
    private String id;

    public cloudSave(imgProcessor receiver, String id) {
        super(receiver);
        this.id = id;
    }

    public cloudSave(imgProcessor receiver, ArrayList<Object> args) {
        super(receiver);
        this.id = (String) args.get(0);
    }

    public String getType(String name) {
        return name.substring(name.lastIndexOf(".") + 1);
    }

    public void save() throws FileNotFoundException, SQLException {
        ImgUtil.saveImginDb(id, iProcessor.getImg(), getType(iProcessor.getPath()));
        try {
			ImgUtil.getImgfromDb("3","66");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void execute() {
        try {
            save();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find the imag file, Please check and try again!");
        } catch (SQLException e) {
            System.out.println("Unable to connect to the cloud server!");
        }

    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
    }

}
