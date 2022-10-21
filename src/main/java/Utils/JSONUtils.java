package Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import Model.PersonModel;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.NotFoundException;

public class JSONUtils {

    public static boolean fileIsJson(String file){
        try {
            new JSONParser().parse(file);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static String jsonPersonToString(PersonModel personModel){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(personModel);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new NotFoundException();
        }
    }

}
