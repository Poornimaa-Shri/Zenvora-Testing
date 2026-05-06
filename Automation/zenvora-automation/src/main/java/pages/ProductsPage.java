package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitHelper;

import java.time.Duration;
import java.util.List;

public class ProductsPage {

    WebDriver driver;
    WaitHelper wait;   

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitHelper(driver);  
    }

    By products = By.className("product-card");
    By productImages = By.tagName("img");
    By productNames = By.tagName("h3");
    By productPrices = By.tagName("p");
    By cartBtn = By.id("cartbutton");
    By wishlistBtn = By.id("wishlistbutton");
    By cartIcons = By.className("cart-btn");
    By wishlistIcons = By.className("wishlist-btn");
    By cartCount = By.id("cartCount");
    By searchBox = By.id("searchInput");
    By categoryBtns = By.className("cat-btn");

    public boolean isProductsDisplayed() {
        List<WebElement> list = driver.findElements(products);
        return list.size() > 0;
    }

    public boolean areImagesDisplayed() {
        List<WebElement> imgs = driver.findElements(productImages);
        for (WebElement img : imgs) {
            if (!img.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public boolean isNameAndPriceDisplayed() {
        List<WebElement> names = driver.findElements(productNames);
        List<WebElement> prices = driver.findElements(productPrices);
        return names.size() > 0 && prices.size() > 0;
    }

    public void clickCart() {
        driver.findElement(cartBtn).click();
    }

    public void clickWishlist() {
        driver.findElement(wishlistBtn).click();
    }

    public void clickFirstCartItem() {
        driver.findElements(cartIcons).get(0).click();
    }

    public void clickFirstWishlistItem() {
        driver.findElements(wishlistIcons).get(0).click();
    }

    public String getCartCount() {
        return driver.findElement(cartCount).getText();
    }

    public void waitForCartCountChange(String oldCount) {

        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(driver -> {
                String newCount = driver.findElement(cartCount).getText();
                return !newCount.equals(oldCount) && !newCount.isEmpty();
            });
    }
    
    public void waitForWishlistToggle(boolean before) {

        new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10))
                .until(d -> isWishlistActive() != before);
    }

    public boolean isWishlistActive() {
        return driver.findElements(wishlistIcons).get(0)
                .getAttribute("class")
                .contains("active");
    }
    
    public void searchProduct(String text) {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(text);
    }

    public void pressEnter() {
        driver.findElement(searchBox).sendKeys(Keys.ENTER);
    }

    public int getProductCount() {
        return driver.findElements(products).size();
    }

    public boolean isNoProductDisplayed() {
        return driver.findElements(products).size() == 0;
    }
    
    public void clickFirstCategory() {
        driver.findElements(categoryBtns).get(0).click();
    }

    public void clickCategoryByName(String name) {
        List<WebElement> cats = driver.findElements(categoryBtns);
        for (WebElement cat : cats) {
            if (cat.getText().toLowerCase().contains(name.toLowerCase())) {
                cat.click();
                break;
            }
        }
    }
}