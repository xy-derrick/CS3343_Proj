package Java.Code.Exception;

public class ExImgHaveExist extends Exception {
	Integer ip;

	public ExImgHaveExist(Integer ip) {
		this.ip = ip;
		System.out.println("The image: " + ip
				+ " have existed in the batch list, please do not add again. Please check and input again");
	}

}
