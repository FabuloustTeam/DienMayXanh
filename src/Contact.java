import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import AbstractClasses.AbstractBOT;
import BaseClass.ExcelUtils;

public class Contact extends AbstractBOT {
//	// 1. Truy cập https://www.dienmayxanh.com/lien-he
//	public String baseUrl = "https://www.dienmayxanh.com/";
//	public WebDriver driver;

	
	/**
	 * Test requirement: TR-DMX-CMCT-01 Test case ID: TC-DMX-CMCT-01
	 * @throws Exception 
	 */
	@Test
	public void testContactSusseccful() throws Exception {
		
		int rowData = ExcelUtils.getRowContains("TC-DMX-CMCT-01", 2);
//		// 1. Truy cập vào website: https://www.dienmayxanh.com
//		System.out.print("Launching chrome browser");
//		driver = new ChromeDriver();
//		driver.get(baseUrl);
		// 2. Nhấn chọn "Góp ý, liên hệ" trên thanh Footer
		WebElement contact = driver.findElement(By.xpath("//a[text()='Gửi góp ý, khiếu nại']"));
		contact.click();
		
		waitForElementClickable(By.id("message"));
		WebElement textarea = driver.findElement(By.id("message"));
		
		// 3. Nhập nội dung muốn góp ý
		textarea.sendKeys("Em mệt lắm, em mệt lắm chị à!");
		
		// 4. Chọn giới tính "Anh" hoặc "Chị"
		waitForElementClickable(By.xpath(".//label[text()=' Chị']"));
		WebElement genderMiss = driver.findElement(By.xpath(".//label[text()=' Chị']"));
		genderMiss.click();

		waitForElementClickable(By.id("fullname"));
		WebElement fullname = driver.findElement(By.id("fullname"));
		// 5. Nhập họ và tên
		fullname.sendKeys("Jônh Aná");

		waitForElementClickable(By.id("contel"));
		WebElement phonenumber = driver.findElement(By.id("contel"));
		// 6. Nhập số điện thoại
		phonenumber.sendKeys(ExcelUtils.getCellData(8, 7));

		// 7. Nhập email
		waitForElementClickable(By.id("conemail"));
		WebElement email = driver.findElement(By.id("conemail"));
		email.sendKeys("thanhnhan@gmail.com");

		WebElement submit = driver.findElement(By.id("submit"));
		// 8. Nhấn "Gửi liên hệ"
		submit.click();

		waitForAlert();
		String actual = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();

		String expected = "Đã gửi thông tin thành công!";
		Assert.assertEquals(actual, expected);

//		this.driver.close();

	}

	/**
	 * Test requirement: TR-DMX-CMCT-02 Test case ID: TC-DMX-CMCT-02
	 */
	@Test
	public void testFailWithoutNumberPhone() throws Exception {
		
		int rowData = ExcelUtils.getRowContains("TC-DMX-CMCT-02", 2);
		
//		// 1. Truy cập vào website: https://www.dienmayxanh.com
//		System.out.print("Launching chrome browser");
//		driver = new ChromeDriver();
//		driver.get(baseUrl);
		
		// 2. Nhấn chọn "Góp ý, liên hệ" trên thanh Footer
		WebElement contact = driver.findElement(By.xpath("//a[text()='Gửi góp ý, khiếu nại']"));
		contact.click();

		waitForElementClickable(By.id("message"));
		WebElement textarea = driver.findElement(By.id("message"));
		textarea.sendKeys("Em mệt lắm, em mệt lắm chị à!");
		waitForElementClickable(By.id("fullname"));
		WebElement fullname = driver.findElement(By.id("fullname"));
		fullname.sendKeys("Jônh Aná");

		waitForElementClickable(By.id("contel"));
		WebElement phonenumber = driver.findElement(By.id("contel"));
		
		// 3. Nhập số vào trường có 'Nhập số điện thoại'
		phonenumber.sendKeys(ExcelUtils.getCellData(15, 7));

		waitForElementClickable(By.xpath(".//label[text()=' Chị']"));
		WebElement genderMiss = driver.findElement(By.xpath(".//label[text()=' Chị']"));
		genderMiss.click();
		WebElement submit = driver.findElement(By.id("submit"));
		// 4. Nhấn "Gửi liên hệ"
		submit.click();

		// driver.close();

//		this.driver.close();

	}

