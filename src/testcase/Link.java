package testcase;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import BaseClass.ExcelUtils;

import AbstractClass.abstractLink;

public class Link extends abstractLink {
	public static final int COL_TESTTYPE = 0;
	public static final int COL_TESTNAME = 1;
	public static final int COL_CASE = 2;
	public static final int COL_EXPRESULT = 3;
	public static final int COL_RESULT = 4;
	public static final int COL_LINK=5;
	

	public static final int iTestBeginRow = 2;
	public static int iTestCaseRow, rowData;
	public String linkInput;

	
	@Test
	public void testLink() throws Exception {
		iTestCaseRow = ExcelUtils.getRowUsed();
		for(int i = iTestBeginRow; i<iTestCaseRow; i++) {
			if (ExcelUtils.getCellData(i, COL_TESTNAME).equals("testLink")
					&& ExcelUtils.getCellData(i, COL_CASE).equals("SUCCESSFULLY")) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
				// Nhấn chọn liên kết trang Facebook.
				waitForElementClickable(By.xpath("//li//a[@href='"+ExcelUtils.getCellData(i, COL_LINK)+"']")).click();
				String MainWindow = driver.getWindowHandle();
				Set<String> s1 = driver.getWindowHandles();
				Iterator<String> i1 = s1.iterator();
				while (i1.hasNext()) {
					String ChildWindow = i1.next();

					if (!MainWindow.equalsIgnoreCase(ChildWindow)) {

						driver.switchTo().window(ChildWindow);
						String expected = ExcelUtils.getCellData(i, COL_EXPRESULT);
						String actual = driver.getTitle();
						Assert.assertEquals(actual, expected);

						if (actual.equals(expected)) {
							ExcelUtils.setCellData(i, COL_RESULT, "PASSED");
						} else {
							ExcelUtils.setCellData(i, COL_RESULT, "FAILED");
						}
						driver.close();
					}
				}
				driver.switchTo().window(MainWindow);
			}
		}
	}
	/**
	 * Test Requirement: TR-DMX-Link-01. TestCaseID: TC-DMX-Link-01
	 * 
	 * @throws Exception
	 */
	@Test(priority = 1)
	public void testLinkfb() throws Exception {
		// Cuộn xuống cuối trang
		iTestCaseRow = ExcelUtils.getRowUsed();
		for (int i = iTestBeginRow; i <= iTestCaseRow; i++) {
			if (ExcelUtils.getCellData(i, COL_TESTNAME).equals("LinkFB")
					&& ExcelUtils.getCellData(i, COL_CASE).equals("SUCCESSFULLY")) {
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
						String expected = ExcelUtils.getCellData(i, COL_EXPRESULT);
						String actual = driver.getTitle();
						Assert.assertEquals(actual, expected);

						if (actual.equals(expected)) {
							ExcelUtils.setCellData(i, COL_RESULT, "PASSED");
						} else {
							ExcelUtils.setCellData(i, COL_RESULT, "FAILED");
						}
						driver.close();
					}
				}
				driver.switchTo().window(MainWindow);
			}
		}
	}

