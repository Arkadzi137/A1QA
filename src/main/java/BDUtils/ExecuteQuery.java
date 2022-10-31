package BDUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecuteQuery {
     public static ResultSet executeQuery(String query) throws SQLException {
         Connection conn = JDBCconnection.getConnection();
         Statement statement = conn.createStatement();
         ResultSet rs = statement.executeQuery(query);
         return rs;
     }
}
