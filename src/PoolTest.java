import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class PoolTest {

    public static void main(String[] args) {
        BasicDataSource ds=new BasicDataSource();
        ds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        ds.setUrl("jdbc:sqlserver://1433;databaseName=JDBCDB");
        ds.setUsername("scott");
        ds.setPassword("tiger");
        ds.setMaxTotal(50);
        ds.setMaxIdle(50);
        try(

                BasicDataSource ds1=ds;
                Connection conn=ds.getConnection();

                Statement st=(Statement) conn.createStatement();
                ResultSet rs=st.executeQuery("select*from emp");
                PreparedStatement ps=conn.prepareStatement("update emp set commission=?")
                ) {

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
