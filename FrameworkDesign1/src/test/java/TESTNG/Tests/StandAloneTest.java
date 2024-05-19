package TESTNG.Tests;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		
		String productName="ZARA COAT 3";
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.xpath("//*[@id='userEmail']")).sendKeys("dsf@perv.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("12345678");
		
		driver.findElement(By.xpath("//input[@id='login']")).click();
		
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(6));
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		//WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("div.card-body h5 b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		//prod.findElement(By.xpath("//div[@class='card-body']/button[2]")).click();
		//prod_name=product.findElement(By.cssSelector("h5 b")).getText();
//CSS		//prod.findElement(By.cssSelector(".card-body button:last-of-type").click();
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#toast-container")));
		w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		
		Thread.sleep(5000);
		
		driver.findElement(By.cssSelector("button[routerlink*='/dashboard/cart']")).click();
		
		//w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[routerlink*='/dashboard']")));
		driver.findElement(By.cssSelector("button[routerlink*='/dashboard']")).click();
		
		List<WebElement> cartProducts= driver.findElements(By.cssSelector("div.cartSection h3"));
		
		//Boolean match = cartProducts.stream().anyMatch((cprod->cprod.getText().equalsIgnoreCase(productName)));
		//Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector("li.totalRow button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section .ta-results")));
		
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		System.out.println("Country Selected");
		driver.findElement(By.xpath("//a[contains(@class,'btnn action__submit')]")).click();
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='toast-container']")));
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='hero-primary']")));
		
		String orderId=driver.findElement(By.xpath("")).getText();
		
		String successMsg = driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
		Assert.assertEquals("THANKYOU FOR THE ORDER." ,successMsg );
	}

}
