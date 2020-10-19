import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class HelloJDBC7_Try_Resource {

	public static void main(String[] args) {
		
		//撠��������神�try銝�
		try ( Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Project","sa","manager");
				) {
			try (Statement stmt = conn.createStatement();
				 ResultSet rs =  stmt.executeQuery("select * from emp")){
				while ( rs.next()){
					int empno = rs.getInt("empno");
					String name = rs.getString("ename");
					System.out.println("empno="+empno+",name="+name);
				}
			}catch(SQLException ex){
			    conn.rollback();
			}catch (Exception ex){
				conn.rollback();
			}
			System.out.println("query finished");
		} catch (SQLException e) {
			e.printStackTrace();
		}//�銵��onnection��tmt�������,銝��神finally�銵�����
		
		
		//http://docs.oracle.com/javase/7/docs/technotes/guides/jdbc/jdbc_41.html
		System.out.println("test the connection!,it's gone!");
		
	}

}
