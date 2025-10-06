import java.util.List;
import java.util.stream.Collectors;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CatalogPage;
import pages.HomePage;

public class TestCatalogPage extends BaseTest {

    private HomePage homePage;
    private CatalogPage catalogPage;

    @BeforeMethod
    public void setUp(String homePageUrl) {

        homePage = new HomePage(homePageUrl);
        catalogPage = new CatalogPage("https://demo.opencart.com/en-gb/catalog/desktops");
    }

    @Test
    public void verifyCatalogPage() {
        homePage.get();
        homePage.waitForLogo();
        homePage.clickShowAllDesktopsLink();

        String expectedUrl = "https://demo.opencart.com/en-gb/catalog/desktops";
        Assert.assertEquals(catalogPage.getCurrentUrl(), expectedUrl, "Desktops catalog is not opened!");

        Assert.assertTrue(catalogPage.isDesktopCatalogDisplayed(), "Desktops Catalog is not displayed!");
        Assert.assertEquals(catalogPage.getDesktopsCatalogTitle(), "Desktops", "Catalog title mismatch!");
        Assert.assertFalse(verifyProductListIsNotEmpty(), "No products found in catalog!");

        verifyEachProductHasPrice();
        checkProductsNameSorting();
    }

    public boolean verifyProductListIsNotEmpty() {
        List<String> products = catalogPage.getProductNames();
        return products.isEmpty();
    }

    public void verifyEachProductHasPrice() {
        List<String> prices = catalogPage.getProductPrices();
        Assert.assertEquals(prices.size(), catalogPage.getProductNames().size(), "Mismatch between product names and prices!");
        Assert.assertTrue(prices.stream().allMatch(price -> !price.isBlank()), "Some products have missing prices!");
    }

    public void checkProductsNameSorting(){
        List<String> products = catalogPage.getProductNames();

        boolean sorted = products.stream()
                .sorted()
                .collect(Collectors.toList())
                .equals(products);

        Assert.assertTrue(sorted, "Products are not sorted alphabetically!");
    }
}
