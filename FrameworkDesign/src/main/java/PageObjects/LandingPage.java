package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AbstractComponents.ReUsableCode;

public class LandingPage extends ReUsableCode {
	WebDriver driver ;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
		PageFactory.initElements(driver	,this);
	}
	//WebElement userMail = driver.findElement(By.id("userEmail"));
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement LoginBtn;
	
	@FindBy(id="[class*='toast-message']")
	WebElement errorMsg;
	
	public ProductCatalogue loginApplication(String email,String password)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		LoginBtn.click();
		return new ProductCatalogue(driver);
		
	}
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String getError()
	{
		waitForWebElementToAppear(errorMsg);
		return errorMsg.getText();
		
	}
	
	


}
