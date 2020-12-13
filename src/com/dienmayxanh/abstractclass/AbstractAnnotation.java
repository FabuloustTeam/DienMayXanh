package com.dienmayxanh.abstractclass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.testng.annotations.*;
import com.dienmayxanh.service.ExcelUtils;

public abstract class AbstractAnnotation {
	
	public static WebDriver driver;
	
	@BeforeMethod
	@Parameters({"url"})
	public void accessWebsite(String url, String Path, String SheetName) throws Exception {
		ExcelUtils.setExcelFile(Path, SheetName);
		
		System.setProperty("webdriver.chrome.silentOutput", "true");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
	}

	@AfterMethod
	public void closeBrowser(String Path) throws Exception {
		ExcelUtils.saveFile(Path);
		driver.close();
	}
	
	@BeforeClass
	public void setExcel(String Path, String SheetName) throws Exception {
		ExcelUtils.setExcelFile(Path, SheetName);
	}
	
	@AfterClass
	public void saveAndCloseExcel(String Path) throws Exception {
		ExcelUtils.closeandsaveFile(Path);
	}
}