package test.Form;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.JsonSettingsFile;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;
import java.util.List;

public class DownloadsPage extends Form {

    IButton btnOs;
    IButton btnDownload;
    IButton btnOtherDownloads = getElementFactory().getButton(By.xpath("//button[@data-at-selector='otherDownloads']"),"Other downloads button");
    IButton btnSendToMe = getElementFactory().getButton(By.xpath("//button[@data-at-selector='sendToMe']"),"Send to me button");
    IButton btnInstallerSend = getElementFactory().getButton(By.xpath("//button[@data-at-selector='installerSendSelfBtn']"), "Installer send self button");
    IButton btnSuccessfullySentOk = getElementFactory().getButton(By.xpath("//button[@data-at-selector='successfullySentOkBtn']"), "Success sent Ok button");
    ITextBox txbCurrentUserEmailForSend = getElementFactory().getTextBox(By.xpath("//input[@data-at-selector='emailInput']"), "Current user's email");
    private final String email = new JsonSettingsFile("testConfig.json").getValue("/userEmail").toString();

    public String getEmail() {
        return email;
    }

    public DownloadsPage() {
        super(By.xpath("//div[contains(@class ,'w-osSelect__list')]"), "Downloads page");
    }


    public void clickOsBtn(String os){
        btnOs = getElementFactory().getButton(By.xpath(String.format("//div[@data-at-selector='osName' and contains(text(),'%s')]",os)), "OS button");
        btnOs.click();
    }

    public void clickDownloadBtn(String product){
        btnDownload = getElementFactory().getButton(By.xpath(String.format("//div[@data-at-selector='productName' " +
                "and contains(text(),'%s')]//ancestor::div[@data-at-selector='downloadApplicationCard']" +
                "//button[@data-at-selector='appInfoDownload']", product)),"Download product button");
        btnDownload.click();
    }

    public void clickOtherDownloadsBtn(){
        btnOtherDownloads.click();
    }

    public void clickSendToMeBtn(){
        btnSendToMe.click();
    }

    public boolean isOpenedDownloadDialog(){
        return btnOtherDownloads.state().waitForDisplayed();
    }

    public boolean isOpenedInstallerSendSelfDialog(){
        return btnInstallerSend.state().waitForDisplayed();
    }

    public String getCurrentEmailForSend(){
        return txbCurrentUserEmailForSend.getValue();
    }

    public void clickInstallerSendBtn(){
        try {
            AqualityServices.getConditionalWait().waitForTrue(()->btnInstallerSend.state().isClickable(), Duration.ofSeconds(120),Duration.ofSeconds(1),"Button should be clickable");
        } catch (TimeoutException | java.util.concurrent.TimeoutException e) {
            Logger.getInstance().error("Installer send button is not clickable");
            e.printStackTrace();
        }
        btnInstallerSend.click();
        btnSuccessfullySentOk.click();
    }

}
