package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.CartPage;

public class ReUsableCode {
	WebDriver driver;
	
	public ReUsableCode(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	@FindBy(css="[routerlink*='/dashboard/cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='/dashboard/myorders']")
	WebElement orderHeader;
	
	By toastMsg =By.cssSelector("div#toast-container");


	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy )
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.invisibilityOf(ele));
		Thread.sleep(5000);
	}
	
	public CartPage goToCartPage()
	{
		cartHeader.click();
		CartPage cp= new CartPage(driver);
		return cp;
	}
	
	public OrderPage goToOrdersPage()
	{
		orderHeader.click();
		OrderPage op = new OrderPage(driver);
		return op;
		
	}
	
	
	

}
