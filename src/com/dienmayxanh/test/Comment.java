package com.dienmayxanh.test;

import com.dienmayxanh.abstractclass.*;
import com.dienmayxanh.service.ExcelUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.*;

public class Comment extends AbstractAnnotation {

	@BeforeMethod
	public void beforeMethod() {
		// 2. Chọn mục Gửi góp ý, khiếu nại ở footer
		goToSendCommentPage();
	}

	/**
	 * Test requirement: TR-DMX-BL-02 - Test case ID: TC-DMX-BL-02
	 * 
	 * @throws Exception
	 */
	@Test(priority = 1)
	public void testWithoutCommentContent() throws Exception {
		int rowData = ExcelUtils.getRowContains("TC-DMX-BL-02", 2);
		ITestResult result = Reporter.getCurrentTestResult();
		result.setAttribute("id", "TC-DMX-BL-02");
		// 3. Không nội dung bình luận
		inputComment("");

		// 4. Nhập họ tên
		String name = ExcelUtils.getCellData(rowData + 3, 7);
		inputName(name);

		// 5. Chọn gửi hình
		inputImage();

		// 6. Nhập email
		String email = ExcelUtils.getCellData(rowData + 5, 7);
		inputEmail(email);

		// 7. Ch�?n gửi
		pressSend();

		String actualError = getErrorTextCommentContent();
		result.setAttribute("actualResult", actualError);

		String expectedError = ExcelUtils.getCellData(rowData + 6, 9);
		Assert.assertEquals(actualError, expectedError);
	}

	/**
	 * Test requirement: TR-DMX-BL-02 - Test case ID: TC-DMX-BL-03
	 * 
	 * @throws Exception
	 */
	@Test(priority = 2)
	public void testOnlySpaceCommentContent() throws Exception {
		int rowData = ExcelUtils.getRowContains("TC-DMX-BL-03", 2);
		ITestResult result = Reporter.getCurrentTestResult();
		result.setAttribute("id", "TC-DMX-BL-03");

		// 3. Chỉ nhập khoảng trắng vào nội dung
		inputComment("    ");

		// 4. Nhập họ tên
		String name = ExcelUtils.getCellData(rowData + 3, 7);
		inputName(name);

		// 5. Chọn gửi hình
		inputImage();

		// 6. Nhập email
		String email = ExcelUtils.getCellData(rowData + 5, 7);
		inputEmail(email);

		// 7. Chọn gửi
		pressSend();

		String actualError = getErrorTextCommentContent();
		result.setAttribute("actualResult", actualError);

		String expectedError = ExcelUtils.getCellData(rowData + 6, 9);
		Assert.assertEquals(actualError, expectedError);
	}

	/**
	 * Test requirement: TR-DMX-BL-03 - Test case ID: TC-DMX-BL-04
	 * 
	 * @throws Exception
	 */
	@Test(priority = 3)
	public void testWithoutName() throws Exception {
		int rowData = ExcelUtils.getRowContains("TC-DMX-BL-04", 2);
		ITestResult result = Reporter.getCurrentTestResult();
		result.setAttribute("id", "TC-DMX-BL-04");

		/// 3. Nhập nội dung bình luận
		String comment = ExcelUtils.getCellData(rowData + 2, 7);
		inputComment(comment);

		// 4. Nhập họ tên
		inputName("");

		// 5. Chọn gửi hình
		inputImage();

		// 6. Nhập email
		String email = ExcelUtils.getCellData(rowData + 5, 7);
		inputEmail(email);

		// 7. Chọn gửi
		pressSend();

		String actualError = getAlertMessage();
		result.setAttribute("actualResult", actualError);

		String expectedError = ExcelUtils.getCellData(rowData + 6, 9);

		Assert.assertEquals(actualError, expectedError);
	}