	/**
	 * Test requirement: TR-DMX-CMCT-02 Test case ID: TC-DMX-CMCT-03
	 */
	@Test
	public void testSpecialCharacterNumberPhone() throws Exception{
		int rowData = ExcelUtils.getRowContains("TC-DMX-CMCT-03", 2);
//		// 1. Truy cập vào website: https://www.dienmayxanh.com
//		System.out.print("Launching chrome browser");
//		driver = new ChromeDriver();
//		driver.get(baseUrl);
		// 2. Nhấn chọn "Góp ý, liên hệ" trên thanh Footer
		WebElement contact = driver.findElement(By.xpath("//a[text()='Gửi góp ý, khiếu nại']"));
		contact.click();

		waitForElementClickable(By.id("message"));
		WebElement textarea = driver.findElement(By.id("message"));
		textarea.sendKeys("Em mệt lắm, em mệt lắm chị à!");
		waitForElementClickable(By.id("fullname"));
		WebElement fullname = driver.findElement(By.id("fullname"));
		fullname.sendKeys("Jônh Aná");
		waitForElementClickable(By.id("contel"));
		WebElement phonenumber = driver.findElement(By.id("contel"));
		// 3. Nhập ký tự đặc biệt vào trường "số điện thoại"
		phonenumber.sendKeys(ExcelUtils.getCellData(23, 7));

		waitForElementClickable(By.xpath(".//label[text()=' Chị']"));
		WebElement genderMiss = driver.findElement(By.xpath(".//label[text()=' Chị']"));
		genderMiss.click();
		WebElement submit = driver.findElement(By.id("submit"));
		// 4. Nhấn "Gửi liên hệ"
		submit.click();

		
//		driver.close();

	}

	/**
	 * Test requirement: TR-DMX-CMCT-02 Test case ID: TC-DMX-CMCT-04
	 */
	@Test
	public void testWrongNumberPhone() throws Exception {
		int rowData = ExcelUtils.getRowContains("TC-DMX-CMCT-04", 2);
		
//		// 1. Truy cập vào website: https://www.dienmayxanh.com
//		System.out.print("Launching chrome browser");
//		driver = new ChromeDriver();
//		driver.get(baseUrl);
		// 2. Nhấn chọn "Góp ý, liên hệ" trên thanh Footer
		WebElement contact = driver.findElement(By.xpath("//a[text()='Gửi góp ý, khiếu nại']"));
		contact.click();

		waitForElementClickable(By.id("message"));
		WebElement textarea = driver.findElement(By.id("message"));
		textarea.sendKeys("Em mệt lắm, em mệt lắm chị à!");

		waitForElementClickable(By.id("fullname"));
		WebElement fullname = driver.findElement(By.id("fullname"));
		fullname.sendKeys("Jônh Aná");
		// 3. Nhập số điện thoại không có thật vào trường 'Nhập số điện thoại mua hàng
		waitForElementClickable(By.id("contel"));
		WebElement phonenumber = driver.findElement(By.id("contel"));
		phonenumber.sendKeys(ExcelUtils.getCellData(8, 7));

		waitForElementClickable(By.xpath(".//label[text()=' Chị']"));
		WebElement genderMiss = driver.findElement(By.xpath(".//label[text()=' Chị']"));
		genderMiss.click();
		// 4. Nhấn "Gửi liên hệ"
		WebElement submit = driver.findElement(By.id("submit"));
		submit.click();

		waitForAlert();
		String actual = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();

//		String expected = "Xảy ra lỗi, vui lòng thử lại sau!";
//		Assert.assertEquals(actual, expected);

//		driver.close();
	}

