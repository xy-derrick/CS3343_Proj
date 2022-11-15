package Java.Code.Software;

import java.awt.image.BufferedImage;

public class imgProcessor {
	BufferedImage img = null;
	String name;

	private String localPath;

	public imgProcessor() {
		Software.getInstance().addImgProcessor(this);
	}

	public imgProcessor(String name) {
		this.name = name;
		Software.getInstance().addImgProcessor(this);
	}

	public imgProcessor(int temp) {
	}

	public void setImg(BufferedImage bufImage) {
		img = bufImage;
		// init
		System.out.println("img file init...");
	}

	public String getName() {
		return this.name;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setPath(String text) {
		this.localPath = text;
	}

	public String getPath() {
		return localPath;
	}
}
