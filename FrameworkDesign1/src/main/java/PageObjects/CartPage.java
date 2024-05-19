package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.ReUsableCode;

public class CartPage extends ReUsableCode {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[routerlink*='/dashboard/cart']")
	WebElement cartBtn;
	
	@FindBy(css="li.totalRow button")
	WebElement checkOutBtn;
	
	
	@FindBy(css="div.cartSection h3")
	List<WebElement> cartProducts;
	
	By cart= By.cssSelector("button[routerlink*='/dashboard/cart']");
	
	public void goToCart()
	{
		waitForWebElementToAppear(cartBtn);
		cartBtn.click();
	}
	public Boolean VerifyProductDisplay(String productName)
	{
		Boolean match = cartProducts.stream().anyMatch(pt->pt.getText().equalsIgnoreCase(productName));
		return match;
	}
	public CheckoutPage goToCheckout() {
		
		checkOutBtn.click();
		return new CheckoutPage(driver);
		
		
	}
	
	
	
	
	

}
