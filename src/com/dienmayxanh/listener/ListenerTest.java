package com.dienmayxanh.listener;

import org.testng.ITestListener;
import java.util.Random;
import org.testng.ITestResult;

import com.dienmayxanh.service.TakeSnapShot;
import com.dienmayxanh.abstractclass.*;

public class ListenerTest extends AbstractPath implements ITestListener {

	@Override
	public void onTestFailure(ITestResult arg0) {
//		String file = System.getProperty("user.dir")+"\\screenshots\\"+"screenshot-"+(arg0.getName())+".png";
		String fileName = "screenshot-" + (arg0.getName()) + ".png";
		String file = getScreenShotsFolderPath() + fileName;
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

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}
}