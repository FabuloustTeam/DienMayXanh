package com.dienmayxanh.test;

import org.testng.Assert;
import org.testng.annotations.*;

import com.dienmayxanh.abstractclass.*;
import com.dienmayxanh.page.ViewProductThroughListPage;
import com.dienmayxanh.service.*;
import com.dienmayxanh.Enum.*;

public class ViewProductThroughList extends AbstractAnnotation {
	public static final int COL_TESTTYPE = 0;
	public static final int COL_TESTNAME = 1;
	public static final int COL_CASE = 2;
	public static final int COL_EXPRESULT = 3;
	public static final int COL_RESULT = 4;
	public static final int COL_CATEGORY = 5;
	public static final int COL_MANUFACTURE = 6;
	public static final int COL_NAMEPRODUCT = 7;

	public static final int iTestBeginRow = 2;
	public static int iTestCaseRow, rowData;
	public static String actual, expected, category, manufacture, product;
	ViewProductThroughListPage objVPTL = new ViewProductThroughListPage();
	
	/**
	 * Test requirement: TR-DMX-VPBLP-01 Test case: TC-DMX-VPBLP-01
	 * @throws Exception 
	 */
	@Test(groups = { "viewByName" })
	public void testSuccessViewProductByList() throws Exception {
		rowData = ExcelUtils.getRowContains("TC-DMX-VPBLP-01", 2);
		// Step 2 Nhấn chọn loại sản phẩm muốn xem trong danh mục
		category = ExcelUtils.getCellData(rowData, COL_CATEGORY);
		objVPTL.chooseCategory(category);
		// Step 3 Nhấn chọn vào hãng muốn xem của sản phẩm đó
		manufacture = ExcelUtils.getCellData(rowData, COL_MANUFACTURE);
		objVPTL.chooseManufacture(manufacture);
		// Step 4 Nhấn chọn vào sản phẩm muốn xem
		product = ExcelUtils.getCellData(rowData, COL_NAMEPRODUCT);
		objVPTL.getProduct(product);

		//compareTitle("Máy lọc nước RO hydrogen ion kiềm Kangaroo KG100EO 7 lõi");
		expected = ExcelUtils.getCellData(rowData, COL_EXPRESULT);
		actual = objVPTL.getActualResult();
		if(actual.equals(expected)) {
			ExcelUtils.setCellData(rowData, COL_RESULT, Result.PASSED.toString());
		}
		else {
			ExcelUtils.setCellData(rowData, COL_RESULT, Result.FAILED.toString());
		}
		Assert.assertEquals(actual, expected);
	}
}