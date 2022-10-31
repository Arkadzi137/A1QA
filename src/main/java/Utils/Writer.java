package Utils;

import aquality.selenium.core.logging.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Writer {

    public static void printResult(ResultSet rs) throws SQLException {
        while(rs.next())
        {
            StringBuilder result = new StringBuilder();
            for (int i = 1; i <= rs.getMetaData().getColumnCount() ; i++) {
                result.append(" |"+rs.getString(rs.getMetaData().getColumnLabel(i))+"| ");
            }
            Logger.getInstance().info(String.valueOf(result));
        }
    }
}
