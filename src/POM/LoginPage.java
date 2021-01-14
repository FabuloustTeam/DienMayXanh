package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AbstractClass.abstractLogin;

public class LoginPage extends abstractLogin{
	By web = By.xpath("//a[@href='/lich-su-mua-hang']");
	By phoneNumber = By.name("txtPhoneNumber");
	By password = By.xpath("//input[@name='txtOTP']");
	By loginPhone = By.xpath("//*[@id=\"frmGetVerifyCode\"]/button");
	By loginOTP = By.xpath("//*[@id=\"frmSubmitVerifyCode\"]/button");
	By messagePhone = By.xpath("//*[@name='txtPhoneNumber']//following::label[1]");
	By messageOTP = By.xpath("//*[@name='txtOTP']//following::label[1]");
	By messageResendOTP = By.xpath("//a[@class='resend-sms']");
	By titleResendOTP = By.xpath("//a[@class='resend-sms']");
	
	public void setPhoneNumber(String strPhoneNumber) {
		waitForElementClickable(phoneNumber).sendKeys(strPhoneNumber);
	}
	
	public void setOTP(String strOtp) {
		waitForElementClickable(password).sendKeys(strOtp);
	}
	
	public void clickWeb() {
		waitForElementClickable(web).click();
	}
	
	public void clickLoginPhone() {
		waitForElementClickable(loginPhone).click();
	}
	
	public void clickLoginOTP() {
		waitForElementClickable(loginOTP).click();
	}
	
	public void clickResendOTP() {
		waitForElementClickable(messageResendOTP).click();
	}
	
	public String getMessagePhone() {
		return waitForElementClickable(messagePhone).getText();
	}
	
	public String getMessageOTP() {
		return waitForElementClickable(messageOTP).getText();
	}
	
	public String getMessageResend() {
		return waitForElementClickable(titleResendOTP).getText();
	}
	
	public void loginPhone(String strPhoneNumber){
		
		this.clickWeb();

        this.setPhoneNumber(strPhoneNumber);

        this.clickLoginPhone();
        
        this.getMessagePhone();
    }
	
	public void loginOTP(String strPhoneNumber, String strOtp){
		
		this.clickWeb();

        this.setPhoneNumber(strPhoneNumber);

        this.clickLoginPhone();
        
        this.setOTP(strOtp);
        
        this.clickLoginOTP();
        
        this.getMessageOTP();
        
    }
	
	public void resendOTP(String strPhoneNumber){
		
		this.clickWeb();

        this.setPhoneNumber(strPhoneNumber);

        this.clickResendOTP();
        
        this.getMessageResend();
    }
	private WebElement waitForElementClickable(By locator) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
}

