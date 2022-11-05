package Code.Command.Commands.HuiyuBAI;

public class ExNoCmdForPreview extends Exception{
    @Override
    public String getMessage() {
        return "There is no command for preview.";
    }
}
