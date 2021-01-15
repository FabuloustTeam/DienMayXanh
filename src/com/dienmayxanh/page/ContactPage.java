package com.dienmayxanh.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.dienmayxanh.Enum.Type;
import com.dienmayxanh.abstractclass.AbstractAnnotation;
import com.dienmayxanh.service.Element;

public class ContactPage extends AbstractAnnotation {

	By webContact = By.xpath("//*[@href='/lien-he']");
	By content = By.xpath("//*[@id='message']");
	By name = By.xpath("//*[@id='fullname']");
	By phone = By.xpath("//*[@id='contel']");
	By email = By.xpath("//*[@id='conemail']");
//	By radiobutton = By.xpath("//*[text()=' Chị']");
	By submit = By.xpath("//*[@id='submit']");
	By error = By.id("errsubmitcon");
	
	private Element elementService;
	public String actual;

	public ContactPage() {
		elementService = new Element();
	}

	public void clickWebContact() {
		elementService.waitForElementClickable(webContact).click();
	}

	public void setName(String strName) {
		elementService.waitForElementClickable(name).sendKeys(strName);
	}

	public void setContent(String strContent) {
		elementService.waitForElementClickable(content).sendKeys(strContent);
		;
	}

	public void setPhone(String strPhone) {
		elementService.waitForElementClickable(phone).sendKeys(strPhone);
	}

	public void setEmail(String strEmail) {
		elementService.waitForElementClickable(email).sendKeys(strEmail);
	}

	public void clickRadioButton(String gender) {
		elementService.waitForElementClickable(By.xpath("//*[text()=' "+gender+"']")).click();
	}
	
	public void submit(String typeValue) {
		WebElement btnSubmit = driver.findElement(submit);
		if(typeValue.equals(Type.Alert.toString())) {
			btnSubmit.click();
			elementService.waitForAlert();
			getActual(typeValue);
			driver.switchTo().alert().accept();
		} else {
			btnSubmit.click();
			getActual(typeValue);
		}
	}
	
	private String getActual(String typeValue) {
		if(typeValue.equals(Type.Alert.toString())) {
			actual = driver.switchTo().alert().getText();
		} else {
			actual = driver.findElement(error).getText();
		}
		return actual;
	}

}
