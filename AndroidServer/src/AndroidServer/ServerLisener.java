package AndroidServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerLisener extends Thread{

	public void run() {
		ServerSocket ss = null;
		try {
			System.out.println("在10001端口监听");
			ss = new ServerSocket(10001);
			//在10001监听
			while(true) {	
				//等待客户端的连接
				Socket s = ss.accept();
				//有客户端连接本机10001端口
				System.out.println("有客户端连接到本机10001端口");
				//接受客户端发来的对象，用于检查账号密码是否正确
				Message respond = null;
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message request = (Message)ois.readObject();
				User user = (User)ois.readObject();
				SentMessageToDB sentmessagetodb = new SentMessageToDB();
				String isSuccess = sentmessagetodb.run(request,user);
				System.out.println("从客户端接受到对象"+user.getUserAccount()+" "+user.getUserPassword());
				respond = new Message();
				//检查客户端请求是否成功，成功发送Success，失败发送Fail
				if(isSuccess.equals("Success")) {
					respond.setMessage("Success");
				}else {
					respond.setMessage("Fail");
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
		}finally {
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
