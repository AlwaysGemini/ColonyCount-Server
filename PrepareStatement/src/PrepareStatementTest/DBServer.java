package PrepareStatementTest;

public class DBServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBServer dbserver = new DBServer();
	}
	
	public DBServer()
	{
		ReceiveMessage rm = new ReceiveMessage();
		rm.run();
	}
}
