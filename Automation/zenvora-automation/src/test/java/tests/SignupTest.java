package tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import config.Config;
import pages.SignupPage;
import utils.DriverFactory;


public class SignupTest{
	
	WebDriver driver;
    SignupPage signupPage;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.getDriver();
        driver.get(Config.baseUrl + Config.loginPage);
        signupPage = new SignupPage(driver);
    }
    
    @Test
    public void validSignupTest() {
    	
    	signupPage.signupClick();
    	signupPage.Signup("demo1", "demo");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isRedirected = wait.until(
            ExpectedConditions.urlContains("login.html")
        );
        Assert.assertTrue(isRedirected);
        System.out.println("Valid Signup Test Passed");
    	
    }
	
    @Test
    public void emptyfields() {
    	
    	signupPage.signupClick();
    	signupPage.Signup("", "");
    	String errorMsg = signupPage.getErrorMessage();
        Assert.assertEquals(errorMsg, "Username and Password Required");
        System.out.println("Empty Fields Test Passed");
    }
    
    @Test
    public void onlyusername() {
    	
    	signupPage.signupClick();
    	signupPage.Signup("demo", "");
    	String errorMsg=signupPage.getErrorMessage();
    	Assert.assertEquals(errorMsg, "Password Required");
    	System.out.println("Only Username Test Passed");
    }
    
    @Test
    public void onlypassword() {
    	
    	signupPage.signupClick();
    	signupPage.Signup("", "demo");
    	String errorMsg=signupPage.getErrorMessage();
    	Assert.assertEquals(errorMsg, "Username Required");
    	System.out.println("Only Password Test Passed");
    }
    
    @Test
    public void duplicateEntry() {
    	
    	signupPage.signupClick();
    	signupPage.Signup("demo", "demo");
    	String errorMsg=signupPage.getErrorMessage();
    	Assert.assertEquals(errorMsg, "User already exists");
    	System.out.println("Duplicate Entry Test Passed");
    }
    
    @AfterMethod
    public void teardown() {
    	driver.quit();
    }
}