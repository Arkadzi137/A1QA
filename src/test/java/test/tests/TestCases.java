package test.tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.Form.MainPage;
import test.tests.BaseTest;

public class TestCases extends BaseTest {

    @Test
    public void dataDriven() {
        MainPage mainPage = new MainPage();


        AqualityServices.getBrowser().goTo("https://my.kaspersky.com");
        AqualityServices.getBrowser().waitForPageToLoad();
        Assert.assertTrue(mainPage.state().waitForDisplayed(), "Main page is opened");
        mainPage.clickBtnOkOnCookieForm();
        System.out.println(mainPage.login);
        mainPage.enterLogin(mainPage.login);

    }

}
