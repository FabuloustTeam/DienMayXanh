package com.dienmayxanh.ActionForListener;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.io.FileUtils;

public class TakeSnapShot {
	static WebDriver driver;
	public static WebDriver getDriver() {
		if(driver== null) {
			WebDriver driver;
			driver = new ChromeDriver();
		}
		return driver;
	}
	
	public static void takeSnapShot(WebDriver driver, String fileWithPath) throws IOException {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(fileWithPath);
		FileUtils.copyFile(srcFile, destFile);
	}
}
