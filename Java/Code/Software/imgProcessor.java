package Code.Software;

public class imgProcessor {
    Object img = null;
    // ArrayList<> undoList = null;
    // ArrayList<> redoList = null;
    static imgProcessor p = null;

    private imgProcessor()
    {

    }

    public static imgProcessor getInstance()
    {
        return p;
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
