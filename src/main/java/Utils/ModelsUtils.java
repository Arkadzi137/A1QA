package Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import Model.PersonModel;
import Model.UserModel;

import java.io.FileReader;
import java.io.IOException;

public class ModelsUtils {

    public static boolean getEmptyBodyPerson(PersonModel person){
        try {
            person.getBody().isEmpty();
            return false;
        }catch (NullPointerException e){
            return true;
        }
    }

    public static boolean dataIsSortedById(PersonModel[] persons){
        for(int i = 0; i < persons.length - 1; i++){
            if(persons[i].getId() >= persons[i+1].getId()) {
                return false;
            }
        }
        return true;
    }

    public static UserModel getJsonUserFromFile(FileReader jsonOnFile){
        UserModel userModel = null;
        try {
            userModel = new ObjectMapper().readValue(jsonOnFile, UserModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userModel;
    }

    public static PersonModel getJsonPersonFromFile(FileReader jsonOnFile){
        PersonModel person = null;
        try {
            person = new ObjectMapper().readValue(jsonOnFile, PersonModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return person;
    }
}
