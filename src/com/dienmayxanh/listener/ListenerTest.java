package com.dienmayxanh.listener;

import org.testng.ITestListener;

import org.testng.ITestResult;

import com.dienmayxanh.service.ExcelUtils;
import com.dienmayxanh.service.TakeSnapShot;
import com.dienmayxanh.abstractclass.*;

public class ListenerTest extends AbstractPath implements ITestListener {
	
	@Override
	public void onTestFailure(ITestResult result) {
//		String fileName = result.getAttribute("testname").toString() + "-" + (result.getName()) + ".png";
//		String file = pathFolderImage + fileName;
//		try {
//			TakeSnapShot.takeSnapShot(file);
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}
}