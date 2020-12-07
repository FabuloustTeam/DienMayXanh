import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class chooserp extends AbstractClass{

	@BeforeMethod
	public void beforeMethod() throws InterruptedException {
		// step 2 Nhấn chọn vào địa chỉ nhận hàng hiển thị trên thanh Header
		WebElement provincesBox = waitForElementVisible(By.xpath("//div[@class='provinces-box']//child::span"));
		provincesBox.click();
	}

	/*
	 * Test requirement: TR-DMX-CNNH-01 - Test case: TC-DMX-CNNH-01
	 */
	@Test(priority = 1, groups={"chooseReceivePlaceSuccess"})
	public void mainSFWithFullInfor() throws InterruptedException {
		//chooseProvince("Hồ Chí Minh");
		// step 3 Nhấn vào drop down list "Vui lòng chọn Quận/Huyện" và chọn địa chỉ
		// tương ứng
		chooseDistrict("Quận Gò Vấp");

		// step 4 Chọn Phường/Xã
		chooseWard("Phường 6");

		// step 5 Nhập vào textbox "Số nhà, tên đường"
		fillAddress("496 Dương Quảng Hàm");

		// step 6 Nhấn Xác nhận và xem kết quả hiển thị
		submitForm();

		confirmResult("496 Dương Quảng Hàm, Phường 6, Quận Gò Vấp, Hồ Chí Minh");
	}

	/*
	 * Test requirement: TR-DMX-CNNH-01 - Test case: TC-DMX-CNNH-02
	 */
	@Test(priority = 2, groups={"chooseRecivePlaceSuccess"})
	public void successWithOnlyProvince() throws InterruptedException {
		// step 3 Chọn tỉnh thành
		chooseProvince("Đồng Nai");

		// step 4 Nhấn Xác nhận và xem kết quả hiển thị
		submitForm();

		confirmResult("Đồng Nai");
	}

	/*
	 * Test requirement: TR-DMX-CNNH-01 - Test case: TC-DMX-CNNH-03
	 */
	@Test(priority = 3, groups={"chooseReceivePlaceSuccess"})
	public void successWithoutFillTextboxAddress() throws InterruptedException {
		// step 3 Chọn tỉnh thành
		chooseProvince("Hồ Chí Minh");

		// step 4 Nhấn vào drop down list "Vui lòng chọn Quận/Huyện" và chọn địa chỉ
		// tương ứng
		chooseDistrict("Quận Bình Thạnh");

		// step 5 Chọn Phường/Xã
		chooseWard("Phường 15");

		// step 6 Nhấn Xác nhận và xem kết quả hiển thị
		submitForm();

		confirmResult("Phường 15, Quận Bình Thạnh, Hồ Chí Minh");
	}

	/*
	 * Test requirement: TR-DMX-CNNH-02 - Test case: TC-DMX-CNNH-04
	 */
	@Test(priority = 4, groups={"chooseReceivePlaceFail"})
	public void failWithoutChooseWard() throws InterruptedException {
		// step 3 Chọn tỉnh thành
		chooseProvince("Bình Định");
		// step 4 Nhấn vào drop down list "Vui lòng chọn Quận/Huyện" và chọn địa chỉ
		// tương ứng
		chooseDistrict("Huyện Vân Canh");

		// step 5 Không chọn Phường/Xã
		WebElement dropboxWard = waitForElementClickable(By.id("location_listWard"));
		dropboxWard.click();

		// step 6 Nhấn Xác nhận và xem kết quả hiển thị
		submitForm();

		confirmErrorWard("Vui lòng chọn phường xã");
	}

	/**
	 * Test requirement: TR-DMX-CNNH-03 - Test case: TC-DMX-CNNH-05
	 */

	@Test(priority = 5, groups={"successUpdateReceivePlace"})
	public void successUpdateWithOnlyProvinceSubmitBefor() throws InterruptedException {
		// step 3 Chọn tỉnh thành
		chooseProvince("Bình Thuận");
		
		// step 4 Nhấn Xác nhận
		submitForm();

		// step 5 Nhấn chọn vào địa chỉ nhận hàng hiển thị trên thanh Header
		chooseProvinceBox();

		// step 6 Nhấn vào drop down list "Vui lòng chọn Quận/Huyện" và chọn địa chỉ
		// tương ứng
		chooseDistrict("Huyện Hàm Tân");

		// step 7 Chọn Phường/Xã
		chooseWard("Xã Tân Đức");

		// step 8 Nhấn Xác nhận và xem kết quả hiển thị
		submitForm();

		confirmResult("Xã Tân Đức, Huyện Hàm Tân, Bình Thuận");
	}

	/**
	 * Test requirement: TR-DMX-CNNH-03 - Test case: TC-DMX-CNNH-06
	 */

	@Test(priority = 6, groups={"successUpdateReceivePlace"})
	public void successUpdateByClickOnButonChange() throws InterruptedException, AWTException {
		// step 3 Chọn tỉnh thành
		chooseProvince("Hà Nội");

		// step 4 Nhấn vào drop down list "Vui lòng chọn Quận/Huyện" và chọn địa chỉ
		// tương ứng
		chooseDistrict("Quận Ba Đình");

		// step 5 Chọn Phường/Xã
		chooseWard("Phường Đội Cấn");

		// step 6 Nhập vào textbox "Số nhà, tên đường"
		fillAddress("13 Đội Cấn");

		// step 7 Nhấn Xác nhận và xem kết quả hiển thị
		submitForm();

		// step 8 Nhấn chọn vào địa chỉ nhận hàng hiển thị trên thanh Header
		chooseProvinceBox();

		// step 9 Nhấn chọn địa chỉ khác
		clickButtonChangeLc();

		// step 10 Thay đổi số nhà, tên đường
		changeInforOnTextBox("17 Đội Mũ");

		// step 11 sNhấn Xác nhận và xem kết quả hiển thị
		submitForm();

		confirmResult("17 Đội Mũ, Phường Đội Cấn, Quận Ba Đình, Hà Nội");
	}

	/**
	 * Test requirement: TR-DMX-CNNH-03 - Test case: TC-DMX-CNNH-07
	 */
	@Test(priority = 7, groups={"successUpdateReceivePlace"})
	public void successUpdateByClickOnDropBoxProvince() throws InterruptedException {
		// step 3 Chọn tỉnh thành
		chooseProvince("Đà Nẵng");

		// step 4 cNhấn vào drop down list "Vui lòng chọn Quận/Huyện" và chọn địa chỉ
		// tương ứng
		chooseDistrict("Quận Ngũ Hành Sơn");

		// step 5 Chọn Phường/Xã
		chooseWard("Phường Mỹ An");

		// step 6 Nhập vào textbox "Số nhà, tên đường"
		fillAddress("27 Sơn An");

		// step 7 Nhấn Xác nhận và xem kết quả hiển thị
		submitForm();

		// step 8 cNhấn chọn vào địa chỉ nhận hàng hiển thị trên thanh Header
		chooseProvinceBox();

		// step 9 Nhấn chọn tình thành khác ở dropdown list tỉnh thành
		chooseProvince("Bắc Kạn");

		// step 10 Nhấn Xác nhận và xem kết quả hiển thị
		submitForm();

		confirmResult("Bắc Kạn");
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

	private void submitForm() {
		WebElement submit = waitForElementClickable(By.id("lc_btn-Confirm"));
		submit.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	private void chooseProvince(String province) throws InterruptedException {
		WebElement setProvince = waitForElementVisible(By.id("location_listPro"));
		setProvince.click();
		Thread.sleep(500);
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
		if(getStyle.equals("display: none;") || getStyle.equals("")) {
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

	private void confirmResult(String expectedResult) throws InterruptedException {
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement actualResult =waitForElementVisible(By.xpath("//div[@class='provinces-box']//child::span"));
		Thread.sleep(2000);
		Assert.assertEquals(actualResult.getText(), expectedResult);
	}

	private void confirmErrorWard(String expectedErr) throws InterruptedException {
		WebElement actualErr = waitForElementVisible(By.className("errWard"));
		Assert.assertEquals(actualErr.getText(), expectedErr);
	}
	
	private WebElement waitForElementClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(this.driver, 20);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	private WebElement waitForElementVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(this.driver, 20);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
}
