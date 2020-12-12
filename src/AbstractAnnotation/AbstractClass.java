package AbstractAnnotation;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public abstract class AbstractClass {
	public static WebDriver driver;
	
	@BeforeMethod
	@Parameters({"url"})
	public void accessWebsite(String url) {
		System.setProperty("webdriver.chrome.silentOutput", "true");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
	}

	@AfterMethod
	public void closeBrowser() {
		driver.close();
	}
}
