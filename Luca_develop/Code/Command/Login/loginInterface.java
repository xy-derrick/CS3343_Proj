package Code.Command.Login;
public interface loginInterface {
    //返回值为登入是否成功
    abstract public boolean login();
    //保存所有imgprocessor的图片到对应用户下
    abstract public void save();
    //如果用户有数据,为用户读取数据
    abstract public void readLastData();

    
}
