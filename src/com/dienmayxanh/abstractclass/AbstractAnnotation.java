package com.dienmayxanh.abstractclass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.testng.annotations.*;
import com.dienmayxanh.service.ExcelUtils;

public abstract class AbstractAnnotation extends AbstractPath {

	public static WebDriver driver;

	@BeforeMethod
	@Parameters({ "url" })
	public void accessWebsite(String url) throws Exception {
		System.setProperty("webdriver.chrome.silentOutput", "true");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
	}

	@AfterMethod
	public void closeBrowser() throws Exception {
		ExcelUtils.saveFile(pathExcel);
		driver.close();
	 }                                           

	@Parameters({ "SheetName" })
	@BeforeClass
	public void setExcelBeforeTest(String SheetName) throws Exception {
		ExcelUtils.setExcelFile(pathExcel, SheetName);
	}

	@AfterClass
	public void saveAndCloseExcel() throws Exception {
		ExcelUtils.closeandsaveFile(pathExcel);
	}
}