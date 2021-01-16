package com.dienmayxanh.page;

import org.openqa.selenium.By;
import com.dienmayxanh.abstractclass.*;
import com.dienmayxanh.service.Element;

public class LogInPage extends AbstractAnnotation {
	By web = By.xpath("//a[@href='/lich-su-mua-hang']");
	By phoneNumber = By.name("txtPhoneNumber");
	By password = By.xpath("//input[@name='txtOTP']");
	By loginPhone = By.xpath("//*[@id=\"frmGetVerifyCode\"]/button");
	By loginOTP = By.xpath("//*[@id=\"frmSubmitVerifyCode\"]/button");
	By messagePhone = By.xpath("//*[@name='txtPhoneNumber']//following::label[1]");
	By messageOTP = By.xpath("//*[@name='txtOTP']//following::label[1]");
	By messageResendOTP = By.xpath("//a[@class='resend-sms']");
	By titleResendOTP = By.xpath("//a[@class='resend-sms']");

	private Element elementService;

	public LogInPage() {
		elementService = new Element();
	}

	public void setPhoneNumber(String strPhoneNumber) {
		elementService.waitForElementClickable(phoneNumber).sendKeys(strPhoneNumber);
	}

	public void setOTP(String strOtp) {
		elementService.waitForElementClickable(password).sendKeys(strOtp);
	}

	public void clickWeb() {
		elementService.waitForElementClickable(web).click();
	}

	public void clickLoginPhone() {
		elementService.waitForElementClickable(loginPhone).click();
	}

	public void clickLoginOTP() {
		elementService.waitForElementClickable(loginOTP).click();
	}

	public void clickResendOTP() {
		elementService.waitForElementClickable(messageResendOTP).click();
	}

	public String getMessagePhone() {
		return elementService.waitForElementClickable(messagePhone).getText();
	}

	public String getMessageOTP() {
		return elementService.waitForElementClickable(messageOTP).getText();
	}

	public String getMessageResend() {
		return elementService.waitForElementClickable(titleResendOTP).getText();
	}

}