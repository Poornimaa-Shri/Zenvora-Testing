package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    By checkoutItems = By.className("checkout-card");
    By total = By.id("finalTotal");
    By placeOrderBtn = By.className("place-order-btn");
    By successText = By.className("success-text");

    public boolean isOrderSummaryDisplayed() {

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> driver.findElements(checkoutItems).size() > 0);
        List<WebElement> items = driver.findElements(checkoutItems);
        return items.size() > 0;
    }

    public String getTotalAmount() {
        return driver.findElement(total).getText();
    }

    public void clickPlaceOrder() {
        driver.findElement(placeOrderBtn).click();
    }

    public boolean isOrderPlaced() {

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> driver.findElements(successText).size() > 0);
        return driver.findElements(successText).size() > 0;
    }
}