package Listeners;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;
import BaseClass.TakeSnapShot;

public class IListeners implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		String file = System.getProperty("user.dir")+"\\screenshots\\"+result.getName()+".png";
		try {
			TakeSnapShot.takeScreenShot(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block 
			////dhs
			e.printStackTrace();
		}
	}
}
