package test.tests;

import java.lang.String;
import Model.SendPostModel;
import Utils.ConfigUtils;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.Api.ApiApplicationRequest;
import test.Api.ImageComparisonUtils;
import test.Form.*;

import static test.Api.ApiApplicationRequest.responseJson;

public class TestCases extends BaseTest {

    @Test
    public void testCase() {
        MainPage mainPage = new MainPage();
        PasswordPage passwordPage = new PasswordPage();
        NavMenuForm navMenuForm = new NavMenuForm();
        MyPage myPage = new MyPage();
        final String login = ConfigUtils.getConfidentialData("userLogin");
        final String password = ConfigUtils.getConfidentialData("userPass");
        final String ownerId = ConfigUtils.getTestConfig("ownerId");
        final String photoFolderPath = ConfigUtils.getTestConfig("imagePath");
        final String imageName = ConfigUtils.getTestConfig("uploadImageName");

        String autogenMessage;
        final int stringLength = 10;

        AqualityServices.getBrowser().goTo("https://vk.com/");
        AqualityServices.getBrowser().waitForPageToLoad();
        Assert.assertTrue(mainPage.state().isDisplayed(), "Main Page doesn't open");
        Logger.getInstance().info("Authorization in vk.com");
        mainPage.signIn(login);
        AqualityServices.getBrowser().waitForPageToLoad();
        Assert.assertTrue(passwordPage.state().waitForDisplayed(), "Password Page hasn't been loaded");
        passwordPage.enterPassword(password);
        AqualityServices.getBrowser().waitForPageToLoad();
        Assert.assertTrue(navMenuForm.state().waitForDisplayed(),"Feed Page hasn't been loaded");
        Logger.getInstance().info("Opening 'My page'");
        navMenuForm.clickMyPageBtn();
        AqualityServices.getBrowser().waitForPageToLoad();

        Logger.getInstance().info("Sending request to create post on the wall");
        autogenMessage = RandomStringUtils.randomAlphanumeric(stringLength);
        SendPostModel sendPostModel = ApiApplicationRequest.sendMessageOnTheWall(autogenMessage);
        Assert.assertEquals(responseJson.getStatusCode(), HttpStatus.SC_OK,
                String.format("status code is not %d status is:%d", HttpStatus.SC_OK, responseJson.getStatusCode()));

        PostForm postForm = myPage.getPostForm("API post", sendPostModel.getPostId(), ownerId);
        Assert.assertEquals(postForm.getPostText(), autogenMessage, "Posted text in GUI and sent text through API are not equal");
        Assert.assertTrue(postForm.state().waitForExist(), String.format("Post %s from user %s doesn't exist", sendPostModel.getPostId(), ownerId));

        Logger.getInstance().info("Sending request to edit post on the wall");
        autogenMessage = RandomStringUtils.randomAlphanumeric(stringLength);
        ApiApplicationRequest.editPostWithAttachment(autogenMessage,postForm.getId(),photoFolderPath+imageName);
        ImageComparisonUtils.savePhoto(postForm.getPhotoLink("style"));
        Assert.assertEquals(postForm.getPostText(),autogenMessage,"Posted text in GUI and sent edited text through API are equal");
        Assert.assertEquals(ImageComparisonState.MATCH, ImageComparisonUtils.runComparison().getImageComparisonState(), "Post doesn't contain photo from previous step");

        Logger.getInstance().info("Sending request to leave a comment to post on the wall");
        autogenMessage = RandomStringUtils.randomAlphanumeric(stringLength);
        String commentId = ApiApplicationRequest.sendCommentToPost(autogenMessage, sendPostModel.getPostId());
        Assert.assertEquals(responseJson.getStatusCode(), HttpStatus.SC_OK,
                String.format("status code is not %d status is:%d", HttpStatus.SC_OK, responseJson.getStatusCode()));
        CommentForm sentComment = postForm.newComment("API comment", commentId, ownerId);
        Assert.assertTrue(sentComment.isExisting(), String.format("Post %s from user %s doesn't exist", commentId, ownerId));

        postForm.clickLikeBtn();
        Assert.assertTrue(ApiApplicationRequest.isLikedPost(sendPostModel.getPostId(), ownerId), String.format("Post %s doesn't have 'like reaction' from user %s", sendPostModel.getPostId(), ownerId));
        Assert.assertEquals(responseJson.getStatusCode(), HttpStatus.SC_OK,
                String.format("status code is not %d status is:%d", HttpStatus.SC_OK, responseJson.getStatusCode()));

        Logger.getInstance().info("Sending request to delete post on the wall");
        ApiApplicationRequest.deletePost(sendPostModel.getPostId());
        Assert.assertEquals(responseJson.getStatusCode(), HttpStatus.SC_OK,
                String.format("status code is not %d status is:%d", HttpStatus.SC_OK, responseJson.getStatusCode()));
        Assert.assertTrue(postForm.postIsDeleted(ownerId, sendPostModel.getPostId()), String.format("Post %s from user %s still exist", sendPostModel.getPostId(), ownerId));
    }
}
