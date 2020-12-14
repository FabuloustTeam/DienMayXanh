package ilistener;
import java.io.File;

import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.ProtocolHandshake.Result;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import AbstractClass.abstractLogin;
import AbstractClass.abstractLink;
import BaseClass.ExcelUtils;


public class ListenerTest implements ITestListener {

	    @Override		
	    public void onTestFailure(ITestResult Result) {	
	    	// TODO Auto-generated method stub
	    	
	    	String file = System.getProperty("user.dir") + "\\screenshots\\" + "ssfailedof" + (Result.getName()) + ".png";
	    	
	    	try {
	    		takeSnapShot(file);
	    		String path = System.getProperty("user.dir")+"\\InputLogin.xlsx";
	    		ExcelUtils.setExcelFile(path, "Sheet1");
	    		int row = ExcelUtils.getRowContains(Result.getName(), 0);
	    		ExcelUtils.setCellData(row, 3, "fail");
	    		ExcelUtils.closeandsaveFile(path);
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    }		

	    @Override		
	    public void onTestSkipped(ITestResult Result) {					
	        // TODO Auto-generated method stub				
	    	try {
	    		String path = System.getProperty("user.dir")+"\\InputLogin.xlsx";
	    		ExcelUtils.setExcelFile(path, "Sheet1");
	    		int row = ExcelUtils.getRowContains(Result.getName(), 0);
	    		ExcelUtils.setCellData(row, 3, "skip");
	    		ExcelUtils.closeandsaveFile(path);
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}	
	    }		


	    @Override		
	    public void onTestSuccess(ITestResult Result) {					
	        // TODO Auto-generated method stub		
	    	try {
	    		String path = System.getProperty("user.dir")+"\\InputLogin.xlsx";
	    		ExcelUtils.setExcelFile(path, "Sheet1");
	    		int row = ExcelUtils.getRowContains(Result.getName(), 0);
	    		ExcelUtils.setCellData(row, 3, "success");
	    		ExcelUtils.closeandsaveFile(path);
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	        		
	    }	
	    
	    public static void takeSnapShot(String filePath) throws Exception {
			TakesScreenshot scrShot = ((TakesScreenshot) abstractLogin.driver);

			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile = new File(filePath);

			FileUtils.copyFile(SrcFile, DestFile);

		}
}
