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

//    @AfterMethod
//    public void tearDown() {
//        AqualityServices.getBrowser().quit();
//    }
}
