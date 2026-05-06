package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrdersPage {

    WebDriver driver;

    public OrdersPage(WebDriver driver) {
        this.driver = driver;
    }

    By ordersContainer = By.id("orders");
    By orderCards = By.className("order-card");

    public boolean isOrdersPageLoaded() {

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> driver.getCurrentUrl().contains("orders"));
        return driver.getCurrentUrl().contains("orders");
    }

    public boolean areOrdersDisplayed() {

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> driver.findElements(orderCards).size() >= 0);
        return driver.findElements(orderCards).size() > 0;
    }

    public int getOrdersCount() {
        return driver.findElements(orderCards).size();
    }

    public String getFirstOrderText() {

        if (getOrdersCount() == 0) return "";
        return driver.findElements(orderCards).get(0).getText();
    }
}