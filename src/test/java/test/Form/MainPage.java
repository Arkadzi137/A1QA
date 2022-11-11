package test.Form;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import static aquality.selenium.browser.AqualityServices.getElementFactory;

public class MainPage extends Form {
    ITextBox txbLogin = getElementFactory().getTextBox(By.id("index_email"),"Login field");
    IButton btnLogin = getElementFactory().getButton(By.xpath("//button[contains(@class, 'signInButton')]"),"Sign in button");

    public MainPage() {
        super(By.id("index_login"), "Main Page");
    }

    public void signIn(String login){
        txbLogin.clearAndType(login);
        btnLogin.click();
    }
}
