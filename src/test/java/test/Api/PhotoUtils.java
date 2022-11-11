package test.Api;

import Model.ResponseJSONModel;
import Model.SavePhotoModel;
import Model.UploadModel;
import Model.WallUploadServerModel;
import Utils.ApiUtils;
import Utils.ConfigUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.net.URLEncoder;

public class PhotoUtils {
    public static ResponseJSONModel responseJson = null;
    private static final String accessToken = ConfigUtils.getConfidentialData("accessToken");
    private static final String apiVersion = ConfigUtils.getTestConfig("apiVersion");

    @SneakyThrows
    public static SavePhotoModel getSavedPhotoResponse(String ownerId, String filePath){
        String request = String.format("%s?user_id=%s&access_token=%s&v=%s",Methods.PHOTO_UPLOAD_SERVER.getMethod(),ownerId, accessToken, apiVersion);
        responseJson = ApiUtils.getRequest(request);
        WallUploadServerModel wallUploadServerModel = new ObjectMapper().readValue(responseJson.getBody().getObject().get("response").toString(), WallUploadServerModel.class);
        ResponseJSONModel jsonResponse1 = ApiUtils.upLoad(wallUploadServerModel.getUploadUrl(), filePath,"photo");
        UploadModel uploadPhotoResponse = new ObjectMapper().readValue(jsonResponse1.getBody().getObject().toString(), UploadModel.class);
        String encodedURL = URLEncoder.encode(uploadPhotoResponse.getPhoto(),"UTF-8");

        String request1 = String.format("%s?user_id=%s&photo=%s&server=%s&hash=%s&access_token=%s&v=%s",Methods.SAVE_WALL_PHOTO.getMethod(),
                ownerId, encodedURL,uploadPhotoResponse.getServer(),uploadPhotoResponse.getHash(), accessToken, apiVersion);
        ResponseJSONModel jsonResponse2 = ApiUtils.postRequest(request1);
        return new ObjectMapper().readValue(jsonResponse2.getBody().getObject().getJSONArray("response").get(0).toString(), SavePhotoModel.class);
    }
}
