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

//@Listeners(ListenerTest.class)			

public class LogIn extends abstractLogin {
	public String baseUrl = "https://www.dienmayxanh.com";
//	public WebDriver driver;

	/**
	 * Test Requirement: TR-DMX-Login-02. TestCaseID: TC-DMX-Login-02
	 * @throws Exception 
	 */
	@Test(priority=1)
	public void testPhoneNumbersWithLetters() throws Exception {
		// Nhấn chọn "Lịch sử mua hàng" trên thanh Header
		int rowData = ExcelUtils.getRowContains("TC-DMX-Login-02", 2);
		WebElement weblogin = waitForElementClickable(By.xpath("//a[@href='/lich-su-mua-hang']"));
		weblogin.click();
		// Nhập chữ vào trường 'Nhập số điện thoại mua hàng'.
		WebElement phone1 = waitForElementClickable(By.name("txtPhoneNumber"));
		phone1.sendKeys(ExcelUtils.getCellData(11,7));
		// Nhấn Enter hoặc chọn 'Tiếp tục'
		WebElement login = waitForElementClickable(By.xpath("//*[@id=\"frmGetVerifyCode\"]/button"));
		login.click();

		String expectedError = ExcelUtils.getCellData(rowData ,9);
		String error = waitForElementClickable(By.xpath("//*[@name='txtPhoneNumber']//following::label[1]")).getText();
		Assert.assertEquals(error, expectedError);

		driver.close();

	}

	/**
	 * Test Requirement: TR-DMX-Login-02. TestCaseID: TC-DMX-Login-03
	 * @throws Exception 
	 */
	@Test(priority=2)
	public void testPhoneNumberWithSpecialCharacters() throws Exception {
		// Nhấn chọn "Lịch sử mua hàng" trên thanh Header
		int rowData = ExcelUtils.getRowContains("TC-DMX-Login-03", 2);
		WebElement weblogin = waitForElementClickable(By.xpath("//a[@href='/lich-su-mua-hang']"));
		weblogin.click();
		// Nhập kí tự đặc biệt vào trường 'Nhập số điện thoại mua hàng'.
		WebElement phone2 = waitForElementClickable(By.name("txtPhoneNumber"));
		phone2.sendKeys(ExcelUtils.getCellData(15,7));
		// Nhấn Enter hoặc chọn 'Tiếp tục'
		WebElement login = waitForElementClickable(By.xpath("//*[@id=\"frmGetVerifyCode\"]/button"));
		login.click();

		String expectedError = ExcelUtils.getCellData(rowData ,9);
		String error = waitForElementClickable(By.xpath("//*[@name='txtPhoneNumber']//following::label[1]")).getText();
		ITestResult result = Reporter.getCurrentTestResult();
		result.setAttribute("error", error);
		Assert.assertEquals(error, expectedError);

	}

	/**
	 * Test Requirement: TR-DMX-Login-02. TestCaseID: TC-DMX-Login-04
	 * @throws Exception 
	 */
	@Test(priority=3)
	public void testWrongPhoneNumber() throws Exception {
		// Nhấn chọn "Lịch sử mua hàng" trên thanh Header
		int rowData = ExcelUtils.getRowContains("TC-DMX-Login-04", 2);
		WebElement weblogin = waitForElementClickable(By.xpath("//a[@href='/lich-su-mua-hang']"));
		weblogin.click();
		// Nhập số điện thoại không có thật vào trường 'Nhập số điện thoại mua hàng'.
		WebElement phone3 = waitForElementClickable(By.name("txtPhoneNumber"));
		phone3.sendKeys(ExcelUtils.getCellData(19,7));
		// Nhấn Enter hoặc chọn 'Tiếp tục'
		WebElement login = waitForElementClickable(By.xpath("//*[@id=\"frmGetVerifyCode\"]/button"));
		login.click();

		String expectedError = ExcelUtils.getCellData(rowData ,9);
		String error = waitForElementClickable(By.xpath("//*[@name='txtPhoneNumber']//following::label[1]")).getText();
		Assert.assertEquals(error, expectedError);

	}

