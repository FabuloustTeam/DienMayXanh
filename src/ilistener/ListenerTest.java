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


public class ListenerTest implements ITestListener {
	 @Override		
	    public void onFinish(ITestContext arg0) {					
	        // TODO Auto-generated method stub				
	        		
	    }		

	    @Override		
	    public void onStart(ITestContext arg0) {					
	        // TODO Auto-generated method stub				
	        		
	    }		

	    @Override		
	    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {					
	        // TODO Auto-generated method stub				
	        		
	    }		

	    @Override		
	    public void onTestFailure(ITestResult Result) {	
	    	// TODO Auto-generated method stub
	    	
	    	String file = System.getProperty("user.dir") + "\\screenshots\\" + "ssfailedof" + (Result.getName()) + ".png";
	    	
	    	try {
	    		takeSnapShot(file);
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    }		

	    @Override		
	    public void onTestSkipped(ITestResult arg0) {					
	        // TODO Auto-generated method stub				
	        		
	    }		

	    @Override		
	    public void onTestStart(ITestResult arg0) {					
	        // TODO Auto-generated method stub				
	        		
	    }		

	    @Override		
	    public void onTestSuccess(ITestResult arg0) {					
	        // TODO Auto-generated method stub				
	        		
	    }	
	    
	    public static void takeSnapShot(String filePath) throws Exception {
			TakesScreenshot scrShot = ((TakesScreenshot) abstractLogin.driver);

			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile = new File(filePath);

			FileUtils.copyFile(SrcFile, DestFile);

		}
}
