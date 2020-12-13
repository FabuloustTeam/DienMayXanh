package com.dienmayxanh.testcase;

import org.testng.annotations.Test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.dienmayxanh.abstractclass.*;

public class LogIn extends AbstractAnnotation {
	public String baseUrl = "https://www.dienmayxanh.com";
	public WebDriver driver;

	/**
	 * Test Requirement: TR-DMX-Login-02. TestCaseID: TC-DMX-Login-02
	 */
	@Test(priority=1)
	public void NhapSDTChuaChuCai() {
		System.out.println("lauching chrome browser");
		driver = new ChromeDriver();
		driver.get(baseUrl);
		// Nhấn ch�?n "Lịch sử mua hàng" trên thanh Header
		WebElement weblogin = waitForElementClickable(By.xpath("//a[@href='/lich-su-mua-hang']"));
		weblogin.click();
		// Nhập chữ vào trư�?ng 'Nhập số điện thoại mua hàng'.
		WebElement phone1 = waitForElementClickable(By.name("txtPhoneNumber"));
		phone1.sendKeys("090dwwwr");
		// Nhấn Enter hoặc ch�?n 'Tiếp tục'
		WebElement login = waitForElementClickable(By.xpath("//*[@id=\"frmGetVerifyCode\"]/button"));
		login.click();

		String expectedError = "Số điện thoại trống/không đúng định dạng";
		String error = waitForElementClickable(By.xpath("//*[@name='txtPhoneNumber']//following::label[1]")).getText();
		Assert.assertEquals(error, expectedError);

		driver.close();

	}

	/**
	 * Test Requirement: TR-DMX-Login-02. TestCaseID: TC-DMX-Login-03
	 */
	@Test(priority=2)
	public void ChuaKiTuDacBiet() {
		System.out.println("lauching chrome browser");
		driver = new ChromeDriver();
		driver.get(baseUrl);
		// Nhấn ch�?n "Lịch sử mua hàng" trên thanh Header
		WebElement weblogin = waitForElementClickable(By.xpath("//a[@href='/lich-su-mua-hang']"));
		weblogin.click();
		// Nhập kí tự đặc biệt vào trư�?ng 'Nhập số điện thoại mua hàng'.
		WebElement phone2 = waitForElementClickable(By.name("txtPhoneNumber"));
		phone2.sendKeys("575(){'.");
		// Nhấn Enter hoặc ch�?n 'Tiếp tục'
		WebElement login = waitForElementClickable(By.xpath("//*[@id=\"frmGetVerifyCode\"]/button"));
		login.click();

		String expectedError = "Số điện thoại trống/không đúng định dạng";
		String error = waitForElementClickable(By.xpath("//*[@name='txtPhoneNumber']//following::label[1]")).getText();
		Assert.assertEquals(error, expectedError);

		driver.close();
	}

	/**
	 * Test Requirement: TR-DMX-Login-02. TestCaseID: TC-DMX-Login-04
	 */
	@Test(priority=3)
	public void SaiSDT() {
		System.out.println("lauching chrome browser");
		driver = new ChromeDriver();
		driver.get(baseUrl);
		// Nhấn ch�?n "Lịch sử mua hàng" trên thanh Header
		WebElement weblogin = waitForElementClickable(By.xpath("//a[@href='/lich-su-mua-hang']"));
		weblogin.click();
		// Nhập số điện thoại không có thật vào trư�?ng 'Nhập số điện thoại mua hàng'.
		WebElement phone3 = waitForElementClickable(By.name("txtPhoneNumber"));
		phone3.sendKeys("5412368521");
		// Nhấn Enter hoặc ch�?n 'Tiếp tục'
		WebElement login = waitForElementClickable(By.xpath("//*[@id=\"frmGetVerifyCode\"]/button"));
		login.click();

		String expectedError = "Số điện thoại trống/không đúng định dạng";
		String error = waitForElementClickable(By.xpath("//*[@name='txtPhoneNumber']//following::label[1]")).getText();
		Assert.assertEquals(error, expectedError);

		driver.close();
	}

