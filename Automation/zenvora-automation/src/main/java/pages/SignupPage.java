package pages;

import utils.WaitHelper;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SignupPage {
	
	WebDriver driver;
	WaitHelper wait;

	public SignupPage(WebDriver driver) {
		this.driver=driver;
		this.wait= new WaitHelper(driver);
	}
	
	By username= By.id("signupUser");
	By password= By.id("signupPass");
	By signupBtn= By.id("signupbtn");
	By errormsg= By.id("signupMsg");
	
	public void signupClick() {
		driver.findElement(By.linkText("Sign up")).click();
	}
	
	public void enterUsername(String user) {
        wait.waitForElement(username);
        driver.findElement(username).clear();
        driver.findElement(username).sendKeys(user);
    }

    public void enterPassword(String pass) {
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pass);
    }

    public void clickSignup() {
        driver.findElement(signupBtn).click();
    }

    public void Signup(String user, String pass) {
        enterUsername(user);
        enterPassword(pass);
        clickSignup();
    }
    
    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions
                .visibilityOfElementLocated(errormsg))
                .getText();
    }
	
	
}
