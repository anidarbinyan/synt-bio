package providers;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class TestDataProvider {
    private static final PropertiesReader propertiesReader = PropertiesReader
            .getInstance("testData.properties");

    public static String getPropertyValue(String propertyName) {
        return propertiesReader.getPropety(propertyName);
    }
}
