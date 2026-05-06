package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import config.Config;
import pages.ProductsPage;
import pages.CategoryPage;
import utils.DriverFactory;

public class FilterTest {

    WebDriver driver;
    ProductsPage productsPage;
    CategoryPage categoryPage;

    @BeforeMethod
    public void setup() {

        driver = DriverFactory.getDriver();
        driver.get(Config.baseUrl + Config.productsPage);
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("localStorage.setItem('user','test');");
        driver.navigate().refresh();
        productsPage = new ProductsPage(driver);
    }

    @Test
    public void filterOpenTest() {

        productsPage.clickFirstCategory();
        categoryPage = new CategoryPage(driver);
        categoryPage.openFilter();
        Assert.assertTrue(categoryPage.isFilterVisible());
        System.out.println("Filter Open Test Passed");
    }

    @Test
    public void sortLowToHighTest() {

        productsPage.clickFirstCategory();
        categoryPage = new CategoryPage(driver);
        categoryPage.openFilter();
        categoryPage.clickLowToHigh();
        List<Integer> prices = categoryPage.getProductPrices();
        List<Integer> sorted = new ArrayList<>(prices);
        Collections.sort(sorted);
        Assert.assertEquals(prices, sorted);
        System.out.println("Low to High Sort Passed");
    }

    @Test
    public void sortHighToLowTest() {

        productsPage.clickFirstCategory();
        categoryPage = new CategoryPage(driver);
        categoryPage.openFilter();
        categoryPage.clickHighToLow();
        List<Integer> prices = categoryPage.getProductPrices();
        List<Integer> sorted = new ArrayList<>(prices);
        sorted.sort(Collections.reverseOrder());
        Assert.assertEquals(prices, sorted);
        System.out.println("High to Low Sort Passed");
    }

    @Test
    public void filterCloseOnOutsideClickTest() {

        productsPage.clickFirstCategory();
        categoryPage = new CategoryPage(driver);
        categoryPage.openFilter();
        categoryPage.clickOutside();
        Assert.assertFalse(categoryPage.isFilterVisible());
        System.out.println("Filter Close Test Passed");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}