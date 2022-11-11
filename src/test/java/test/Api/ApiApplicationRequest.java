package test.Api;

import Model.*;
import Utils.ApiUtils;
import Utils.ConfigUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.openqa.selenium.By;

import static aquality.selenium.browser.AqualityServices.getElementFactory;
import static test.Api.PhotoUtils.getSavedPhotoResponse;

public class ApiApplicationRequest {
    public static ResponseJSONModel responseJson = null;
    private static final String ownerId = ConfigUtils.getTestConfig("ownerId");
    private static final String accessToken = ConfigUtils.getConfidentialData("accessToken");
    private static final String apiVersion = ConfigUtils.getTestConfig("apiVersion");

    @SneakyThrows
    public static SendPostModel sendMessageOnTheWall(String message){
        String request = String.format("%s?owner_id=%s&message=%s&access_token=%s&v=%s",Methods.SEND_POST.getMethod(), ownerId, message, accessToken, apiVersion);
        responseJson = ApiUtils.postRequest(request);
        return new ObjectMapper().readValue(responseJson.getBody().getObject().get("response").toString(), SendPostModel.class);
    }

    @SneakyThrows
    public static String editPostWithAttachment(String message, String postId, String filePath){
        String sentPhotoId = getSavedPhotoResponse(ownerId,filePath).getId();
        String request = String.format("%s?owner_id=%s&post_id=%s&message=%s&attachments=photo%s_%s&access_token=%s&v=%s",
                Methods.EDIT_POST.getMethod(), ownerId,postId,message, ownerId,sentPhotoId, accessToken, apiVersion);
        responseJson = ApiUtils.postRequest(request);
        return responseJson.getBody().getObject().get("response").toString();
    }

    @SneakyThrows
    public static String sendCommentToPost(String message, String postId) {
        String request = String.format("%s?owner_id=%s&post_id=%s&message=%s&access_token=%s&v=%s",Methods.WALL_CREATE_COMMENT.getMethod(), ownerId,postId,message, accessToken, apiVersion);
        responseJson = ApiUtils.postRequest(request);
        SendCommentModel response = new ObjectMapper().readValue(responseJson.getBody().getObject().get("response").toString(), SendCommentModel.class);
        return response.getCommentId();
    }

    @SneakyThrows
    public static boolean isLikedPost(String postId,String userId){
        String request = String.format("%s?owner_id=%s&user_id=%s&type=post&item_id=%s&access_token=%s&v=%s",Methods.IS_LIKED.getMethod(), ownerId,userId,postId, accessToken, apiVersion);
        responseJson = ApiUtils.getRequest(request);
        IsLikedModel response = new ObjectMapper().readValue(responseJson.getBody().getObject().get("response").toString(), IsLikedModel.class);
        return response.isLiked();
    }

    @SneakyThrows
    public static void deletePost(String postId){
        String request = String.format("%s?owner_id=%s&post_id=%s&access_token=%s&v=%s",Methods.DELETE_POST.getMethod(), ownerId,postId, accessToken, apiVersion);
        ApiUtils.postRequest(request);
    }

    public static boolean postNotExist(String el, String authorId, String id) {
        return getElementFactory().getLabel(By.xpath(String.format(el, authorId, id)),"Deleted post").state().waitForNotExist();
    }

}
