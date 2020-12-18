package Listeners;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import BaseClass.ExcelUtils;
import BaseClass.TakeSnapShot;

public class IListeners implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		String file = System.getProperty("user.dir")+"\\screenshots\\"+result.getName()+".png";
		try {
			TakeSnapShot.takeScreenShot(file);
			String path = System.getProperty("user.dir")+"\\DienmayXANH-FunctionalTestExecution.xlsx";
			ExcelUtils.setExcelFile(path, "Detailed status");
			int row = ExcelUtils.getRowContains(result.getName(), 4);
			ExcelUtils.setCellData(row, 8, "Failed");
			ExcelUtils.closeandsaveFile(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		try {
			String path = System.getProperty("user.dir")+"\\DienmayXANH-FunctionalTestExecution.xlsx";
			ExcelUtils.setExcelFile(path, "Detailed status");
			int row = ExcelUtils.getRowContains(result.getName(), 4);
			ExcelUtils.setCellData(row, 8, "Success");
			ExcelUtils.closeandsaveFile(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
		try {
			String path = System.getProperty("user.dir")+"\\DienmayXANH-FunctionalTestExecution.xlsx";
			ExcelUtils.setExcelFile(path, "Detailed status");
			int row = ExcelUtils.getRowContains(result.getName(), 4);
			ExcelUtils.setCellData(row, 8, "Skipped");
			ExcelUtils.closeandsaveFile(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
	}
}
