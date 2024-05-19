package TESTNG.Tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
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

public class ErrorValidations extends BaseTest {
	

	String productName="ZARA COAT 3";
	

	@Test
	public void LoginErrorValidations() throws InterruptedException, IOException
	{
		
//		ReUsableCode rc= new ReUsableCode(driver);		
		ProductCatalogue pc = lp.loginApplication("Munna@Gurjar.com", "GURJAR RAAJ");
		System.out.println(lp.getError());

		AssertJUnit.assertEquals("Incorrect email or password.",lp.getError());			
	}
	@Test
	public void productErrorValidation() throws InterruptedException, IOException
	{
//		ReUsableCode rc= new ReUsableCode(driver);		
		String productName="ZARA COAT 3";
		
		ProductCatalogue pc = lp.loginApplication("dsf@perv.com", "12345678");
		List<WebElement>products=pc.getProductsList();
		pc.getProductByName(productName);
		
		CartPage cp = pc.addProductToCart(productName);
		cp.goToCart();
		
		Boolean match = cp.VerifyProductDisplay("Zara Product 33");
		Assert.assertFalse(match);
		
	}
	
}

