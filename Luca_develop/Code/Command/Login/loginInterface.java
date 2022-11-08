package Code.Command.Login;

import java.awt.image.BufferedImage;

public interface loginInterface {
    //返回值为登入是否成功
    abstract public void login(BufferedImage imag);
    abstract public void register();
    //保存所有imgprocessor的图片到对应用户下
    abstract public void save(BufferedImage imag);
    //如果用户有数据,为用户读取数据
    abstract public BufferedImage readLastData(BufferedImage imag);

    
}
