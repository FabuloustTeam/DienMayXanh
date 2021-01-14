import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import AbstractClasses.AbstractBOT;
import BaseClass.ExcelUtils;
import BaseClass.TakeSnapShot;
import Enum.*;
import POM.ContactPage;

public class Contact extends AbstractBOT {
	
	private final int COL_EXPECT = 3;
	private final int COL_RESULT = 4;
	private final int COL_INPUT_CONTENT = 5;
	private final int COL_INPUT_NAME = 6;
	private final int COL_INPUT_PHONE = 7;
	private final int COL_INPUT_GENTLE = 8;
	private final int COL_INPUT_EMAIL = 9;
	private final int COL_TYPE = 10;
	private final int START_ROW = 2;
	private final int COL_TESTNAME = 1;
	private int iTestCaseRow;
	private String typeValue = "";
	private String content = "";
	private String name = "";
	private String phone = "";
	private String gentle = "";
	private String email = "";
	private String actual = "";
	private String expected = "";
	private String testCaseName = "";
	String driverPath = "E:\\Selenium\\chromedriver_win32\\chromedriver.exe";
	
	ContactPage objContact = new ContactPage();
	
	@Parameters({ "url" })
	@Test(priority = 1)
	public void testContact(String url) throws Exception {
		iTestCaseRow = ExcelUtils.getRowUsed();
		for(int i = START_ROW; i <= iTestCaseRow; i++) {
			actual = "";
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(url);
			
			objContact.clickWeb();
			
			// 3. Nhập nội dung muốn góp ý
			content = ExcelUtils.getCellData(i, COL_INPUT_CONTENT);
			objContact.setContent(content);
			
			// 4. Chọn giới tính "Anh" hoặc "Chị"
			gentle = ExcelUtils.getCellData(i, COL_INPUT_GENTLE);
			if(!gentle.equals("")) {
				objContact.clickRadioButton();
			}

			// 5. Nhập họ và tên
			name = ExcelUtils.getCellData(i, COL_INPUT_NAME);
			objContact.setName(name);
	
			waitForElementClickable(By.id("contel"));
			WebElement phonenumber = driver.findElement(By.id("contel"));
			// 6. Nhập số điện thoại
			phone = ExcelUtils.getCellData(i, COL_INPUT_PHONE);
			objContact.setPhone(phone);
	
			// 7. Nhập email
			email = ExcelUtils.getCellData(i, COL_INPUT_EMAIL);
			objContact.setEmail(email);
	
			WebElement submit = driver.findElement(By.id("submit"));
			// 8. Nhấn "Gửi liên hệ"
			
			
			typeValue = ExcelUtils.getCellData(i, COL_TYPE);
			if(typeValue.equals(Type.Alert.toString())) {
				submit.click();
				waitForAlert();
				actual = driver.switchTo().alert().getText();
				driver.switchTo().alert().accept();
			} else {
				submit.click();
				actual = driver.findElement(By.id("errsubmitcon")).getText();
				
			}
			
			expected = ExcelUtils.getCellData(i, COL_EXPECT);
			if(actual.equals(expected)) {
				ExcelUtils.setCellData(i, COL_RESULT, Result.PASSED.toString());
			} else {
				testCaseName = ExcelUtils.getCellData(i, COL_TESTNAME);
				String file = System.getProperty("user.dir") + "\\screenshots\\" + testCaseName + ".png";
				try {
					TakeSnapShot.takeScreenShot(file);
				} catch(Exception e) {
					e.printStackTrace();
				}
				ExcelUtils.setCellData(i, COL_RESULT, Result.FAILED.toString());
			}
			
			if(actual.equals(""))
				ExcelUtils.setCellData(i, COL_RESULT, Result.SKIPPED.toString());
			
			driver.close();
		}
	}
	
	
//	// 1. Truy cập https://www.dienmayxanh.com/lien-he
//	public String baseUrl = "https://www.dienmayxanh.com/";
//	public WebDriver driver;
//	/**
//	 * Test requirement: TR-DMX-CMCT-01 Test case ID: TC-DMX-CMCT-01
//	 * @throws Exception 
//	 */
//	@Test(priority = 1)
//	public void Contact_Susseccful() throws Exception {
//		
//		int rowData = ExcelUtils.getRowContains("Contact_Susseccful", 2);
////		// 1. Truy cập vào website: https://www.dienmayxanh.com
////		System.out.print("Launching chrome browser");
////		driver = new ChromeDriver();
////		driver.get(baseUrl);
//		// 2. Nhấn chọn "Góp ý, liên hệ" trên thanh Footer
//		WebElement contact = driver.findElement(By.xpath("//a[text()='Gửi góp ý, khiếu nại']"));
//		contact.click();
//		
//		waitForElementClickable(By.id("message"));
//		WebElement textarea = driver.findElement(By.id("message"));
//		
//		// 3. Nhập nội dung muốn góp ý
//		textarea.sendKeys(ExcelUtils.getCellData(2, 5));
//		
//		// 4. Chọn giới tính "Anh" hoặc "Chị"
//		waitForElementClickable(By.xpath(".//label[text()=' Chị']"));
//		WebElement genderMiss = driver.findElement(By.xpath(".//label[text()=' Chị']"));
//		genderMiss.click();
//
//		waitForElementClickable(By.id("fullname"));
//		WebElement fullname = driver.findElement(By.id("fullname"));
//		// 5. Nhập họ và tên
//		fullname.sendKeys(ExcelUtils.getCellData(2, 6));
//
//		waitForElementClickable(By.id("contel"));
//		WebElement phonenumber = driver.findElement(By.id("contel"));
//		// 6. Nhập số điện thoại
//		phonenumber.sendKeys(ExcelUtils.getCellData(2, 7));
//
//		// 7. Nhập email
//		waitForElementClickable(By.id("conemail"));
//		WebElement email = driver.findElement(By.id("conemail"));
//		email.sendKeys(ExcelUtils.getCellData(2, 9));
//
//		WebElement submit = driver.findElement(By.id("submit"));
//		// 8. Nhấn "Gửi liên hệ"
//		submit.click();
//
//		waitForAlert();
//		String actual = driver.switchTo().alert().getText();
//		driver.switchTo().alert().accept();
//
////		String expected = "Đã gửi thông tin thành công!";
////		Assert.assertEquals(actual, expected);
//
////		this.driver.close();
//
//	}
//
//	/**
//	 * Test requirement: TR-DMX-CMCT-02 Test case ID: TC-DMX-CMCT-02
//	 */
//	@Test(priority = 2)
//	public void Contact_FailWithoutNumberPhone() throws Exception {
//		
//		int rowData = ExcelUtils.getRowContains("TC-DMX-CMCT-02", 2);
//		
////		// 1. Truy cập vào website: https://www.dienmayxanh.com
////		System.out.print("Launching chrome browser");
////		driver = new ChromeDriver();
////		driver.get(baseUrl);
//		
//		// 2. Nhấn chọn "Góp ý, liên hệ" trên thanh Footer
//		WebElement contact = driver.findElement(By.xpath("//a[text()='Gửi góp ý, khiếu nại']"));
//		contact.click();
//
//		waitForElementClickable(By.id("message"));
//		WebElement textarea = driver.findElement(By.id("message"));
//		textarea.sendKeys(ExcelUtils.getCellData(3, 5));
//		waitForElementClickable(By.id("fullname"));
//		WebElement fullname = driver.findElement(By.id("fullname"));
//		fullname.sendKeys(ExcelUtils.getCellData(3, 6));
//
//		waitForElementClickable(By.id("contel"));
//		WebElement phonenumber = driver.findElement(By.id("contel"));
//		
//		// 3. Nhập số vào trường có 'Nhập số điện thoại'
//		phonenumber.sendKeys(ExcelUtils.getCellData(3, 7));
//
//		waitForElementClickable(By.xpath(".//label[text()=' Chị']"));
//		WebElement genderMiss = driver.findElement(By.xpath(".//label[text()=' Chị']"));
//		genderMiss.click();
//		WebElement submit = driver.findElement(By.id("submit"));
//		// 4. Nhấn "Gửi liên hệ"
//		submit.click();
//
//		// driver.close();
//
////		this.driver.close();
//
//	}
//
//	/**
//	 * Test requirement: TR-DMX-CMCT-02 Test case ID: TC-DMX-CMCT-03
//	 */
//	@Test(priority = 3)
//	public void Contact_SpecialCharacterNumberPhone() throws Exception{
//		int rowData = ExcelUtils.getRowContains("TC-DMX-CMCT-03", 2);
////		// 1. Truy cập vào website: https://www.dienmayxanh.com
////		System.out.print("Launching chrome browser");
////		driver = new ChromeDriver();
////		driver.get(baseUrl);
//		// 2. Nhấn chọn "Góp ý, liên hệ" trên thanh Footer
//		WebElement contact = driver.findElement(By.xpath("//a[text()='Gửi góp ý, khiếu nại']"));
//		contact.click();
//
//		waitForElementClickable(By.id("message"));
//		WebElement textarea = driver.findElement(By.id("message"));
//		textarea.sendKeys(ExcelUtils.getCellData(4, 5));
//		waitForElementClickable(By.id("fullname"));
//		WebElement fullname = driver.findElement(By.id("fullname"));
//		fullname.sendKeys(ExcelUtils.getCellData(4, 6));
//		waitForElementClickable(By.id("contel"));
//		WebElement phonenumber = driver.findElement(By.id("contel"));
//		// 3. Nhập ký tự đặc biệt vào trường "số điện thoại"
//		phonenumber.sendKeys(ExcelUtils.getCellData(4, 7));
//
//		waitForElementClickable(By.xpath(".//label[text()=' Chị']"));
//		WebElement genderMiss = driver.findElement(By.xpath(".//label[text()=' Chị']"));
//		genderMiss.click();
//		WebElement submit = driver.findElement(By.id("submit"));
//		// 4. Nhấn "Gửi liên hệ"
//		submit.click();
//
//		
////		driver.close();
//
//	}
//
//	/**
//	 * Test requirement: TR-DMX-CMCT-02 Test case ID: TC-DMX-CMCT-04
//	 */
//	@Test(priority = 4)
//	public void Contact_WrongNumberPhone() throws Exception {
//		int rowData = ExcelUtils.getRowContains("TC-DMX-CMCT-04", 2);
//		
////		// 1. Truy cập vào website: https://www.dienmayxanh.com
////		System.out.print("Launching chrome browser");
////		driver = new ChromeDriver();
////		driver.get(baseUrl);
//		// 2. Nhấn chọn "Góp ý, liên hệ" trên thanh Footer
//		WebElement contact = driver.findElement(By.xpath("//a[text()='Gửi góp ý, khiếu nại']"));
//		contact.click();
//
//		waitForElementClickable(By.id("message"));
//		WebElement textarea = driver.findElement(By.id("message"));
//		textarea.sendKeys(ExcelUtils.getCellData(5, 5));
//
//		waitForElementClickable(By.id("fullname"));
//		WebElement fullname = driver.findElement(By.id("fullname"));
//		fullname.sendKeys(ExcelUtils.getCellData(5, 6));
//		// 3. Nhập số điện thoại không có thật vào trường 'Nhập số điện thoại mua hàng
//		waitForElementClickable(By.id("contel"));
//		WebElement phonenumber = driver.findElement(By.id("contel"));
//		phonenumber.sendKeys(ExcelUtils.getCellData(5, 7));
//
//		waitForElementClickable(By.xpath(".//label[text()=' Chị']"));
//		WebElement genderMiss = driver.findElement(By.xpath(".//label[text()=' Chị']"));
//		genderMiss.click();
//		// 4. Nhấn "Gửi liên hệ"
//		WebElement submit = driver.findElement(By.id("submit"));
//		submit.click();
//
//		waitForAlert();
//		String actual = driver.switchTo().alert().getText();
//		driver.switchTo().alert().accept();
//
////		String expected = "Xảy ra lỗi, vui lòng thử lại sau!";
////		Assert.assertEquals(actual, expected);
//
////		driver.close();
//	}
//
//	/**
//	 * Test requirement: TR-DMX-CMCT-03 Test case ID: TC-DMX-CMCT-05
//	 */
//	@Test(priority = 5)
//	public void Contact_DoNotChooseGentle() throws Exception {
//		int rowData = ExcelUtils.getRowContains("TC-DMX-CMCT-05", 2);
////		// 1. Truy cập vào website: https://www.dienmayxanh.com
////		System.out.print("Launching chrome browser");
////		driver = new ChromeDriver();
////		driver.get(baseUrl);
//		// 2. Nhấn chọn "Góp ý, liên hệ" trên thanh Footer
//		WebElement contact = driver.findElement(By.xpath("//a[text()='Gửi góp ý, khiếu nại']"));
//		contact.click();
//
//		waitForElementClickable(By.id("message"));
//		WebElement textarea = driver.findElement(By.id("message"));
//		textarea.sendKeys(ExcelUtils.getCellData(6, 5));
//		waitForElementClickable(By.id("fullname"));
//		WebElement fullname = driver.findElement(By.id("fullname"));
//		fullname.sendKeys(ExcelUtils.getCellData(6, 6));
//		waitForElementClickable(By.id("contel"));
//		WebElement phonenumber = driver.findElement(By.id("contel"));
//		phonenumber.sendKeys(ExcelUtils.getCellData(6, 7));
//
//		// 3. Bỏ trống không chọn nút "Anh", " Chị"
//		waitForElementClickable(By.xpath(".//label[text()=' Chị']"));
//		WebElement genderMiss = driver.findElement(By.xpath(".//label[text()=' Chị']"));
//		//// genderMiss.click();
//
//		WebElement submit = driver.findElement(By.id("submit"));
//		// 4. Nhấn "Gửi liên hệ"
//		submit.click();
//
////		driver.close();
//
//	}
//
//	/**
//	 * Test requirement: TR-DMX-CMCT-04 Test case ID: TC-DMX-CMCT-06
//	 * @throws Exception
//	 */
//	@Test(priority = 6)
//	public void Contact_DoNotEnterName() throws Exception {
//		
////		// 1. Truy cập vào website: https://www.dienmayxanh.com
////		System.out.print("Launching chrome browser");
////		driver = new ChromeDriver();
////		driver.get(baseUrl);
//		// 2. Nhấn chọn "Góp ý, liên hệ" trên thanh Footer
//		WebElement contact = driver.findElement(By.xpath("//a[text()='Gửi góp ý, khiếu nại']"));
//		contact.click();
//
//		waitForElementClickable(By.id("message"));
//		WebElement textarea = driver.findElement(By.id("message"));
//		textarea.sendKeys(ExcelUtils.getCellData(7, 5));
//		waitForElementClickable(By.id("fullname"));
//		WebElement fullname = driver.findElement(By.id("fullname"));
//		// 3. Bỏ trống trường họ và tên vào trường 'Nhập họ và tên'.
//		fullname.sendKeys(ExcelUtils.getCellData(7, 6));
//
//		waitForElementClickable(By.id("contel"));
//		WebElement phonenumber = driver.findElement(By.id("contel"));
//		phonenumber.sendKeys(ExcelUtils.getCellData(7, 7));
//
//		waitForElementClickable(By.xpath(".//label[text()=' Chị']"));
//		WebElement genderMiss = driver.findElement(By.xpath(".//label[text()=' Chị']"));
//		genderMiss.click();
//		WebElement submit = driver.findElement(By.id("submit"));
//		// 4. Nhấn "Gửi liên hệ"
//		submit.click();
//		
////		String expectedError =  ExcelUtils.getCellData(rowData, 9);
////		String error = waitForElementClickable(By.xpath("//*[text()='Vui lòng nhập họ tên !']")).getText();
////		Assert.assertEquals(error, expectedError);
////		driver.close();
//
//	}
//
//	/**
//	 * Test requirement: TR-DMX-CMCT-05 Test case ID: TC-DMX-CMCT-07
//	 * @throws Exception 
//	 */
//	@Test(priority = 7)
//	public void Contact_EnterCaptainEmail() throws Exception {
//		int rowData = ExcelUtils.getRowContains("TC-DMX-CMCT-07", 2);
////		// 1. Truy cập vào website: https://www.dienmayxanh.com
////		System.out.print("Launching chrome browser");
////		driver = new ChromeDriver();
////		driver.get(baseUrl);
//		// 2. Nhấn chọn "Góp ý, liên hệ" trên thanh Footer
//		WebElement contact = driver.findElement(By.xpath("//a[text()='Gửi góp ý, khiếu nại']"));
//		contact.click();
//		waitForElementClickable(By.id("message"));
//		WebElement textarea = driver.findElement(By.id("message"));
//		textarea.sendKeys(ExcelUtils.getCellData(8, 5));
//
//		waitForElementClickable(By.id("fullname"));
//		WebElement fullname = driver.findElement(By.id("fullname"));
//		fullname.sendKeys(ExcelUtils.getCellData(8, 6));
//		// 3. Nhập chữ vào trường 'Email'.
//		waitForElementClickable(By.id("conemail"));
//		WebElement email = driver.findElement(By.id("conemail"));
//		email.sendKeys(ExcelUtils.getCellData(8, 9));
//
//		waitForElementClickable(By.xpath(".//label[text()=' Chị']"));
//		WebElement genderMiss = driver.findElement(By.xpath(".//label[text()=' Chị']"));
//		genderMiss.click();
//		// 4. Nhấn "Gửi liên hệ"
//		WebElement submit = driver.findElement(By.id("submit"));
//		submit.click();
//
//		waitForAlert();
//		String actual = driver.switchTo().alert().getText();
//		driver.switchTo().alert().accept();
//
//		String expected = "Xảy ra lỗi, vui lòng thử lại sau!";
//		Assert.assertEquals(actual, expected);
//
////		driver.close();
//
//	}
//
//	/**
//	 * Test requirement: TR-DMX-CMCT-05 Test case ID: TC-DMX-CMCT-08
//	 * @throws Exception 
//	 */
//	@Test(priority = 8)
//	public void Contatct_SpecialCharacterMail() throws Exception {
//		int rowData = ExcelUtils.getRowContains("TC-DMX-CMCT-08", 2);
////		// 1. Truy cập vào website: https://www.dienmayxanh.com
////		System.out.print("Launching chrome browser");
////		driver = new ChromeDriver();
////		driver.get(baseUrl);
//		// 2. Nhấn chọn "Góp ý, liên hệ" trên thanh Footer
//		WebElement contact = driver.findElement(By.xpath("//a[text()='Gửi góp ý, khiếu nại']"));
//		contact.click();
//
//		waitForElementClickable(By.id("message"));
//		WebElement textarea = driver.findElement(By.id("message"));
//		textarea.sendKeys(ExcelUtils.getCellData(9, 5));
//		waitForElementClickable(By.id("fullname"));
//		WebElement fullname = driver.findElement(By.id("fullname"));
//		fullname.sendKeys(ExcelUtils.getCellData(9, 6));
//
//		waitForElementClickable(By.id("conemail"));
//		WebElement email = driver.findElement(By.id("conemail"));
//		// 3. Nhập kí tự đặc biệt vào trường 'Email'.
//		email.sendKeys(ExcelUtils.getCellData(9, 9));
//
//		waitForElementClickable(By.xpath(".//label[text()=' Chị']"));
//		WebElement genderMiss = driver.findElement(By.xpath(".//label[text()=' Chị']"));
//		genderMiss.click();
//		WebElement submit = driver.findElement(By.id("submit"));
//		// 4. Nhấn "Gửi liên hệ"
//		submit.click();
//
////		driver.close();
//
//	}
//
//	/**
//	 * Test requirement: TR-DMX-CMCT-05 Test case ID: TC-DMX-CMCT-09
//	 * @throws Exception 
//	 */
//	@Test(priority = 9)
//	public void Contact_WrongMail() throws Exception {
//		int rowData = ExcelUtils.getRowContains("TC-DMX-CMCT-09", 2);
////		// 1. Truy cập vào website: https://www.dienmayxanh.com
////		System.out.print("Launching chrome browser");
////		driver = new ChromeDriver();
////		driver.get(baseUrl);
//		// 2. Nhấn chọn "Góp ý, liên hệ" trên thanh Footer
//		WebElement contact = driver.findElement(By.xpath("//a[text()='Gửi góp ý, khiếu nại']"));
//		contact.click();
//
//		waitForElementClickable(By.id("message"));
//		WebElement textarea = driver.findElement(By.id("message"));
//		textarea.sendKeys(ExcelUtils.getCellData(10, 5));
//
//		waitForElementClickable(By.id("fullname"));
//		WebElement fullname = driver.findElement(By.id("fullname"));
//		fullname.sendKeys(ExcelUtils.getCellData(10, 6));
//
//		waitForElementClickable(By.id("conemail"));
//		WebElement email = driver.findElement(By.id("conemail"));
//		// 3. Nhập email không có thật vào trường 'Email'.
//		email.sendKeys(ExcelUtils.getCellData(10, 9));
//
//		waitForElementClickable(By.xpath(".//label[text()=' Chị']"));
//		WebElement genderMiss = driver.findElement(By.xpath(".//label[text()=' Chị']"));
//		genderMiss.click();
//		// 4. Nhấn "Gửi liên hệ"
//		WebElement submit = driver.findElement(By.id("submit"));
//		submit.click();
//
//		waitForAlert();
//		String actual = driver.switchTo().alert().getText();
//		driver.switchTo().alert().accept();
//
//		String expected = "Xảy ra lỗi, vui lòng thử lại sau!";
//		Assert.assertEquals(actual, expected);
//
////		driver.close();

//	}

	public WebElement waitForElementClickable(final By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));

	}

	public Alert waitForAlert() {
		WebDriverWait wait = new WebDriverWait(driver, 1000);
		return wait.until(ExpectedConditions.alertIsPresent());

	}
}
