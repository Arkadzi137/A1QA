package Utils;

import aquality.selenium.core.utilities.JsonSettingsFile;

public class ConfigUtils {
    public static String getTestConfig(String key) {
        return new JsonSettingsFile("testConfig.json").getValue(String.format("/%s", key)).toString();
    }

    public static String getConfidentialData(String key) {
        return new JsonSettingsFile("confidentialData.json").getValue(String.format("/%s", key)).toString();
    }
}
