package test.Form;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MyPage extends Form {

    public MyPage() {
        super(By.xpath("//div[@class='Profile ProfileBase']"), "My Page");
    }

    public PostForm getPostForm(String message, String id, String authorId) {
        return new PostForm(message, id, authorId);
    }
}
