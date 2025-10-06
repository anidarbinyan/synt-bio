package pages;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import helpers.ActionsHelper;
import helpers.WaitHelper;

public class HomePage extends BasePage{
    private String homePageUrl;

    @FindBy(xpath = "//div[@id='logo']//img")
    private WebElement homePageLogo;

    @FindBy(xpath = "//div[@id='narbar-menu']//li[contains(@class, 'nav-item')]/a")
    private List<WebElement> navbarMenuItems;

    @FindBy(xpath = "(//div[@id='narbar-menu']//li[contains(@class, 'nav-item')]/a)[1]")
     private WebElement desktopMenuItem;

    @FindBy(xpath ="//a[normalize-space(text())='Show All Desktops']")
    private WebElement showAllDesktopsLink;

    public HomePage(String homePageUrl) {
        this.homePageUrl = homePageUrl;
    }

    protected String pageUrl(){
        return homePageUrl;
    }

    public boolean waitForLogo() {
        WaitHelper.waitElementToBeVisible(homePageLogo);
        return homePageLogo.isDisplayed();
    }

    public List<String> getNavbarMenuItemsNames() {
        return navbarMenuItems.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void clickShowAllDesktopsLink() {
        ActionsHelper.hoverElement(desktopMenuItem);
        WaitHelper.waitElementToBeVisible(showAllDesktopsLink);
        showAllDesktopsLink.click();
    }
}
