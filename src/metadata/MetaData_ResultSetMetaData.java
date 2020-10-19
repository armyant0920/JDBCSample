package metadata;

import java.sql.*;

public class MetaData_ResultSetMetaData {

    public static void main(String[] args) {


        //copy from HelloJDBC7

        try (Connection conn = DriverManager.getConnection(
                "jdbc:sqlserver://localhost;databaseName=JDBCDB",
                "scott", "tiger");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select empno from emp");
        ) {

            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                //取得欄位label
                String label = rsmd.getColumnLabel(i);
                String name = rsmd.getColumnName(i);
                
                int nullable = rsmd.isNullable(i);
                if ( nullable == ResultSetMetaData.columnNullable ){
                    System.out.println(name +"允許空值");
                }else if (nullable == ResultSetMetaData.columnNoNulls ){
                    System.out.println(name +"不允許空值");
                }else if ( nullable == ResultSetMetaData.columnNullableUnknown ){
                    System.out.println(name +"不確定");
                }
                //取得資料庫中欄位的型態以及大小
                String dbType = rsmd.getColumnTypeName(i);
                int displaySize = rsmd.getColumnDisplaySize(i);
                System.out.println("label=" + label + ",name=" + name + ",ColumnTypeName=" + dbType + ",size=" + displaySize);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
