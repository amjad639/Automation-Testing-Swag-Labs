package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    }
    //locators
    By continueShoppingButton = By.id("continue-shopping");
    By checkoutButton = By.id("checkout");
    By cartItems = By.className("cart_item");
    By cartIcon = By.id("shopping_cart_container");
    By cartProductNames= By.className("inventory_item_name");
    By itemTotal = By.className("summary_subtotal_label");
    By firstName = By.id("first-name");
    By lastName = By.id("last-name");
    By postalCode = By.id("postal-code");
    By continueButton = By.id("continue");
    By errorMessage = By.cssSelector("[data-test='error']");


    public int getCartItemsCount(){
        return driver.findElements(cartItems).size();
     }
     public void OpenCart(){
         wait.until(
                 ExpectedConditions.elementToBeClickable(cartIcon));
         Allure.step("Click on cartIcon");
         driver.findElement(cartIcon).click();
     }

     public List<String> getCartProducts(){
         wait.until(
                 ExpectedConditions.visibilityOfAllElementsLocatedBy(cartItems)
         );
        List<String> products = new ArrayList<>();
        List<WebElement> elements =
                driver.findElements(cartProductNames);

        for (WebElement element : elements) {
            products.add(element.getText());
        }
        return products;
     }
    public void removeProduct(String productName) {
        List<WebElement> items = driver.findElements(cartItems);
        for (WebElement item : items) {
            String name = item.findElement(
                    By.className("inventory_item_name")
            ).getText();
            if (name.equals(productName)) {
                item.findElement(
                        By.className("cart_button")
                ).click();
                break;
            }
        }
    }
    public void clickContinueShopping() {

        wait.until(
                ExpectedConditions.elementToBeClickable(continueShoppingButton)
        );

        driver.findElement(continueShoppingButton).click();
    }
    public void clickCheckout() {
        wait.until(
                ExpectedConditions.elementToBeClickable(checkoutButton)
        );
        Allure.step("click on checkout button");
        driver.findElement(checkoutButton).click();
    }
    public double getItemTotal() {
        String total =driver.findElement(itemTotal).getText();
        return Double.parseDouble(
                total
                        .replace("Item total:", "")
                        .replace("$", "")
                        .trim()
        );
    }
    public void enterCheckoutInformation(String first, String last, String zip){
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstName)
        );
        Allure.step("Enter checkout information");
        driver.findElement(firstName).sendKeys(first);
        driver.findElement(lastName).sendKeys(last);
        driver.findElement(postalCode).sendKeys(zip);

    }
    public void clickContinueCheckout(){

        wait.until(
                ExpectedConditions.elementToBeClickable(continueButton)
        );
        Allure.step("Click Continue button");
        driver.findElement(continueButton).click();
    }
    public boolean getErrorMessage(){

        return !driver.findElements(errorMessage).isEmpty();

    }
    public boolean isErrorMessageDisplayed(){

        return !driver.findElements(errorMessage).isEmpty();

    }
}

