package stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;

import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.ConfirmationPage;
import PageObjects.LandingPage;
import PageObjects.ProductCatalogue;
import TESTNG.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinitionImpl extends BaseTest {
	
	public LandingPage lp;
	public ProductCatalogue pc;
	public CartPage cp;
	public CheckoutPage cop;
	public ConfirmationPage confP;
	WebDriver driver;
	@Given("I am on the eCommerce login page")
	public void I_am_on_the_eCommerce_login_page() throws IOException
	{
		lp =launchApplication();
	}
	@Given("^Logged in with the (.+) and (.+)$")
	public void Logged_in_with_username_and_password(String userName, String password)
	{
		pc = lp.loginApplication(userName,password);
	}
	
	@When("^I add the product (.+) to cart$")
	public void i_add_the_product_tocart(String productName) throws InterruptedException
	{
		List<WebElement>products=pc.getProductsList();
		cp = pc.addProductToCart(productName);
	}
	@When("^submit the order for (.+)$")
	public void submit_the_order_for_product(String productName)
	{
		cp.goToCart();
		
		Boolean match=cp.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		
		cop = cp.goToCheckout();
		cop.selectCountry("India");
		confP= cop.submitOrder();
	}
	@Then("I verify {string} is displayed")
	public void verify_successMsg_is_displayed(String string)
	{
		String successMSG=confP.OrderSuccessMsg();
		AssertJUnit.assertTrue(successMSG.equalsIgnoreCase(string));
	}

}
