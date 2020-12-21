package AbstractClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import BaseClass.ExcelUtils;

public abstract class abstractLink {
	public String baseUrl = "https://www.dienmayxanh.com";
	String driverPath = "C:\\Users\\Phuong\\Downloads\\chromedriver_win32\\chromedriver.exe";
	public WebDriver driver;


	@BeforeTest
	public void launchBrowser() throws Exception {
		System.out.println("lauching chrome browser");
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get(baseUrl);
		ExcelUtils.setExcelFile(System.getProperty("user.dir")+"\\DienmayXANH-TestData.xlsx", "Link");
	}
	
	@AfterTest
	public void closeBrowser() throws Exception {
		String path = System.getProperty("user.dir")+"\\DienmayXANH-TestData.xlsx";
		ExcelUtils.closeandsaveFile(path);
		driver.close();
	}
	
}
