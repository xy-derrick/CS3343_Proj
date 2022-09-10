import Code.Command.Base.Invoker;
import Code.Command.Commands.SampleCommand;
import Code.Software.imgProcessor;

public class Main {
    public static void main(String[] args) {

        imgProcessor.run();
        imgProcessor iProcessor = imgProcessor.getInstance();
        Invoker invoker = new Invoker();
        invoker.setCommand(new SampleCommand(iProcessor));
        invoker.execute();
    }
}
