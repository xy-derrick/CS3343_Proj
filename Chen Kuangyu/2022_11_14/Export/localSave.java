package Code.Command.Export;

import java.io.File;
import Code.Command.Base.*;
import Code.Software.imgProcessor;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import Code.exportException.*;;

public class localSave extends Command implements CommandNoncancelable {
	private String path;

	public localSave(imgProcessor receiver, String path) {
		super(receiver);
		this.path = path;
	}

	public localSave(imgProcessor receiver, ArrayList<Object> args) {
		super(receiver);
		this.path = (String) args.get(0);
	}

	public String getName(String localPath) {
		String fName = localPath.trim();
		return fName.substring(fName.lastIndexOf("\\") + 1);
	}

	public String getType(String name) {
		return name.substring(name.lastIndexOf(".") + 1);
	}

	public void save() throws nameNotFoundException{
		/*
		 * 当前图片存入本地
		 */
		try {
			// 得到原始文件名
			String localPath = iProcessor.getPath();
			String name = getName(localPath);
			int seq = zipSeq.getInstance().getSeq();
			

			if (name.length()==4 || name.equals(".tiff")) {
				throw new nameNotFoundException();
			}

			// 存入本地
			String type = getType(name);
			
			File outputfile = new File(path + "\\" + String.valueOf(seq) + name);
			ImageIO.write(iProcessor.getImg(), type, outputfile);

			System.out.println("Save to local successfully!\n");
			zipSeq.getInstance().plus();
		}  catch (IOException e) {
			System.out.println("Unknown errors happended when write to local file!");
		} 

	}

	@Override
	public void execute() {
		// ignore receiver
		try {
			save();
			zipSeq.getInstance().plus();
		} catch (nameNotFoundException e) {
			System.out.println("Please rename your imag file!");
		} 

	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}
}
