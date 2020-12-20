package TestCases;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import AbstractAnnotation.*;
import ActionsForITestListener.*;
import Enum.*;

import static org.testng.Assert.expectThrows;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class ChooseReceivedPlace extends AbstractClass {
	public static final int COL_TESTTYPE = 0;
	public static final int COL_TESTNAME = 1;
	public static final int COL_CASE = 2;
	public static final int COL_EXPRESULT = 3;
	public static final int COL_RESULT = 4;
	public static final int COL_PROVINCE = 5;
	public static final int COL_DISTRICT = 6;
	public static final int COL_WARD = 7;
	public static final int COL_ADDRESS = 8;
	
	public static final int iTestBeginRow = 2;
	public static int iTestCaseRow, rowData;
	public static String actual, expected, province, district, ward, address, changeAddress, changeProvince;

	@BeforeMethod
	public void beforeMethod() throws InterruptedException {
		// step 2 Nhấn chọn vào địa chỉ nhận hàng hiển thị trên thanh Header
		WebElement provincesBox = waitForElementVisible(By.xpath("//div[@class='provinces-box']//child::span"));
		provincesBox.click();
	}

	/*
	 * Test requirement: TR-DMX-CNNH-01 - Test case: TC-DMX-CNNH-01
	 */
	@Test(priority = 1, groups = { "chooseReceivePlaceSuccess" })
	public void testMainSFWithFullInfor() throws Exception {
		
		rowData = ExcelUtils.getRowContains("testMainSFWithFullInfor", COL_TESTNAME);
		// step 3 Nhấn vào drop down list "Vui lòng chọn Quận/Huyện" và chọn địa chỉ
		// tương ứng
		district = ExcelUtils.getCellData(rowData, COL_DISTRICT);
		chooseDistrict(district);

		// step 4 Chọn Phường/Xã
		ward = ExcelUtils.getCellData(rowData, COL_WARD);
		chooseWard(ward);

		// step 5 Nhập vào textbox "Số nhà, tên đường"
		address = ExcelUtils.getCellData(rowData, COL_ADDRESS);
		fillAddress(address);

		// step 6 Nhấn Xác nhận và xem kết quả hiển thị
		submitForm();
		
		actual = getActualResult();
		expected = ExcelUtils.getCellData(rowData, COL_EXPRESULT);
		Assert.assertEquals(actual, expected);
		if(actual.equals(expected)) {
			ExcelUtils.setCellData(rowData, COL_RESULT, Result.PASSED.toString());
		}
		else {
			ExcelUtils.setCellData(rowData, COL_RESULT, Result.FAILED.toString());
		}

//		ITestResult result = Reporter.getCurrentTestResult();
//		result.setAttribute("actualResult", actualResult);
		

		//Assert.assertEquals(actualResult, expectedResult);
	}

	/*
	 * Test requirement: TR-DMX-CNNH-01 - Test case: TC-DMX-CNNH-02
	 */
	@Test(priority = 2, groups = { "chooseRecivePlaceSuccess" })
	public void testSuccessWithOnlyProvince() throws Exception {
		rowData = ExcelUtils.getRowContains("testSuccessWithOnlyProvince", COL_TESTNAME);
		// step 3 Chọn tỉnh thành
		province = ExcelUtils.getCellData(rowData, COL_PROVINCE);
		chooseProvince(province);
		
		// step 4 Nhấn Xác nhận và xem kết quả hiển thị
		submitForm();

		actual = getActualResult();
		expected = ExcelUtils.getCellData(rowData, COL_EXPRESULT);
		Assert.assertEquals(actual, expected);
		if(actual.equals(expected)) {
			ExcelUtils.setCellData(rowData, COL_RESULT, Result.PASSED.toString());
		}
		else {
			ExcelUtils.setCellData(rowData, COL_RESULT, Result.FAILED.toString());
		}
//		String actualR = getActualResult();
//		ITestResult result = Reporter.getCurrentTestResult();
//		result.setAttribute("actualResult", actualResult);
//		
//		expected = ExcelUtils.getCellData(rowData, COL_EXPRESULT);
		//Assert.assertEquals(actualResult, expectedResult);
	}

	/*
	 * Test requirement: TR-DMX-CNNH-01 - Test case: TC-DMX-CNNH-03
	 */
	@Test(priority = 3, groups = { "chooseReceivePlaceSuccess" })
	public void testSuccessWithoutFillTextboxAddress() throws Exception {
		rowData = ExcelUtils.getRowContains("testSuccessWithoutFillTextboxAddress", COL_TESTNAME);
		// step 3 Chọn tỉnh thành
		province = ExcelUtils.getCellData(rowData, COL_PROVINCE);
		chooseProvince(province);

		// step 4 Nhấn vào drop down list "Vui lòng chọn Quận/Huyện" và chọn địa chỉ
		// tương ứng
		district = ExcelUtils.getCellData(rowData, COL_DISTRICT);
		chooseDistrict(district);

		// step 5 Chọn Phường/Xã
		ward = ExcelUtils.getCellData(rowData, COL_WARD);
		chooseWard(ward);

		// step 6 Nhấn Xác nhận và xem kết quả hiển thị
		submitForm();

		actual = getActualResult();
		expected = ExcelUtils.getCellData(rowData, COL_EXPRESULT);
		Assert.assertEquals(actual, expected);
		if(actual.equals(expected)) {
			ExcelUtils.setCellData(rowData, COL_RESULT, Result.PASSED.toString());
		}
		else {
			ExcelUtils.setCellData(rowData, COL_RESULT, Result.FAILED.toString());
		}
	}

	/*
	 * Test requirement: TR-DMX-CNNH-02 - Test case: TC-DMX-CNNH-04
	 */
	@Test(priority = 4, groups = { "chooseReceivePlaceFail" })
	public void testFailWithoutChooseWard() throws Exception {
		rowData = ExcelUtils.getRowContains("testFailWithoutChooseWard", COL_TESTNAME);
		// step 3 Chọn tỉnh thành
		province = ExcelUtils.getCellData(rowData, COL_PROVINCE);
		chooseProvince(province);

		// step 4 Nhấn vào drop down list "Vui lòng chọn Quận/Huyện" và chọn địa chỉ
		// tương ứng
		district = ExcelUtils.getCellData(rowData, COL_DISTRICT);
		chooseDistrict(district);

		// step 5 Không chọn Phường/Xã
		clickOnWardDropBox();

		// step 6 Nhấn Xác nhận và xem kết quả hiển thị
		submitForm();
		
		actual = getActualErrorWard();
		expected = ExcelUtils.getCellData(rowData, COL_EXPRESULT);
		Assert.assertEquals(actual, expected);
		if(actual.equals(expected)) {
			ExcelUtils.setCellData(rowData, COL_RESULT, Result.PASSED.toString());
		}
		else {
			ExcelUtils.setCellData(rowData, COL_RESULT, Result.FAILED.toString());
		}
	}

	/**
	 * Test requirement: TR-DMX-CNNH-03 - Test case: TC-DMX-CNNH-05
	 * 
	 * @throws Exception
	 */

	@Test(priority = 5, groups = { "successUpdateReceivePlace" })
	public void testSuccessUpdateWithOnlyProvinceSubmitBefor() throws Exception {
		rowData = ExcelUtils.getRowContains("testSuccessUpdateWithOnlyProvinceSubmitBefor", COL_TESTNAME);
		// step 3 Chọn tỉnh thành
		province = ExcelUtils.getCellData(rowData, COL_PROVINCE);
		chooseProvince(province);

		// step 4 Nhấn Xác nhận
		submitForm();

		// step 5 Nhấn chọn vào địa chỉ nhận hàng hiển thị trên thanh Header
		chooseProvinceBox();

		// step 6 Nhấn vào drop down list "Vui lòng chọn Quận/Huyện" và chọn địa chỉ
		// tương ứng
		district = ExcelUtils.getCellData(rowData + 1, COL_DISTRICT);
		chooseDistrict(district);

		// step 7 Chọn Phường/Xã
		String ward = ExcelUtils.getCellData(rowData + 1, COL_WARD);
		chooseWard(ward);

		// step 8 Nhấn Xác nhận và xem kết quả hiển thị
		submitForm();

		actual = getActualResult();
		expected = ExcelUtils.getCellData(rowData, COL_EXPRESULT);
		Assert.assertEquals(actual, expected);
		if(actual.equals(expected)) {
			ExcelUtils.setCellData(rowData, COL_RESULT, Result.PASSED.toString());
		}
		else {
			ExcelUtils.setCellData(rowData, COL_RESULT, Result.FAILED.toString());
		}
	}

	/**
	 * Test requirement: TR-DMX-CNNH-03 - Test case: TC-DMX-CNNH-06
	 * 
	 * @throws Exception
	 */

	@Test(priority = 6, groups = { "successUpdateReceivePlace" })
	public void testSuccessUpdateByClickOnButonChange() throws Exception {
		rowData = ExcelUtils.getRowContains("testSuccessUpdateByClickOnButonChange", COL_TESTNAME);
		// step 3 Chọn tỉnh thành
		province = ExcelUtils.getCellData(rowData, COL_PROVINCE);
		chooseProvince(province);

		// step 4 Nhấn vào drop down list "Vui lòng chọn Quận/Huyện" và chọn địa chỉ
		// tương ứng
		district = ExcelUtils.getCellData(rowData, COL_DISTRICT);
		chooseDistrict(district);

		// step 5 Chọn Phường/Xã
		ward = ExcelUtils.getCellData(rowData, COL_WARD);
		chooseWard(ward);

		// step 6 Nhập vào textbox "Số nhà, tên đường"
		address = ExcelUtils.getCellData(rowData, COL_ADDRESS);
		fillAddress(address);

		// step 7 Nhấn Xác nhận và xem kết quả hiển thị
		submitForm();

		// step 8 Nhấn chọn vào địa chỉ nhận hàng hiển thị trên thanh Header
		chooseProvinceBox();

		// step 9 Nhấn chọn địa chỉ khác
		clickButtonChangeLc();

		// step 10 Thay đổi số nhà, tên đường
		changeAddress = ExcelUtils.getCellData(rowData + 1, COL_ADDRESS);
		changeInforOnTextBox(changeAddress);

		// step 11 sNhấn Xác nhận và xem kết quả hiển thị
		submitForm();

		actual = getActualResult();
		expected = ExcelUtils.getCellData(rowData, COL_EXPRESULT);
		Assert.assertEquals(actual, expected);
		if(actual.equals(expected)) {
			ExcelUtils.setCellData(rowData, COL_RESULT, Result.PASSED.toString());
		}
		else {
			ExcelUtils.setCellData(rowData, COL_RESULT, Result.FAILED.toString());
		}
	}

	/**
	 * Test requirement: TR-DMX-CNNH-03 - Test case: TC-DMX-CNNH-07
	 * 
	 * @throws Exception
	 */
	@Test(priority = 7, groups = { "successUpdateReceivePlace" })
	public void testSuccessUpdateByClickOnDropBoxProvince() throws Exception {
		rowData = ExcelUtils.getRowContains("testSuccessUpdateByClickOnDropBoxProvince", COL_TESTNAME);
		// step 3 Chọn tỉnh thành
		province = ExcelUtils.getCellData(rowData, COL_PROVINCE);
		chooseProvince(province);

		// step 4 Nhấn vào drop down list "Vui lòng chọn Quận/Huyện" và chọn địa chỉ
		// tương ứng
		district = ExcelUtils.getCellData(rowData, COL_DISTRICT);
		chooseDistrict(district);

		// step 5 Chọn Phường/Xã
		ward = ExcelUtils.getCellData(rowData, COL_WARD);
		chooseWard(ward);

		// step 6 Nhập vào textbox "Số nhà, tên đường"
		address = ExcelUtils.getCellData(rowData, COL_ADDRESS);
		fillAddress(address);

		// step 7 Nhấn Xác nhận và xem kết quả hiển thị
		submitForm();

		// step 8 cNhấn chọn vào địa chỉ nhận hàng hiển thị trên thanh Header
		chooseProvinceBox();

		// step 9 Nhấn chọn tình thành khác ở dropdown list tỉnh thành
		changeProvince = ExcelUtils.getCellData(rowData+1, COL_PROVINCE);
		chooseProvince(changeProvince);

		// step 10 Nhấn Xác nhận và xem kết quả hiển thị
		submitForm();

		actual = getActualResult();
		expected = ExcelUtils.getCellData(rowData, COL_EXPRESULT);
		Assert.assertEquals(actual, expected);
		if(actual.equals(expected)) {
			ExcelUtils.setCellData(rowData, COL_RESULT, Result.PASSED.toString());
		}
		else {
			ExcelUtils.setCellData(rowData, COL_RESULT, Result.FAILED.toString());
		}
	}

	private void chooseProvinceBox() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement provincesBox = waitForElementVisible(By.xpath("//div[@class='provinces-box']//child::span"));
		provincesBox.click();
	}

	private void fillAddress(String address) {
		WebElement fillAddress = waitForElementClickable(By.id("locationAddress"));
		fillAddress.sendKeys(address);
	}

	private void clickOnWardDropBox() {
		WebElement dropboxWard = waitForElementClickable(By.id("location_listWard"));
		dropboxWard.click();
	}

	private void submitForm() throws InterruptedException {
		WebElement submit = waitForElementClickable(By.id("lc_btn-Confirm"));
		submit.click();
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	private void chooseProvince(String province) throws InterruptedException {
		WebElement setProvince = waitForElementVisible(By.id("location_listPro"));
		setProvince.click();
		Thread.sleep(1000);
		WebElement divContainsUl = waitForElementVisible(By.xpath("//div[@class='flex']//ul"));
		List<WebElement> lis = divContainsUl.findElements(By.tagName("li"));
		for (int i = 0; i < lis.size(); i++) {
			if (lis.get(i).findElement(By.tagName("a")).getText().contentEquals(province)) {
				lis.get(i).click();
				break;
			}
		}
	}

	private void chooseDistrict(String district) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		List<WebElement> scrollboxDistrict = driver.findElements(By.xpath("//div[@class='boxprov__listTT--scroll']"));
		String getStyle = scrollboxDistrict.get(2).getAttribute("style");
		if (getStyle.equals("display: none;") || getStyle.equals("")) {
			WebElement setDistrict = waitForElementClickable(By.id("location_listDistrict"));
			setDistrict.click();
		}
		WebElement divContainsLi = waitForElementVisible(By.id("lstDistrict"));
		List<WebElement> lis = divContainsLi.findElements(By.xpath("//div[@id='lstDistrict']//child::li"));
		for (int i = 0; i < lis.size(); i++) {
			if (lis.get(i).findElement(By.tagName("a")).getText().contentEquals(district)) {
				lis.get(i).click();
				break;
			}
		}
	}

	private void chooseWard(String ward) {
		WebElement divContainsLi = waitForElementVisible(By.id("lstWard"));
		List<WebElement> lis = divContainsLi.findElements(By.xpath("//div[@id='lstWard']//child::li"));
		for (int i = 0; i < lis.size(); i++) {
			if (lis.get(i).findElement(By.tagName("a")).getText().contentEquals(ward)) {
				lis.get(i).click();
				break;
			}
		}
	}

	private void changeInforOnTextBox(String address) throws AWTException, InterruptedException {
		WebElement locationTextBox = waitForElementClickable(By.id("locationAddress"));
		locationTextBox.click();
		final Robot robot = new Robot();
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_A);
		locationTextBox.sendKeys(address);
	}

	private void clickButtonChangeLc() {
		WebElement btnChangeLc = waitForElementClickable(By.xpath("//a[@id='lc_btn-changeLc']"));
		btnChangeLc.click();
	}

	private String getActualResult() throws InterruptedException {
		// driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(1000);
		String actualResult = (waitForElementVisible(By.xpath("//div[@class='provinces-box']//child::span"))).getText();
		return actualResult;
	}

	private String getActualErrorWard() throws InterruptedException {
		String actualErr = (waitForElementVisible(By.className("errWard"))).getText();
		return actualErr;
	}

	private WebElement waitForElementClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	private WebElement waitForElementVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
}
