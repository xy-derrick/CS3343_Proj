package Code.Command.Commands.HuiyuBAI;

public class ExWrongBatchType extends Exception {
    public ExWrongBatchType() {
        
    }

    public String getMessage() {
        return "Please begin your input as img>> or cmd>>\n";
    }

}
