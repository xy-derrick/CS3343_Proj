package Code.Software;

public class imgProcessor {
    Object img = null;
    // ArrayList<> undoList = null;
    // ArrayList<> redoList = null;
    private static imgProcessor instance

    private imgProcessor()
    {

    }

    public static imgProcessor getInstance()
    {
        return instance;
    }

    static public void run()
    {
        instance = new imgProcessor();
    }

    Object getImg()
    {
        return img;
    }
}
