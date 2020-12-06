import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class abstractLogin {
	public String baseUrl = "https://www.dienmayxanh.com";
	String driverPath = "C:\\Users\\Phuong\\Downloads\\chromedriver_win32\\chromedriver.exe";
	public WebDriver driver;
	
	@BeforeMethod
	public void launchBrowser() {
		System.out.println("lauching chrome browser");
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get(baseUrl);
	}
	
	@AfterMethod
	public void terminateBrowser() {
		driver.close();
	}

}