	/**
	 * Test Requirement: TR-DMX-Login-03. TestCaseID: TC-DMX-Login-05
	 */
	@Test(priority=4)
	public void KhongNhapSDT() {
		System.out.println("lauching chrome browser");
		driver = new ChromeDriver();
		driver.get(baseUrl);
		// Nhấn ch�?n "Lịch sử mua hàng" trên thanh Header
		WebElement weblogin = waitForElementClickable(By.xpath("//a[@href='/lich-su-mua-hang']"));
		weblogin.click();
		// B�? trống trư�?ng 'Nhập số điện thoại mua hàng'.
		WebElement phone4 = waitForElementClickable(By.name("txtPhoneNumber"));
		phone4.sendKeys("");
		// Nhấn Enter hoặc ch�?n 'Tiếp tục'
		WebElement login = waitForElementClickable(By.xpath("//*[@id=\"frmGetVerifyCode\"]/button"));
		login.click();

		String expectedError = "Số điện thoại trống/không đúng định dạng";
		String error = waitForElementClickable(By.xpath("//*[@name='txtPhoneNumber']//following::label[1]")).getText();
		Assert.assertEquals(error, expectedError);

		driver.close();
	}

	/**
	 * Test Requirement: TR-DMX-Login-03. TestCaseID: TC-DMX-Login-06
	 */
	@Test(priority=5)
	public void NhapKhoangTrang() {
		System.out.println("lauching chrome browser");
		driver = new ChromeDriver();
		driver.get(baseUrl);
		// Nhấn ch�?n "Lịch sử mua hàng" trên thanh Header
		WebElement weblogin = waitForElementClickable(By.xpath("//a[@href='/lich-su-mua-hang']"));
		weblogin.click();
		// Nhập khoảng trắng vào trư�?ng 'Nhập số điện thoại mua hàng'.
		WebElement phone5 = waitForElementClickable(By.name("txtPhoneNumber"));
		phone5.sendKeys("     ");
		// Nhấn Enter hoặc ch�?n 'Tiếp tục'
		WebElement login = waitForElementClickable(By.xpath("//*[@id=\"frmGetVerifyCode\"]/button"));
		login.click();

		String expectedError = "Số điện thoại trống/không đúng định dạng";
		String error = waitForElementClickable(By.xpath("//*[@name='txtPhoneNumber']//following::label[1]")).getText();
		Assert.assertEquals(error, expectedError);

		driver.close();
	}

	/**
	 * Test Requirement: TR-DMX-Login-04. TestCaseID: TC-DMX-Login-07
	 */
	@Test(priority=6)
	public void NhapSaiMaOTP() throws InterruptedException {
		System.out.println("lauching chrome browser");
		driver = new ChromeDriver();
		driver.get(baseUrl);
		// Nhấn ch�?n "Lịch sử mua hàng" trên thanh Header
		WebElement weblogin = waitForElementClickable(By.xpath("//a[@href='/lich-su-mua-hang']"));
		weblogin.click();
		// Nhập số điện thoại vào trư�?ng 'Nhập số điện thoại mua hàng'.
		WebElement phone = waitForElementClickable(By.name("txtPhoneNumber"));
		phone.sendKeys("0342256477");
		// Nhấn Enter hoặc ch�?n 'Tiếp tục' và đợi mã xác nhận được gửi v�? SMS.
		WebElement login = waitForElementClickable(By.xpath("//*[@id=\"frmGetVerifyCode\"]/button"));
		login.click();
//		Thread.sleep(5000);
		// Nhập sai mã xác nhận vào trư�?ng 'Nhập mã xác nhận gồm 4 số'
		WebElement otp = waitForElementClickable(By.xpath("//input[@name='txtOTP']"));
		otp.sendKeys("4512");
//		Thread.sleep(5000);
		// Nhấn ch�?n "Tiếp tục" hoặc phím Enter.
		WebElement code = waitForElementClickable(By.xpath("//*[@id=\"frmSubmitVerifyCode\"]/button"));
		code.click();

		String expectedError = "* Mã xác nhận không đúng, vui lòng thử lại.";
		String error = waitForElementClickable(By.xpath("//*[@name='txtOTP']//following::label[1]")).getText();
		Assert.assertEquals(error, expectedError);

		driver.close();
	}