	/**
	 * Test requirement: TR-DMX-BL-03 - Test case ID: TC-DMX-BL-05
	 * 
	 * @throws Exception
	 */
	@Test(priority = 4)
	public void testOnlySpaceName() throws Exception {
		int rowData = ExcelUtils.getRowContains("TC-DMX-BL-05", 2);
		ITestResult result = Reporter.getCurrentTestResult();
		result.setAttribute("id", "TC-DMX-BL-05");

		// 3. Nhập nội dung bình luận
		String comment = ExcelUtils.getCellData(rowData + 2, 7);
		inputComment(comment);

		// 4. Nhập họ tên
		inputName("      ");

		// 5. Chọn gửi hình
		inputImage();

		// 6. Nhập email
		String email = ExcelUtils.getCellData(rowData + 5, 7);
		inputEmail(email);

		// 7. Chọn gửi
		pressSend();

		String actualError = getAlertMessage();
		result.setAttribute("actualResult", actualError);

		String expectedError = ExcelUtils.getCellData(rowData + 6, 9);

		Assert.assertEquals(actualError, expectedError);
	}

	/**
	 * Test requirement: TR-DMX-BL-04 - Test case ID: TC-DMX-BL-06
	 * 
	 * @throws Exception
	 */
	@Test(priority = 5)
	public void testContentCommentIsLessThan10Characters() throws Exception {
		int rowData = ExcelUtils.getRowContains("TC-DMX-BL-06", 2);
		ITestResult result = Reporter.getCurrentTestResult();
		result.setAttribute("id", "TC-DMX-BL-06");

		// 3. Nhập nội dung bình luận ít hơn 10 ký tự
		String comment = ExcelUtils.getCellData(rowData + 2, 7);
		inputComment(comment);

		// 4. Nhập họ tên
		String name = ExcelUtils.getCellData(rowData + 3, 7);
		inputName(name);

		// 5. Chọn gửi hình
		inputImage();

		// 6. Nhập email
		String email = ExcelUtils.getCellData(rowData + 5, 7);
		inputEmail(email);

		// 7. Chọn gửi
		pressSend();

		String actualError = getAlertMessage();
		result.setAttribute("actualResult", actualError);

		String expectedError = ExcelUtils.getCellData(rowData + 6, 9);

		Assert.assertEquals(actualError, expectedError);
	}

	private void goToSendCommentPage() {
		WebElement linkCommentPage = waitForElementClickable(By.xpath("//footer//a[text()='Gửi góp ý, khiếu nại']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		linkCommentPage.click();
	}

	private void inputComment(String comment) throws AWTException, InterruptedException {
		WebElement maskComment = waitForElementClickable(
				By.xpath("//textarea[@placeholder='Mời bạn thảo luận, vui lòng nhập tiếng Việt có dấu']"));

		Thread.sleep(5000);
		Actions builder = new Actions(driver);
		builder.moveToElement(maskComment, 1, 1).click().build().perform();

		WebElement txtBoxComment = waitForElementClickable(By.id("content"));
		txtBoxComment.sendKeys(comment);
	}

	private void inputName(String name) {
		WebElement txtBoxName = waitForElementClickable(By.xpath("//input[@placeholder='Họ tên (bắt buộc)']"));
		txtBoxName.sendKeys(name);
	}

	private void inputImage() throws AWTException, InterruptedException {
		WebElement attachImage = waitForElementClickable(By.xpath("//div[@class='attach']"));
		attachImage.click();
		Thread.sleep(1000);
		uploadFile("shy.jpg");
	}

	private void uploadFile(String fileName) throws AWTException, InterruptedException {
		StringSelection strSelection = new StringSelection(fileName);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(strSelection, null);

		Robot robot = new Robot();
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		robot.delay(500);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	private void inputEmail(String email) {
		WebElement txtBoxEmail = waitForElementClickable(By.xpath("//input[@placeholder='Email']"));
		txtBoxEmail.sendKeys(email);
	}

	private void pressSend() {
		WebElement btnSend = waitForElementClickable(By.id("btnSendCmtNoLogin"));
		btnSend.click();
	}

	private String getErrorTextCommentContent() {
		waitForTextElement(By.id("content"));
		WebElement txtBoxComment = getElement(By.id("content"));
		String errorText = txtBoxComment.getText();
		return errorText;
	}

	private String getAlertMessage() {
		waitForAlert();
		String alertMessage = driver.switchTo().alert().getText();
		this.driver.switchTo().alert().accept();
		return alertMessage;
	}

	private void waitForAlert() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	private void waitForTextElement(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.findElement(locator).getText().trim().length() != 0;
			}
		});
	}

	private WebElement waitForElementClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	private WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		return element;
	}
}
