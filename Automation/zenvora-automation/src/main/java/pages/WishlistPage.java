package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.WaitHelper;

public class WishlistPage {

    WebDriver driver;
    WaitHelper wait;

    public WishlistPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitHelper(driver); 
    }

    By addToCartBtn = By.className("cart-icon");
    By cartCount = By.id("cartCount");

    public void clickFirstAddToCart() {

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> driver.findElements(addToCartBtn).size() > 0);
        List<WebElement> buttons = driver.findElements(addToCartBtn);
        if (buttons.size() == 0) {
            throw new RuntimeException("No cart icon found in wishlist");
        }

        buttons.get(0).click();
    }

    public int getCartCount() {

        wait.waitForElement(cartCount);
        String text = driver.findElement(cartCount).getText();
        if (text == null || text.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(text);
    }

    public void waitForCartCountToIncrease(int before) {

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> getCartCount() > before);
    }
}