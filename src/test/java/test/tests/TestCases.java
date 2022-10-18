package test.tests;

import Model.OsAndApp;
import Utils.MailUtils;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.Form.DownloadsPage;
import test.Form.LicensesPage;
import test.Form.MainPage;


public class TestCases extends BaseTest {

    @Test(dataProvider = "ListOfOsAndApp", dataProviderClass = BaseTest.class)
    public void dataDriven(OsAndApp osAndApp) {
        MainPage mainPage = new MainPage();
        LicensesPage licensesPage = new LicensesPage();
        DownloadsPage downloadsPage = new DownloadsPage();

        AqualityServices.getBrowser().goTo("https://my.kaspersky.com");
        AqualityServices.getBrowser().waitForPageToLoad();
        Assert.assertTrue(mainPage.state().waitForDisplayed(), "Main page is opened");
        mainPage.clickBtnOkOnCookieForm();
        Logger.getInstance().info("Authorization in web site");
        mainPage.enterLogin(mainPage.getLogin());
        mainPage.enterPassword(mainPage.getPassword());
        mainPage.clickBtnSignIn();
        Assert.assertTrue(licensesPage.state().waitForDisplayed(),"Licenses page is opened" );
        Logger.getInstance().info("Going to Downloads tab");
        licensesPage.clickBtnDownload();
        Assert.assertTrue(downloadsPage.state().waitForDisplayed(),"Downloads page is opened" );
        Logger.getInstance().info("Choosing operation system: "+osAndApp.getOs());
        downloadsPage.clickOsBtn(osAndApp.getOs());
        Logger.getInstance().info("Pressing download for "+osAndApp.getApp()+" product");
        downloadsPage.clickDownloadBtn(osAndApp.getApp());
        Assert.assertTrue(downloadsPage.isOpenedDownloadDialog(), "Download dialog is not opened");
        Logger.getInstance().info("Choosing send link for download by email");
        downloadsPage.clickOtherDownloadsBtn();
        downloadsPage.clickSendToMeBtn();
        Assert.assertTrue(downloadsPage.isOpenedInstallerSendSelfDialog(), "Installer send self dialog is not opened");
        Assert.assertEquals(downloadsPage.getCurrentEmailForSend(),downloadsPage.getEmail(), "Current email for send doesn't match with user's email");
        Logger.getInstance().info("Press 'Send' and checking email for new message with product's link");
        downloadsPage.clickInstallerSendBtn();
        String emailText = MailUtils.getTextFromLatestMessage();
        System.out.println("-----------"+emailText);
        Assert.assertTrue(emailText.contains(osAndApp.getApp()), "Email text doesn't contain name of product");
    }
}
