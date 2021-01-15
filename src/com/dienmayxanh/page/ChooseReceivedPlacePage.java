package com.dienmayxanh.page;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dienmayxanh.abstractclass.AbstractAnnotation;

public class ChooseReceivedPlacePage extends AbstractAnnotation {

	By chooseReceivedPlaceBox = By.xpath("//div[@class='provinces-box']//child::span");
	
	By dropBoxProvince = By.id("location_listPro");
	By allListProvince = By.xpath("//div[@class='flex']//ul");
	
	By dropBoxDistrict = By.id("location_listDistrict");
	By allListDistrict = By.id("lstDistrict");
	By styleBoxDistrict = By.xpath("//div[@class='boxprov__listTT--scroll']");
	
	By dropBoxWard = By.id("location_listWard");
	By allListWard = By.id("lstWard");
	By errorWard = By.className("errWard");
	
	By textBoxAddress = By.id("locationAddress");
	
	By buttonSubmit = By.id("lc_btn-Confirm");
	By buttonChangeLocation = By.xpath("//a[@id='lc_btn-changeLc']");
	
	By list = By.tagName("li");
	By eachItem = By.tagName("a");
	
	public List<WebElement> getList(By locator){
		return waitForElementVisible(locator).findElements(list);
	}
	
	public List<WebElement> getStyleWard(){
		return driver.findElements(styleBoxDistrict);
	}
	public void chooseProvinceBox() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		waitForElementVisible(chooseReceivedPlaceBox).click();
	}

	public void fillAddress(String address) {
		waitForElementClickable(textBoxAddress).sendKeys(address);
	}

	public void clickOnWardDropBox() {
		waitForElementClickable(dropBoxWard).click();
	}

	public void submitForm() throws InterruptedException {
		waitForElementClickable(buttonSubmit).click();
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public void chooseProvince(String province) throws InterruptedException {
		waitForElementVisible(dropBoxProvince).click();
		Thread.sleep(1000);
		for (int i = 0; i < getList(allListProvince).size(); i++) {
			if (getList(allListProvince).get(i).findElement(eachItem).getText().contentEquals(province)) {
				getList(allListProvince).get(i).click();
				break;
			}
		}
	}

	public void clickOnDistrictDropBox() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String getStyle = getStyleWard().get(2).getAttribute("style");
		if (getStyle.equals("display: none;") || getStyle.equals("")) {
			waitForElementClickable(dropBoxDistrict).click();
		}
	}
	public void chooseDistrict(String district) throws InterruptedException {
		clickOnDistrictDropBox();
		//waitForElementClickable(dropBoxDistrict).click();
		for (int i = 0; i < getList(allListDistrict).size(); i++) {
			if (getList(allListDistrict).get(i).findElement(eachItem).getText().contentEquals(district)) {
				getList(allListDistrict).get(i).click();
				break;
			}
		}
	}

	public void chooseWard(String ward) {
		for (int i = 0; i < getList(allListWard).size(); i++) {
			if (getList(allListWard).get(i).findElement(eachItem).getText().contentEquals(ward)) {
				getList(allListWard).get(i).click();
				break;
			}
		}
	}

	public void changeInforOnTextBox(String address) throws AWTException, InterruptedException {
		waitForElementClickable(textBoxAddress).click();
		final Robot robot = new Robot();
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_A);
		waitForElementVisible(textBoxAddress).sendKeys(address);
	}

	public void clickButtonChangeLc() {
		waitForElementClickable(buttonChangeLocation).click();
	}

	public String getActualResult() throws InterruptedException {
		// driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(1000);
		String actualResult = (waitForElementVisible(chooseReceivedPlaceBox)).getText();
		return actualResult;
	}

	public String getActualErrorWard() throws InterruptedException {
		String actualErr = (waitForElementVisible(errorWard)).getText();
		return actualErr;
	}

	public WebElement waitForElementClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public WebElement waitForElementVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

}