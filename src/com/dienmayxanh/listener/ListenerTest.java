package com.dienmayxanh.listener;

import org.testng.ITestListener;

import java.io.IOException;
import java.util.Random;

import org.testng.ITestContext;
import org.testng.ITestResult;

import com.dienmayxanh.ActionForListener.TakeSnapShot;

public class ListenerTest implements ITestListener {
	
	@Override
	public void onTestFailure(ITestResult arg0) {
		String file = System.getProperty("user.dir")+"\\"+"screenshot"+(new Random().nextInt())+".png";
		
		try {
			TakeSnapShot.takeSnapShot(TakeSnapShot.getDriver(), file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}