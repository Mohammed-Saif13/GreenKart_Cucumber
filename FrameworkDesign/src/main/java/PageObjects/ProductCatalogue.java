package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.ReUsableCode;

public class ProductCatalogue extends ReUsableCode {
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	@FindBy(xpath="//div[@class='card-body']/button[2]")
	WebElement cartbtn;
	
	By toastMsg =By.cssSelector("div#toast-container");
	
	By cartBtn=By.xpath("//div[@class='card-body']/button[2]");
	
	By productsBy=By.cssSelector(".mb-3");//PAGEFACTORY CANNOT be implemented 
	
	public List<WebElement> getProductsList()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getProductsList().stream().filter(product->product.findElement(By.cssSelector("div.card-body h5 b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return prod;
	}
	public CartPage addProductToCart(String productName) throws InterruptedException
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(cartBtn).click();;
		waitForElementToAppear(toastMsg);
		waitForElementToDisappear(spinner);
		Thread.sleep(5000);
		return new CartPage(driver);
	}
	
	
	
	
	
	
	

}
