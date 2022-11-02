package Code.Command.Login;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import ImgUtil.ImgUtil;
import JDBCUtils.JDBCUtils;
import JDBCUtils.jdbc_test;

class loginController implements loginInterface{
    //用户数据sample
    private int uid;
    private int latest_img_id = 0;
    private String username;
    private String password; 

    @Override
    public boolean login(){
    	 try (Scanner scanner = new Scanner(System.in)) {
  			System.out.print("Input your user name: ");
  			username = scanner.next();
  			System.out.print("Input your your password:：");
  			password = scanner.next();

  			boolean flag = new jdbc_test().login(username, password);
  			while(!flag) {
  			    System.out.print("Login fail,Please reinput the usename：");
  			    username = scanner.next();
  			    System.out.print("Please inpit your password：");
  			    password = scanner.next();
  			    flag = new jdbc_test().login(username, password);
  			}
  			uid =  new jdbc_test().getId(username);
  		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          System.out.println("Sucessful login");
        return true;
    }

    @Override
    public void save() {
    	String uid_s = getUid()+"";
    	//ImgUtil.saveImginDb(uid_s,);//第二个蚕参数写目标地址、（打算改imagebuffer 还没完成）
    }

    @Override
    public void readLastData() {
    	if(getLatestImgId()==0)
    	{
    		System.out.print("No latest image.");
    		return;
    	}
    	else {
    		String img_id= getLatestImgId()+"";
        	String uid_s = getUid()+"";
        	try {
    			ImgUtil.getImgfromDb(uid_s,img_id);
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	
        
    }
    //完成接口后,在main流程内为software生成一个loginController
    //并完成以下调用 1.循环判断登入是否成功 
    //2.多线程一个计时器每1min储存一次或cnt记录每5个command储存一次,推荐计时1min 
    //3.登入后数据库查询是否有数据,有->询问用户是否读取上次数据 Yes->读取上次数据生成imgProcessor,不能undo

    //辅助函数
    public int getLatestImgId()
    {
    	Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getCounection();
            //define sql
            String sql = "select latest_img_id from user where name='" + username + "' and password ='" + password + "' ";
            //get the executed sql object 
            stmt = conn.createStatement();
            //query
            rs = stmt.executeQuery(sql);

            if(rs.next())
            {
            	rs.last();
            	String latest_img_id_s = rs.getString("latest_img_id");
            	latest_img_id = Integer.parseInt(latest_img_id_s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
				JDBCUtils.close(rs, stmt, conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        System.out.print("The latest image id is: "+ latest_img_id);
        return latest_img_id;   
    }
    public int getUid(){
    	return uid;
    }
    public String  getUsername(){
    	return username;
    }
}