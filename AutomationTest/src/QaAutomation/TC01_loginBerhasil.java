package QaAutomation;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class TC01_loginBerhasil {
		
	public static void main(String[] args) throws InterruptedException {
		
		// Build Variable Username and Password
		String userName = "Andika Isvari";
		String password = "rahulshettyacademy";
		// Open Browser
		System.setProperty("webdriver.chrome.driver", "C:/Program Files/chromedriver-win64/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/locatorspractice/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		System.out.println(driver.getCurrentUrl()); // Verify URL
		System.out.println(driver.getTitle()); // Verify Title
	
		// Login
		driver.findElement(By.xpath("//input[@id='inputUsername']")).sendKeys(userName); 
		driver.findElement(By.cssSelector("[name='inputPassword']")).sendKeys(password); 
		driver.findElement(By.className("signInBtn")).click(); 
		
		// Verify Homepage
		Thread.sleep(2000);
		System.out.println(driver.findElement(By.tagName("p")).getText());
		Assert.assertEquals(driver.findElement(By.tagName("p")).getText(), "You are successfully logged in.");

		System.out.println(driver.findElement(By.cssSelector("div .login-container h2")).getText()); 
		Assert.assertEquals(driver.findElement(By.cssSelector("div .login-container h2")).getText(), "Hello " + userName + ",");
		
		// Log Out and Close Browser
		driver.findElement(By.xpath("//button[text()='Log Out']")).click();
		driver.close();
	}

}
