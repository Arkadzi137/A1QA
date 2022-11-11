package Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SendPostModel {
    @JsonProperty(value = "post_id")
    private String postId;
}
