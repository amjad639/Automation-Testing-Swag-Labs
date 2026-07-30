package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class BaseTest {
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver(){

        return driver.get();

    }
    @BeforeMethod
    public void SetUpDriver() {
        driver.set(new ChromeDriver());
        getDriver().manage().window().maximize();
        getDriver().get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void CloseDriver() {
        getDriver().quit();
        driver.remove();
    }
}
