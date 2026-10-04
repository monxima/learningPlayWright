package com.salesforce.tests;

import com.salesforce.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class InvalidLoginTest extends BaseTest {

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentials() {
        return new Object[][]{
                {"invalid.user@example.com", "WrongPass@123", "Please check your username and password"},
                {"another.invalid@example.com", "Admin@12345", "Please check your username and password"},
                {"invalid.user@example.com", "x", "Please check your username and password"}
        };
    }

    @Test(dataProvider = "invalidCredentials", description = "Wrong username or password shows an authentication error")
    public void verifyErrorForInvalidCredentials(String user, String pass, String expectedError) {
        loginPage.doLogin(user, pass);
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message was not displayed");
        Assert.assertTrue(loginPage.getErrorMessage().contains(expectedError),
                "Unexpected error message: " + loginPage.getErrorMessage());
        Assert.assertTrue(loginPage.isOnLoginPage(), "User must remain on the login page");
    }

    @Test(description = "Empty username is rejected")
    public void verifyErrorForEmptyUsername() {
        loginPage.submitUsername("");
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message was not displayed");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Please enter your username"),
                "Unexpected error message: " + loginPage.getErrorMessage());
    }

    @Test(description = "Empty password is rejected")
    public void verifyErrorForEmptyPassword() {
        loginPage.submitUsername("invalid.user@example.com");
        Assert.assertTrue(loginPage.isPasswordDisplayed(), "Password field did not appear");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message was not displayed");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Please enter your password"),
                "Unexpected error message: " + loginPage.getErrorMessage());
    }

    @Test(description = "Failed login keeps the user on the login page")
    public void verifyUserRemainsOnLoginPageAfterFailure() {
        loginPage.doLogin("invalid.user@example.com", "WrongPass@123");
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message was not displayed");
        Assert.assertTrue(loginPage.isLoaded(), "Login form should still be available for retry");
    }
}
