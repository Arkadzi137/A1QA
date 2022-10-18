package test.tests;

import Utils.ReadFiles;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.testng.annotations.*;

import java.util.List;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        Browser browser = AqualityServices.getBrowser();
        browser.maximize();
    }

    @DataProvider(name = "ListOfOsAndApp")
    public static Object[][] ListOfUsers() {
        Object[][] obj = new Object[ReadFiles.getOsAndApps().size()][1];
        for (int i = 0; i < ReadFiles.getOsAndApps().size(); i++) {
            obj[i][0]=ReadFiles.getOsAndApps().get(i);
        }
        return obj;
    }

    @AfterMethod
    public void tearDown() {
        AqualityServices.getBrowser().quit();
    }
}
