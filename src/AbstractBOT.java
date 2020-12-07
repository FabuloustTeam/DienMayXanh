
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.testng.annotations.*;

public abstract class AbstractBOT {
	
	public WebDriver driver;

	String driverPath = "E:\\Selenium\\chromedriver_win32\\chromedriver.exe";
	@BeforeMethod
	@Parameters({"url"})
	public void accessWebsite(String url) {
		System.setProperty("webdriver.chrome.silentOutput", "true");

		System.setProperty("webdriver.chrome.driver", driverPath);
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		this.driver.get(url);
	}

	@AfterMethod
	public void closeBrowser() {
		this.driver.close();
	}
}