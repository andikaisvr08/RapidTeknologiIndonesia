package QaAutomation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class TC02_loginGagal {

	public static void main(String[] args) {
		// Build Variable Username and Password
		String userName = "Andika Isvari";
		String password = "Password Salah";
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

		// Verify Error
		System.out.println(driver.findElement(By.cssSelector("p.error")).getText());
		Assert.assertEquals(driver.findElement(By.cssSelector("p.error")).getText(), "* Incorrect username or password");
		
		// Close Browser
		driver.close();
	}

}
