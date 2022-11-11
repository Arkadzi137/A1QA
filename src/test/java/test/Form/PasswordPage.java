package test.Form;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class PasswordPage extends Form {
    ITextBox txbPass = getElementFactory().getTextBox(By.xpath("//input[@type = 'password']"),"Password field");
    IButton btnContinue = getElementFactory().getButton(By.xpath("//button[@type = 'submit']"),"Continue button");

    public PasswordPage() {
        super(By.xpath("//div[contains(@class, 'vkuiPanel__in')]"), "Password Page");
    }

    public void enterPassword(String password){
        txbPass.clearAndType(password);
        btnContinue.click();
    }
}
