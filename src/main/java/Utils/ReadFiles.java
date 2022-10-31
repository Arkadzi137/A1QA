package Utils;

import aquality.selenium.browser.AqualityServices;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ReadFiles {
    public static FileReader getExpectedJson(String filePath) {
        FileReader fileReaderTesting = null;
        try {
            fileReaderTesting = new FileReader("./src/test/resources/"+filePath+".json");
        } catch (FileNotFoundException e) {
            AqualityServices.getLogger().error("Json file "+filePath+" not found");
            throw new RuntimeException(e);
        }
        return fileReaderTesting;
    }
}
