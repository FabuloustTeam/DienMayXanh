package com.dienmayxanh.testcases;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.dienmayxanh.abstractclass.AbstractAnnotation;

public class Link extends AbstractAnnotation {

	/**
	 * Test Requirement: TR-DMX-Link-01. TestCaseID: TC-DMX-Link-01
	 */
	@Test (priority=1)
	public void LinkFacebook() {
		// Cuộn xuống cuối trang
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		// Nhấn chọn liên kết trang Facebook.
		waitForElementClickable(By.xpath("//*[(@class='linkfb')]")).click();
		String MainWindow = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();
		while (i1.hasNext()) {
			String ChildWindow = i1.next();

			if (!MainWindow.equalsIgnoreCase(ChildWindow)) {

				driver.switchTo().window(ChildWindow);
				String expectedTitle = "Điện máy XANH (dienmayxanh.com) - Trang chủ | Facebook";
				String actualTitle = driver.getTitle();
				Assert.assertEquals(actualTitle, expectedTitle);

				driver.close();
			}
		}
		driver.switchTo().window(MainWindow);
	}
//	
	/**
	 * Test Requirement: TR-DMX-Link-01. TestCaseID: TC-DMX-Link-02
	 */
	@Test (priority=2)
	public void LinkYoutube() {
		// Cuộn xuống cuối trang
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		// Nhấn chọn liên kết trang YouTube.
		waitForElementClickable(By.xpath("//*[(@class='linkyt')]")).click();
		String MainWindow = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();
		while (i1.hasNext()) {
			String ChildWindow = i1.next();

			if (!MainWindow.equalsIgnoreCase(ChildWindow)) {

				driver.switchTo().window(ChildWindow);
				String expectedTitle = "Điện máy XANH - YouTube";
				String actualTitle = driver.getTitle();
				Assert.assertEquals(actualTitle, expectedTitle);

				driver.close();
			}
		}
		driver.switchTo().window(MainWindow);
	}
	
	/**
	 * Test Requirement: TR-DMX-Link-01. TestCaseID: TC-DMX-Link-03
	 */
	@Test (priority=3)
	public void LinkMaiAm() {
		// Cuộn xuống cuối trang
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		// Nhấn chọn liên kết trang Mái Ấm.
		waitForElementClickable(By.xpath("//*[(@class='icondmx-logomaiam')]")).click();
		String MainWindow = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();
		while (i1.hasNext()) {
			String ChildWindow = i1.next();

			if (!MainWindow.equalsIgnoreCase(ChildWindow)) {

				driver.switchTo().window(ChildWindow);
				String expectedTitle = "Trang chủ - Mái Ấm TGDD";
				String actualTitle = driver.getTitle();
				Assert.assertEquals(actualTitle, expectedTitle);

				driver.close();
			}
		}
		driver.switchTo().window(MainWindow);
	}
	
	/**
	 * Test Requirement: TR-DMX-Link-01. TestCaseID: TC-DMX-Link-04
	 */
	@Test (priority=4)
	public void LinkTgdd() {
		// Cuộn xuống cuối trang
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		// Nhấn chọn liên kết trang thegioididong.
		waitForElementClickable(By.xpath("//*[(@class='icondmx-logotgdd')]")).click();
		String MainWindow = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();
		while (i1.hasNext()) {
			String ChildWindow = i1.next();

			if (!MainWindow.equalsIgnoreCase(ChildWindow)) {

				driver.switchTo().window(ChildWindow);
				String expectedTitle = "Thegioididong.com - Điện thoại, Laptop, Phụ kiện, Đồng hồ chính hãng";
				String actualTitle = driver.getTitle();
				Assert.assertEquals(actualTitle, expectedTitle);

				driver.close();
			}
		}
		driver.switchTo().window(MainWindow);
	}
	
	/**
	 * Test Requirement: TR-DMX-Link-01. TestCaseID: TC-DMX-Link-05
	 */
	@Test (priority=5)
	public void LinkBachhoaXanh() {
		// Cuộn xuống cuối trang
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		// Nhấn chọn liên kết trang Bách hóa XANH.
		waitForElementClickable(By.xpath("//*[(@class='icondmx-logobhx')]")).click();
		String MainWindow = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();
		while (i1.hasNext()) {
			String ChildWindow = i1.next();

			if (!MainWindow.equalsIgnoreCase(ChildWindow)) {

				driver.switchTo().window(ChildWindow);
				String expectedTitle = "Siêu thị Bách hoá XANH - Mua bán thực phẩm, sản phẩm gia đình";
				String actualTitle = driver.getTitle();
				Assert.assertEquals(actualTitle, expectedTitle);

				driver.close();
			}
		}
		driver.switchTo().window(MainWindow);
	}
	
	private WebElement waitForElementClickable(By locator) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(this.driver, 10);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
}