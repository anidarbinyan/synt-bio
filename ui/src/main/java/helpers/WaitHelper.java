package helpers;

import core.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class WaitHelper {

    private WaitHelper(){}

    private static WebDriver driver = Driver.getDriver();
    private static Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(80));

    public static void waitElementToBeVisible(WebElement target){
        wait.until(ExpectedConditions.visibilityOf(target));
    }
}
