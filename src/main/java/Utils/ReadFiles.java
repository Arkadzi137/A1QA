package Utils;

import aquality.selenium.core.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class ReadFiles {

    //public static JSONArray osAndAppsJson = (JSONArray)jsonTesting.get("OsAndApp");

    static {
        try {
            JSONParser parser = new JSONParser();
            try {
                FileReader fileReaderTesting = new FileReader("./src/test/resources/expectedPost99.json");
                jsonTesting = (JSONObject) parser.parse(fileReaderTesting);
            }
            catch (Exception ex) {
                Logger.getInstance().error("Didn't found Json file");
                throw new RuntimeException(ex);
            }
        } catch (Exception e) {
            Logger.getInstance().error("Didn't found Json file");
            throw new RuntimeException(e);
        }
    }
    public static JSONObject jsonTesting;
//    public static List<OsAndApp> getOsAndApps(){
//        List<OsAndApp> osAndApps = new ArrayList<>();
//        for (int i = 0; i < osAndAppsJson.size(); i++) {
//            OsAndApp osAndApp = new OsAndApp();
//            JSONObject userJson = (JSONObject) osAndAppsJson.get(i);
//            osAndApp.setOs(userJson.get("OS").toString());
//            osAndApp.setApp (userJson.get("app").toString());
//            osAndApps.add(osAndApp);
//        }
//        return osAndApps;
//    }
}
