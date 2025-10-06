import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;

public class TestHomePage extends BaseTest {

    private HomePage homePage;

    @BeforeMethod
    @Parameters({"homePageUrl"})
    public void setUp(String homePageUrl) {
        homePage = new HomePage(homePageUrl);
    }

    @Test
    public void verifyNavbarMenuItems() {
        homePage.get();
        homePage.waitForLogo();

        List<String> expectedMenuItems =
                Arrays.asList("Desktops", "Laptops & Notebooks", "Components", "Tablets", "Software", "Phones & PDAs", "Cameras", "MP3 Players");
        List<String> actualMenuItems = homePage.getNavbarMenuItemsNames();

        Assert.assertEquals(actualMenuItems, expectedMenuItems, "Navbar menu items mismatch!");
    }

    @Test
    public void verifyAllDesktops() {
        homePage.get();
        homePage.waitForLogo();
        homePage.clickShowAllDesktopsLink();

        String expectedUrl = "https://demo.opencart.com/en-gb/catalog/desktops";

        Assert.assertEquals(homePage.getCurrentUrl(), expectedUrl, "Desktops catalog is not opened!");
    }
}
