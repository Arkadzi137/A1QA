package test.tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    @BeforeTest
    public void setUp() {
        Browser browser = AqualityServices.getBrowser();
        browser.maximize();
    }


    @AfterMethod
    public void tearDown() {
//        AqualityServices.getBrowser().quit();
    }
}
