package Code.Command.Login;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.*;

import Code.Command.Commands.Common.displayImg;
import Code.Software.Software;
import Code.Software.imgProcessor;
import ImgUtil.ImgUtil;
import JDBCUtils.JDBCUtils;
import JDBCUtils.jdbc_test;

class loginController implements loginInterface{
    //用户数据sample
    private int uid;
    private int latest_img_id = 0;
    private String username;
    private String password; 

    loginController()
    {
    	
    }
    public static void main(String args[])throws IOException, SQLException
    {
    	BufferedImage imag = null;
    	loginController l =new loginController();
    	l.login(imag);
    }
    
    @Override
    public void login(BufferedImage imag){
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
          imag = readLastData(imag);  
          //JFrame jf= new JFrame();
         // JLabel jl=new JLabel();
          //jl.setIconTextGap();
          
          imgProcessor ip= new imgProcessor();
          ip.setImg(imag);
          Software main_software = Software.getInstance();
          main_software.setCommand(new displayImg(ip));
          main_software.execute();
    }
    
    @Override
	public void register() {
		 try (Scanner scanner = new Scanner(System.in)) {
	 			System.out.print("Input your user name:");
	 			String username = scanner.next();
	 			System.out.print("Input your your password:：");
	 			String password = scanner.next();

	 			boolean flag = new jdbc_test().register(username, password);
	 			while(!flag) {
	 			    System.out.print("Register fail,Please reinput the usename：");
	 			    username = scanner.next();
	 			    System.out.print("Please inpit your password：");
	 			    password = scanner.next();
	 			    flag = new jdbc_test().register(username, password);
	 			}
	 		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         System.out.println("Sucessful register");
	}
    
    @Override
    //取当前的image buffer存到 sql 每分钟存一次
    public void save(BufferedImage imag) {
    	String uid_s = getUid()+"";
    	try {
			ImgUtil.saveImginDb(uid_s,imag,"jpg");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//第二个蚕参数写目标地址、（打算改imagebuffer 还没完成）
    }

    @Override
    public BufferedImage readLastData(BufferedImage imag) {
    	if(getLatestImgId()==0)
    	{
    		System.out.print("No latest image.");
    		return null;
    	}
    	else {
    		String img_id= getLatestImgId()+"";
        	String uid_s = getUid()+"";
            System.out.print("The latest image id is: "+ latest_img_id+"\n");
        	try {
        		imag=ImgUtil.getImgfromDb(uid_s,imag, img_id);
        		System.out.print(imag.getHeight());
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} finally {
    			return imag;
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
            String sql = "select id from user_operation where id = (select max(id) from user_operation where user_id=" + uid +")";
            //select id from user_operation where id = (select max(id) from user_operation where user_id= uid)
            
            //get the executed sql object 
            stmt = conn.createStatement();
            //query
            rs = stmt.executeQuery(sql);
           
            if(rs.next())
            {
            	//rs.previous();
            	String latest_img_id_s = rs.getString(1);
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
        return latest_img_id;   
    }
    public int getUid(){
    	return uid;
    }
    public String  getUsername(){
    	return username;
    }

	
}