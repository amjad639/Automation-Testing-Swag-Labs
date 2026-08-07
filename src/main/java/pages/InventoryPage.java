package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

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
    By twitter = By.className("social_twitter");
    By facebook = By.className("social_facebook");
    By linkedin = By.className("social_linkedin");
    By inventoryItems = By.className("inventory_items");
    By productName = By.className("inventory_item_name");
    By addToCartButton = By.className("btn_inventory");
    By productPrice = By.className("inventory_item_price");
    By menuButton = By.id("react-burger-menu-btn");
    By logoutButton = By.id("logout_sidebar_link");



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
    public void ClickOnTwitterIcon (){
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(twitter)
        );
        Allure.step("Click on twitter icon");
        driver.findElement(twitter).click();
    }
    public void ClickOnFacebookIcon (){
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(facebook)
        );
        Allure.step("Click on facebook icon");
        driver.findElement(facebook).click();
    }
    public void ClickOnLinkedInIcon(){
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(linkedin)
        );
        Allure.step("Click on linkedin icon");
        driver.findElement(linkedin).click();
    }
    public void SwitchToNewTab (){
        String currentWindow = driver.getWindowHandle();
        for (String window : driver.getWindowHandles()){
            if(!window.equals(currentWindow)){
                driver.switchTo().window(window);
                break;
            }
        }
    }
    public String getCurretURL(){
        return driver.getCurrentUrl();
    }
    public boolean isTwitterIconDisplayed(){
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(twitter)
        );
        return driver.findElement(twitter).isDisplayed();
    }
    public boolean isFacebookIconDisplayed(){
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(facebook)
        );
        return driver.findElement(facebook).isDisplayed();
    }
    public boolean isLinkedInIconDisplayed(){
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(linkedin)
        );
        return driver.findElement(linkedin).isDisplayed();
    }

    public void addProductToCart(String productName){
        List<WebElement> products =
                driver.findElements(this.products);
        for (WebElement product : products){
            String name =
                    product.findElement(
                            By.className("inventory_item_name")
                    ).getText();
            if (name.equals(productName)){
                product.findElement(
                        By.className("btn_inventory")
                ).click();
                break;
            }
        }
    }
    public String getButtonText(String productName) {
        List<WebElement> productList = driver.findElements(products);
        for (WebElement product : productList) {
            String name = product.findElement(
                    By.className("inventory_item_name")
            ).getText();
            if (name.equals(productName)) {
                return product.findElement(
                        By.className("btn_inventory")
                ).getText();
            }
        }
        return "";
    }
    public double getProductPrice(String productName){
        List<WebElement> productList = driver.findElements(products);
        for (WebElement product : productList) {
            String name = product.findElement(
                    By.className("inventory_item_name")
            ).getText();
            if (name.equals(productName)) {
                String priceText = product.findElement(
                        productPrice
                ).getText();
                return Double.parseDouble(priceText.replace("$",""));
            }
        }
        throw new RuntimeException("Product not found"+productName);
    }
    public void logout(){
        wait.until(
                ExpectedConditions.elementToBeClickable(menuButton)
        );
        driver.findElement(menuButton).click();
        wait.until(
                ExpectedConditions.elementToBeClickable(logoutButton)
        );
        Allure.step("Logout");
        driver.findElement(logoutButton).click();

    }




}
