package AbstractClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.testng.annotations.*;

import BaseClass.ExcelUtils;

public abstract class AbstractBOT {

	public static WebDriver driver;

	String driverPath = "E:\\Selenium\\chromedriver_win32\\chromedriver.exe";
	String pathExcel = System.getProperty("user.dir") + "\\DienmayXANH-TestData.xlsx";
	
	@BeforeMethod		
	@Parameters({ "url" })
	public void accessWebsite(String url) throws Exception {
		System.setProperty("webdriver.chrome.silentOutput", "true");

//		System.setProperty("webdriver.chrome.driver", driverPath);
//		AbstractBOT.driver = new ChromeDriver();
//		AbstractBOT.driver.manage().window().maximize();
//		AbstractBOT.driver.get(url);
		
		// ExcelUtils.setExcelFile(System.getProperty("user.dir")+"\\DienmayXANH-TestData.xlsx",
		// "ViewListProducts");
	}

	@AfterMethod
	public void closeBrowser() throws Exception {
		ExcelUtils.saveFile(pathExcel);
//		driver.close();
	}

	@BeforeClass
	@Parameters({ "SheetName" })
	public void setExcelFile(String SheetName) throws Exception {
		ExcelUtils.setExcelFile(pathExcel, SheetName);
	}
	
	@AfterClass
	public void closeAndSaveExcel() throws Exception {
		ExcelUtils.closeandsaveFile(pathExcel);
	}
}