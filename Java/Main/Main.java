import Code.Command.Base.Invoker;
import Code.Command.Commands.SampleCommand;
import Code.Software.Software;

public class Main {
    public static void main(String[] args) {

        Software Software = Code.Software.Software.getInstance();
        Invoker invoker = new Invoker();
        invoker.setCommand(new SampleCommand(Software));
        invoker.execute();
    }
}
