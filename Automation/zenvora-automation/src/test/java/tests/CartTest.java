package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import config.Config;
import pages.ProductsPage;
import pages.CartPage;
import utils.DriverFactory;

public class CartTest {

    WebDriver driver;
    ProductsPage productsPage;
    CartPage cartPage;

    @BeforeMethod
    public void setup() {

        driver = DriverFactory.getDriver();
        driver.get(Config.baseUrl + Config.productsPage);
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("localStorage.setItem('user','test');");
        driver.navigate().refresh();
        productsPage = new ProductsPage(driver);
    }

    @Test(priority=1)
    public void addToCartTest() {

        String before = productsPage.getCartCount();
        productsPage.clickFirstCartItem();
        productsPage.waitForCartCountChange(before);
        String after = productsPage.getCartCount();
        Assert.assertNotEquals(after, before);
        System.out.println("Add to Cart Test Passed");
    }

    @Test(priority=2)
    public void removeFromCartTest() {

        if (productsPage.getCartCount().equals("0")) {
            productsPage.clickFirstCartItem();
            productsPage.waitForCartCountChange("0");
        }
        String before = productsPage.getCartCount();
        productsPage.clickFirstCartItem(); 
        productsPage.waitForCartCountChange(before);
        String after = productsPage.getCartCount();
        Assert.assertNotEquals(after, before);
        System.out.println("Remove from Cart Test Passed");
    }
    public void openCartPage() {
        productsPage.clickCart();
        cartPage = new CartPage(driver);
    }

    @Test(priority=3)
    public void increaseQuantityTest() {

        productsPage.clickFirstCartItem();
        productsPage.clickCart();
        cartPage = new CartPage(driver);
        int before = cartPage.getFirstItemQuantity();
        cartPage.clickIncreaseQty();
        cartPage.waitForQuantityChange(before);
        int after = cartPage.getFirstItemQuantity();
        Assert.assertTrue(after > before);
        System.out.println("Increase Quantity Test Passed");
    }

    @Test(priority=4)
    public void decreaseQuantityTest() {

        productsPage.clickFirstCartItem();
        productsPage.clickCart();
        cartPage = new CartPage(driver);
        int before = cartPage.getFirstItemQuantity();
        cartPage.clickDecreaseQty();
        cartPage.waitForQuantityChange(before);
        int after = cartPage.getFirstItemQuantity();
        Assert.assertTrue(after <= before);
        System.out.println("Decrease Quantity Test Passed");
    }

    @Test(priority=5)
    public void deleteItemTest() {

        productsPage.clickFirstCartItem();
        productsPage.clickCart();
        cartPage = new CartPage(driver);
        int before = cartPage.getCartItemCount();
        cartPage.clickDelete();
        cartPage.waitForItemRemoval(before);
        int after = cartPage.getCartItemCount();
        Assert.assertTrue(after < before);
        System.out.println("Delete Item Test Passed");
    }

    @Test(priority=6)
    public void totalCalculationTest() {

        productsPage.clickFirstCartItem();
        productsPage.clickFirstCartItem(); 
        productsPage.clickCart();
        cartPage = new CartPage(driver);
        int total = cartPage.getTotalAmount();
        Assert.assertTrue(total > 0);
        System.out.println("Total Calculation Test Passed");
    }

    @Test(priority=7)
    public void cartBadgeUpdateTest() {

        String before = productsPage.getCartCount();
        productsPage.clickFirstCartItem();
        productsPage.waitForCartCountChange(before);
        String after = productsPage.getCartCount();
        Assert.assertNotEquals(after, before);
        System.out.println("Cart Badge Update Test Passed");
    }

    @Test(priority=8)
    public void cartPersistTest() {

        productsPage.clickFirstCartItem();
        driver.navigate().refresh();
        try { Thread.sleep(1000); } catch (Exception e) {}
        String count = productsPage.getCartCount();
        Assert.assertTrue(!count.equals("0"));
        System.out.println("Cart Persist Test Passed");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}