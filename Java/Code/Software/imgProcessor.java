package Code.Software;

public class imgProcessor {
    Object img = null;
    static imgProcessor p = null;

    public imgProcessor()
    {

    }

    static public void run()
    {
        p = new imgProcessor();
    }

    public Object getImg()
    {
        return img;
    }
}
