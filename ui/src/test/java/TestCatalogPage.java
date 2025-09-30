import org.testng.annotations.BeforeClass;
import pages.BasePage;
import pages.CatalogPage;
import pages.HomePage;

public class TestCatalogPage extends BaseTest {

    HomePage homePage;
    CatalogPage catalogPage;

    @BeforeClass
    public void setUp() {
        homePage = new HomePage(baseUrl);
    }
}
