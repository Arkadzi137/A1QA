package Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UploadModel {
    @JsonProperty("server")
    private String server;
    @JsonProperty("photo")
    private String photo;
    @JsonProperty("hash")
    private String hash;
}
