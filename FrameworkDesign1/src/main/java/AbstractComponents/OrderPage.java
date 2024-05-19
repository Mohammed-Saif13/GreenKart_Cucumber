package AbstractComponents;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage extends ReUsableCode{

	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//tr[@class='ng-star-inserted']/td[2]")
	List<WebElement> orders ;
	
	public Boolean VerifyOrderDisplay(String productName)
	{
		Boolean match = orders.stream().anyMatch(item->item.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	

}
