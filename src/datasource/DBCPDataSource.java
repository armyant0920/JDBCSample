package datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBCPDataSource {
	
	public static void main(String[] args) throws Exception {
		
		long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
        	Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Project","sa","manager");
        	conn.close();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("瘝�onnection Pool 甇瑟��: " + (endTime - startTime));
		
	    BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ds.setUrl("jdbc:sqlserver://localhost:1433;databaseName=JDBCDB");
		ds.setUsername("scott");
		ds.setPassword("tiger");
		ds.setMaxTotal(50); //閮剖��憭onnection銝��,頞�蝙��������
		ds.setMaxIdle(50);   //閮剖��憭dle��onnection,頞��onnection��◤�銵onnection.close()

		startTime = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
        	Connection conn = ds.getConnection();
			//System.out.println(ds.getNumActive()+ " connection active");
			conn.close(); //憒�� comment close��ctive connection�����,頞�axTotal,������
		}
        endTime = System.currentTimeMillis();
		System.out.println("BasicDataSource 甇瑟��: " + (endTime - startTime));
		ds.close();

	}
}
