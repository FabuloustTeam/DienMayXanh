package com.dienmayxanh.listener;

import org.testng.ITestListener;

import org.testng.ITestResult;

import com.dienmayxanh.service.ExcelUtils;
import com.dienmayxanh.service.TakeSnapShot;
import com.dienmayxanh.abstractclass.*;

public class ListenerTest extends AbstractPath implements ITestListener {

	private final String PASSED = "PASSED";
	private final String FAILED = "FAILED";
	private final String SKIPPED = "SKIPPED";
	private final int COL_RESULT = 4;
	
	@Override
	public void onTestFailure(ITestResult result) {
		String fileName = result.getAttribute("id").toString() + "-" + (result.getName()) + ".png";
		String file = pathFolderImage + fileName;
		try {
			TakeSnapShot.takeSnapShot(file);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		try {

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		try {

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}