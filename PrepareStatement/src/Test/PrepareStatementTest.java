package Test;
import java.sql.*;
public class PrepareStatementTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection ct = null;
		
		    
		try {
			
			//1.��������
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			//2���õ����ӡ�ָ�����ӵ��Ǹ�����Դ���û��������롿
			ct = DriverManager.getConnection("jdbc:odbc:mysqlodbc", "root", "123");
			
			//�������ݿ����Ƿ�����˺����붼�໥ƥ��ļ�¼
			ps=ct.prepareStatement("select * from clonecountsql.useraccount where UserAccount=? and UserPassword=?");
			ps.setString(1, "185547890");
			ps.setString(2, "123456");
			rs=ps.executeQuery();
			//������������ļ�¼����������������ڡ�
			if(rs.next()==false) System.out.println("������");
			else System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	}

}
