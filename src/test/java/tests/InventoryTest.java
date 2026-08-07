package tests;

import base.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import utils.DataDriven;

import java.io.IOException;

public class InventoryTest extends BaseTest {

    @Test
    public void VerifyInventoryElementsAfterLogin(){
        LoginPage loginPage = new LoginPage(getDriver());        //TC steps
        //login
        loginPage.EnterUserName("standard_user");
        loginPage.EnterPassword("secret_sauce");
        loginPage.ClickLoginButton();

        //inventory page
        InventoryPage inventoryPage = new InventoryPage(getDriver());

        //assertion the page title
        Assert.assertEquals(inventoryPage.getPageTitle(),"Swag Labs","Swag Labs");

        //assertion the cart icon
        Assert.assertTrue(inventoryPage.isCartDisplayed(),"cart is not displayed");

        //assertino there are 6 product
        Assert.assertEquals(inventoryPage.getProductsCount(),6,"Product count is not equal to 6");

    }
    @Test
    public void VerifyTwitterIconWorks() throws IOException {

        JsonNode data = DataDriven.jsonReader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.ValidLogin(
                data.get("validLogin").get("username").asText(),
                data.get("validLogin").get("password").asText()
        );

        Assert.assertEquals(
                getDriver().getCurrentUrl(),
                "https://www.saucedemo.com/inventory.html");

        InventoryPage inventoryPage = new InventoryPage(getDriver());
        inventoryPage.isTwitterIconDisplayed();
        inventoryPage.ClickOnTwitterIcon();
        inventoryPage.SwitchToNewTab();
        Assert.assertEquals(
                getDriver().getCurrentUrl(),
                "https://x.com/saucelabs");

    }
    @Test
    public void verifyFacebookIconWorks() throws IOException {
        JsonNode data = DataDriven.jsonReader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.ValidLogin(
                data.get("validLogin").get("username").asText(),
                data.get("validLogin").get("password").asText()
        );
        Assert.assertEquals(
                getDriver().getCurrentUrl(),
                "https://www.saucedemo.com/inventory.html");
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        inventoryPage.isFacebookIconDisplayed();
        inventoryPage.ClickOnFacebookIcon();
        inventoryPage.SwitchToNewTab();
        Assert.assertTrue(
                getDriver().getCurrentUrl().contains("facebook"),
                "facebook link is not working"
        );

    }
    @Test
    public void verifyLinkedinIconWorks() throws IOException {
        JsonNode data = DataDriven.jsonReader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.ValidLogin(
                data.get("validLogin").get("username").asText(),
                data.get("validLogin").get("password").asText()
        );
        Assert.assertEquals(
                getDriver().getCurrentUrl(),
                "https://www.saucedemo.com/inventory.html"
        );
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        inventoryPage.isLinkedInIconDisplayed();
        inventoryPage.ClickOnLinkedInIcon();
        inventoryPage.SwitchToNewTab();
        Assert.assertTrue(
                getDriver().getCurrentUrl().contains("linkedin"),
                "linkedin link is not working"
        );

    }

}
