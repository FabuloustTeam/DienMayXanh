package AbstractClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.testng.annotations.*;

public abstract class AbstractBOT {
	
	public static WebDriver driver;

	String driverPath = "E:\\Selenium\\chromedriver_win32\\chromedriver.exe";
	@BeforeMethod
	@Parameters({"url"})
	public void accessWebsite(String url) {
		System.setProperty("webdriver.chrome.silentOutput", "true");

		System.setProperty("webdriver.chrome.driver", driverPath);
		AbstractBOT.driver = new ChromeDriver();
		AbstractBOT.driver.manage().window().maximize();
		AbstractBOT.driver.get(url);
	}

	@AfterMethod
	public void closeBrowser() {
		driver.close();
	}
}