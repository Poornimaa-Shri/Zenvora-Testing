package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import config.Config;
import pages.ProductsPage;
import pages.WishlistPage;
import utils.DriverFactory;

public class WishlistTest {

    WebDriver driver;
    ProductsPage productsPage;
    WishlistPage wishlistPage;

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
    public void addToWishlistTest() {

        boolean before = productsPage.isWishlistActive();
        productsPage.clickFirstWishlistItem();
        productsPage.waitForWishlistToggle(before);
        boolean after = productsPage.isWishlistActive();
        Assert.assertTrue(after);
        System.out.println("Add to Wishlist Test Passed");
    }

    @Test(priority=4)
    public void removeFromWishlistTest() {

        if (!productsPage.isWishlistActive()) {
            productsPage.clickFirstWishlistItem();
            productsPage.waitForWishlistToggle(false);
        }
        boolean before = productsPage.isWishlistActive();
        productsPage.clickFirstWishlistItem();
        productsPage.waitForWishlistToggle(before);
        boolean after = productsPage.isWishlistActive();
        Assert.assertFalse(after);
        System.out.println("Remove from Wishlist Test Passed");
    }

    @Test(priority=2)
    public void wishlistPersistTest() {

        if (!productsPage.isWishlistActive()) {
            productsPage.clickFirstWishlistItem();
            productsPage.waitForWishlistToggle(false);
        }
        driver.navigate().refresh();
        try { Thread.sleep(1000); } catch (Exception e) {}
        boolean state = productsPage.isWishlistActive();
        Assert.assertTrue(state);
        System.out.println("Wishlist Persist Test Passed");
    }

    @Test(priority=3)
    public void wishlistPageLoadTest() {

        if (!productsPage.isWishlistActive()) {
            productsPage.clickFirstWishlistItem();
            productsPage.waitForWishlistToggle(false);
        }
        productsPage.clickWishlist();
        new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10))
                .until(d -> driver.getCurrentUrl().contains("wishlist"));
        Assert.assertTrue(driver.getCurrentUrl().contains("wishlist"));
        System.out.println("Wishlist Page Navigation Passed");
    }
    
    @Test(priority=5)
    public void wishlistAddToCartTest() {

        boolean beforeState = productsPage.isWishlistActive();
        if (!beforeState) {
            productsPage.clickFirstWishlistItem();
            productsPage.waitForWishlistToggle(beforeState);
        }
        productsPage.clickWishlist();
        wishlistPage = new WishlistPage(driver);
        int before = wishlistPage.getCartCount();
        wishlistPage.clickFirstAddToCart();
        wishlistPage.waitForCartCountToIncrease(before);
        int after = wishlistPage.getCartCount();
        Assert.assertTrue(after > before);
        System.out.println("Wishlist Add to Cart Test Passed");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}