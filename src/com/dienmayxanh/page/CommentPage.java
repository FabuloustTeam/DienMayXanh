package com.dienmayxanh.page;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.dienmayxanh.abstractclass.*;
import com.dienmayxanh.service.*;

public class CommentPage extends AbstractAnnotation {
	
	By linkCommentPageLocator = By.xpath("//footer//a[text()='Gửi góp ý, khiếu nại']");
	By maskCommentLocator = By.xpath("//textarea[@placeholder='Mời bạn thảo luận, vui lòng nhập tiếng Việt có dấu']");
	By txtBoxCommentLocator = By.id("content");
	By txtBoxNameLocator = By.xpath("//input[@placeholder='Họ tên (bắt buộc)']");
	By attachImageLocator = By.xpath("//div[@class='attach']");
	By txtBoxEmailLocator = By.xpath("//input[@placeholder='Email']");
	By btnSendLocator = By.id("btnSendCmtNoLogin");
	private Element elementService;
	
	public CommentPage() {
		elementService = new Element();
	}
	
	public void goToSendCommentPage() {
		WebElement linkCommentPage = elementService.waitForElementClickable(linkCommentPageLocator);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		linkCommentPage.click();
	}

	public void inputComment(String comment) throws AWTException, InterruptedException {
		WebElement maskComment = elementService.waitForElementClickable(maskCommentLocator);
		
		Thread.sleep(5000);
		Actions builder = new Actions(driver);
		builder.moveToElement(maskComment, 1, 1).click().build().perform();

		WebElement txtBoxComment = elementService.waitForElementClickable(txtBoxCommentLocator);
		txtBoxComment.sendKeys(comment);
	}

	public void inputName(String name) {
		WebElement txtBoxName = elementService.waitForElementClickable(txtBoxNameLocator);
		txtBoxName.sendKeys(name);
	}

	public void inputImage(String fileName) throws AWTException, InterruptedException {
		WebElement attachImage = elementService.waitForElementClickable(attachImageLocator);
		attachImage.click();
		Thread.sleep(1000);
		uploadFile(fileName);
	}

	public void uploadFile(String fileName) throws AWTException, InterruptedException {
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

	public void inputEmail(String email) {
		WebElement txtBoxEmail = elementService.waitForElementClickable(txtBoxEmailLocator);
		txtBoxEmail.sendKeys(email);
	}

	public void pressSend() {
		WebElement btnSend = elementService.waitForElementClickable(btnSendLocator);
		btnSend.click();
	}

	public String getErrorTextCommentContent() {
		waitForTextElement(txtBoxCommentLocator);
		WebElement txtBoxComment = elementService.getElement(txtBoxCommentLocator);
		String errorText = txtBoxComment.getText();
		return errorText;
	}

	public void waitForTextElement(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.findElement(locator).getText().trim().length() != 0;
			}
		});
	}

}
