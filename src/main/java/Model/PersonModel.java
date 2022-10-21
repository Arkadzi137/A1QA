package Model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC)
public class PersonModel {

    private int userId;
    private int id;
    private String title;
    private String body;

    public PersonModel(@JsonProperty(value = "userId") int userId, @JsonProperty(value = "id") int id,
                       @JsonProperty(value = "title") String title, @JsonProperty(value = "body") String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }
}

