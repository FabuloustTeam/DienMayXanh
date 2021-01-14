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
//		String testCaseName = result.getAttribute("id").toString();
//		String file = System.getProperty("user.dir") + "\\screenshots\\" + testCaseName + ".png";
		try {
//			TakeSnapShot.takeScreenShot(file);
//			String path = System.getProperty("user.dir")+"\\DienmayXANH-TestData.xlsx";
//			ExcelUtils.setExcelFile(path, "Contact");
//			int row = ExcelUtils.getRowContains(result.getName(), 1);
//			ExcelUtils.setCellData(row, 4, "FAILED");
//			ExcelUtils.closeandsaveFile(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	@Override
//	public void onTestSuccess(ITestResult result) {
//		// TODO Auto-generated method stub
//		ITestListener.super.onTestSuccess(result);
//		try {
//			String path = System.getProperty("user.dir")+"\\DienmayXANH-TestData.xlsx";
//			ExcelUtils.setExcelFile(path, "Contact");
//			int row = ExcelUtils.getRowContains(result.getName(), 1);
//			ExcelUtils.setCellData(row, 4, "PASSED");
//			ExcelUtils.closeandsaveFile(path);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block 
//			e.printStackTrace();
//		}
//	}

//	@Override
//	public void onTestSkipped(ITestResult result) {
//		// TODO Auto-generated method stub
//		ITestListener.super.onTestSkipped(result);
//		try {
//			String path = System.getProperty("user.dir")+"\\DienmayXANH-TestData.xlsx";
//			ExcelUtils.setExcelFile(path, "Contact");
//			int row = ExcelUtils.getRowContains(result.getName(), 1);
//			ExcelUtils.setCellData(row, 4, "SKIPPED");
//			ExcelUtils.closeandsaveFile(path);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block 
//			e.printStackTrace();
//		}
//	}
}
