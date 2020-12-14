package com.dienmayxanh.test;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.dienmayxanh.abstractclass.AbstractAnnotation;

public class Contact extends AbstractAnnotation {
	
	/**
	 * Test requirement: TR-DMX-CMCT-01 Test case ID: TC-DMX-CMCT-01
	 */
	@Test
	public void Lienhethanhcong() {
//		// 1. Truy cập vào website: https://www.dienmayxanh.com
//		System.out.print("Launching chrome browser");
//		driver = new ChromeDriver();
//		driver.get(baseUrl);
		// 2. Nhấn ch�?n "Góp ý, liên hệ" trên thanh Footer
		WebElement contact = driver.findElement(By.xpath("//a[text()='Gửi góp ý, khiếu nại']"));
		contact.click();

		waitForElementClickable(By.id("message"));
		WebElement textarea = driver.findElement(By.id("message"));
		
		// 3. Nhập nội dung muốn góp ý
		textarea.sendKeys("Em mệt lắm, em mệt lắm chị à!");
		
		// 4. Ch�?n giới tính "Anh" hoặc "Chị"
		waitForElementClickable(By.xpath(".//label[text()=' Chị']"));
		WebElement genderMiss = driver.findElement(By.xpath(".//label[text()=' Chị']"));
		genderMiss.click();

		waitForElementClickable(By.id("fullname"));
		WebElement fullname = driver.findElement(By.id("fullname"));
		// 5. Nhập h�? và tên
		fullname.sendKeys("Jônh Aná");

		waitForElementClickable(By.id("contel"));
		WebElement phonenumber = driver.findElement(By.id("contel"));
		// 6. Nhập số điện thoại
		phonenumber.sendKeys("0907978025");

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

		String expected = "�?ã gửi thông tin thành công!";
		Assert.assertEquals(actual, expected);

		this.driver.close();

	}

	/**
	 * Test requirement: TR-DMX-CMCT-02 Test case ID: TC-DMX-CMCT-02
	 */
	@Test
	public void SaiTruongSDT() {
//		
//		// 1. Truy cập vào website: https://www.dienmayxanh.com
//		System.out.print("Launching chrome browser");
//		driver = new ChromeDriver();
//		driver.get(baseUrl);
		
		// 2. Nhấn ch�?n "Góp ý, liên hệ" trên thanh Footer
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
		
		// 3. Nhập số vào trư�?ng có 'Nhập số điện thoại'
		phonenumber.sendKeys("0907898mbcd");

		waitForElementClickable(By.xpath(".//label[text()=' Chị']"));
		WebElement genderMiss = driver.findElement(By.xpath(".//label[text()=' Chị']"));
		genderMiss.click();
		WebElement submit = driver.findElement(By.id("submit"));
		// 4. Nhấn "Gửi liên hệ"
		submit.click();

		// driver.close();

		this.driver.close();

	}

	/**
	 * Test requirement: TR-DMX-CMCT-02 Test case ID: TC-DMX-CMCT-03
	 */
	@Test
	public void NhapkytudacbiettruongSDT() {
//		// 1. Truy cập vào website: https://www.dienmayxanh.com
//		System.out.print("Launching chrome browser");
//		driver = new ChromeDriver();
//		driver.get(baseUrl);
		// 2. Nhấn ch�?n "Góp ý, liên hệ" trên thanh Footer
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
		// 3. Nhập ký tự đặc biệt vào trư�?ng "số điện thoại"
		phonenumber.sendKeys("090943432!./?");

		waitForElementClickable(By.xpath(".//label[text()=' Chị']"));
		WebElement genderMiss = driver.findElement(By.xpath(".//label[text()=' Chị']"));
		genderMiss.click();
		WebElement submit = driver.findElement(By.id("submit"));
		// 4. Nhấn "Gửi liên hệ"
		submit.click();

		driver.close();

	}

	/**
	 * Test requirement: TR-DMX-CMCT-02 Test case ID: TC-DMX-CMCT-04
	 */
	@Test
	public void SDTkhonghople() {
//		// 1. Truy cập vào website: https://www.dienmayxanh.com
//		System.out.print("Launching chrome browser");
//		driver = new ChromeDriver();
//		driver.get(baseUrl);
		// 2. Nhấn ch�?n "Góp ý, liên hệ" trên thanh Footer
		WebElement contact = driver.findElement(By.xpath("//a[text()='Gửi góp ý, khiếu nại']"));
		contact.click();

		waitForElementClickable(By.id("message"));
		WebElement textarea = driver.findElement(By.id("message"));
		textarea.sendKeys("Em mệt lắm, em mệt lắm chị à!");

		waitForElementClickable(By.id("fullname"));
		WebElement fullname = driver.findElement(By.id("fullname"));
		fullname.sendKeys("Jônh Aná");
		// 3. Nhập số điện thoại không có thật vào trư�?ng 'Nhập số điện thoại mua hàng
		waitForElementClickable(By.id("contel"));
		WebElement phonenumber = driver.findElement(By.id("contel"));
		phonenumber.sendKeys("0542344234");

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

		driver.close();
	}

