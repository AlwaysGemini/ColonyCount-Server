package AndroidServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SentMessageToDB extends Thread{

	public String run(Message request ,Object object) {
		try {
			Socket socket = new Socket("127.0.0.1",10002);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectOutputStream.writeObject(request);
            objectOutputStream.writeObject(object);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Message respond = (Message)objectInputStream.readObject();
            return  respond.getMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
		return "error";
	}

}
