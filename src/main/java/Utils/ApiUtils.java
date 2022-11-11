package Utils;

import Model.ResponseJSONModel;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.JsonSettingsFile;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.File;

public class ApiUtils {

    private static final String baseUrl = new JsonSettingsFile("testConfig.json").getValue("/apiBaseUrl").toString();

    public static ResponseJSONModel getRequest(String get){
        HttpResponse<JsonNode> jsonResponse = null;
        try {
            jsonResponse = Unirest.get(baseUrl +get).asJson();

        } catch (UnirestException e) {
            AqualityServices.getLogger().error("Unable to send Get request");
            e.printStackTrace();
        }
        return new ResponseJSONModel(jsonResponse.getStatus(), jsonResponse.getBody());
    }

    public static ResponseJSONModel postRequest(String request){
        HttpResponse<JsonNode> jsonResponse = null;
        try {
            jsonResponse = Unirest.post(baseUrl +request).asJson();
        } catch (UnirestException e) {
            AqualityServices.getLogger().error("Unable to send Post request");
            e.printStackTrace();
        }
        return new ResponseJSONModel(jsonResponse.getStatus(), jsonResponse.getBody());
    }

    public static ResponseJSONModel upLoad(String requestPath, String filePath,String typeOfFile){
        HttpResponse<JsonNode> jsonResponse = null;
        try {
            jsonResponse = Unirest.post(requestPath).field(typeOfFile, new File(filePath)).asJson();
        } catch (UnirestException e) {
            AqualityServices.getLogger().error("Unable to send Post request");
            e.printStackTrace();
        }
        return new ResponseJSONModel(jsonResponse.getStatus(), jsonResponse.getBody());
    }
}
