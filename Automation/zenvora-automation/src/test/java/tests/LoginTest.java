package tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import config.Config;
import pages.LoginPage;
import utils.DriverFactory;

public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.getDriver();
        driver.get(Config.baseUrl + Config.loginPage);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void validLoginTest() {

        loginPage.login(Config.username, Config.password);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isRedirected = wait.until(
            ExpectedConditions.urlContains("products.html")
        );
        Assert.assertTrue(isRedirected);
        System.out.println("Valid Login Test Passed");
    }

    @Test
    public void invalidPasswordTest() {

        loginPage.login("test", "wrong123");
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertEquals(errorMsg, "Invalid Credentials");
        System.out.println("Invalid Password Test Passed");
    }

    @Test
    public void invalidUsernameTest() {

        loginPage.login("wronguser", "test@123");
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertEquals(errorMsg, "Invalid Credentials");
        System.out.println("Invalid Username Test Passed");
    }

    @Test
    public void emptyFieldsTest() {

        loginPage.login("", "");
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertEquals(errorMsg, "Invalid Credentials");
        System.out.println("Empty Fields Test Passed");
    }

    @Test
    public void onlyUsernameTest() {

        loginPage.login("test", "");
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertEquals(errorMsg, "Password Required");
        System.out.println("Only Username Test Passed");
    }

    @Test
    public void onlyPasswordTest() {

        loginPage.login("", "test@123");
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertEquals(errorMsg, "Username Required");
        System.out.println("Only Password Test Passed");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}