	/**
	 * Test Requirement: TR-DMX-Login-05. TestCaseID: TC-DMX-Login-08
	 */
	@Test(priority=7)
	public void NhapKhongDuSo() throws InterruptedException {
		System.out.println("lauching chrome browser");
		driver = new ChromeDriver();
		driver.get(baseUrl);
		// Nhấn ch�?n "Lịch sử mua hàng" trên thanh Header
		WebElement weblogin = waitForElementClickable(By.xpath("//a[@href='/lich-su-mua-hang']"));
		weblogin.click();
		// Nhập số điện thoại vào trư�?ng 'Nhập số điện thoại mua hàng'.
		WebElement phone = waitForElementClickable(By.name("txtPhoneNumber"));
		phone.sendKeys("0348775124");
		// Nhấn Enter hoặc ch�?n 'Tiếp tục' và đợi mã xác nhận được gửi v�? SMS.
		WebElement login = waitForElementClickable(By.xpath("//*[@id=\"frmGetVerifyCode\"]/button"));
		login.click();
//		Thread.sleep(5000);
		// Nhập không đủ 4 chữ số mã xác nhận vào trư�?ng 'Nhập mã xác nhận gồm 4 số'
		WebElement otp1 = waitForElementClickable(By.xpath("//input[@name='txtOTP']"));
		otp1.sendKeys("452");
//		Thread.sleep(5000);
		// Nhấn ch�?n "Tiếp tục" hoặc phím Enter.
		WebElement code = waitForElementClickable(By.xpath("//*[@id=\"frmSubmitVerifyCode\"]/button"));
		code.click();

		String expectedError = "* Vui lòng nhập mã OTP.";
		String error = waitForElementClickable(By.xpath("//*[@name='txtOTP']//following::label[1]")).getText();
		Assert.assertEquals(error, expectedError);

		driver.close();
	}

	/**
	 * Test Requirement: TR-DMX-Login-06. TestCaseID: TC-DMX-Login-09
	 */
	@Test(priority=8)
	public void OTPChuaChuCai() throws InterruptedException {
		System.out.println("lauching chrome browser");
		driver = new ChromeDriver();
		driver.get(baseUrl);
		// Nhấn ch�?n "Lịch sử mua hàng" trên thanh Header
		WebElement weblogin = waitForElementClickable(By.xpath("//a[@href='/lich-su-mua-hang']"));
		weblogin.click();
		// Nhập số điện thoại vào trư�?ng 'Nhập số điện thoại mua hàng'.
		WebElement phone = waitForElementClickable(By.name("txtPhoneNumber"));
		phone.sendKeys("0341235789");
		// Nhấn Enter hoặc ch�?n 'Tiếp tục' và đợi mã xác nhận được gửi v�? SMS.
		WebElement login = waitForElementClickable(By.xpath("//*[@id=\"frmGetVerifyCode\"]/button"));
		login.click();
//		Thread.sleep(5000);
		// Nhập chữ vào trư�?ng 'Nhập mã xác nhận gồm 4 số'
		WebElement otp2 = waitForElementClickable(By.xpath("//input[@name='txtOTP']"));
		otp2.sendKeys("45a2");
//		Thread.sleep(5000);
		// Nhấn ch�?n "Tiếp tục" hoặc phím Enter.
		WebElement code = waitForElementClickable(By.xpath("//*[@id=\"frmSubmitVerifyCode\"]/button"));
		code.click();

		String expectedError = "* Mã xác nhận không đúng, vui lòng thử lại.";
		String error = waitForElementClickable(By.xpath("//*[@name='txtOTP']//following::label[1]")).getText();
		Assert.assertEquals(error, expectedError);

		driver.close();
	}

