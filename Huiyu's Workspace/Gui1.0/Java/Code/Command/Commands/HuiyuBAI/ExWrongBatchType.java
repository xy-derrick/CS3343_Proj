package Code.Command.Commands.HuiyuBAI;

public class ExWrongBatchType extends Exception {
    public ExWrongBatchType() {
        
    }
    @Override
    public String getMessage() {
        return "Please begin your input as img>> or cmd>>\n";
    }

}
