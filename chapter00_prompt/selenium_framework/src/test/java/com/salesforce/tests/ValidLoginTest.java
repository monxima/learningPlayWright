package com.salesforce.tests;

import com.salesforce.base.BaseTest;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class ValidLoginTest extends BaseTest {

    @Test(priority = 1, description = "Login page renders all mandatory elements")
    public void verifyLoginPageElementsAreDisplayed() {
        Assert.assertTrue(loginPage.isLoaded(), "Login form did not load");
        Assert.assertTrue(loginPage.isForgotPasswordLinkDisplayed(), "Forgot password link missing");
        Assert.assertTrue(loginPage.getPageTitle().contains("Login"), "Unexpected page title");
    }

    @Test(priority = 2, description = "Username and password fields accept input")
    public void verifyCredentialsFieldsAcceptInput() {
        loginPage.enterUsername("qa.user@example.com");
        Assert.assertEquals(loginPage.getUsernameValue(), "qa.user@example.com");
        loginPage.clickLogin();
        loginPage.enterPassword("Sample@123");
        Assert.assertTrue(loginPage.isPasswordDisplayed(), "Password field should appear after username step");
    }

    @Test(priority = 3, description = "Remember Me checkbox toggles on and off")
    public void verifyRememberMeCanBeToggled() {
        Assert.assertFalse(loginPage.isRememberMeSelected(), "Remember Me should be unchecked by default");
        loginPage.setRememberMe(true);
        Assert.assertTrue(loginPage.isRememberMeSelected(), "Remember Me should be checked");
        loginPage.setRememberMe(false);
        Assert.assertFalse(loginPage.isRememberMeSelected(), "Remember Me should be unchecked");
    }

    @Test(priority = 4, description = "Valid credentials move the user off the login page")
    public void verifyLoginWithValidCredentials() {
        String user = System.getProperty("sf.username", "");
        String pass = System.getProperty("sf.password", "");
        if (user.isBlank() || pass.isBlank()) {
            throw new SkipException("Provide -Dsf.username and -Dsf.password to run the valid login test");
        }
        loginPage.setRememberMe(true);
        loginPage.doLogin(user, pass);
        Assert.assertTrue(loginPage.hasNavigatedAwayFromLogin(), "User was not redirected after valid login");
    }
}
