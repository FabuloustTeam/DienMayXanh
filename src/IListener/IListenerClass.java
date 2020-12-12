package IListener;

import AbstractAnnotation.AbstractClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.*;

public class IListenerClass implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		// System.out.println("The test case failed is " + result.getName());

		String file = System.getProperty("user.dir") + "\\screenshots\\" + "ssfailedof" + (result.getName()) + ".png";
		try {
			takeSnapShot(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		// System.out.println(result.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	public static void takeSnapShot(String filePath) throws Exception {
		TakesScreenshot scrShot = ((TakesScreenshot) AbstractClass.driver);

		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(filePath);

		FileUtils.copyFile(SrcFile, DestFile);

	}

}
