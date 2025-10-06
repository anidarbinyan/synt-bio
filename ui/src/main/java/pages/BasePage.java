package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import core.Driver;

public abstract class BasePage {

    protected WebDriver driver;

    public BasePage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    protected String pageUrl(){
        return "";
    }

    public void get(){
        driver.get(pageUrl());
    }

    public void goBackPage(){
        driver.navigate().back();
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }
}