	/**
	 * Test requirement: TR-DMX-CMCT-03 Test case ID: TC-DMX-CMCT-05
	 */
	@Test
	public void testDoNotChooseGentle() throws Exception {
		int rowData = ExcelUtils.getRowContains("TC-DMX-CMCT-05", 2);
//		// 1. Truy cập vào website: https://www.dienmayxanh.com
//		System.out.print("Launching chrome browser");
//		driver = new ChromeDriver();
//		driver.get(baseUrl);
		// 2. Nhấn chọn "Góp ý, liên hệ" trên thanh Footer
		WebElement contact = driver.findElement(By.xpath("//a[text()='Gửi góp ý, khiếu nại']"));
		contact.click();

		waitForElementClickable(By.id("message"));
		WebElement textarea = driver.findElement(By.id("message"));
		textarea.sendKeys("Em mệt lắm, em mệt lắm chị à!");
		waitForElementClickable(By.id("fullname"));
		WebElement fullname = driver.findElement(By.id("fullname"));
		fullname.sendKeys("Jônh Aná");
		waitForElementClickable(By.id("contel"));
		WebElement phonenumber = driver.findElement(By.id("contel"));
		phonenumber.sendKeys(ExcelUtils.getCellData(8, 7));

		// 3. Bỏ trống không chọn nút "Anh", " Chị"
		waitForElementClickable(By.xpath(".//label[text()=' Chị']"));
		WebElement genderMiss = driver.findElement(By.xpath(".//label[text()=' Chị']"));
		//// genderMiss.click();

		WebElement submit = driver.findElement(By.id("submit"));
		// 4. Nhấn "Gửi liên hệ"
		submit.click();

//		driver.close();

	}

	/**
	 * Test requirement: TR-DMX-CMCT-04 Test case ID: TC-DMX-CMCT-06
	 * @throws Exception
	 */
	@Test
	public void testDoNotEnterName() throws Exception {
		
//		// 1. Truy cập vào website: https://www.dienmayxanh.com
//		System.out.print("Launching chrome browser");
//		driver = new ChromeDriver();
//		driver.get(baseUrl);
		// 2. Nhấn chọn "Góp ý, liên hệ" trên thanh Footer
		WebElement contact = driver.findElement(By.xpath("//a[text()='Gửi góp ý, khiếu nại']"));
		contact.click();

		waitForElementClickable(By.id("message"));
		WebElement textarea = driver.findElement(By.id("message"));
		textarea.sendKeys(ExcelUtils.getCellData(42, 7));
		waitForElementClickable(By.id("fullname"));
		WebElement fullname = driver.findElement(By.id("fullname"));
		// 3. Bỏ trống trường họ và tên vào trường 'Nhập họ và tên'.
		fullname.sendKeys("");

		waitForElementClickable(By.id("contel"));
		WebElement phonenumber = driver.findElement(By.id("contel"));
		phonenumber.sendKeys(ExcelUtils.getCellData(44, 7));

		waitForElementClickable(By.xpath(".//label[text()=' Chị']"));
		WebElement genderMiss = driver.findElement(By.xpath(".//label[text()=' Chị']"));
		genderMiss.click();
		WebElement submit = driver.findElement(By.id("submit"));
		// 4. Nhấn "Gửi liên hệ"
		submit.click();
		
//		String expectedError =  ExcelUtils.getCellData(rowData, 9);
//		String error = waitForElementClickable(By.xpath("//*[text()='Vui lòng nhập họ tên !']")).getText();
//		Assert.assertEquals(error, expectedError);
//		driver.close();

	}

	/**
	 * Test requirement: TR-DMX-CMCT-05 Test case ID: TC-DMX-CMCT-07
	 * @throws Exception 
	 */
	@Test
	public void testEnterCaptainEmail() throws Exception {
		int rowData = ExcelUtils.getRowContains("TC-DMX-CMCT-07", 2);
//		// 1. Truy cập vào website: https://www.dienmayxanh.com
//		System.out.print("Launching chrome browser");
//		driver = new ChromeDriver();
//		driver.get(baseUrl);
		// 2. Nhấn chọn "Góp ý, liên hệ" trên thanh Footer
		WebElement contact = driver.findElement(By.xpath("//a[text()='Gửi góp ý, khiếu nại']"));
		contact.click();
		waitForElementClickable(By.id("message"));
		WebElement textarea = driver.findElement(By.id("message"));
		textarea.sendKeys("Em mệt lắm, em mệt lắm chị à!");

		waitForElementClickable(By.id("fullname"));
		WebElement fullname = driver.findElement(By.id("fullname"));
		fullname.sendKeys("Jônh Aná");
		// 3. Nhập chữ vào trường 'Email'.
		waitForElementClickable(By.id("conemail"));
		WebElement email = driver.findElement(By.id("conemail"));
		email.sendKeys(ExcelUtils.getCellData(52, 7));

		waitForElementClickable(By.xpath(".//label[text()=' Chị']"));
		WebElement genderMiss = driver.findElement(By.xpath(".//label[text()=' Chị']"));
		genderMiss.click();
		// 4. Nhấn "Gửi liên hệ"
		WebElement submit = driver.findElement(By.id("submit"));
		submit.click();

		waitForAlert();
		String actual = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();

		String expected = "Xảy ra lỗi, vui lòng thử lại sau!";
		Assert.assertEquals(actual, expected);

//		driver.close();

	}

