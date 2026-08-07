package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;
    public LoginPage(WebDriver driver) {

        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    }
    // locator
    By username = By.id("user-name");
    By password = By.id("password");
    By ClickLoginButton = By.id("login-button");
    By errorMessage = By.xpath("//h3[@data-test=\"error\"]");

    //method
    public void EnterUserName(String user){
        wait.until(
                ExpectedConditions.visibilityOf(driver.findElement(username))
        );
        Allure.step("Enter username");
        driver.findElement(username).sendKeys(user);
    }

    public void EnterPassword(String pass){
        wait.until(
                ExpectedConditions.visibilityOf(driver.findElement(password))
        );
        Allure.step("Enter password");
        driver.findElement(password).sendKeys(pass);
    }
    public void ClickLoginButton(){
        wait.until(
                ExpectedConditions.visibilityOf(driver.findElement(username))
        );
        Allure.step("Click login button");
        driver.findElement(ClickLoginButton).click();
    }
    public String getErrorMessage(){
        return driver.findElement(errorMessage).getText();
    }
    public String ErrorMessagePasswordRequired(){
        return driver.findElement(errorMessage).getText();
    }

    public void ValidLogin(String username, String password){
        EnterUserName(username);
        EnterPassword(password);
        ClickLoginButton();
    }

}
