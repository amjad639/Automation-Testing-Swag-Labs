package tests;

import base.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import io.qameta.allure.Description;
import io.qameta.allure.internal.shadowed.jackson.databind.ser.Serializers;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataDriven;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Test
    public void VerifyValidLogin() throws IOException {
        LoginPage loginPage = new LoginPage(getDriver());        //TC steps
        JsonNode data = DataDriven.jsonReader();

        loginPage.EnterUserName(
                data.get("validLogin")
                        .get("username")
                        .asText());

        loginPage.EnterPassword(
                data.get("validLogin")
                        .get("password")
                        .asText());

        loginPage.ClickLoginButton();

        //assertion
        Assert.assertEquals(
                getDriver().getCurrentUrl(),
                "https://www.saucedemo.com/inventory.html");
    }
    @Test
    public void VerifyInvalidLogin() throws IOException {

        LoginPage loginPage = new LoginPage(getDriver());
        //TC steps
        JsonNode data = DataDriven.jsonReader();
        loginPage.EnterUserName(
                data.get("invalidLogin")
                        .get("username")
                        .asText());

        loginPage.EnterPassword(
                data.get("invalidLogin")
                        .get("password")
                        .asText());
        loginPage.ClickLoginButton();

        //assertion
        Assert.assertTrue(
                loginPage.
                        getErrorMessage().
                        contains("Username and password do not match"));
    }
    @Test
    public void VerifyLoginWithoudPassword() throws IOException {

        LoginPage loginPage = new LoginPage(getDriver());
        //tc steps
        JsonNode data = DataDriven.jsonReader();
        loginPage.EnterUserName(
                data.get("validLogin")
                        .get("username")
                        .asText());

        loginPage.ClickLoginButton();

        //Assertion
        Assert.assertTrue(
                loginPage
                        .getErrorMessage()
                        .contains("Password is required"));
    }
}

