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
			System.out.println("��10001�˿ڼ���");
			ss = new ServerSocket(10001);
			//��10001����
			while(true) {	
				//�ȴ��ͻ��˵�����
				Socket s = ss.accept();
				//�пͻ������ӱ���10001�˿�
				System.out.println("�пͻ������ӵ�����10001�˿�");
				//���ܿͻ��˷����Ķ������ڼ���˺������Ƿ���ȷ
				Message respond = null;
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message request = (Message)ois.readObject();
				User user = (User)ois.readObject();
				SentMessageToDB sentmessagetodb = new SentMessageToDB();
				String isSuccess = sentmessagetodb.run(request,user);
				System.out.println("�ӿͻ��˽��ܵ�����"+user.getUserAccount()+" "+user.getUserPassword());
				respond = new Message();
				//���ͻ��������Ƿ�ɹ����ɹ�����Success��ʧ�ܷ���Fail
				if(isSuccess.equals("Success")) {
					respond.setMessage("Success");
				}else {
					respond.setMessage("Fail");
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
