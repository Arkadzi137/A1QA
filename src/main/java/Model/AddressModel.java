package Model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC)
public class AddressModel {

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoModel geoModel;

    public AddressModel(@JsonProperty(value = "street") String street, @JsonProperty(value = "suite")  String suite,
                        @JsonProperty(value = "city")  String city, @JsonProperty(value = "zipcode")  String zipcode,
                        @JsonProperty(value = "geo") GeoModel geoModel) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.geoModel = geoModel;
    }
}