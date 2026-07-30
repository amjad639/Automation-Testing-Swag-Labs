package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class InventoryTest extends BaseTest {

    @Test
    public void VerifyInventoryElementsAfterLogin(){
        LoginPage loginPage = new LoginPage(driver);
        //login
        loginPage.EnterUserName("standard_user");
        loginPage.EnterPassword("secret_sauce");
        loginPage.ClickLoginButton();

        //inventory page
        InventoryPage inventoryPage = new InventoryPage(driver);

        //assertion the page title
        Assert.assertEquals(inventoryPage.getPageTitle(),"Swag Labs","Swag Labs");

        //assertion the cart icon
        Assert.assertTrue(inventoryPage.isCartDisplayed(),"cart is not displayed");

        //assertino there are 6 product
        Assert.assertEquals(inventoryPage.getProductsCount(),6,"Product count is not equal to 6");

    }
}
