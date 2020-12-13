package BaseClass;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;

import AbstractClasses.AbstractBOT;
import AbstractClasses.abstractAnontation;

public class TakeSnapShot  {
	public static void takeScreenShot(String fileWithPath) throws IOException {
		TakesScreenshot srcShot = ((TakesScreenshot) AbstractBOT.driver);
		
		File srcFile = srcShot.getScreenshotAs(OutputType.FILE);
		
		File desFile = new File(fileWithPath);
		
		FileUtils.copyFile(srcFile, desFile);
	}
}
