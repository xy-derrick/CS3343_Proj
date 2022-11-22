package Java.Code.Exception;

import Java.Code.Command.Batch.batchProcessor;

public class ExImgNotExist extends Exception {
	Integer idx;

	public ExImgNotExist(Integer idx) {
//		this.idx = idx;
//		String msg = "The index " + idx + " of image is not in the batch image list.\n"
//				+ "The image idx(es) in batch image buffer are: " + batchProcessor.getInstance().ImgBufferToStr() + "\n"
//				+ "Please check and input again";
//		System.out.println(msg);
	}

}
