package ActionsForITestListener;

import java.io.File;
import org.openqa.selenium.*;
import org.apache.commons.io.FileUtils;

import AbstractAnnotation.AbstractClass;

public class TakeSnapShot {
	public static void takeSnapShot(String filePath) throws Exception {
		TakesScreenshot scrShot = ((TakesScreenshot) AbstractClass.driver);

		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(filePath);

		FileUtils.copyFile(srcFile, destFile);
	}

}
