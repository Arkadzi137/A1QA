package test.API;

import Model.PersonModel;
import Model.ResponseJSONModel;
import Model.UserModel;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.JsonSettingsFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

import static Utils.APIUtils.*;
import static Utils.APIUtils.sendPostRequest;
import static Utils.JSONUtils.jsonPersonToString;

public class ApiApplicationRequest {

    public static ResponseJSONModel responseJson = null;
    private final static String POSTS_POSTFIX = new JsonSettingsFile("testConfig.json").getValue("/postfixPosts").toString();;
    private final static String USERS_POSTFIX = new JsonSettingsFile("testConfig.json").getValue("/postfixUsers").toString();;
    private final static String BASE_HTTP = new JsonSettingsFile("testConfig.json").getValue("/baseHttp").toString();

    public static PersonModel getJsonPerson(int number){
        responseJson = sendGetRequest(BASE_HTTP,POSTS_POSTFIX+"/"+number);
        PersonModel person = null;
        try {
            person = new ObjectMapper().readValue(responseJson.getBody().toString(), PersonModel.class);
        } catch (IOException e) {
            AqualityServices.getLogger().error("unable to parse Json-file");
            e.printStackTrace();
        }
        return person;
    }

    public static PersonModel[] getJsonPersons(){
        responseJson = sendGetRequest(BASE_HTTP, POSTS_POSTFIX);
        PersonModel[] persons = null;
        try {
            persons = new ObjectMapper().readValue(responseJson.getBody().toString(), PersonModel[].class);
        } catch (IOException e) {
            AqualityServices.getLogger().error("unable to parse Json-file");
            e.printStackTrace();
        }
        return persons;
    }

    public static UserModel getJsonUser(int number){
        responseJson = sendGetRequest(BASE_HTTP,USERS_POSTFIX+"/"+number);
        UserModel userModel = null;
        try {
            userModel = new ObjectMapper().readValue(responseJson.getBody().toString(), UserModel.class);
        } catch (IOException e) {
            AqualityServices.getLogger().error("unable to parse Json-file");
            e.printStackTrace();
        }
        return userModel;
    }

    public static UserModel[] getJsonUsers(){
        responseJson = sendGetRequest(BASE_HTTP, USERS_POSTFIX);
        UserModel[] userModels = null;
        try {
            userModels = new ObjectMapper().readValue(responseJson.getBody().toString(), UserModel[].class);
        } catch (IOException e) {
            AqualityServices.getLogger().error("unable to parse Json-file");
            e.printStackTrace();
        }
        return userModels;
    }

    public static PersonModel postJsonPerson(PersonModel personPost){
        responseJson = sendPostRequest(BASE_HTTP, POSTS_POSTFIX, jsonPersonToString(personPost));
        PersonModel person = null;
        try {
            person = new ObjectMapper().readValue(responseJson.getBody().toString(), PersonModel.class);
        } catch (IOException e) {
            AqualityServices.getLogger().error("unable to parse Json-file");
            e.printStackTrace();
        }
        return person;
    }
}