	/**
	 * Test Requirement: TR-DMX-Login-06. TestCaseID: TC-DMX-Login-010
	 */
	@Test(priority=9)
	public void OTPChuaKiTuDacBiet() throws InterruptedException {
		System.out.println("lauching chrome browser");
		driver = new ChromeDriver();
		driver.get(baseUrl);
		// Nhấn ch�?n "Lịch sử mua hàng" trên thanh Header
		WebElement weblogin = waitForElementClickable(By.xpath("//a[@href='/lich-su-mua-hang']"));
		weblogin.click();
		// Nhập số điện thoại vào trư�?ng 'Nhập số điện thoại mua hàng'.
		WebElement phone = waitForElementClickable(By.name("txtPhoneNumber"));
		phone.sendKeys("0386586541");
		// Nhấn Enter hoặc ch�?n 'Tiếp tục' và đợi mã xác nhận được gửi v�? SMS.
		WebElement login = waitForElementClickable(By.xpath("//*[@id=\"frmGetVerifyCode\"]/button"));
		login.click();
//		Thread.sleep(7000);
		// Nhập kí tự đặc biệt vào trư�?ng 'Nhập mã xác nhận gồm 4 số'
		WebElement otp3 = waitForElementClickable(By.xpath("//input[@name='txtOTP']"));
		otp3.sendKeys("4>?2");
//		Thread.sleep(5000);
		// Nhấn ch�?n "Tiếp tục" hoặc phím Enter.
		WebElement code = waitForElementClickable(By.xpath("//*[@id=\"frmSubmitVerifyCode\"]/button"));
		code.click();

		String expectedError = "* Mã xác nhận không đúng, vui lòng thử lại.";
		String error = waitForElementClickable(By.xpath("//*[@name='txtOTP']//following::label[1]")).getText();
		Assert.assertEquals(error, expectedError);

		driver.close();
	}

	/**
	 * Test Requirement: TR-DMX-Login-07. TestCaseID: TC-DMX-Login-011
	 */
	@Test(priority=10)
	public void OTPNhapKhoangTrang() throws InterruptedException {
		System.out.println("lauching chrome browser");
		driver = new ChromeDriver();
		driver.get(baseUrl);
		// Nhấn ch�?n "Lịch sử mua hàng" trên thanh Header
		WebElement weblogin = waitForElementClickable(By.xpath("//a[@href='/lich-su-mua-hang']"));
		weblogin.click();
		// Nhập số điện thoại vào trư�?ng 'Nhập số điện thoại mua hàng'.
		WebElement phone = waitForElementClickable(By.name("txtPhoneNumber"));
		phone.sendKeys("0345154856");
		// Nhấn Enter hoặc ch�?n 'Tiếp tục' và đợi mã xác nhận được gửi v�? SMS.
		WebElement login = waitForElementClickable(By.xpath("//*[@id=\"frmGetVerifyCode\"]/button"));
		login.click();
//		Thread.sleep(5000);
		// Nhập khoảng trắng vào trư�?ng 'Nhập mã xác nhận gồm 4 số'
		WebElement otp4 = waitForElementClickable(By.xpath("//input[@name='txtOTP']"));
		otp4.sendKeys("    ");
//		Thread.sleep(5000);
		// Nhấn ch�?n "Tiếp tục" hoặc phím Enter.
		WebElement code = waitForElementClickable(By.xpath("//*[@id=\"frmSubmitVerifyCode\"]/button"));
		code.click();

		String expectedError = "* Mã xác nhận không đúng, vui lòng thử lại.";
		String error = waitForElementClickable(By.xpath("//*[@name='txtOTP']//following::label[1]")).getText();
		Assert.assertEquals(error, expectedError);

		driver.close();
	}

