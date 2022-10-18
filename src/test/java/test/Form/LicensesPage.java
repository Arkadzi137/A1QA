package test.Form;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class LicensesPage extends Form {
    public LicensesPage() {
        super(By.id("activationCode"), "Licenses page");
    }

    private final IButton btnDownload = AqualityServices.getElementFactory().getButton(By.xpath("//a[contains(@data-at-menu , 'Downloads')]"),"button Downloads");

    public void clickBtnDownload(){
        btnDownload.click();
    }
}