	/**
	 * Test requirement: TR-DMX-CMCT-05 Test case ID: TC-DMX-CMCT-08
	 * @throws Exception 
	 */
	@Test
	public void testSpecialCharacterMail() throws Exception {
		int rowData = ExcelUtils.getRowContains("TC-DMX-CMCT-08", 2);
//		// 1. Truy cập vào website: https://www.dienmayxanh.com
//		System.out.print("Launching chrome browser");
//		driver = new ChromeDriver();
//		driver.get(baseUrl);
		// 2. Nhấn chọn "Góp ý, liên hệ" trên thanh Footer
		WebElement contact = driver.findElement(By.xpath("//a[text()='Gửi góp ý, khiếu nại']"));
		contact.click();

		waitForElementClickable(By.id("message"));
		WebElement textarea = driver.findElement(By.id("message"));
		textarea.sendKeys("Em mệt lắm, em mệt lắm chị à!");
		waitForElementClickable(By.id("fullname"));
		WebElement fullname = driver.findElement(By.id("fullname"));
		fullname.sendKeys("Jônh Aná");

		waitForElementClickable(By.id("conemail"));
		WebElement email = driver.findElement(By.id("conemail"));
		// 3. Nhập kí tự đặc biệt vào trường 'Email'.
		email.sendKeys(ExcelUtils.getCellData(60, 7));

		waitForElementClickable(By.xpath(".//label[text()=' Chị']"));
		WebElement genderMiss = driver.findElement(By.xpath(".//label[text()=' Chị']"));
		genderMiss.click();
		WebElement submit = driver.findElement(By.id("submit"));
		// 4. Nhấn "Gửi liên hệ"
		submit.click();

//		driver.close();

	}

	/**
	 * Test requirement: TR-DMX-CMCT-05 Test case ID: TC-DMX-CMCT-09
	 * @throws Exception 
	 */
	@Test
	public void testWrongMail() throws Exception {
		int rowData = ExcelUtils.getRowContains("TC-DMX-CMCT-09", 2);
//		// 1. Truy cập vào website: https://www.dienmayxanh.com
//		System.out.print("Launching chrome browser");
//		driver = new ChromeDriver();
//		driver.get(baseUrl);
		// 2. Nhấn chọn "Góp ý, liên hệ" trên thanh Footer
		WebElement contact = driver.findElement(By.xpath("//a[text()='Gửi góp ý, khiếu nại']"));
		contact.click();

		waitForElementClickable(By.id("message"));
		WebElement textarea = driver.findElement(By.id("message"));
		textarea.sendKeys("Em mệt lắm, em mệt lắm chị à!");

		waitForElementClickable(By.id("fullname"));
		WebElement fullname = driver.findElement(By.id("fullname"));
		fullname.sendKeys("Jônh Aná");

		waitForElementClickable(By.id("conemail"));
		WebElement email = driver.findElement(By.id("conemail"));
		// 3. Nhập email không có thật vào trường 'Email'.
		email.sendKeys(ExcelUtils.getCellData(68, 7));

		waitForElementClickable(By.xpath(".//label[text()=' Chị']"));
		WebElement genderMiss = driver.findElement(By.xpath(".//label[text()=' Chị']"));
		genderMiss.click();
		// 4. Nhấn "Gửi liên hệ"
		WebElement submit = driver.findElement(By.id("submit"));
		submit.click();

		waitForAlert();
		String actual = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();

		String expected = "Xảy ra lỗi, vui lòng thử lại sau!";
		Assert.assertEquals(actual, expected);

//		driver.close();

	}

	public WebElement waitForElementClickable(final By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));

	}

	public Alert waitForAlert() {
		WebDriverWait wait = new WebDriverWait(driver, 1000);
		return wait.until(ExpectedConditions.alertIsPresent());

	}
}