	/**
	 * Test Requirement: TR-DMX-Login-03. TestCaseID: TC-DMX-Login-05
	 * @throws Exception 
	 */
	@Test(priority=4)
	public void testDoNotEnterPhoneNumber() throws Exception {
		// Nhấn chọn "Lịch sử mua hàng" trên thanh Header
		int rowData = ExcelUtils.getRowContains("TC-DMX-Login-05", 2);
		WebElement weblogin = waitForElementClickable(By.xpath("//a[@href='/lich-su-mua-hang']"));
		weblogin.click();
		// Bỏ trống trường 'Nhập số điện thoại mua hàng'.
		WebElement phone4 = waitForElementClickable(By.name("txtPhoneNumber"));
		phone4.sendKeys(ExcelUtils.getCellData(23,7));
		// Nhấn Enter hoặc chọn 'Tiếp tục'
		WebElement login = waitForElementClickable(By.xpath("//*[@id=\"frmGetVerifyCode\"]/button"));
		login.click();

		String expectedError = ExcelUtils.getCellData(rowData ,9);
		String error = waitForElementClickable(By.xpath("//*[@name='txtPhoneNumber']//following::label[1]")).getText();
		Assert.assertEquals(error, expectedError);

		driver.close();
	}

	/**
	 * Test Requirement: TR-DMX-Login-03. TestCaseID: TC-DMX-Login-06
	 * @throws Exception 
	 */
	@Test(priority=5)
	public void testEnterSpace() throws Exception {
		// Nhấn chọn "Lịch sử mua hàng" trên thanh Header
		int rowData = ExcelUtils.getRowContains("TC-DMX-Login-06", 2);
		WebElement weblogin = waitForElementClickable(By.xpath("//a[@href='/lich-su-mua-hang']"));
		weblogin.click();
		// Nhập khoảng trắng vào trường 'Nhập số điện thoại mua hàng'.
		WebElement phone5 = waitForElementClickable(By.name("txtPhoneNumber"));
		phone5.sendKeys(ExcelUtils.getCellData(27,7));
		// Nhấn Enter hoặc chọn 'Tiếp tục'
		WebElement login = waitForElementClickable(By.xpath("//*[@id=\"frmGetVerifyCode\"]/button"));
		login.click();

		String expectedError = ExcelUtils.getCellData(rowData ,9);
		String error = waitForElementClickable(By.xpath("//*[@name='txtPhoneNumber']//following::label[1]")).getText();
		Assert.assertEquals(error, expectedError);

		driver.close();
	}

	/**
	 * Test Requirement: TR-DMX-Login-04. TestCaseID: TC-DMX-Login-07
	 * @throws Exception 
	 */
	@Test(priority=6)
	public void testWrongOTP() throws Exception {
		// Nhấn chọn "Lịch sử mua hàng" trên thanh Header
		int rowData = ExcelUtils.getRowContains("TC-DMX-Login-07", 2);
		WebElement weblogin = waitForElementClickable(By.xpath("//a[@href='/lich-su-mua-hang']"));
		weblogin.click();
		// Nhập số điện thoại vào trường 'Nhập số điện thoại mua hàng'.
		WebElement phone = waitForElementClickable(By.name("txtPhoneNumber"));
		phone.sendKeys(ExcelUtils.getCellData(31,7));
		// Nhấn Enter hoặc chọn 'Tiếp tục' và đợi mã xác nhận được gửi về SMS.
		WebElement login = waitForElementClickable(By.xpath("//*[@id=\"frmGetVerifyCode\"]/button"));
		login.click();
//		Thread.sleep(5000);
		// Nhập sai mã xác nhận vào trường 'Nhập mã xác nhận gồm 4 số'
		WebElement otp = waitForElementClickable(By.xpath("//input[@name='txtOTP']"));
		otp.sendKeys(ExcelUtils.getCellData(33,7));
//		Thread.sleep(5000);
		// Nhấn chọn "Tiếp tục" hoặc phím Enter.
		WebElement code = waitForElementClickable(By.xpath("//*[@id=\"frmSubmitVerifyCode\"]/button"));
		code.click();

		String expectedError = ExcelUtils.getCellData(rowData ,9);
		String error = waitForElementClickable(By.xpath("//*[@name='txtOTP']//following::label[1]")).getText();
		Assert.assertEquals(error, expectedError);

		driver.close();
	}

