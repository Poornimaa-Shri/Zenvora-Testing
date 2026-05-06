package tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import config.Config;
import pages.ProductsPage;
import pages.CategoryPage;
import utils.DriverFactory;

public class CategoryTest {

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
    public void categoryNavigationTest() {

        productsPage.clickFirstCategory();
        Assert.assertTrue(driver.getCurrentUrl().contains("category"));
        System.out.println("Category Navigation Test Passed");
    }

    @Test
    public void categoryProductsLoadTest() {

        productsPage.clickFirstCategory();
        categoryPage = new CategoryPage(driver);
        Assert.assertTrue(categoryPage.isProductsDisplayed());
        System.out.println("Category Products Load Test Passed");
    }

    @Test
    public void categoryHeadingTest() {

        productsPage.clickCategoryByName("living");
        categoryPage = new CategoryPage(driver);
        String heading = categoryPage.getHeading().toLowerCase();
        Assert.assertTrue(heading.contains("living"));
        System.out.println("Category Heading Test Passed");
    }

    @Test
    public void subCategoryVisibleTest() {

        productsPage.clickFirstCategory();
        categoryPage = new CategoryPage(driver);
        Assert.assertTrue(categoryPage.isSubCategoryDisplayed());
        System.out.println("SubCategory Visible Test Passed");
    }

    @Test
    public void subCategoryFilterTest() {

        productsPage.clickFirstCategory();
        categoryPage = new CategoryPage(driver);
        String before = categoryPage.getActiveSubCategory();
        categoryPage.clickSecondSubCategory();
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(d -> !categoryPage.getActiveSubCategory().equals(before));
        String after = categoryPage.getActiveSubCategory();
        Assert.assertNotEquals(after, before);
        System.out.println("SubCategory Filter Test Passed");
    }
    
    @Test
    public void defaultSubCategoryTest() {

        productsPage.clickFirstCategory();
        categoryPage = new CategoryPage(driver);
        Assert.assertTrue(categoryPage.getProductCount() > 0);
        System.out.println("Default SubCategory Test Passed");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}