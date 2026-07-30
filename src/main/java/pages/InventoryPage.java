package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class InventoryPage {
    WebDriver driver;
    WebDriverWait wait;
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    }
    // locators
    By cartIcon = By.id("shopping_cart_container");
    By products = By.className("inventory_item");

    //Methods
    public String getPageTitle(){
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartIcon)
        );
        return driver.getTitle();
    }
    public boolean isCartDisplayed(){
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartIcon)
        );
        return driver.findElement(cartIcon).isDisplayed();
    }
    public int getProductsCount(){
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartIcon)
        );
        return driver.findElements(products).size();
    }


}
