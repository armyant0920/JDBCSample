import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class HelloJDBC {

	public static void main(String[] args) {
		
		//�閰㏎QL Server��蝺嚗Ⅱ隤��eaking: SELECT session_id,auth_scheme,client_net_address,client_tcp_port, local_net_address,local_tcp_port,connect_time FROM sys.dm_exec_connections order by connect_time desc
		/*try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}*/
		/*
		try {
			Driver driver = new com.microsoft.sqlserver.jdbc.SQLServerDriver();
			DriverManager.registerDriver(driver);
		} catch (SQLException e) {
			e.printStackTrace();
		}
          */
		Calendar calendar = new GregorianCalendar(2020, 0, 1);
		java.sql.Date sqlDate = new Date(calendar.getTimeInMillis());
		Connection conn = null;
		Statement stmt  = null;
		ResultSet rs = null;
		//"jdbc:sqlserver://10.31.25.3:1433;databaseName=JDBCDB","scott","tiger"
		try {
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Project","sa","manager");
			stmt  = conn.createStatement();
			rs =  stmt.executeQuery("select * from emp");
			while ( rs.next()){
				int empno = rs.getInt("empno");
				String name = rs.getString("ename");
				System.out.println("empno="+empno+",name="+name);
			}
			System.out.println("query finished");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if ( rs != null){
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if ( stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if ( conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

}