	/**
	 * Test requirement: TR-DMX-CMCT-03 Test case ID: TC-DMX-CMCT-05
	 */
	@Test
	public void Chuachongioitinh() {
//		// 1. Truy cập vào website: https://www.dienmayxanh.com
//		System.out.print("Launching chrome browser");
//		driver = new ChromeDriver();
//		driver.get(baseUrl);
		// 2. Nhấn ch�?n "Góp ý, liên hệ" trên thanh Footer
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
		phonenumber.sendKeys("");

		// 3. B�? trống không ch�?n nút "Anh", " Chị"
		waitForElementClickable(By.xpath(".//label[text()=' Chị']"));
		WebElement genderMiss = driver.findElement(By.xpath(".//label[text()=' Chị']"));
		//// genderMiss.click();

		WebElement submit = driver.findElement(By.id("submit"));
		// 4. Nhấn "Gửi liên hệ"
		submit.click();

		driver.close();

	}

	/**
	 * Test requirement: TR-DMX-CMCT-04 Test case ID: TC-DMX-CMCT-06
	 */
	@Test
	public void BotrongtruongHvT() {
//		// 1. Truy cập vào website: https://www.dienmayxanh.com
//		System.out.print("Launching chrome browser");
//		driver = new ChromeDriver();
//		driver.get(baseUrl);
		// 2. Nhấn ch�?n "Góp ý, liên hệ" trên thanh Footer
		WebElement contact = driver.findElement(By.xpath("//a[text()='Gửi góp ý, khiếu nại']"));
		contact.click();

		waitForElementClickable(By.id("message"));
		WebElement textarea = driver.findElement(By.id("message"));
		textarea.sendKeys("Em mệt lắm, em mệt lắm chị à!");
		waitForElementClickable(By.id("fullname"));
		WebElement fullname = driver.findElement(By.id("fullname"));
		// 3. B�? trống trư�?ng h�? và tên vào trư�?ng 'Nhập h�? và tên'.
		fullname.sendKeys("");

		waitForElementClickable(By.id("contel"));
		WebElement phonenumber = driver.findElement(By.id("contel"));
		phonenumber.sendKeys("0907972811");

		waitForElementClickable(By.xpath(".//label[text()=' Chị']"));
		WebElement genderMiss = driver.findElement(By.xpath(".//label[text()=' Chị']"));
		genderMiss.click();
		WebElement submit = driver.findElement(By.id("submit"));
		// 4. Nhấn "Gửi liên hệ"
		submit.click();

		driver.close();

	}

	/**
	 * Test requirement: TR-DMX-CMCT-05 Test case ID: TC-DMX-CMCT-07
	 */
	@Test
	public void NhapchuhoavaotruognEmail() {
//		// 1. Truy cập vào website: https://www.dienmayxanh.com
//		System.out.print("Launching chrome browser");
//		driver = new ChromeDriver();
//		driver.get(baseUrl);
		// 2. Nhấn ch�?n "Góp ý, liên hệ" trên thanh Footer
		WebElement contact = driver.findElement(By.xpath("//a[text()='Gửi góp ý, khiếu nại']"));
		contact.click();
		waitForElementClickable(By.id("message"));
		WebElement textarea = driver.findElement(By.id("message"));
		textarea.sendKeys("Em mệt lắm, em mệt lắm chị à!");

		waitForElementClickable(By.id("fullname"));
		WebElement fullname = driver.findElement(By.id("fullname"));
		fullname.sendKeys("Jônh Aná");
		// 3. Nhập chữ vào trư�?ng 'Email'.
		waitForElementClickable(By.id("conemail"));
		WebElement email = driver.findElement(By.id("conemail"));
		email.sendKeys("Thanhnhan@gmail.com");

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

		driver.close();

	}

	/**
	 * Test requirement: TR-DMX-CMCT-05 Test case ID: TC-DMX-CMCT-08
	 */
	@Test
	public void NhapkytudacbietvaotruongEmail() {
//		// 1. Truy cập vào website: https://www.dienmayxanh.com
//		System.out.print("Launching chrome browser");
//		driver = new ChromeDriver();
//		driver.get(baseUrl);
		// 2. Nhấn ch�?n "Góp ý, liên hệ" trên thanh Footer
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
		// 3. Nhập kí tự đặc biệt vào trư�?ng 'Email'.
		email.sendKeys("thanhnhan?..'`@gmail.com");

		waitForElementClickable(By.xpath(".//label[text()=' Chị']"));
		WebElement genderMiss = driver.findElement(By.xpath(".//label[text()=' Chị']"));
		genderMiss.click();
		WebElement submit = driver.findElement(By.id("submit"));
		// 4. Nhấn "Gửi liên hệ"
		submit.click();

		driver.close();

	}

	/**
	 * Test requirement: TR-DMX-CMCT-05 Test case ID: TC-DMX-CMCT-09
	 */
	@Test
	public void NhapEmailkhonghople() {
//		// 1. Truy cập vào website: https://www.dienmayxanh.com
//		System.out.print("Launching chrome browser");
//		driver = new ChromeDriver();
//		driver.get(baseUrl);
		// 2. Nhấn ch�?n "Góp ý, liên hệ" trên thanh Footer
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
		// 3. Nhập email không có thật vào trư�?ng 'Email'.
		email.sendKeys("motconvitxoa2caichan@gmail.com");

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

		driver.close();

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