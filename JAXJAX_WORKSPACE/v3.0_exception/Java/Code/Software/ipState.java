package Code.Software;

import java.util.ArrayList;

public class ipState {
    private ArrayList<imgProcessor> imgProcessorList = null;
    private imgProcessor main_ip = null;

    public ipState(ArrayList<imgProcessor> imgProcessorList,imgProcessor main_ip)
    {
        if(imgProcessorList==null){this.imgProcessorList = null;}
        else{this.imgProcessorList = (ArrayList<imgProcessor>) imgProcessorList.clone();}
        this.main_ip = main_ip;
    }

    public ArrayList<imgProcessor> getImgProcessorList() {
        return imgProcessorList;
    }
    public void setImgProcessorList(ArrayList<imgProcessor> imgProcessorList) {
        this.imgProcessorList = imgProcessorList;
    }
    public imgProcessor getMain_ip() {
        return main_ip;
    }
    public void setMain_ip(imgProcessor main_ip) {
        this.main_ip = main_ip;
    }
}
