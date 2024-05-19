package TESTNG.Tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import AbstractComponents.OrderPage;
import AbstractComponents.ReUsableCode;
import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.ConfirmationPage;
import PageObjects.LandingPage;
import PageObjects.ProductCatalogue;
import TESTNG.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	
	String productName="ADIDAS ORIGINAL";

	@Test(dataProvider="getData" ,groups= {"Purchase"})
	public void SubmitOrder(HashMap<String,String> input) throws InterruptedException, IOException
	{
		
//		ReUsableCode rc= new ReUsableCode(driver);			
		ProductCatalogue pc = lp.loginApplication(input.get("email"),input.get("password"));
		List<WebElement>products=pc.getProductsList();
		//pc.addProductToCart(productName);
		//prod_name=product.findElement(By.cssSelector("h5 b")).getText()----------prod.findElement(By.cssSelector(".card-body button:last-of-type").click();
		CartPage cp = pc.addProductToCart(input.get("product"));
		cp.goToCart();
		
		Boolean match=cp.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		
		CheckoutPage cop = cp.goToCheckout();
		cop.selectCountry("India");
		ConfirmationPage confP= cop.submitOrder();
		String successMSG=confP.OrderSuccessMsg();
		AssertJUnit.assertTrue(successMSG.equalsIgnoreCase("Thankyou for the order."));
		
	}
	
	
	public String getScreenshot(String testcaseName) throws IOException
	{
	   TakesScreenshot ts =(TakesScreenshot)driver;
	   File source = ts.getScreenshotAs(OutputType.FILE);
	   File file = new File(System.getProperty("user.dir")+"//reports"+testcaseName+".png");
	   FileUtils.copyFile(source, file);
	   return System.getProperty(("user.dir")+"//reports"+testcaseName+".png");
	}
	@Test(dependsOnMethods= {"SubmitOrder"},dataProvider= "getData")
	public void orderHistoryTest()
	{
		ProductCatalogue pc = lp.loginApplication("dsf@perv.com", "12345678");
		OrderPage op =pc.goToOrdersPage();
		Assert.assertTrue(op.VerifyOrderDisplay(productName));
		
	}
	
	@DataProvider
	public Object[][] getData()
	{
		
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "anshika@gmail.com");
		map.put("password", "Iamking@000");
		map.put("product","ZARA COAT 3");
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "shetty@gmail.com");
		map1.put("password", "12345678");
		map1.put("product","ADIDAS ORIGINAL");
		
		return new Object[][] {{map} , {map1} };
		//return new Object[][] {{"dsf@perv.com", "12345678","ZARA COAT 3" },{"anshika@gmail.com","Iamking@000","ADIDAS ORIGINAL"},{} };
		
		
	}
}

