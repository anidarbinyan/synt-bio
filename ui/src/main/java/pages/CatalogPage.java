package pages;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CatalogPage extends BasePage {
    private final String catalogPageUrl;

    @FindBy(xpath = "//div[@id='content']/h2")
    private WebElement desktopTitle;

    @FindBy(css = "div.product-thumb h4 a")
    private List<WebElement> productNames;

    @FindBy(css = "div.product-thumb .price")
    private List<WebElement> productPrices;

    public CatalogPage(String catalogPageUrl) {
        this.catalogPageUrl = catalogPageUrl;
    }

    protected String pageUrl() {
        return catalogPageUrl;
    }

    public String getDesktopsCatalogTitle() {
        return desktopTitle.getText().trim();
    }

    public boolean isDesktopCatalogDisplayed() {
        return desktopTitle.isDisplayed();
    }

    public List<String> getProductNames() {
        return productNames.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getProductPrices() {
        return productPrices.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
