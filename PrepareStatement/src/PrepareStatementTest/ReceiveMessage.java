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
			System.out.println("��10002�˿ڼ���");
			ss = new ServerSocket(10002);
		
			while(true) {	
				//�ȴ��ͻ��˵�����
				Socket s = ss.accept();
				//�пͻ������ӱ���10002�˿�
				System.out.println("�пͻ������ӵ�����10002�˿�");
				//���ܿͻ��˷����Ķ������ڼ���˺������Ƿ���ȷ
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message request = (Message)ois.readObject();
				Message respond = null;
				if(request.getMessage().equals("Login")) {
					User user = (User)ois.readObject();
					System.out.println("�ӿͻ��˽��ܵ�����"+user.getUserAccount()+" "+user.getUserPassword());
					CheckUserAccount checkuseraccount = new CheckUserAccount();
					boolean isExist = checkuseraccount.run(user);
					respond = new Message();
					//���ͻ��˷������û���Ϣ�Ƿ���ڣ�����������ƥ������Success��ʧ�ܷ���Fail
					if(isExist) {
						respond.setMessage("Success");
					}else {
						respond.setMessage("Fail");
					}
				}else if(request.getMessage().equals("Register")) {
					User user = (User)ois.readObject();
					System.out.println("�ӿͻ��˽��ܵ�����"+user.getUserAccount()+" "+user.getUserPassword());
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
				//�Ͽ���ͻ��˵�����
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
