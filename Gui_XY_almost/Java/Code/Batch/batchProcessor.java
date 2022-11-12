package Java.Code.Batch;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Java.Code.Exception.ArgsInvalidException;
import Java.Code.Software.Software;
import Java.Code.Software.imgProcessor;

public class batchProcessor implements Observable {
	private static batchProcessor bp = new batchProcessor();

	private batchProcessor() {
	}

	public static batchProcessor getInstance() {
		return bp;
	}

	private ArrayList<Integer> imgBuffer = new ArrayList<Integer>();
	private ArrayList<transCommand> cmdBuffer = new ArrayList<transCommand>();
	private Integer cmdSize = 0;
	private imgProcessor mainIp;
	private ArrayList<notifyIP> batchedImg = new ArrayList<notifyIP>();

	ArrayList<Integer> getImgBuffer() {
		return imgBuffer;
	}

	public void addElement(transCommand c) {
		cmdBuffer.add(c);
		System.out.printf("add command(s) successfully, now %d command(s) in processor.\n", cmdBuffer.size());
	}

	Integer getCmdSize() {
		return cmdBuffer.size() * imgBuffer.size();
	}

	public void addElement(Integer ip) {

		imgBuffer.add(ip);
		System.out.printf("add img(s) successfully, now %d img(s) in processor.\n", imgBuffer.size());
	}

	public void deleteElement(transCommand c) {

		cmdBuffer.remove(c);
		System.out.printf("delete cmd(s) successfully, now %d cmd(s) in processor.\n", cmdBuffer.size());

	}

	public void deleteElement(Integer i) {

		imgBuffer.remove(i);
		System.out.printf("delete img(s) successfully, now %d img(s) in processor.\n", imgBuffer.size());

	}

	public BufferedImage preview() throws ArgsInvalidException {

		// get edit command
		ArrayList<transCommand> temp = new ArrayList<transCommand>();
		for (transCommand i : cmdBuffer) {
			if (transCommand.checkPreviewable(i))
				temp.add(i);
		}
		// get Img copy
		Integer previewIP = imgBuffer.get(0);
		imgProcessor ip = Software.getInstance().getImgProcessorList().get(previewIP);
		notifyIP imgCopy = new notifyIP(ip);
		imgCopy.previewDisplay(temp);

		return imgCopy.getImg();

	}

	public void notifyAlls() throws ArgsInvalidException {
		cmdSize = getCmdSize();
		System.out.printf("Now we will execute %d commands...\n", cmdSize);

		mainIp = Software.getInstance().getMain_ip();

		for (Integer i : imgBuffer) {
			imgProcessor ip = Software.getInstance().getImgProcessorList().get(i);
			notifyIP p = new notifyIP(ip);
			p.update();
			batchedImg.add(p);
		}
		for (Integer i = 0; i < batchedImg.size(); i++) {
			Integer imgIdx = imgBuffer.get(i);
			BufferedImage bf = batchedImg.get(i).getImg();
			Software.getInstance().getImgProcessorList().get(imgIdx).setImg(bf);
		}
		Software.getInstance().setMain_ip(mainIp);
	}

	public Boolean checkPreviewable() {
		int countForEditCommand = 0;
		for (transCommand i : cmdBuffer) {
			if (transCommand.checkPreviewable(i))
				countForEditCommand++;
		}
		if (countForEditCommand == 0)
			return false;
		return true;
	}

	public ArrayList<BufferedImage> getBatchResult() {
		ArrayList<BufferedImage> imgs = new ArrayList<>();
		for (notifyIP ip : batchedImg)
			imgs.add(ip.getImg());
		return imgs;

	}

	public void reset() {

		imgBuffer = new ArrayList<Integer>();
		;
		cmdBuffer = new ArrayList<transCommand>();
		;
		cmdSize = 0;
		mainIp = null;

		info();
	}

	void info() {
		System.out.printf("imgBuffer has %d elements,\ncmdBuffer has %d elements\n", imgBuffer.size(),
				cmdBuffer.size());

	}

	public ArrayList<transCommand> getCmdBuffer() {
		return cmdBuffer;
	}

	public String ImgBufferToStr() {
		String buffer = "";
		for (Integer img : imgBuffer) {
			buffer += img + ", ";
		}
		return buffer.substring(0, buffer.length() - 2);
	}

	public String cmdBufferToStr() {
		String buffer = "";
		for (transCommand sc : cmdBuffer) {
			buffer += sc.toString() + ", ";
		}
		return buffer.substring(0, buffer.length() - 2);
	}

}
