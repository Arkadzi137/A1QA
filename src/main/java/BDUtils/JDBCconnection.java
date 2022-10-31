package BDUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class JDBCconnection {

    public static Connection getConnection() {
        try {
            String url = new JsonSettingsFile("testConfig.json").getValue("/url").toString();
            String username = new JsonSettingsFile("testConfig.json").getValue("/username").toString();;
            String password = new JsonSettingsFile("testConfig.json").getValue("/password").toString();;
            Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            Logger.getInstance().info("Connected to union_reporting DB");
            return conn;
        } catch (Exception ex) {
            Logger.getInstance().info("Connection failed...");
            Logger.getInstance().info(ex.toString());
            return null;
        }
    }
}
