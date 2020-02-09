package classTestNG;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//Scenario: Add new account on Techfios Site 
//Beginner:
//1: Open Browser and go to site http://techfios.com/test/billing/?ng=admin/
//2. Enter username: techfiosdemo@gmail.com
//3. Enter password: abc123
//4. Click login button
//5. Click on Bank & Cash
//6. Click on New Account
//7. Fill in the Add New Account Form (Randomize Account Title and Balance)
//8. Click submit,
//​Intermediate:
//9. Validate the presence of "Account Created Successfully" message
//Advance: 
//10. Scroll Down,
//11. Validate new account showed up in the bottom of the table
//12. ​Delete that account
//13. Validate account deleted from the bottom of the table

public class hwClass6 {

	WebDriver driver;

	@BeforeTest
	public void geturl() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://techfios.com/test/billing/?ng=login/");
		waitForElement(driver, 10, By.id("username"));
		driver.findElement(By.id("username")).sendKeys("techfiosdemo@gmail.com");
		driver.findElement(By.id("password")).sendKeys("abc123");
		driver.findElement(By.name("login")).click();
	}

	@Test
	public void hw() throws InterruptedException {
		Random rnd = new Random();
		int randmNm = rnd.nextInt(500);

		waitForElement(driver, 10, By.xpath("//span[contains(text(),'Bank')]"));
		driver.findElement(By.xpath("//span[contains(text(),'Bank')]")).click();
		waitForElement(driver, 10, By.xpath("//a[contains(text(),'New Account')]"));
		driver.findElement(By.linkText("New Account")).click();

		String AddedName = "farberTest1234" + randmNm;
		// waitForElement(driver, 10, By.id("account"));
		Thread.sleep(3000);
		driver.findElement(By.id("account")).sendKeys(AddedName);
		driver.findElement(By.id("description")).sendKeys("justForTest ");
		driver.findElement(By.id("balance")).sendKeys("950000");

		driver.findElement(By.xpath("//i[@class='fa fa-check']")).click();

		Thread.sleep(3000);
		// waitForElement(driver, 10, By.xpath("//div[@class='alert alert-success fade
		// in']"));

		driver.findElement(By.xpath("//tbody/descendant::td[text()='" + AddedName + "']")).isDisplayed();

		// scroll down
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(0,13000)");

		WebElement deletrow = driver.findElement(
				By.xpath("//td[text()='" + AddedName + "']/parent::tr/descendant::i[@class='fa fa-trash']"));
		deletrow.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()='OK']")).click();
		driver.findElement(By.xpath("//div[@class='alert alert-success fade in']")).isDisplayed();

		
		
		
		
	}

	public static void waitForElement(WebDriver driver, int TimeInSeconds, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, TimeInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}
}