	/**
	 * Test Requirement: TR-DMX-Login-05. TestCaseID: TC-DMX-Login-08
	 * @throws Exception 
	 */
	@Test(priority=7)
	public void testDoNotEnterEnoughOTP() throws Exception {
		// Nhấn chọn "Lịch sử mua hàng" trên thanh Header
		int rowData = ExcelUtils.getRowContains("TC-DMX-Login-08", 2);
		WebElement weblogin = waitForElementClickable(By.xpath("//a[@href='/lich-su-mua-hang']"));
		weblogin.click();
		// Nhập số điện thoại vào trường 'Nhập số điện thoại mua hàng'.
		WebElement phone = waitForElementClickable(By.name("txtPhoneNumber"));
		phone.sendKeys(ExcelUtils.getCellData(36,7));
		// Nhấn Enter hoặc chọn 'Tiếp tục' và đợi mã xác nhận được gửi về SMS.
		WebElement login = waitForElementClickable(By.xpath("//*[@id=\"frmGetVerifyCode\"]/button"));
		login.click();
//		Thread.sleep(5000);
		// Nhập không đủ 4 chữ số mã xác nhận vào trường 'Nhập mã xác nhận gồm 4 số'
		WebElement otp1 = waitForElementClickable(By.xpath("//input[@name='txtOTP']"));
		otp1.sendKeys(ExcelUtils.getCellData(38,7));
//		Thread.sleep(5000);
		// Nhấn chọn "Tiếp tục" hoặc phím Enter.
		WebElement code = waitForElementClickable(By.xpath("//*[@id=\"frmSubmitVerifyCode\"]/button"));
		code.click();

		String expectedError = ExcelUtils.getCellData(rowData ,9);
		String error = waitForElementClickable(By.xpath("//*[@name='txtOTP']//following::label[1]")).getText();
		Assert.assertEquals(error, expectedError);

		driver.close();
	}

	/**
	 * Test Requirement: TR-DMX-Login-06. TestCaseID: TC-DMX-Login-09
	 * @throws Exception 
	 */
	@Test(priority=8)
	public void testOTPHasLetters() throws Exception {
		// Nhấn chọn "Lịch sử mua hàng" trên thanh Header
		int rowData = ExcelUtils.getRowContains("TC-DMX-Login-09", 2);
		WebElement weblogin = waitForElementClickable(By.xpath("//a[@href='/lich-su-mua-hang']"));
		weblogin.click();
		// Nhập số điện thoại vào trường 'Nhập số điện thoại mua hàng'.
		WebElement phone = waitForElementClickable(By.name("txtPhoneNumber"));
		phone.sendKeys(ExcelUtils.getCellData(41,7));
		// Nhấn Enter hoặc chọn 'Tiếp tục' và đợi mã xác nhận được gửi về SMS.
		WebElement login = waitForElementClickable(By.xpath("//*[@id=\"frmGetVerifyCode\"]/button"));
		login.click();
//		Thread.sleep(5000);
		// Nhập chữ vào trường 'Nhập mã xác nhận gồm 4 số'
		WebElement otp2 = waitForElementClickable(By.xpath("//input[@name='txtOTP']"));
		otp2.sendKeys(ExcelUtils.getCellData(43,7));
//		Thread.sleep(5000);
		// Nhấn chọn "Tiếp tục" hoặc phím Enter.
		WebElement code = waitForElementClickable(By.xpath("//*[@id=\"frmSubmitVerifyCode\"]/button"));
		code.click();

		String expectedError = ExcelUtils.getCellData(rowData ,9);
		String error = waitForElementClickable(By.xpath("//*[@name='txtOTP']//following::label[1]")).getText();
		Assert.assertEquals(error, expectedError);

		driver.close();
	}

