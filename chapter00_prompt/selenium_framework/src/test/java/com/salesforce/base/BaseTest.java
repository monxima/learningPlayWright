package com.salesforce.base;

import com.salesforce.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected static final String LOGIN_URL = "https://login.salesforce.com/?locale=in";

    protected WebDriver driver;
    protected LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        try {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized", "--disable-notifications", "--remote-allow-origins=*");
            if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
                options.addArguments("--headless=new", "--window-size=1920,1080");
            }
            driver = new ChromeDriver(options);
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
            driver.get(LOGIN_URL);
            loginPage = new LoginPage(driver);
        } catch (WebDriverException e) {
            tearDown();
            throw new IllegalStateException("Browser session could not be started", e);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (WebDriverException e) {
                System.err.println("Driver quit failed: " + e.getMessage());
            } finally {
                driver = null;
            }
        }
    }
}
