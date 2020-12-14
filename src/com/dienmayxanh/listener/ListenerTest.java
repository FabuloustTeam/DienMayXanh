package com.dienmayxanh.listener;

import org.testng.ITestListener;

import org.testng.ITestResult;

import com.dienmayxanh.service.ExcelUtils;
import com.dienmayxanh.service.TakeSnapShot;
import com.dienmayxanh.abstractclass.*;

public class ListenerTest extends AbstractPath implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {
//		String file = System.getProperty("user.dir")+"\\screenshots\\"+"screenshot-"+(arg0.getName())+".png";
		String fileName = result.getAttribute("id").toString() + "-" + (result.getName()) + ".png";
		String file = getScreenShotsFolderPath() + fileName;
		try {
			TakeSnapShot.takeSnapShot(file);
			
			ExcelUtils.setExcelFile(getReportFilePath(), "Detailed status");
			int row = ExcelUtils.getRowContains(result.getAttribute("id").toString(), 5);
			ExcelUtils.setCellData(row, 8, "Failed");
			ExcelUtils.setCellData(row, 6, result.getAttribute("actualResult").toString());
			ExcelUtils.setCellData(row, 9, "DienMayXanh\\screenshots\\"+fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		try {
			ExcelUtils.setExcelFile(getReportFilePath(), "Detailed status");
			int row = ExcelUtils.getRowContains(result.getAttribute("id").toString(), 5);
			ExcelUtils.setCellData(row, 8, "Passed");
			ExcelUtils.setCellData(row, 6, result.getAttribute("actualResult").toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		try {
			ExcelUtils.setExcelFile(getReportFilePath(), "Detailed status");
			int row = ExcelUtils.getRowContains(result.getAttribute("id").toString(), 5);
			ExcelUtils.setCellData(row, 8, "Skipped");
			ExcelUtils.setCellData(row, 6, result.getAttribute("actualResult").toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}