package PrepareStatementTest;

import AndroidServer.User;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user = new User("185547890","123456");
		UserRegister userRegister = new UserRegister();
		userRegister.run(user);
	}

}
