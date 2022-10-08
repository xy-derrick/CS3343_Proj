package Code.Command.Commands.Export;

public class zipSeq {
    private static int seq;
    private static zipSeq instance=new zipSeq();

    private zipSeq()
    {
        seq=1;
    }

    public static zipSeq getInstance()
    {
        return instance;
    }

    public void plus()
    {
        seq++;
    }

    public int getSeq()
    {
        return seq;
    }
}
