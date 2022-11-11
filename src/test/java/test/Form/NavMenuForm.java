package test.Form;

import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class NavMenuForm extends Form {
    ITextBox txbMyPage = getElementFactory().getTextBox(By.id("l_pr"), "My page button");

    public NavMenuForm() {
        super(By.id("page_body"), "Navigation menu");
    }

    public void clickMyPageBtn(){
        txbMyPage.state().waitForClickable();
        txbMyPage.click();
    }
}
