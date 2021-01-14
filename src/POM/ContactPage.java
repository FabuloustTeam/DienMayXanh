package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AbstractClasses.AbstractBOT;

public class ContactPage extends AbstractBOT{
	By web = By.xpath("//*[@href='/lien-he']");
	By content = By.xpath("//*[@id='message']");
	By name = By.xpath("//*[@id='fullname']");
	By phone = By.xpath("//*[@id='contel']");
	By email = By.xpath("//*[@id='conemail']"); 
	By radiobutton = By.xpath("//*[text()=' Chị']");
	By submit = By.xpath("//*[@id='submit']");
	
	public void clickWeb () {
		waitForElementClickable(web).click();
	}
	public void setName (String strName) {
		waitForElementClickable(name).sendKeys(strName);
	}
	public void setContent (String strContent) {
		waitForElementClickable(content).sendKeys(strContent);;
	}
	public void setPhone (String strPhone) {
		waitForElementClickable(phone).sendKeys(strPhone);
	}
	public void setEmail (String strEmail) {
		waitForElementClickable(email).sendKeys(strEmail);
	}
	public void clickRadioButton () {
		waitForElementClickable(radiobutton).click();
	}
	
	public void sendContact(String strName, String strContent, String strPhone, String strEmail) {
		this.clickWeb();
		this.setContent(strContent);
		this.setName(strName);
		this.clickRadioButton();
		this.setPhone(strPhone);
		this.setEmail(strEmail);
		
	}
	
	public WebElement waitForElementClickable(final By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));

	}
	
}
