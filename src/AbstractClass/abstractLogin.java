package AbstractClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.beust.jcommander.Parameters;

import BaseClass.ExcelUtils;

public abstract class abstractLogin {
	public String baseUrl = "https://www.dienmayxanh.com";
	String driverPath = "C:\\Users\\Phuong\\Downloads\\chromedriver_win32\\chromedriver.exe";
	public static WebDriver driver;
	
	@BeforeMethod
	public void launchBrowser() throws Exception {
		System.out.println("lauching chrome browser");
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get(baseUrl);
		ExcelUtils.setExcelFile(System.getProperty("user.dir")+"\\test cases\\"+"DienmayXANH-LogIn.xlsx", "Test case");
	}
	
	@AfterMethod
	public void closeBrowser() throws Exception {
		String path = System.getProperty("user.dir")+"\\DienmayXANH-FunctionalTestExecution.xlsx";
		ExcelUtils.saveFile(path);
		driver.close();
	}

}
