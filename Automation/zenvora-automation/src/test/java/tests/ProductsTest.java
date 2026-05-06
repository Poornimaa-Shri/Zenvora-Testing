package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import config.Config;
import pages.ProductsPage;
import utils.DriverFactory;

public class ProductsTest {

    WebDriver driver;
    ProductsPage productsPage;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.getDriver();
        driver.get(Config.baseUrl + Config.productsPage);
        ((JavascriptExecutor) driver).executeScript(
            "localStorage.setItem('user','test');"
        );
        driver.navigate().refresh();
        productsPage = new ProductsPage(driver);
    }

    @Test
    public void productsLoadTest() {
        Assert.assertTrue(productsPage.isProductsDisplayed());
        System.out.println("Products Load Test Passed");
    }

    @Test
    public void productImagesTest() {
        Assert.assertTrue(productsPage.areImagesDisplayed());
        System.out.println("Product Images Test Passed");
    }

    @Test
    public void productNamePriceTest() {
        Assert.assertTrue(productsPage.isNameAndPriceDisplayed());
        System.out.println("Product Name & Price Test Passed");
    }

    @Test
    public void cartNavigationTest() {
        productsPage.clickCart();
        Assert.assertTrue(driver.getCurrentUrl().contains("cart"));
        System.out.println("Cart Navigation Test Passed");
    }

    @Test
    public void wishlistNavigationTest() {
        productsPage.clickWishlist();
        Assert.assertTrue(driver.getCurrentUrl().contains("wishlist"));
        System.out.println("Wishlist Navigation Test Passed");
    }
    
    @Test
    public void cartCountUpdateTest() {

        String before = productsPage.getCartCount();
        productsPage.clickFirstCartItem();
        productsPage.waitForCartCountChange(before);
        String after = productsPage.getCartCount();
        Assert.assertNotEquals(after, before);
        System.out.println("Cart Count Update Test Passed");
    }
    
    @Test
    public void wishlistToggleTest() {

        boolean before = productsPage.isWishlistActive();
        productsPage.clickFirstWishlistItem();
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(driver -> {
                boolean current = productsPage.isWishlistActive();
                return current != before;
            });
        boolean after = productsPage.isWishlistActive();
        Assert.assertNotEquals(after, before);
        System.out.println("Wishlist Toggle Test Passed");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}