	/**
	 * Test Requirement: TR-DMX-Login-06. TestCaseID: TC-DMX-Login-010
	 * @throws Exception 
	 */
	@Test(priority=9)
	public void testOTPHasSpecialCharacters() throws Exception {
		// Nhấn chọn "Lịch sử mua hàng" trên thanh Header
		int rowData = ExcelUtils.getRowContains("TC-DMX-Login-010", 2);
		WebElement weblogin = waitForElementClickable(By.xpath("//a[@href='/lich-su-mua-hang']"));
		weblogin.click();
		// Nhập số điện thoại vào trường 'Nhập số điện thoại mua hàng'.
		WebElement phone = waitForElementClickable(By.name("txtPhoneNumber"));
		phone.sendKeys(ExcelUtils.getCellData(46,7));
		// Nhấn Enter hoặc chọn 'Tiếp tục' và đợi mã xác nhận được gửi về SMS.
		WebElement login = waitForElementClickable(By.xpath("//*[@id=\"frmGetVerifyCode\"]/button"));
		login.click();
//		Thread.sleep(7000);
		// Nhập kí tự đặc biệt vào trường 'Nhập mã xác nhận gồm 4 số'
		WebElement otp3 = waitForElementClickable(By.xpath("//input[@name='txtOTP']"));
		otp3.sendKeys(ExcelUtils.getCellData(48, 7));
//		Thread.sleep(5000);
		// Nhấn chọn "Tiếp tục" hoặc phím Enter.
		WebElement code = waitForElementClickable(By.xpath("//*[@id=\"frmSubmitVerifyCode\"]/button"));
		code.click();

		String expectedError = ExcelUtils.getCellData(rowData ,9);
		String error = waitForElementClickable(By.xpath("//*[@name='txtOTP']//following::label[1]")).getText();
		Assert.assertEquals(error, expectedError);

		driver.close();
	}

	/**
	 * Test Requirement: TR-DMX-Login-07. TestCaseID: TC-DMX-Login-011
	 * @throws Exception 
	 */
	@Test(priority=10)
	public void testOTPEntersSpace() throws Exception {
		// Nhấn chọn "Lịch sử mua hàng" trên thanh Header
		int rowData = ExcelUtils.getRowContains("TC-DMX-Login-011", 2);
		WebElement weblogin = waitForElementClickable(By.xpath("//a[@href='/lich-su-mua-hang']"));
		weblogin.click();
		// Nhập số điện thoại vào trường 'Nhập số điện thoại mua hàng'.
		WebElement phone = waitForElementClickable(By.name("txtPhoneNumber"));
		phone.sendKeys(ExcelUtils.getCellData(51,7));
		// Nhấn Enter hoặc chọn 'Tiếp tục' và đợi mã xác nhận được gửi về SMS.
		WebElement login = waitForElementClickable(By.xpath("//*[@id=\"frmGetVerifyCode\"]/button"));
		login.click();
//		Thread.sleep(5000);
		// Nhập khoảng trắng vào trường 'Nhập mã xác nhận gồm 4 số'
		WebElement otp4 = waitForElementClickable(By.xpath("//input[@name='txtOTP']"));
		otp4.sendKeys(ExcelUtils.getCellData(53,7));
//		Thread.sleep(5000);
		// Nhấn chọn "Tiếp tục" hoặc phím Enter.
		WebElement code = waitForElementClickable(By.xpath("//*[@id=\"frmSubmitVerifyCode\"]/button"));
		code.click();

		String expectedError = ExcelUtils.getCellData(rowData ,9);
		String error = waitForElementClickable(By.xpath("//*[@name='txtOTP']//following::label[1]")).getText();
		Assert.assertEquals(error, expectedError);

		driver.close();
	}

