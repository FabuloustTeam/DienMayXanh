package com.dienmayxanh.test;

import com.dienmayxanh.abstractclass.*;
import com.dienmayxanh.service.ExcelUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import com.dienmayxanh.Enum.*;

public class Comment extends AbstractAnnotation {

	private final int COL_CASE = 2;
	private final int COL_EXPECT = 3;
	private final int COL_RESULT = 4;
	private final int COL_INPUT_COMMENT = 5;
	private final int COL_INPUT_NAME = 6;
	private final int COL_INPUT_IMAGE = 7;
	private final int COL_INPUT_EMAIL = 8;
	private final int COL_TYPE = 9;
	private final int START_ROW = 2;
	private final int COL_TESTNAME = 1;
	private int iTestCaseRow;
	private String caseValue = "";
	private String typeValue = "";
	private String comment = "";
	private String name = "";
	private String image = "";
	private String email = "";
	private String actual = "";
	private String expected = "";

	@Parameters({ "url" })
	@Test(priority = 1)
	public void CommentUnsuccessfullyWithError(String url) throws Exception {
		iTestCaseRow = ExcelUtils.getRowUsed();
		for(int i = START_ROW; i <= iTestCaseRow; i++) {
			caseValue = ExcelUtils.getCellData(i, COL_CASE);
			typeValue = ExcelUtils.getCellData(i, COL_TYPE);
			if(caseValue.equals(Case.UNSUCCESSFULLY.toString()) && typeValue.equals(Type.ERROR.toString())) {
				goToSendCommentPage();
				
				comment = ExcelUtils.getCellData(i, COL_INPUT_COMMENT);
				inputComment(comment);
				
				name = ExcelUtils.getCellData(i, COL_INPUT_NAME);
				inputName(name);
				
				image = ExcelUtils.getCellData(i, COL_INPUT_IMAGE);
				inputImage(image);
				
				email = ExcelUtils.getCellData(i, COL_INPUT_EMAIL);
				inputEmail(email);
				
				pressSend();
				
				while(true) {
					try {
						actual = getErrorTextCommentContent();
						if(!actual.equals("")) 
							break;
					} catch (Exception e) {
						pressSend();
					}
				}
				
				expected = ExcelUtils.getCellData(i, COL_EXPECT);
				
				ITestResult result = Reporter.getCurrentTestResult();
				result.setAttribute("testname", ExcelUtils.getCellData(i, COL_TESTNAME));
				if(actual.equals(expected)) {
					ExcelUtils.setCellData(i, COL_RESULT, Result.PASSED.toString());
				} else {
					ExcelUtils.setCellData(i, COL_RESULT, Result.FAILED.toString());
				}
				
				Robot robo = new Robot();
				robo.keyPress(KeyEvent.VK_F5);
				robo.keyRelease(KeyEvent.VK_F5);
				waitForPageLoad();
			}
		}
	}
	
	@Parameters({ "url" })
	@Test(priority = 2)
	public void CommentUnsuccessfullyWithAlert(String url) throws Exception {
		iTestCaseRow = ExcelUtils.getRowUsed();
		for(int i = START_ROW; i <= iTestCaseRow; i++) {
			caseValue = ExcelUtils.getCellData(i, COL_CASE);
			typeValue = ExcelUtils.getCellData(i, COL_TYPE);
			if(caseValue.equals(Case.UNSUCCESSFULLY.toString()) && typeValue.equals(Type.ALERT.toString())) {
				goToSendCommentPage();
				
				comment = ExcelUtils.getCellData(i, COL_INPUT_COMMENT);
				inputComment(comment);
				
				name = ExcelUtils.getCellData(i, COL_INPUT_NAME);
				inputName(name);
				
				image = ExcelUtils.getCellData(i, COL_INPUT_IMAGE);
				inputImage(image);
				
				email = ExcelUtils.getCellData(i, COL_INPUT_EMAIL);
				inputEmail(email);
				
				pressSend();
				
				actual = getAlertMessage();
				expected = ExcelUtils.getCellData(i, COL_EXPECT);
				
				ITestResult result = Reporter.getCurrentTestResult();
				result.setAttribute("testname", ExcelUtils.getCellData(i, COL_TESTNAME));
				if(actual.equals(expected)) {
					ExcelUtils.setCellData(i, COL_RESULT, Result.PASSED.toString());
				} else {
					ExcelUtils.setCellData(i, COL_RESULT, Result.FAILED.toString());
				}
				
				Robot robo = new Robot();
				robo.keyPress(KeyEvent.VK_F5);
				robo.keyRelease(KeyEvent.VK_F5);
				waitForPageLoad();
			}
		}
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

	private void inputImage(String fileName) throws AWTException, InterruptedException {
		WebElement attachImage = waitForElementClickable(By.xpath("//div[@class='attach']"));
		attachImage.click();
		Thread.sleep(1000);
		uploadFile(fileName);
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
		driver.switchTo().alert().accept();
		return alertMessage;
	}

	private void waitForAlert() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	private void waitForTextElement(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
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
	
	private void waitForPageLoad() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
}
