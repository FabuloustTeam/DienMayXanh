package AbstractClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.testng.annotations.*;

import BaseClass.ExcelUtils;

public abstract class AbstractBOT {
	
	public static WebDriver driver;

	String driverPath = "E:\\Selenium\\chromedriver_win32\\chromedriver.exe";
	@BeforeMethod
	@Parameters({"url"})
	public void accessWebsite(String url) throws Exception {
		System.setProperty("webdriver.chrome.silentOutput", "true");

		System.setProperty("webdriver.chrome.driver", driverPath);
		AbstractBOT.driver = new ChromeDriver();
		AbstractBOT.driver.manage().window().maximize();
		AbstractBOT.driver.get(url);
		ExcelUtils.setExcelFile(System.getProperty("user.dir")+"\\test case\\"+"\\LienheGopy-Nhannnn.xlsx", "Test case");
		ExcelUtils.setExcelFile(System.getProperty("user.dir")+"\\test case\\"+"\\ViewListProducts-Nhi-Nhan.xlsx", "Test case");
	}
	

	@AfterMethod
	public void closeBrowser() {
		driver.close();
	}
}