package com.dienmayxanh.test;

import org.testng.annotations.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.dienmayxanh.service.*;
import com.dienmayxanh.abstractclass.*;
import com.dienmayxanh.Enum.*;
import com.dienmayxanh.page.CommentPage;
import com.dienmayxanh.page.LogInPage;

//@Listeners(ListenerTest.class)			

public class LogIn extends AbstractAnnotation {
	public static final int COL_TESTTYPE = 0;
	public static final int COL_TESTNAME = 1;
	public static final int COL_CASE = 2;
	public static final int COL_EXPRESULT = 3;
	public static final int COL_RESULT = 4;
	public static final int COL_PHONENUMBER = 5;
	public static final int COL_OTP = 6;

	public static final int iTestBeginRow = 3;
	public static int iTestCaseRow, rowData;
	public static String expected, actual;
	private LogInPage objLogIn = new LogInPage();

	@Test(priority = 1)
	public void testLoginWithWrongFormatPhoneNumber() throws Exception {
		iTestCaseRow = ExcelUtils.getRowUsed();
		for (int i = iTestBeginRow; i < iTestCaseRow; i++) {
			if (ExcelUtils.getCellData(i, COL_TESTNAME).equals("LoginWithWrongFormatPhoneNumber")
					&& ExcelUtils.getCellData(i, COL_CASE).equals(Case.UNSUCCESSFULLY.toString())) {
				objLogIn.clickWeb();

				// Nhập chữ vào trường 'Nhập số điện thoại mua hàng'.
				objLogIn.setPhoneNumber(ExcelUtils.getCellData(i, COL_PHONENUMBER));

				// Nhấn Enter hoặc chọn 'Tiếp tục'
				objLogIn.clickLoginPhone();

				expected = ExcelUtils.getCellData(i, COL_EXPRESULT);
				actual = objLogIn.getMessagePhone();
				Assert.assertEquals(actual, expected);

				if (actual.equals(expected)) {
					ExcelUtils.setCellData(i, COL_RESULT, Result.PASSED.toString());
				} else {
					ExcelUtils.setCellData(i, COL_RESULT, Result.FAILED.toString());
				}
			}
		}
	}

	@Test(priority = 2)
	public void testLoginWithWrongOTP() throws Exception {
		iTestCaseRow = ExcelUtils.getRowUsed();
		for (int i = iTestBeginRow; i < iTestCaseRow; i++) {
			if (ExcelUtils.getCellData(i, COL_TESTNAME).equals("LoginWithWrongOTP")
					&& ExcelUtils.getCellData(i, COL_CASE).equals(Case.UNSUCCESSFULLY.toString())) {
				objLogIn.clickWeb();
				// Nhập chữ vào trường 'Nhập số điện thoại mua hàng'.
				objLogIn.setPhoneNumber(ExcelUtils.getCellData(i, COL_PHONENUMBER));
				// Nhấn Enter hoặc chọn 'Tiếp tục'
				objLogIn.clickLoginPhone();

				objLogIn.setOTP(ExcelUtils.getCellData(i, COL_OTP));

				objLogIn.clickLoginOTP();

				expected = ExcelUtils.getCellData(i, COL_EXPRESULT);
				actual = objLogIn.getMessageOTP();
				Assert.assertEquals(actual, expected);

				if (actual.equals(expected)) {
					ExcelUtils.setCellData(i, COL_RESULT, Result.PASSED.toString());
				} else {
					ExcelUtils.setCellData(i, COL_RESULT, Result.FAILED.toString());
				}
			}
		}
	}

	@Test(priority = 3)
	public void testNoticeOfResendingOTP() throws Exception {
		iTestCaseRow = ExcelUtils.getRowUsed();
		for (int i = iTestBeginRow; i < iTestCaseRow; i++) {
			if (ExcelUtils.getCellData(i, COL_TESTNAME).equals("NoticeOfResendingOTP")
					&& ExcelUtils.getCellData(i, COL_CASE).equals(Case.UNSUCCESSFULLY.toString())) {
				objLogIn.clickWeb();
				
				// Nhập chữ vào trường 'Nhập số điện thoại mua hàng'.
				objLogIn.setPhoneNumber(ExcelUtils.getCellData(i, COL_PHONENUMBER));
				
				// Nhấn Enter hoặc chọn 'Tiếp tục'
				objLogIn.clickLoginPhone();
				Thread.sleep(35000);

				// Nhấn chọn "Tôi không nhận được mã, vui lòng gửi lại"
				//objLogIn.clickResendOTP();

				expected = ExcelUtils.getCellData(i, COL_EXPRESULT);
				actual = objLogIn.getMessageResend();
				//Thread.sleep(1000);
				Assert.assertEquals(actual, expected);

				if (actual.equals(expected)) {
					ExcelUtils.setCellData(i, COL_RESULT, Result.PASSED.toString());
				} else {
					ExcelUtils.setCellData(i, COL_RESULT, Result.FAILED.toString());
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
					&& ExcelUtils.getCellData(i, COL_CASE).equals(Case.SUCCESSFULLY.toString())) {
				objLogIn.clickWeb();
				
				expected = ExcelUtils.getCellData(i, COL_EXPRESULT);
				actual = driver.getTitle();
				Assert.assertEquals(actual, expected);

				if(actual.equals(expected)) {
					ExcelUtils.setCellData(i, COL_RESULT, Result.PASSED.toString());
				}
				else {
					ExcelUtils.setCellData(i, COL_RESULT, Result.FAILED.toString());
				}
			}
		}
	}
}