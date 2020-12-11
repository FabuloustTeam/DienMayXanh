package com.dienmayxanh.listener;

import org.testng.ITestListener;
import java.util.Random;
import org.testng.ITestResult;
import com.dienmayxanh.ActionForListener.TakeSnapShot;

public class ListenerTest implements ITestListener {
	
	@Override
	public void onTestFailure(ITestResult arg0) {
		String file = System.getProperty("user.dir")+"\\screenshots\\"+"screenshot"+(new Random().nextInt())+".png";
		
		try {
			TakeSnapShot.takeSnapShot(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}