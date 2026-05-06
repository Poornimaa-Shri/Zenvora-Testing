package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import config.Config;
import pages.ProductsPage;
import utils.DriverFactory;

public class ProfileTest {

    WebDriver driver;
    ProductsPage productsPage;

    By profileBtn = By.className("profile-btn");
    By profileMenu = By.id("profileMenu");
    By userDisplay = By.id("userDisplay");
    By logoutBtn = By.id("logoutBtn");

    @BeforeMethod
    public void setup() {

        driver = DriverFactory.getDriver();
        driver.get(Config.baseUrl + Config.productsPage);
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("localStorage.setItem('user','test');");
        driver.navigate().refresh();
        productsPage = new ProductsPage(driver);
    }

    @Test(priority = 1)
    public void usernameDisplayTest() {

        driver.findElement(profileBtn).click();
        String username = driver.findElement(userDisplay).getText().toLowerCase();
        Assert.assertTrue(username.contains("test"));
        System.out.println("Username Display Test Passed");
    }

    @Test(priority = 2)
    public void profileDropdownOpenCloseTest() {

        driver.findElement(profileBtn).click();
        Assert.assertTrue(driver.findElement(profileMenu).isDisplayed());
        driver.findElement(By.tagName("body")).click();
        try { Thread.sleep(500); } catch (Exception e) {}
        String classValue = driver.findElement(profileMenu).getAttribute("class");
        Assert.assertFalse(classValue.contains("show"));
        System.out.println("Profile Dropdown Open/Close Test Passed");
    }

    @Test(priority = 3)
    public void logoutTest() {

        driver.findElement(profileBtn).click();
        driver.findElement(logoutBtn).click();
        new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                .until(d -> driver.getCurrentUrl().contains("login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
        System.out.println("Logout Test Passed");
    }

    @Test(priority = 4)
    public void unauthorizedAccessTest() {

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("localStorage.removeItem('user');");
        driver.get(Config.baseUrl + "/pages/cart.html");
        new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                .until(d -> driver.getCurrentUrl().contains("login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
        System.out.println("Unauthorized Access Test Passed");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}