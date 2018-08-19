package PrepareStatementTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import AndroidServer.User;

public class UserRegister extends Thread{

	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection ct = null;
	public boolean run(User user)
	{
		try {
			ct = DriverManager.getConnection("jdbc:odbc:mysqlodbc", "root", "123");
			
			//�ж����ݿ����Ƿ�����Ը��û����ļ�¼
			ps=ct.prepareStatement("select * from clonecountsql.useraccount where UserAccount=?");
			ps.setString(1, user.getUserAccount());
			//�������������ע�����
			if(ps.executeQuery().next()==false) {
				ps=ct.prepareStatement("insert into clonecountsql.useraccount (UserAccount,UserPassword) values(?,?)");
				ps.setString(1, user.getUserAccount());
				ps.setString(2, user.getUserPassword());
				int isSuccess = ps.executeUpdate();
				//����ӳɹ�����true��ʧ�ܷ���false
				if(isSuccess==1) return true;
				else return false;
			}
			//�����������������false
			else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)
				{
					rs.close();
				}
				if(ps!=null)
				{
					ps.close();
				}
				if(ct!=null)
				{
					ct.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return false;
		
		
	}
}