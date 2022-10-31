package test.tests;

import Model.PersonModel;
import Enum.StatusCodes;
import Model.UserModel;
import Utils.RandomUtils;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static Utils.JSONUtils.fileIsJson;
import static Utils.ModelsUtils.*;
import static Utils.ReadFiles.getExpectedJson;
import static test.API.ApiApplicationRequest.*;

public class TestCases {

    UserModel[] userModels = null;

    @Test
    public void testCase() {
        AqualityServices.getLogger().info("Send a GET request to get all posts (/posts)");
        PersonModel[] persons = getJsonPersons();
        Assert.assertEquals(responseJson.getStatusCode(), StatusCodes.OK.getStatus(),
                "status code is not "+ StatusCodes.OK.getStatus()+" status is:"+ responseJson.getStatusCode());
        Assert.assertTrue(fileIsJson(responseJson.getBody().toString()), "file the file is not json");
        Assert.assertTrue(dataIsSortedById(persons), "the tags are not in ascending order");


        SoftAssert softAssert = new SoftAssert();
        int numberOfGetPerson = (int)new JsonSettingsFile("testConfig.json").getValue("/personGet");

        AqualityServices.getLogger().info("Send a GET request to receive post /posts/"+numberOfGetPerson);
        PersonModel actualPerson = getJsonPerson(numberOfGetPerson);
        PersonModel expectedPerson = getJsonPersonFromFile(getExpectedJson("expectedPost99"));
        Assert.assertEquals(responseJson.getStatusCode(), StatusCodes.OK.getStatus(),
                "status code is not "+StatusCodes.OK.getStatus()+" status is:"+ responseJson.getStatusCode());
        softAssert.assertEquals(actualPerson.getUserId(),expectedPerson.getUserId(),"userId don't match");
        softAssert.assertEquals(actualPerson.getId(),expectedPerson.getId(), "id don't match");
        softAssert.assertFalse(actualPerson.getBody().isEmpty(), "body is empty");
        softAssert.assertFalse(actualPerson.getTitle().isEmpty(), "title is empty");
        softAssert.assertAll();


        numberOfGetPerson = (int)new JsonSettingsFile("testConfig.json").getValue("/nullPersonGet");

        AqualityServices.getLogger().info("Send a GET request to receive post "+numberOfGetPerson);
        PersonModel personModelEmpty = getJsonPerson(numberOfGetPerson);
        Assert.assertEquals(responseJson.getStatusCode(), StatusCodes.EMPTY.getStatus(),
                "status code is not "+StatusCodes.EMPTY.getStatus()+" status is:"+ responseJson.getStatusCode());
        Assert.assertTrue(getEmptyBodyPerson(personModelEmpty), "the request body is not empty");


        AqualityServices.getLogger().info("Send a POST request to create a record /posts.In the request body," +
                " add randomly generated text to the body and title fields.The user Id field must contain 1.");
        PersonModel expectedPersonPost = new PersonModel(1,101, RandomUtils.getRandomString(8), RandomUtils.getRandomString(8));
        PersonModel actualPersonModelPost = postJsonPerson(expectedPersonPost);
        Assert.assertEquals(responseJson.getStatusCode(), StatusCodes.POST.getStatus(),
                "status code is not "+StatusCodes.POST.getStatus()+" status is:"+ responseJson.getStatusCode());
        softAssert.assertEquals(expectedPersonPost.getTitle(), actualPersonModelPost.getTitle(), "the title does not match");
        softAssert.assertEquals(expectedPersonPost.getBody(), actualPersonModelPost.getBody(), "the body does not match");
        softAssert.assertEquals(expectedPersonPost.getUserId(), actualPersonModelPost.getUserId(), "the UserID does not match");
        softAssert.assertEquals(expectedPersonPost.getId(), actualPersonModelPost.getId(), "the id does not match");
        softAssert.assertAll();


        int numExpectedUser = (int)new JsonSettingsFile("testConfig.json").getValue("/numExpectedUser");

        AqualityServices.getLogger().info("Send a GET request to get users /users");
        userModels = getJsonUsers();
        UserModel expectedUserModel = getJsonUserFromFile(getExpectedJson("expectedUser5"));
        Assert.assertEquals(responseJson.getStatusCode(), StatusCodes.OK.getStatus(),
                "status code is not "+StatusCodes.OK.getStatus()+" status is:"+ responseJson.getStatusCode());
        Assert.assertTrue(fileIsJson(responseJson.getBody().toString()), "file the file is not json");
        softAssert.assertEquals(expectedUserModel, userModels[numExpectedUser-1],
                "The user with the number "+numExpectedUser+" does not match the expected user with this number");
        softAssert.assertAll();


        numExpectedUser = (int)new JsonSettingsFile("testConfig.json").getValue("/numExpectedUser");

        AqualityServices.getLogger().info("Send a GET request to get user "+numExpectedUser);
        UserModel userModel = getJsonUser(numExpectedUser);
        Assert.assertEquals(responseJson.getStatusCode(), StatusCodes.OK.getStatus(),
                "status code is not "+StatusCodes.OK.getStatus()+" status is:"+ responseJson.getStatusCode());
        softAssert.assertEquals(userModels[numExpectedUser-1], userModel,
                "the user does not match with the user from the list number "+numExpectedUser+" from the previous request");
        softAssert.assertAll();
    }
}
