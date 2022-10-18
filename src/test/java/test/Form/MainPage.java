package test.Form;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.JsonSettingsFile;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainPage extends Form {

    public MainPage() {
        super(By.xpath("//app[contains(@class ,'welcome-main')]"), "Main page");
    }

    private final String login = new JsonSettingsFile("testConfig.json").getValue("/userEmail").toString();
    private final String password = new JsonSettingsFile("testConfig.json").getValue("/userPassword").toString();
    private final ILabel cookiesMassegeForm = AqualityServices.getElementFactory().getLabel(By.id("CybotCookiebotDialog"),"cookie box");
    private final IButton btnOkOnCookieForm = AqualityServices.getElementFactory().getButton(By.id("CybotCookiebotDialogBodyLevelButtonAccept"),"button OK");
    private final ITextBox inputEmail = AqualityServices.getElementFactory().getTextBox(By.xpath("//input[@name ='email']"),"input email");
    private final ITextBox inputPassword = AqualityServices.getElementFactory().getTextBox(By.xpath("//input[@name ='password']"),"input password");
    private final IButton btnSignIn = AqualityServices.getElementFactory().getButton(By.xpath("//button[contains(@data-omniture-cta-name , 'Sign in')]"),"button Sign in");

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void clickBtnOkOnCookieForm(){
        if (cookiesMassegeForm.state().isDisplayed()) btnOkOnCookieForm.click();
    }

    public void enterLogin(String email){
        inputEmail.clearAndType(email);
    }

    public void enterPassword(String password){
        inputPassword.clearAndType(password);
    }

    public void clickBtnSignIn(){
        btnSignIn.click();
    }

}
