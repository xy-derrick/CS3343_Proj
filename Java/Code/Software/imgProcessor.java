package Code.Software;

public class imgProcessor {
    private Object img = null;
    // ArrayList<> undoList = null;
    // ArrayList<> redoList = null;

    private imgProcessor(Object img)
    {
        this.img=img;
        Code.Software.Software.getInstance().getImgList().add(this);
    }

    public Object getImg()
    {
        return img;
    }
}
