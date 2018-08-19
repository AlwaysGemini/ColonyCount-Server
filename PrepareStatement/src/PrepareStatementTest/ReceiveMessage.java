package PrepareStatementTest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import AndroidServer.Message;
import AndroidServer.User;

public class ReceiveMessage extends Thread{

	public void run() {
		ServerSocket ss = null;
		try {
			System.out.println("在10002端口监听");
			ss = new ServerSocket(10002);
		
			while(true) {	
				//等待客户端的连接
				Socket s = ss.accept();
				//有客户端连接本机10002端口
				System.out.println("有客户端连接到本机10002端口");
				//接受客户端发来的对象，用于检查账号密码是否正确
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message request = (Message)ois.readObject();
				Message respond = null;
				if(request.getMessage().equals("Login")) {
					User user = (User)ois.readObject();
					System.out.println("从客户端接受到对象"+user.getUserAccount()+" "+user.getUserPassword());
					CheckUserAccount checkuseraccount = new CheckUserAccount();
					boolean isExist = checkuseraccount.run(user);
					respond = new Message();
					//检查客户端发来的用户信息是否存在，存在且密码匹配则发送Success，失败发送Fail
					if(isExist) {
						respond.setMessage("Success");
					}else {
						respond.setMessage("Fail");
					}
				}else if(request.getMessage().equals("Register")) {
					User user = (User)ois.readObject();
					System.out.println("从客户端接受到对象"+user.getUserAccount()+" "+user.getUserPassword());
					UserRegister userregister = new UserRegister();
					boolean isSuccess = userregister.run(user);
					respond = new Message();
					if(isSuccess) {
						respond.setMessage("Success");
					}else {
						respond.setMessage("Fail");
					}
					
				}
				
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(respond);
				//断开与客户端的连接
				s.close();		
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(ss!=null)
				{
					ss.close();
				}			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
