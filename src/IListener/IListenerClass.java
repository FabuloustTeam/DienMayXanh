package IListener;

import org.testng.*;
import ActionsForITestListener.*;
import AbstractAnnotation.*;

public class IListenerClass extends AbstractPath implements ITestListener {

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onTestStart(ITestResult result) {
//		try {
//			ExcelUtils.setExcelFile(getReportFilePath(), "Detailed status");
//			int row  = ExcelUtils.getRowContains(result.getAttribute("id").toString(), 6);
//			ExcelUtils.setCellData(row, 5, result.getName());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		try {
			ExcelUtils.setExcelFile(getReportFilePath(), "Detailed status");
			int row  = ExcelUtils.getRowContains(result.getName(), 4);
			ExcelUtils.setCellData(row, 8, "Passed" );
			ExcelUtils.setCellData(row, 6, result.getAttribute("actualResult").toString());
			//System.out.println(result.getAttribute("actualResult").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		try {
			ExcelUtils.setExcelFile(getReportFilePath(), "Detailed status");
			int row  = ExcelUtils.getRowContains(result.getName(), 4);
			ExcelUtils.setCellData(row, 8, "Skipped" );
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		String file =  getScreenShotsFolderPath()+ "screenshot-" + (result.getName()) + ".png";
		try {
			TakeSnapShot.takeSnapShot(file);
			ExcelUtils.setExcelFile(getReportFilePath(), "Detailed status");
			int row = ExcelUtils.getRowContains(result.getName(), 4);			
			
			if(result.getAttribute("actualResult").toString()!=null) {
				ExcelUtils.setCellData(row, 6, result.getAttribute("actualResult").toString());
				System.out.println(result.getAttribute("actualResult").toString());
			}
			
			ExcelUtils.setCellData(row, 8, "Failed");
			String[] subsStringFile = file.split("DienMayXanh");
					
			String srcImage = "DienMayXanh"+subsStringFile[1];
			ExcelUtils.setCellData(row, 9, srcImage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
	}
}