//	
	/**
	 * Test Requirement: TR-DMX-Link-01. TestCaseID: TC-DMX-Link-02
	 * 
	 * @throws Exception
	 */
	@Test(priority = 2)
	public void testLinkYoutube() throws Exception {
		// Cuộn xuống cuối trang
		iTestCaseRow = ExcelUtils.getRowUsed();
		for (int i = iTestBeginRow; i <= iTestCaseRow; i++) {
			if (ExcelUtils.getCellData(i, COL_TESTNAME).equals("LinkYT")
					&& ExcelUtils.getCellData(i, COL_CASE).equals("SUCCESSFULLY")) {
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
						String expected = ExcelUtils.getCellData(i, COL_EXPRESULT);
						String actual = driver.getTitle();
						Assert.assertEquals(actual, expected);

						if (actual.equals(expected)) {
							ExcelUtils.setCellData(i, COL_RESULT, "PASSED");
						} else {
							ExcelUtils.setCellData(i, COL_RESULT, "FAILED");
						}

						driver.close();
					}
				}
				driver.switchTo().window(MainWindow);
			}
		}
	}

	/**
	 * Test Requirement: TR-DMX-Link-01. TestCaseID: TC-DMX-Link-03
	 * 
	 * @throws Exception
	 */
	@Test(priority = 3)
	public void testLinkMaiAm() throws Exception {
		// Cuộn xuống cuối trang
		iTestCaseRow = ExcelUtils.getRowUsed();
		for (int i = iTestBeginRow; i <= iTestCaseRow; i++) {
			if (ExcelUtils.getCellData(i, COL_TESTNAME).equals("LinkMA")
					&& ExcelUtils.getCellData(i, COL_CASE).equals("SUCCESSFULLY")) {
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
						String expected = ExcelUtils.getCellData(i, COL_EXPRESULT);
						String actual = driver.getTitle();
						Assert.assertEquals(actual, expected);

						if (actual.equals(expected)) {
							ExcelUtils.setCellData(i, COL_RESULT, "PASSED");
						} else {
							ExcelUtils.setCellData(i, COL_RESULT, "FAILED");
						}

						driver.close();
					}
				}
				driver.switchTo().window(MainWindow);
			}
		}
	}

	/**
	 * Test Requirement: TR-DMX-Link-01. TestCaseID: TC-DMX-Link-04
	 * 
	 * @throws Exception
	 */
	@Test(priority = 4)
	public void testLinkTgdd() throws Exception {
		// Cuộn xuống cuối trang
		iTestCaseRow = ExcelUtils.getRowUsed();
		for (int i = iTestBeginRow; i <= iTestCaseRow; i++) {
			if (ExcelUtils.getCellData(i, COL_TESTNAME).equals("LinkTGDD")
					&& ExcelUtils.getCellData(i, COL_CASE).equals("SUCCESSFULLY")) {
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
						String expected = ExcelUtils.getCellData(i, COL_EXPRESULT);
						String actual = driver.getTitle();
						Assert.assertEquals(actual, expected);

						if (actual.equals(expected)) {
							ExcelUtils.setCellData(i, COL_RESULT, "PASSED");
						} else {
							ExcelUtils.setCellData(i, COL_RESULT, "FAILED");
						}

						driver.close();
					}
				}
				driver.switchTo().window(MainWindow);
			}
		}
	}

	/**
	 * Test Requirement: TR-DMX-Link-01. TestCaseID: TC-DMX-Link-05
	 * 
	 * @throws Exception
	 */
	@Test(priority = 5)
	public void testLinkBachhoaXanh() throws Exception {
		// Cuộn xuống cuối trang
		iTestCaseRow = ExcelUtils.getRowUsed();
		for (int i = iTestBeginRow; i <= iTestCaseRow; i++) {
			if (ExcelUtils.getCellData(i, COL_TESTNAME).equals("LinkBHX")
					&& ExcelUtils.getCellData(i, COL_CASE).equals("SUCCESSFULLY")) {
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
						String expected = ExcelUtils.getCellData(i, COL_EXPRESULT);
						String actual = driver.getTitle();
						Assert.assertEquals(actual, expected);

						if (actual.equals(expected)) {
							ExcelUtils.setCellData(i, COL_RESULT, "PASSED");
						} else {
							ExcelUtils.setCellData(i, COL_RESULT, "FAILED");
						}

						driver.close();
					}
				}
				driver.switchTo().window(MainWindow);
			}
		}
	}

	public void getLink(String link) throws Exception {
		String path = System.getProperty("user.dir") + "\\DienmayXANH-TestData.xlsx";
		ExcelUtils.setExcelFile(path, "Link");
	}

	private WebElement waitForElementClickable(By locator) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(this.driver, 10);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
}
