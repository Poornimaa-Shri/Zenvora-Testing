package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import config.Config;
import pages.ProductsPage;
import pages.CartPage;
import pages.CheckoutPage;
import pages.OrdersPage;
import utils.DriverFactory;

public class OrdersTest {

    WebDriver driver;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    OrdersPage ordersPage;

    @BeforeMethod
    public void setup() {

        driver = DriverFactory.getDriver();
        driver.get(Config.baseUrl + Config.productsPage);
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("localStorage.setItem('user','test');");
        driver.navigate().refresh();
        productsPage = new ProductsPage(driver);
        productsPage.clickFirstCartItem();
        productsPage.clickCart();
        cartPage = new CartPage(driver);
        cartPage.clickCheckout();
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.clickPlaceOrder();
    }

    @Test(priority = 1)
    public void ordersPageLoadTest() {

        driver.get(Config.baseUrl + "/pages/orders.html");
        ordersPage = new OrdersPage(driver);
        Assert.assertTrue(ordersPage.isOrdersPageLoaded());
        System.out.println("Orders Page Load Test Passed");
    }

    @Test(priority = 2)
    public void displayOrdersTest() {

        driver.get(Config.baseUrl + "/pages/orders.html");
        ordersPage = new OrdersPage(driver);
        Assert.assertTrue(ordersPage.areOrdersDisplayed());
        System.out.println("Display Orders Test Passed");
    }

    @Test(priority = 3)
    public void orderDetailsTest() {

        driver.get(Config.baseUrl + "/pages/orders.html");
        ordersPage = new OrdersPage(driver);
        String text = ordersPage.getFirstOrderText().toLowerCase();
        Assert.assertTrue(text.contains("qty"));
        Assert.assertTrue(text.contains("₹"));
        System.out.println("Order Details Test Passed");
    }

    @Test(priority = 4)
    public void ordersPersistTest() {

        driver.get(Config.baseUrl + "/pages/orders.html");
        ordersPage = new OrdersPage(driver);
        int before = ordersPage.getOrdersCount();
        driver.navigate().refresh();
        try { Thread.sleep(1000); } catch (Exception e) {}
        int after = ordersPage.getOrdersCount();
        Assert.assertEquals(after, before);
        System.out.println("Orders Persist Test Passed");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}