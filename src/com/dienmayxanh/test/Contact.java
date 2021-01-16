package com.dienmayxanh.test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dienmayxanh.Enum.Result;
import com.dienmayxanh.abstractclass.AbstractAnnotation;
import com.dienmayxanh.page.ContactPage;
import com.dienmayxanh.service.ExcelUtils;
import com.dienmayxanh.service.TakeSnapShot;

public class Contact extends AbstractAnnotation {
	
	private final int COL_EXPECT = 3;
	private final int COL_RESULT = 4;
	private final int COL_INPUT_CONTENT = 5;
	private final int COL_INPUT_NAME = 6;
	private final int COL_INPUT_PHONE = 7;
	private final int COL_INPUT_GENDER = 8;
	private final int COL_INPUT_EMAIL = 9;
	private final int COL_TYPE = 10;
	private final int START_ROW = 2;
	private final int COL_TESTNAME = 1;
	private int iTestCaseRow;
	private String typeValue = "";
	private String content = "";
	private String name = "";
	private String phone = "";
	private String gender = "";
	private String email = "";
	private String actual = "";
	private String expected = "";
	private String testCaseName = "";
	
	ContactPage objContact = new ContactPage();
	
	@Test(priority = 1)
	@Parameters({ "url" })
	public void testContact(String url) throws Exception {
		iTestCaseRow = ExcelUtils.getRowUsed();
		for(int i = START_ROW; i <= iTestCaseRow; i++) {
			actual = "";
			
			objContact.clickWebContact();
			
			// 3. Nhập nội dung muốn góp ý
			content = ExcelUtils.getCellData(i, COL_INPUT_CONTENT);
			objContact.setContent(content);
			
			// 4. Chọn giới tính "Anh" hoặc "Chị"
			gender = ExcelUtils.getCellData(i, COL_INPUT_GENDER);
			if(!gender.equals("")) {
				objContact.clickRadioButton(gender);
			}

			// 5. Nhập họ và tên
			name = ExcelUtils.getCellData(i, COL_INPUT_NAME);
			objContact.setName(name);
	
			// 6. Nhập số điện thoại
			phone = ExcelUtils.getCellData(i, COL_INPUT_PHONE);
			objContact.setPhone(phone);
	
			// 7. Nhập email
			email = ExcelUtils.getCellData(i, COL_INPUT_EMAIL);
			objContact.setEmail(email);
	
//			WebElement submit = driver.findElement(By.id("submit"));
			// 8. Nhấn "Gửi liên hệ"
			typeValue = ExcelUtils.getCellData(i, COL_TYPE);
			objContact.submit(typeValue);
			
			actual = objContact.actual;
			
			expected = ExcelUtils.getCellData(i, COL_EXPECT);
			if(actual.equals(expected)) {
				ExcelUtils.setCellData(i, COL_RESULT, Result.PASSED.toString());
			} else {
				testCaseName = ExcelUtils.getCellData(i, COL_TESTNAME);
				String file = System.getProperty("user.dir") + "\\screenshots\\" + testCaseName + ".png";
				try {
					TakeSnapShot.takeSnapShot(file);
				} catch(Exception e) {
					e.printStackTrace();
				}
				ExcelUtils.setCellData(i, COL_RESULT, Result.FAILED.toString());
			}
			
			if(actual.equals(""))
				ExcelUtils.setCellData(i, COL_RESULT, Result.SKIPPED.toString());
			
			driver.close();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(url);
		}
	}
}