	/**
	 * Test Requirement: TR-DMX-Login-07. TestCaseID: TC-DMX-Login-012
	 * @throws Exception 
	 */
	@Test(priority=11)
	public void testDoNotEnterOTP() throws Exception {
		// Nhấn chọn "Lịch sử mua hàng" trên thanh Header
		int rowData = ExcelUtils.getRowContains("TC-DMX-Login-012", 2);
		WebElement weblogin = waitForElementClickable(By.xpath("//a[@href='/lich-su-mua-hang']"));
		weblogin.click();
		// Nhập số điện thoại vào trường 'Nhập số điện thoại mua hàng'.
		WebElement phone = waitForElementClickable(By.name("txtPhoneNumber"));
		phone.sendKeys(ExcelUtils.getCellData(56,7));
		// Nhấn Enter hoặc chọn 'Tiếp tục' và đợi mã xác nhận được gửi về SMS.
		WebElement login = waitForElementClickable(By.xpath("//*[@id=\"frmGetVerifyCode\"]/button"));
		login.click();
//		Thread.sleep(5000);
		// Bỏ trống trường 'Nhập mã xác nhận gồm 4 số'
		WebElement otp5 = waitForElementClickable(By.xpath("//input[@name='txtOTP']"));
		otp5.sendKeys(ExcelUtils.getCellData(58,7));
//		Thread.sleep(5000);
		// Nhấn chọn "Tiếp tục" hoặc phím Enter.
		WebElement code = waitForElementClickable(By.xpath("//*[@id=\"frmSubmitVerifyCode\"]/button"));
		code.click();

		String expectedError = ExcelUtils.getCellData(rowData ,9);
		String error = waitForElementClickable(By.xpath("//*[@name='txtOTP']//following::label[1]")).getText();
		Assert.assertEquals(error, expectedError);

		driver.close();
	}

	/**
	 * Test Requirement: TR-DMX-Login-08. TestCaseID: TC-DMX-Login-013
	 * @throws Exception 
	 */
	@Test(priority=12)
	public void testNoticeOfResendingOTP() throws Exception {
		// Nhấn chọn "Lịch sử mua hàng" trên thanh Header
		int rowData = ExcelUtils.getRowContains("TC-DMX-Login-013", 2);
		WebElement weblogin = waitForElementClickable(By.xpath("//a[@href='/lich-su-mua-hang']"));
		weblogin.click();
		// Nhập số điện thoại vào trường 'Nhập số điện thoại mua hàng'.
		WebElement phone = waitForElementClickable(By.name("txtPhoneNumber"));
		phone.sendKeys(ExcelUtils.getCellData(62,1));
		// Nhấn Enter hoặc chọn 'Tiếp tục' và đợi mã xác nhận được gửi về SMS.
		WebElement login = waitForElementClickable(By.xpath("//*[@id=\"frmGetVerifyCode\"]/button"));
		login.click();
		Thread.sleep(35000);
		// Nhấn chọn "Tôi không nhận được mã, vui lòng gửi lại"
		WebElement thongbao = waitForElementClickable(By.xpath("//a[@class='resend-sms']"));
		thongbao.click();

		driver.close();
	}

	/**
	 * Test Requirement: TR-DMX-Login-10. TestCaseID: TC-DMX-Login-015
	 * @throws Exception 
	 */
	@Test(priority=13)
	public void testTitle() throws Exception {
		// Nhấn chọn "Lịch sử mua hàng" trên thanh Header
		int rowData = ExcelUtils.getRowContains("TC-DMX-Login-015", 2);
		WebElement weblogin = waitForElementClickable(By.xpath("//a[@href='/lich-su-mua-hang']"));
		weblogin.click();

		String expectedTitle = ExcelUtils.getCellData(rowData ,9);
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);

		driver.close();

	}

	private WebElement waitForElementClickable(By locator) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
}
