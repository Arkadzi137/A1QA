package test.tests;


import BDUtils.ExecuteQuery;
import Utils.Writer;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.testng.annotations.Test;

import java.sql.SQLException;


public class TestCases {

    @Test
    public static void runTask1() throws SQLException {
        String query = new JsonSettingsFile("testConfig.json").getValue("/query1").toString();
        Logger.getInstance().info("task1 DB");
        Writer.printResult(ExecuteQuery.executeQuery(query));
    }

    @Test
    public static void runTask2() throws SQLException {
        String query = new JsonSettingsFile("testConfig.json").getValue("/query2").toString();
        Logger.getInstance().info("task2 DB");
        Writer.printResult(ExecuteQuery.executeQuery(query));
    }

    @Test
    public static void runTask3() throws SQLException {
        String query = new JsonSettingsFile("testConfig.json").getValue("/query3").toString();
        Logger.getInstance().info("task3 DB");
        Writer.printResult(ExecuteQuery.executeQuery(query));
    }

    @Test
    public static void runTask4() throws SQLException {
        String query = new JsonSettingsFile("testConfig.json").getValue("/query4").toString();
        Logger.getInstance().info("task4 DB");
        Writer.printResult(ExecuteQuery.executeQuery(query));
    }
}
