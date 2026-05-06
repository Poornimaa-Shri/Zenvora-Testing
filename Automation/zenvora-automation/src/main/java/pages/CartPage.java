package pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.WaitHelper;

public class CartPage {

    WebDriver driver;
    WaitHelper wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitHelper(driver);
    }

    // Locators
    By cartItems = By.className("cart-item");
    By plusBtn = By.cssSelector(".fa-plus");
    By minusBtn = By.cssSelector(".fa-minus");
    By deleteBtn = By.className("delete");
    By qtyNumber = By.className("qty-number");
    By total = By.id("total");
    By cartCountText = By.id("cartCountText");
    By checkoutBtn = By.id("checkoutBtn");

    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }

    public boolean isCartEmpty() {
        return getCartItemCount() == 0;
    }

    public int getCartCountText() {
        wait.waitForElement(cartCountText);
        return Integer.parseInt(driver.findElement(cartCountText).getText());
    }

    public int getFirstItemQuantity() {
        wait.waitForElement(qtyNumber);
        return Integer.parseInt(driver.findElements(qtyNumber).get(0).getText());
    }

    public void clickIncreaseQty() {
        wait.waitForElement(plusBtn);
        driver.findElements(plusBtn).get(0).click();
    }

    public void clickDecreaseQty() {
        wait.waitForElement(minusBtn);
        driver.findElements(minusBtn).get(0).click();
    }

    public void clickDelete() {
        wait.waitForElement(deleteBtn);
        driver.findElements(deleteBtn).get(0).click();
    }

    public int getTotalAmount() {
        wait.waitForElement(total);
        String text = driver.findElement(total).getText().replace("₹", "");
        return Integer.parseInt(text);
    }

    public void waitForCartCountChange(int before) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> getCartCountText() != before);
    }

    public void waitForQuantityChange(int before) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> getFirstItemQuantity() != before);
    }

    public void waitForItemRemoval(int beforeCount) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> getCartItemCount() < beforeCount);
    }
    
    public void clickCheckout() {

        new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10))
                .until(d -> driver.findElement(checkoutBtn).isDisplayed());
        driver.findElement(checkoutBtn).click();
    }
}