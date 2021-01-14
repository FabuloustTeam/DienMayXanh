package testcase;

import org.testng.annotations.Listeners;

import org.testng.annotations.Test;

import AbstractClass.abstractLogin;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import BaseClass.ExcelUtils;
import POM.LoginPage;

//@Listeners(ListenerTest.class)			

public class LogIn extends abstractLogin {
	public static final int COL_TESTTYPE = 0;
	public static final int COL_TESTNAME = 1;
	public static final int COL_CASE = 2;
	public static final int COL_EXPRESULT = 3;
	public static final int COL_RESULT = 4;
	public static final int COL_PHONENUMBER = 5;
	public static final int COL_OTP = 6;

	public static final int iTestBeginRow = 3;
	public static int iTestCaseRow, rowData;
	
	LoginPage objLogin = new LoginPage();
	// public String baseUrl = "https://www.dienmayxanh.com";
//	public WebDriver driver;

	@Test(priority = 1)
	public void testLoginWithWrongFormatPhoneNumber() throws Exception {
		iTestCaseRow = ExcelUtils.getRowUsed();
		for(int i = iTestBeginRow; i<iTestCaseRow;i++) {
			if(ExcelUtils.getCellData(i, COL_TESTNAME).equals("LoginWithWrongFormatPhoneNumber")
					&& ExcelUtils.getCellData(i, COL_CASE).equals("UNSUCCESSFULLY")) {
				objLogin.clickWeb();
				// Nhập chữ vào trường 'Nhập số điện thoại mua hàng'.
				//WebElement phone1 = waitForElementClickable(By.name("txtPhoneNumber"));
				objLogin.setPhoneNumber(ExcelUtils.getCellData(i, COL_PHONENUMBER));
				// Nhấn Enter hoặc chọn 'Tiếp tục'
				objLogin.clickLoginPhone();
				
				String expected = ExcelUtils.getCellData(i, COL_EXPRESULT);
				String actual = objLogin.getMessagePhone();
				Assert.assertEquals(actual, expected);

				if(actual.equals(expected)) {
					ExcelUtils.setCellData(i, COL_RESULT, "PASSED");
				}
				else {
					ExcelUtils.setCellData(i, COL_RESULT, "FAILED");
				}
			}
		}
	}
	@Test(priority = 2)
	public void testLoginWithWrongOTP() throws Exception {
		iTestCaseRow = ExcelUtils.getRowUsed();
		for(int i = iTestBeginRow; i<iTestCaseRow;i++) {
			if(ExcelUtils.getCellData(i, COL_TESTNAME).equals("LoginWithWrongOTP")
					&& ExcelUtils.getCellData(i, COL_CASE).equals("UNSUCCESSFULLY")) {
				objLogin.clickWeb();
				// Nhập chữ vào trường 'Nhập số điện thoại mua hàng'.
				objLogin.setPhoneNumber(ExcelUtils.getCellData(i, COL_PHONENUMBER));
				// Nhấn Enter hoặc chọn 'Tiếp tục'
				objLogin.clickLoginPhone();
		
				objLogin.setOTP(ExcelUtils.getCellData(i, COL_OTP));
				
				objLogin.clickLoginOTP();;
				
				String expected = ExcelUtils.getCellData(i, COL_EXPRESULT);
				String actual = objLogin.getMessageOTP();
				Assert.assertEquals(actual, expected);

				if(actual.equals(expected)) {
					ExcelUtils.setCellData(i, COL_RESULT, "PASSED");
				}
				else {
					ExcelUtils.setCellData(i, COL_RESULT, "FAILED");
				}
			}
		}
	}


	@Test(priority = 3)
	public void testNoticeOfResendingOTP() throws Exception {
		iTestCaseRow = ExcelUtils.getRowUsed();
		for(int i = iTestBeginRow; i<iTestCaseRow;i++) {
			if(ExcelUtils.getCellData(i, COL_TESTNAME).equals("NoticeOfResendingOTP")
					&& ExcelUtils.getCellData(i, COL_CASE).equals("UNSUCCESSFULLY")) {
				objLogin.clickWeb();
				// Nhập chữ vào trường 'Nhập số điện thoại mua hàng'.
				objLogin.setPhoneNumber(ExcelUtils.getCellData(i, COL_PHONENUMBER));
				// Nhấn Enter hoặc chọn 'Tiếp tục'
				objLogin.clickLoginPhone();
				Thread.sleep(35000);
				
				// Nhấn chọn "Tôi không nhận được mã, vui lòng gửi lại"
				objLogin.clickResendOTP();
				
				String expected = ExcelUtils.getCellData(i, COL_EXPRESULT);
				String actual = objLogin.getMessageResend();
				Assert.assertEquals(actual, expected);

				if(actual.equals(expected)) {
					ExcelUtils.setCellData(i, COL_RESULT, "PASSED");
				}
				else {
					ExcelUtils.setCellData(i, COL_RESULT, "FAILED");
				}
			}
		}
	}
	/**
	 * Test Requirement: TR-DMX-Login-10. TestCaseID: TC-DMX-Login-015
	 * 
	 * @throws Exception
	 */
	@Test(priority = 4)
	public void testTitle() throws Exception {
		iTestCaseRow = ExcelUtils.getRowUsed();
		for(int i = iTestBeginRow; i<=iTestCaseRow;i++) {
			if(ExcelUtils.getCellData(i, COL_TESTNAME).equals("Title")
					&& ExcelUtils.getCellData(i, COL_CASE).equals("SUCCESSFULLY")) {
				objLogin.clickWeb();
				
				String expected = ExcelUtils.getCellData(i, COL_EXPRESULT);
				String actual = driver.getTitle();
				Assert.assertEquals(actual, expected);

				if(actual.equals(expected)) {
					ExcelUtils.setCellData(i, COL_RESULT, "PASSED");
				}
				else {
					ExcelUtils.setCellData(i, COL_RESULT, "FAILED");
				}
			}
		}
}

	
}
