package com.dienmayxanh.abstractclass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.testng.annotations.*;

public abstract class AbstractAnnotation {
	
	public static WebDriver driver;
	
	@BeforeMethod
	@Parameters({"url"})
	public void accessWebsite(String url) {
		System.setProperty("webdriver.chrome.silentOutput", "true");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
	}

	@AfterMethod
	public void closeBrowser() {
		driver.close();
	}
}