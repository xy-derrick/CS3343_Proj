package Java.Code.Command.Export;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import Java.Code.Command.Base.*;
import Java.Code.Software.imgProcessor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipOutputStream;
import Java.Code.exportException.*;
import javax.imageio.ImageIO;

public class imagCompress extends Command implements CommandCancelable  {
	private String path;

	public imagCompress(imgProcessor receiver, String path) {
		super(receiver);
		this.path = path;
	}

	public imagCompress(imgProcessor receiver, ArrayList<Object> args) {
		super(receiver);
		this.path = (String) args.get(0);
	}

	public String getName(String localPath) {
		String fName = localPath.trim();
		return fName.substring(fName.lastIndexOf("/") + 1);
	}

	public String getType(String name) {
		return name.substring(name.lastIndexOf(".") + 1);
	}

	public void compress() throws nameNotFoundException {
		/*
		 * 将图片文件读入到文件流中， 创建zip文件以及文件中的图片目标文件 将文件流写入zip
		 */
		try {
			String localPath = iProcessor.getPath();
			int seq = zipSeq.getInstance().getSeq();

			// 得到原始文件名以及类型
			String name = null;
			String type = null;
			if (localPath.isEmpty()) {
				name = iProcessor.getName();
				type = getType(name);
			} else {
				name = getName(localPath);
				type = "jpg";
				name = name + ".jpg";
			}

			if (name.length() == 4 || name.equals(".tiff")) {
				throw new nameNotFoundException();
			}

			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(iProcessor.getImg(), type, os);
			ByteArrayInputStream file = new ByteArrayInputStream(os.toByteArray());
			path = path + "/" + name.substring(0, name.lastIndexOf(".")) + "_" + String.valueOf(seq) + ".zip";
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(path));
			out.putNextEntry(new ZipEntry(name));
			int len;
			byte[] buffer = new byte[1024];
			while ((len = file.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			out.closeEntry();
			file.close();
			out.close();
			System.out.println("Create zip file successfully!\n");
		} catch (IOException e) {
			System.out.println("Can't get imag from the imag Processor or some other error occurs!");
		}

	}

	@Override
	public void execute() {
		// ignore receiver
		try {
			compress();
			zipSeq.getInstance().plus();
		} catch (nameNotFoundException e) {
			System.out.println("Please check and input again!");
		}
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}
}
