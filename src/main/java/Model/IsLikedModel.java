package Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class IsLikedModel {
    private boolean liked;

    public void setLiked(int liked) {
        this.liked = liked == 1;
    }

}
