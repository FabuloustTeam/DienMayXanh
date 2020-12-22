package Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import AbstractAnnotation.AbstractClass;
import ActionsForITestListener.ExcelUtils;
import Enum.Result;

public class DMXChooseReceivedPlace extends AbstractClass {
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
	@Test(priority = 1)
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
	}
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
