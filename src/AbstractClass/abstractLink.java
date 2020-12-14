package AbstractClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class abstractLink {
	public String baseUrl = "https://www.dienmayxanh.com";
	String driverPath = "C:\\Users\\Phuong\\Downloads\\chromedriver_win32\\chromedriver.exe";
	public WebDriver driver;


	@BeforeTest
	public void launchBrowser() {
		System.out.println("lauching chrome browser");
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get(baseUrl);
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.close();
	}
	
}
