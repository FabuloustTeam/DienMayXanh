package com.dienmayxanh.service;

import java.io.File;
import com.dienmayxanh.abstractclass.AbstractAnnotation;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeSnapShot {

	public static void takeSnapShot(String fileWithPath) throws Exception {
		TakesScreenshot scrShot = ((TakesScreenshot) AbstractAnnotation.driver);

		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(fileWithPath);

		FileUtils.copyFile(SrcFile, DestFile);
	}
}
