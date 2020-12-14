package AbstractAnnotation;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import ActionsForITestListener.ExcelUtils;
import TestCases.*;

public abstract class AbstractClass extends AbstractPath {
	public static WebDriver driver;
	//String actualResult = (driver.findElement(By.xpath("//div[@class='provinces-box']//child::span"))).getText();

	@BeforeMethod
	@Parameters({"url", "testcaseName", "sheetName"})
	public void accessWebsite(String url, String testcaseName, String sheetName) throws Exception {
		String path = getTestCasesFolderPath() + testcaseName + ".xlsx";
		ExcelUtils.setExcelFile(path, sheetName);
	
		System.setProperty("webdriver.chrome.silentOutput", "true");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
	}

	@AfterMethod
	public void closeBrowser() throws Exception {
		ExcelUtils.saveFile(getReportFilePath());
		driver.close();
	}
//	@AfterSuite
//	public void saveAndCloseAllFile() throws Exception {
//		ExcelUtils.closeandsaveFile(getReportFilePath());
//		driver.quit();
//	}
	@AfterClass
	public void saveAndCloseExcel() throws Exception {
		ExcelUtils.closeandsaveFile(getReportFilePath());
	}
}
