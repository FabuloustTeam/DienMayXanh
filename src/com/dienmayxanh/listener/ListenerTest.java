package com.dienmayxanh.listener;

import org.testng.ITestListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

public class ListenerTest implements ITestListener {
	
	@Override
	public void onTestFailure(ITestResult arg0) {
		System.out.println("on test failure");
	}
}