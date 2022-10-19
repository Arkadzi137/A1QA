package Utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import Model.ResponseJSONModel;

public abstract class APIUtils {

    public static ResponseJSONModel sendGetRequest(String httpBody, String get){
        HttpResponse<JsonNode> jsonResponse = null;
        try {
            jsonResponse = Unirest.get(httpBody+get).asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return new ResponseJSONModel(jsonResponse.getStatus(), jsonResponse.getBody());
    }

    public static ResponseJSONModel sendPostRequest(String httpBody, String path, String json){
        HttpResponse<JsonNode> jsonResponse = null;
        try {
            jsonResponse = Unirest.post(httpBody+path)
                    .header("Content-Type", "application/json").body(json).asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return new ResponseJSONModel(jsonResponse.getStatus(), jsonResponse.getBody());
    }
}