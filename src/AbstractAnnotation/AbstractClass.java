package AbstractAnnotation;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import ActionsForITestListener.ExcelUtils;
import TestCases.*;

public abstract class AbstractClass extends AbstractPath {
	public static WebDriver driver;
	public static String ExcelSheetName;

	@BeforeClass
	@Parameters({"sheetName"})
	public void defineSheetExcel(String sheetName) throws Exception {
		ExcelUtils.setExcelFile(getExcelFilePath(), sheetName);
	}
	
	@BeforeMethod
	@Parameters({"url"})
	public void accessWebsite(String url) throws Exception {
		System.setProperty("webdriver.chrome.silentOutput", "true");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
	}

	@AfterMethod
	public void closeBrowser() throws Exception {
		ExcelUtils.saveFile(getExcelFilePath());
		driver.close();
	}
//	@AfterSuite
//	public void saveAndCloseAllFile() throws Exception {
//		ExcelUtils.closeandsaveFile(getReportFilePath());
//		driver.quit();
//	}
	@AfterClass
	public void saveAndCloseExcel() throws Exception {
		ExcelUtils.closeandsaveFile(getExcelFilePath());
	}
}