	/**
	 * Test Requirement: TR-DMX-Login-07. TestCaseID: TC-DMX-Login-012
	 */
	@Test(priority=11)
	public void BoTrongOTP() throws InterruptedException {
		System.out.println("lauching chrome browser");
		driver = new ChromeDriver();
		driver.get(baseUrl);
		// Nhấn ch�?n "Lịch sử mua hàng" trên thanh Header
		WebElement weblogin = waitForElementClickable(By.xpath("//a[@href='/lich-su-mua-hang']"));
		weblogin.click();
		// Nhập số điện thoại vào trư�?ng 'Nhập số điện thoại mua hàng'.
		WebElement phone = waitForElementClickable(By.name("txtPhoneNumber"));
		phone.sendKeys("0389852132");
		// Nhấn Enter hoặc ch�?n 'Tiếp tục' và đợi mã xác nhận được gửi v�? SMS.
		WebElement login = waitForElementClickable(By.xpath("//*[@id=\"frmGetVerifyCode\"]/button"));
		login.click();
//		Thread.sleep(5000);
		// B�? trống trư�?ng 'Nhập mã xác nhận gồm 4 số'
		WebElement otp5 = waitForElementClickable(By.xpath("//input[@name='txtOTP']"));
		otp5.sendKeys("");
//		Thread.sleep(5000);
		// Nhấn ch�?n "Tiếp tục" hoặc phím Enter.
		WebElement code = waitForElementClickable(By.xpath("//*[@id=\"frmSubmitVerifyCode\"]/button"));
		code.click();

		String expectedError = "* Vui lòng nhập mã OTP.";
		String error = waitForElementClickable(By.xpath("//*[@name='txtOTP']//following::label[1]")).getText();
		Assert.assertEquals(error, expectedError);

		driver.close();
	}

	/**
	 * Test Requirement: TR-DMX-Login-08. TestCaseID: TC-DMX-Login-013
	 */
	@Test(priority=12)
	public void ThongBaoGuiLaiMa() throws InterruptedException {
		System.out.println("lauching chrome browser");
		driver = new ChromeDriver();
		driver.get(baseUrl);
		// Nhấn ch�?n "Lịch sử mua hàng" trên thanh Header
		WebElement weblogin = waitForElementClickable(By.xpath("//a[@href='/lich-su-mua-hang']"));
		weblogin.click();
		// Nhập số điện thoại vào trư�?ng 'Nhập số điện thoại mua hàng'.
		WebElement phone = waitForElementClickable(By.name("txtPhoneNumber"));
		phone.sendKeys("0345468365");
		// Nhấn Enter hoặc ch�?n 'Tiếp tục' và đợi mã xác nhận được gửi v�? SMS.
		WebElement login = waitForElementClickable(By.xpath("//*[@id=\"frmGetVerifyCode\"]/button"));
		login.click();
		Thread.sleep(35000);
		// Nhấn ch�?n "Tôi không nhận được mã, vui lòng gửi lại"
		WebElement thongbao = waitForElementClickable(By.xpath("//a[@class='resend-sms']"));
		thongbao.click();

		driver.close();
	}

	/**
	 * Test Requirement: TR-DMX-Login-10. TestCaseID: TC-DMX-Login-015
	 */
	@Test(priority=13)
	public void TestTitle() {
		System.out.println("lauching chrome browser");
		driver = new ChromeDriver();
		driver.get(baseUrl);
		// Nhấn ch�?n "Lịch sử mua hàng" trên thanh Header
		WebElement weblogin = waitForElementClickable(By.xpath("//a[@href='/lich-su-mua-hang']"));
		weblogin.click();

		String expectedTitle = "Lịch sử mua hàng | Dienmayxanh.com | Dienmayxanh.com";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);

		driver.close();

	}

	private WebElement waitForElementClickable(By locator) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(this.driver, 10);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
}