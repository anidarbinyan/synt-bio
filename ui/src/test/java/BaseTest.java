import core.Driver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected final String baseUrl = "https://demo.opencart.com/en-gb?route=common/home";

    @BeforeMethod
    public void setupMethod(){
      Driver.getDriver();
    }

    @AfterMethod
    public void tearDown(){
        Driver.quitDriver();
    }
}
