package Test;
import java.sql.*;
public class PrepareStatementTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection ct = null;
		
		    
		try {
			
			//1.加载驱动
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			//2、得到连接【指定连接到那个数据源，用户名和密码】
			ct = DriverManager.getConnection("jdbc:odbc:mysqlodbc", "root", "123");
			
			//查找数据库中是否存在账号密码都相互匹配的记录
			ps=ct.prepareStatement("select * from clonecountsql.useraccount where UserAccount=? and UserPassword=?");
			ps.setString(1, "185547890");
			ps.setString(2, "123456");
			rs=ps.executeQuery();
			//若存在则输出改记录，否则输出“不存在”
			if(rs.next()==false) System.out.println("不存在");
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
