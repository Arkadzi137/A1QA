package test.tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.Test;


public class TestCases extends BaseTest {

    @Test
    public void dataDriven() {

        AqualityServices.getBrowser().goTo("https://vk.com/");
        AqualityServices.getBrowser().waitForPageToLoad();
//        Assert.assertTrue(mainPage.state().waitForDisplayed(), "Main page is opened");
//        Logger.getInstance().info("Authorization in web site");
//        mainPage.performAuthorization(login,password);

    }
}
