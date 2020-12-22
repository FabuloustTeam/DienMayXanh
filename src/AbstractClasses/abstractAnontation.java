package AbstractClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.*;

public class abstractAnontation {
	public static WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {
		driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		System.out.println(" before method");
	}
	
	@AfterMethod
	public void afterMethod() {
		driver.close();
	}

}
