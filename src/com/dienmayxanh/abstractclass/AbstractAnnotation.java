package com.dienmayxanh.abstractclass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.testng.annotations.*;
import com.dienmayxanh.service.ExcelUtils;

public abstract class AbstractAnnotation extends AbstractPath {
	
	public static WebDriver driver;
	
	@BeforeMethod
	@Parameters({ "url", "testCaseName", "SheetName" })
	public void accessWebsite(String url, String testCaseName, String SheetName) throws Exception {
		String Path = getTestCasesFolderPath() + "DienmayXANH-" + testCaseName + ".xlsx";
		ExcelUtils.setExcelFile(Path, SheetName);

		System.setProperty("webdriver.chrome.silentOutput", "true");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
	}

	@AfterMethod
	public void closeBrowser() throws Exception {
		String Path = getReportFilePath();
		ExcelUtils.saveFile(Path);
		driver.close();
	}

	@AfterSuite
	public void saveAndCloseExcel() throws Exception {
		String Path = getReportFilePath();
		ExcelUtils.closeandsaveFile(Path);
	}
}