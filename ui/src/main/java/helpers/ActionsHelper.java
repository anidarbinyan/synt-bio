package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import core.Driver;

public final class ActionsHelper {

    private ActionsHelper(){}

    private static WebDriver driver = Driver.getDriver();
    private static Actions actions = new Actions(driver);

    public static void hoverElement(WebElement hoverElement){
        actions.moveToElement(hoverElement).perform();
    }
}
