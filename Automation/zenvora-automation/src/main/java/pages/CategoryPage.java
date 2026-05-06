package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.WaitHelper;

public class CategoryPage {

    WebDriver driver;
    WaitHelper wait;

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitHelper(driver);   
    }

    By products = By.className("product-card");
    By heading = By.id("pageTitle");   
    By subCategoryBtns = By.className("sub-btn");
    By activeSubCategory = By.cssSelector(".sub-btn.active");
    By filterBtn = By.id("filterBtn");
    By filterMenu = By.id("filterMenu");
    By lowToHigh = By.cssSelector("[data-sort='low']");
    By highToLow = By.cssSelector("[data-sort='high']");
    By prices = By.tagName("p"); 


    public boolean isProductsDisplayed() {
        return driver.findElements(products).size() > 0;
    }

    public String getHeading() {
        wait.waitForElement(heading);
        return driver.findElement(heading).getText();
    }

    public boolean isSubCategoryDisplayed() {
        return driver.findElements(subCategoryBtns).size() > 0;
    }

    public void clickFirstSubCategory() {
        driver.findElements(subCategoryBtns).get(0).click();
    }

    public void clickSecondSubCategory() {
        List<WebElement> list = driver.findElements(subCategoryBtns);
        if (list.size() > 1) {
            list.get(1).click();
        }
    }

    public int getProductCount() {
        return driver.findElements(products).size();
    }

    public void waitForProductsToChange(int oldCount) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> getProductCount() != oldCount);
    }
    
    public String getActiveSubCategory() {
        return driver.findElement(activeSubCategory).getText();
    }
    
    public void openFilter() {
        driver.findElement(filterBtn).click();
    }
    
    public boolean isFilterVisible() {
        return driver.findElement(filterMenu).isDisplayed();
    }
    
    public void clickLowToHigh() {
        driver.findElement(lowToHigh).click();
    }
    
    public void clickHighToLow() {
        driver.findElement(highToLow).click();
    }
    
    public void clickOutside() {
        driver.findElement(By.tagName("body")).click();
    }
    
    public List<Integer> getProductPrices() {

        List<WebElement> priceElements =
                driver.findElements(By.cssSelector(".product-card p"));
        List<Integer> prices = new ArrayList<>();
        for (WebElement el : priceElements) {
            String text = el.getText().replace("₹", "").trim();
            prices.add(Integer.parseInt(text));
        }

        return prices;
    }
    
}