package Model;

import com.mashape.unirest.http.JsonNode;

public class ResponseJSONModel {
    protected int statusCode;
    protected JsonNode body;

    public int getStatusCode() {
        return statusCode;
    }

    public JsonNode getBody() {
        return body;
    }

    public ResponseJSONModel(int statusCode, JsonNode body) {
        this.statusCode = statusCode;
        this.body = body;
    }
}

