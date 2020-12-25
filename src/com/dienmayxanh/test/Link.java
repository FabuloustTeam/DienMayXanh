package com.dienmayxanh.test;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.dienmayxanh.service.*;

import com.dienmayxanh.abstractclass.*;

public class Link extends AbstractAnnotation {
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
	private WebElement waitForElementClickable(By locator) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(this.driver, 10);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
}