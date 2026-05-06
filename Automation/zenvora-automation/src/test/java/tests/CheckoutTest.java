package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import config.Config;
import pages.ProductsPage;
import pages.CartPage;
import pages.CheckoutPage;
import utils.DriverFactory;

public class CheckoutTest {

    WebDriver driver;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

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
    public void navigateToCheckoutTest() {

        productsPage.clickFirstCartItem();   
        productsPage.clickCart();            
        cartPage = new CartPage(driver);
        cartPage.clickCheckout();
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout"));
        System.out.println("Checkout Navigation Test Passed");
    }

    @Test(priority = 2)
    public void orderSummaryTest() {

        productsPage.clickFirstCartItem();
        productsPage.clickCart();
        cartPage = new CartPage(driver);
        cartPage.clickCheckout();
        checkoutPage = new CheckoutPage(driver);
        Assert.assertTrue(checkoutPage.isOrderSummaryDisplayed());
        System.out.println("Order Summary Test Passed");
    }

    @Test(priority = 3)
    public void placeOrderTest() {

        productsPage.clickFirstCartItem();
        productsPage.clickCart();
        cartPage = new CartPage(driver);
        cartPage.clickCheckout();
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.clickPlaceOrder();
        Assert.assertTrue(checkoutPage.isOrderPlaced());
        System.out.println("Place Order Test Passed");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}