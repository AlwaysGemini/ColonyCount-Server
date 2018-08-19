package PrepareStatementTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import AndroidServer.User;
public class CheckUserAccount extends Thread{

	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection ct = null;
	public boolean run(User user)
	{
		try {
			ct = DriverManager.getConnection("jdbc:odbc:mysqlodbc", "root", "123");
			//�������ݿ����Ƿ�����˺����붼�໥ƥ��ļ�¼
			ps=ct.prepareStatement("select * from clonecountsql.useraccount where UserAccount=? and UserPassword=?");
			ps.setString(1, user.getUserAccount());
			ps.setString(2, user.getUserPassword());
			rs=ps.executeQuery();
			//�������򷵻�true�����򷵻�false
			if(rs.next()==false) return false;
			else return true;
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
