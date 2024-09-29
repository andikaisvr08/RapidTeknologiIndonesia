package QaAutomation;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TC04_loginDataDriven {

	public static void main(String[] args) throws IOException, InterruptedException {
		// Variable File path to Excel
		String excelFilePath = "C:/Users/Andika Isvari/Downloads/ListUserLogin.xlsx";
		// Open Excel File
		FileInputStream fileInputStream = new FileInputStream(excelFilePath);
		Workbook workbook = new XSSFWorkbook(fileInputStream);
		Sheet sheet = workbook.getSheet("Sheet1");
		
		// Open Browser
		System.setProperty("webdriver.chrome.driver", "C:/Program Files/chromedriver-win64/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/locatorspractice/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		System.out.println(driver.getCurrentUrl()); // Verify URL
		System.out.println(driver.getTitle()); // Verify Title

		// Loop Excel data
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            String userName = row.getCell(0).getStringCellValue();
            String password = row.getCell(1).getStringCellValue();
            String expectedResult = row.getCell(2).getStringCellValue();

            // Perform login
            driver.findElement(By.xpath("//input[@id='inputUsername']")).clear();
            driver.findElement(By.xpath("//input[@id='inputUsername']")).sendKeys(userName);
            driver.findElement(By.cssSelector("[name='inputPassword']")).clear();
            driver.findElement(By.cssSelector("[name='inputPassword']")).sendKeys(password);
            driver.findElement(By.className("signInBtn")).click();

            // Verify if login was successful or failed
            if (expectedResult.equalsIgnoreCase("Success")) {
            	// Verify Homepage
        		Thread.sleep(2000);
        		System.out.println(driver.findElement(By.tagName("p")).getText());
        		Assert.assertEquals(driver.findElement(By.tagName("p")).getText(), "You are successfully logged in.");

        		System.out.println(driver.findElement(By.cssSelector("div .login-container h2")).getText()); 
        		Assert.assertEquals(driver.findElement(By.cssSelector("div .login-container h2")).getText(), "Hello " + userName + ",");
        		// Log Out
        		driver.findElement(By.xpath("//button[text()='Log Out']")).click();
            } else if (expectedResult.equalsIgnoreCase("Fail")) {
                // Verify Error Message
                String errorMessage = driver.findElement(By.cssSelector("p.error")).getText();
                Assert.assertEquals(errorMessage, "* Incorrect username or password");
                System.out.println("User " + userName + " login failed as expected.");
            }
        }

        // Close resources
        workbook.close();
        fileInputStream.close();
        driver.quit();
    }
}

