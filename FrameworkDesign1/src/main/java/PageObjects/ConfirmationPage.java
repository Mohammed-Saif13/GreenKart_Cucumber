package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.ReUsableCode;

public class ConfirmationPage extends ReUsableCode{

	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(xpath="//h1[@class='hero-primary']")
	WebElement confirmationmsg;
	
	@FindBy(xpath="//label[@class='ng-star-inserted']")
	WebElement orderId;
	
	By toastMsg=By.xpath("//div[@id='toast-container']");
	
	public String OrderSuccessMsg()
	
	{
		waitForElementToAppear(toastMsg);
		String msg=confirmationmsg.getText();
		System.out.println("Order : "+orderId.getText());
		return msg;
	}
	

}
