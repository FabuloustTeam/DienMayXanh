import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class abstractLink {
	public String baseUrl = "https://www.dienmayxanh.com";
	public WebDriver driver;


	@BeforeTest
	public void launchBrowser() {
		System.out.println("lauching chrome browser");
		driver = new ChromeDriver();
		driver.get(baseUrl);
	}
	
	@AfterTest
	public void terminateBrowser() {
		driver.close();
	}
	
}
