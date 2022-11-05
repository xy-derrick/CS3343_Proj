package Code.Command.Commands.HuiyuBAI;

public class ExImgHaveExist extends Exception{
    Integer ip;
    public ExImgHaveExist(Integer ip) {
        this.ip = ip;
    }
    @Override
    public String getMessage() {
        return "The image: "+ip+ " have existed in the batch list, please do not add again. Please check and input again";
    }

}
