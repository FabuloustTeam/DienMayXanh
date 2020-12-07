import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public abstract class AbstractClass {
	public WebDriver driver;
	
	@BeforeMethod
	@Parameters({"url"})
	public void accessWebsite(String url) {
		System.setProperty("webdriver.chrome.silentOutput", "true");
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		this.driver.get(url);
	}

	@AfterMethod
	public void closeBrowser() {
		this.driver.close();
	}
}
