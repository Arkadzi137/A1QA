package test.Form;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class CommentForm extends Form {
    private final String id;
    private final String authorId;
    private final String postId;
    private final IButton btnShowNextReplies;
    private final static String locatorShowNextReplie = "//div[@id='page_wall_posts']/div[@data-post-id='%s_%s']//child::a[contains(@class,'replies_next_main')]";

    protected CommentForm(String name, String id, String authorId, String postId, String postAuthorId) {
        super(By.xpath(String.format("//div[@id='page_wall_posts']//child::div[contains(@class,'replies_list')]/div[@data-post-id='%s_%s' and contains(@class,'reply')]", authorId, id)), name);
        this.id = id;
        this.postId = postId;
        this.authorId = authorId;
        btnShowNextReplies = getElementFactory().getButton(By.xpath(String.format(locatorShowNextReplie, postAuthorId, postId)), "Next replies button");
    }

    public boolean isExisting() {
        if (btnShowNextReplies.state().waitForDisplayed()) {
            btnShowNextReplies.click();
        }
        return this.state().waitForDisplayed();
    }

}
