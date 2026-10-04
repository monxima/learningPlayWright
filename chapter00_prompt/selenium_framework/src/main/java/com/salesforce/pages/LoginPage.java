package com.salesforce.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private static final Duration EXPLICIT_WAIT = Duration.ofSeconds(20);

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//input[@id='username']")
    private WebElement username;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//input[@id='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@id='rememberUn']")
    private WebElement rememberMe;

    @FindBy(xpath = "//div[@id='error']")
    private WebElement errorMessage;

    @FindBy(xpath = "//a[@id='forgot_password_link']")
    private WebElement forgotPasswordLink;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, EXPLICIT_WAIT);
        PageFactory.initElements(driver, this);
    }

    public boolean isLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOf(username));
            wait.until(ExpectedConditions.visibilityOf(loginButton));
            wait.until(ExpectedConditions.visibilityOf(rememberMe));
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public String getPageTitle() {
        try {
            wait.until(ExpectedConditions.visibilityOf(loginButton));
            return driver.getTitle();
        } catch (TimeoutException e) {
            throw new IllegalStateException("Login page did not render before reading title", e);
        }
    }

    public void enterUsername(String value) {
        try {
            wait.until(ExpectedConditions.visibilityOf(username));
            username.clear();
            username.sendKeys(value);
        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
            throw new IllegalStateException("Unable to enter username", e);
        }
    }

    public void enterPassword(String value) {
        try {
            wait.until(ExpectedConditions.visibilityOf(password));
            password.clear();
            password.sendKeys(value);
        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
            throw new IllegalStateException("Unable to enter password", e);
        }
    }

    public void clickLogin() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginButton));
            loginButton.click();
        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
            throw new IllegalStateException("Unable to click Log In button", e);
        }
    }

    public void setRememberMe(boolean selected) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(rememberMe));
            if (rememberMe.isSelected() != selected) {
                rememberMe.click();
            }
        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
            throw new IllegalStateException("Unable to set Remember Me checkbox", e);
        }
    }

    public boolean isRememberMeSelected() {
        try {
            wait.until(ExpectedConditions.visibilityOf(rememberMe));
            return rememberMe.isSelected();
        } catch (TimeoutException | NoSuchElementException e) {
            throw new IllegalStateException("Remember Me checkbox not available", e);
        }
    }

    public boolean isForgotPasswordLinkDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(forgotPasswordLink));
            return forgotPasswordLink.isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public String getUsernameValue() {
        try {
            wait.until(ExpectedConditions.visibilityOf(username));
            return username.getAttribute("value");
        } catch (TimeoutException | NoSuchElementException e) {
            throw new IllegalStateException("Username field not available", e);
        }
    }

    public boolean isErrorDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public String getErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.getText().trim();
        } catch (TimeoutException | NoSuchElementException e) {
            throw new IllegalStateException("Error message was not displayed", e);
        }
    }

    public boolean hasNavigatedAwayFromLogin() {
        try {
            return wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("login.salesforce.com")));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isPasswordDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(password));
            return password.isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public boolean isOnLoginPage() {
        return driver.getCurrentUrl().contains("login.salesforce.com");
    }

    public void submitUsername(String user) {
        enterUsername(user);
        clickLogin();
    }

    public void doLogin(String user, String pass) {
        submitUsername(user);
        enterPassword(pass);
        clickLogin();
    }
}
