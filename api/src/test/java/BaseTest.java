import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

import providers.TestDataProvider;

public class BaseTest {
    private final String BASE_URL = TestDataProvider.getPropertyValue("baseURL");

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }
}
