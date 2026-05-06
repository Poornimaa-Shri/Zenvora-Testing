package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import config.Config;
import pages.ProductsPage;
import utils.DriverFactory;

public class SearchTest {

    WebDriver driver;
    ProductsPage productsPage;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.getDriver();
        driver.get(Config.baseUrl + Config.productsPage);
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("localStorage.setItem('user','test');");
        driver.navigate().refresh();
        productsPage = new ProductsPage(driver);
    }

    @Test
    public void validSearchTest() {

        productsPage.searchProduct("Sofa");
        productsPage.pressEnter();
        Assert.assertTrue(productsPage.getProductCount() > 0);
        System.out.println("Valid Search Test Passed");
    }

    @Test
    public void partialSearchTest() {

        productsPage.searchProduct("Sof");
        productsPage.pressEnter();
        Assert.assertTrue(productsPage.getProductCount() > 0);
        System.out.println("Partial Search Test Passed");
    }

    @Test
    public void invalidSearchTest() {

        productsPage.searchProduct("xyz123");
        productsPage.pressEnter();
        Assert.assertTrue(productsPage.isNoProductDisplayed());
        System.out.println("Invalid Search Test Passed");
    }

    @Test
    public void emptySearchTest() {

        productsPage.searchProduct("");
        productsPage.pressEnter();
        Assert.assertTrue(productsPage.getProductCount() > 0);
        System.out.println("Empty Search Test Passed");
    }

    @Test
    public void enterKeySearchTest() {

        productsPage.searchProduct("Chair");
        productsPage.pressEnter();
        Assert.assertTrue(productsPage.getProductCount() > 0);
        System.out.println("Enter Key Search Test Passed");
    }
    
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}