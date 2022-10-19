package Model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC)
public class UserModel {

    private int id;
    private String name;
    private String username;
    private String email;
    private AddressModel addressModel;
    private String phone;
    private String website;
    private CompanyModel companyModel;

    public UserModel(@JsonProperty(value = "id") int id, @JsonProperty(value = "name") String name,
                     @JsonProperty(value = "username") String username, @JsonProperty(value = "email") String email,
                     @JsonProperty(value = "address") AddressModel addressModel, @JsonProperty(value = "phone") String phone,
                     @JsonProperty(value = "website") String website, @JsonProperty(value = "company") CompanyModel companyModel){
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.addressModel = addressModel;
        this.phone = phone;
        this.website = website;
        this.companyModel = companyModel;
    }
}
