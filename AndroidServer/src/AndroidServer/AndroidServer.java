package AndroidServer;


public class AndroidServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AndroidServer as = new AndroidServer();
	}

	public AndroidServer()
	{
		ServerLisener sl = new ServerLisener();
		sl.start();
	}
}
