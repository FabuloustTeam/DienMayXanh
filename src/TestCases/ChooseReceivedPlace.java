package TestCases;

import org.testng.*;
import org.testng.annotations.*;
import AbstractAnnotation.*;
import ActionsForITestListener.*;
import Enum.*;
import Pages.*;

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
	public static int rowData;
	public static String actual, expected, province, district, ward, address, changeAddress, changeProvince;
	
	DMXChooseReceivedPlace objCRP = new DMXChooseReceivedPlace();

	@BeforeMethod
	public void beforeMethod() throws InterruptedException {
		// step 2 Nhấn chọn vào địa chỉ nhận hàng hiển thị trên thanh Header
		objCRP.chooseProvinceBox();
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
		objCRP.chooseDistrict(district);

		// step 4 Chọn Phường/Xã
		ward = ExcelUtils.getCellData(rowData, COL_WARD);
		objCRP.chooseWard(ward);

		// step 5 Nhập vào textbox "Số nhà, tên đường"
		address = ExcelUtils.getCellData(rowData, COL_ADDRESS);
		objCRP.fillAddress(address);

		// step 6 Nhấn Xác nhận và xem kết quả hiển thị
		objCRP.submitForm();
		
		actual = objCRP.getActualResult();
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
	 * Test requirement: TR-DMX-CNNH-01 - Test case: TC-DMX-CNNH-02
	 */
	@Test(priority = 2, groups = { "chooseRecivePlaceSuccess" })
	public void testSuccessWithOnlyProvince() throws Exception {
		rowData = ExcelUtils.getRowContains("testSuccessWithOnlyProvince", COL_TESTNAME);
		// step 3 Chọn tỉnh thành
		province = ExcelUtils.getCellData(rowData, COL_PROVINCE);
		objCRP.chooseProvince(province);
		
		// step 4 Nhấn Xác nhận và xem kết quả hiển thị
		objCRP.submitForm();

		actual = objCRP.getActualResult();
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
		objCRP.chooseProvince(province);

		// step 4 Nhấn vào drop down list "Vui lòng chọn Quận/Huyện" và chọn địa chỉ
		// tương ứng
		district = ExcelUtils.getCellData(rowData, COL_DISTRICT);
		objCRP.chooseDistrict(district);

		// step 5 Chọn Phường/Xã
		ward = ExcelUtils.getCellData(rowData, COL_WARD);
		objCRP.chooseWard(ward);

		// step 6 Nhấn Xác nhận và xem kết quả hiển thị
		objCRP.submitForm();

		actual = objCRP.getActualResult();
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
		objCRP.chooseProvince(province);

		// step 4 Nhấn vào drop down list "Vui lòng chọn Quận/Huyện" và chọn địa chỉ
		// tương ứng
		district = ExcelUtils.getCellData(rowData, COL_DISTRICT);
		objCRP.chooseDistrict(district);

		// step 5 Không chọn Phường/Xã
		objCRP.clickOnWardDropBox();

		// step 6 Nhấn Xác nhận và xem kết quả hiển thị
		objCRP.submitForm();
		
		actual = objCRP.getActualErrorWard();
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
		objCRP.chooseProvince(province);

		// step 4 Nhấn Xác nhận
		objCRP.submitForm();

		// step 5 Nhấn chọn vào địa chỉ nhận hàng hiển thị trên thanh Header
		objCRP.chooseProvinceBox();

		// step 6 Nhấn vào drop down list "Vui lòng chọn Quận/Huyện" và chọn địa chỉ
		// tương ứng
		district = ExcelUtils.getCellData(rowData + 1, COL_DISTRICT);
		objCRP.chooseDistrict(district);

		// step 7 Chọn Phường/Xã
		String ward = ExcelUtils.getCellData(rowData + 1, COL_WARD);
		objCRP.chooseWard(ward);

		// step 8 Nhấn Xác nhận và xem kết quả hiển thị
		objCRP.submitForm();

		actual = objCRP.getActualResult();
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
		objCRP.chooseProvince(province);

		// step 4 Nhấn vào drop down list "Vui lòng chọn Quận/Huyện" và chọn địa chỉ
		// tương ứng
		district = ExcelUtils.getCellData(rowData, COL_DISTRICT);
		objCRP.chooseDistrict(district);

		// step 5 Chọn Phường/Xã
		ward = ExcelUtils.getCellData(rowData, COL_WARD);
		objCRP.chooseWard(ward);

		// step 6 Nhập vào textbox "Số nhà, tên đường"
		address = ExcelUtils.getCellData(rowData, COL_ADDRESS);
		objCRP.fillAddress(address);

		// step 7 Nhấn Xác nhận và xem kết quả hiển thị
		objCRP.submitForm();

		// step 8 Nhấn chọn vào địa chỉ nhận hàng hiển thị trên thanh Header
		objCRP.chooseProvinceBox();

		// step 9 Nhấn chọn địa chỉ khác
		objCRP.clickButtonChangeLc();

		// step 10 Thay đổi số nhà, tên đường
		changeAddress = ExcelUtils.getCellData(rowData + 1, COL_ADDRESS);
		objCRP.changeInforOnTextBox(changeAddress);

		// step 11 sNhấn Xác nhận và xem kết quả hiển thị
		objCRP.submitForm();

		actual = objCRP.getActualResult();
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
		objCRP.chooseProvince(province);

		// step 4 Nhấn vào drop down list "Vui lòng chọn Quận/Huyện" và chọn địa chỉ
		// tương ứng
		district = ExcelUtils.getCellData(rowData, COL_DISTRICT);
		objCRP.chooseDistrict(district);

		// step 5 Chọn Phường/Xã
		ward = ExcelUtils.getCellData(rowData, COL_WARD);
		objCRP.chooseWard(ward);

		// step 6 Nhập vào textbox "Số nhà, tên đường"
		address = ExcelUtils.getCellData(rowData, COL_ADDRESS);
		objCRP.fillAddress(address);

		// step 7 Nhấn Xác nhận và xem kết quả hiển thị
		objCRP.submitForm();

		// step 8 cNhấn chọn vào địa chỉ nhận hàng hiển thị trên thanh Header
		objCRP.chooseProvinceBox();

		// step 9 Nhấn chọn tình thành khác ở dropdown list tỉnh thành
		changeProvince = ExcelUtils.getCellData(rowData+1, COL_PROVINCE);
		objCRP.chooseProvince(changeProvince);

		// step 10 Nhấn Xác nhận và xem kết quả hiển thị
		objCRP.submitForm();

		actual = objCRP.getActualResult();
		expected = ExcelUtils.getCellData(rowData, COL_EXPRESULT);
		Assert.assertEquals(actual, expected);
		if(actual.equals(expected)) {
			ExcelUtils.setCellData(rowData, COL_RESULT, Result.PASSED.toString());
		}
		else {
			ExcelUtils.setCellData(rowData, COL_RESULT, Result.FAILED.toString());
		}
	}
}
