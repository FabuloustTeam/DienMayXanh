package com.dienmayxanh.test;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.dienmayxanh.Enum.Case;
import com.dienmayxanh.Enum.Result;
import com.dienmayxanh.Enum.Type;
import com.dienmayxanh.abstractclass.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import com.dienmayxanh.service.Element;
import com.dienmayxanh.service.ExcelUtils;
import com.dienmayxanh.page.*;

public class ViewListProducts extends AbstractAnnotation {
	
	private final int COL_CASE = 2;
	private final int COL_EXPECT = 3;
	private final int COL_RESULT = 4;
	private final int COL_INPUT_CATEGORY = 5;
	private final int COL_INPUT_BRAND = 6;
	private final int COL_INPUT_SORTTYPE = 7;
	private final int START_ROW = 2;
	private final int COL_TESTNAME = 1;
	private final int COL_TYPE = 8;
	private int iTestCaseRow;
	private String caseValue = "";
	private String typeValue = "";
	private String category = "";
	private String sortType = "";
	private boolean actual;
	private ViewListProductsPage objViewListProducts;
	private Element elementService;
	private boolean isAllProductTrueName;
	private boolean isOnlyBrandProducts;
	/**
	 * Test requirement: TR-DMX-VLP-01 Test Case ID: TC-DMX-VLP-01
	 * @throws Exception 
	 */
	@Test(priority = 1)
	@Parameters({ "url" })
	public void testShowAllProduct(String url) throws Exception {
		objViewListProducts = new ViewListProductsPage();
		elementService = new Element();
		iTestCaseRow = ExcelUtils.getRowUsed();
		for(int i = START_ROW; i <= iTestCaseRow; i++) {
			caseValue = ExcelUtils.getCellData(i, COL_CASE);
			typeValue = ExcelUtils.getCellData(i, COL_TYPE);
			if(caseValue.equals(Case.SUCCESSFULLY.toString()) && typeValue.equals(Type.ALL_LIST.toString())) {
				
				// 2. Nhấn ch�?n danh sách sản phẩm trong danh mục
				String category = ExcelUtils.getCellData(i, COL_INPUT_CATEGORY);
				objViewListProducts.chooseCategory(category);

				elementService.waitForPageLoad();

				List<WebElement> products = objViewListProducts.getAllProducts();
				isAllProductTrueName = objViewListProducts.isAllProductRightName(products, category);
				if(isAllProductTrueName) {
					ExcelUtils.setCellData(i, COL_RESULT, Result.PASSED.toString());
				} else {
					ExcelUtils.setCellData(i, COL_RESULT, Result.FAILED.toString());
				}
				Assert.assertEquals(isAllProductTrueName, true);
				driver.close();
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.get(url);
			}
		}
	}
	
	/**
	 * Test requirement: TR-DMX-VLP-01 Test Case ID: TC-DMX-VLP-02
	 * @throws Exception 
	 */
	@Test(priority = 2)
	@Parameters({ "url" })
	private void testShowAllProductOfBrand(String url) throws Exception {
		objViewListProducts = new ViewListProductsPage();
		elementService = new Element();
		iTestCaseRow = ExcelUtils.getRowUsed();
		for(int i = START_ROW; i <= iTestCaseRow; i++) {
			caseValue = ExcelUtils.getCellData(i, COL_CASE);
			typeValue = ExcelUtils.getCellData(i, COL_TYPE);
			if(caseValue.equals(Case.SUCCESSFULLY.toString()) && typeValue.equals(Type.ALL_OF_BRAND.toString())) {
				String category = ExcelUtils.getCellData(i, COL_INPUT_CATEGORY);
				objViewListProducts.chooseCategory(category);

				elementService.waitForPageLoad();
				
				// 3. Chọn hãng
				String manufacture = ExcelUtils.getCellData(i, COL_INPUT_BRAND);
				objViewListProducts.chooseManufacture(manufacture);
				
				elementService.waitForPageLoad();
				
				List<WebElement> allProducts = objViewListProducts.getAllProducts();
				isOnlyBrandProducts = objViewListProducts.isOnlyBrandProduct(allProducts, manufacture);
				
				if(isOnlyBrandProducts) {
					ExcelUtils.setCellData(i, COL_RESULT, Result.PASSED.toString());
				} else {
					ExcelUtils.setCellData(i, COL_RESULT, Result.FAILED.toString());
				}
				
				Assert.assertEquals(isOnlyBrandProducts, true);
				
				driver.close();
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.get(url);
			}
		}
		
	}

	/**
	 * Test requirement: TR-DMX-VLP-02. Test case ID: TC-DMX-VLP-03
	 * @throws Exception 
	 */
	@Test (priority = 3)
	@Parameters({ "url" })
	public void ViewList_SortPrice(String url) throws Exception {
		objViewListProducts = new ViewListProductsPage();
		elementService = new Element();
		
		iTestCaseRow = ExcelUtils.getRowUsed();
		for(int i = START_ROW; i <= iTestCaseRow; i++) {
			caseValue = ExcelUtils.getCellData(i, COL_CASE);
			typeValue = ExcelUtils.getCellData(i, COL_TYPE);
			if(caseValue.equals(Case.SUCCESSFULLY.toString()) && typeValue.equals(Type.SORTING.toString())) {
				ITestResult result = Reporter.getCurrentTestResult();
				result.setAttribute("testname", ExcelUtils.getCellData(i, COL_TESTNAME));
				
				// 2. Nhấn chọn danh sách sản phẩm trong danh mục
				String category = ExcelUtils.getCellData(i, COL_INPUT_CATEGORY);
				objViewListProducts.chooseCategory(category);

				elementService.waitForPageLoad();
				
				// 3. Chọn sắp xếp
				sortType = ExcelUtils.getCellData(i, COL_INPUT_SORTTYPE);
				objViewListProducts.chooseSort(sortType);
				
				WebElement[][] dataTable = objViewListProducts.getTable();
				
				if(sortType.equals("Giá cao đến thấp")) {
					actual = objViewListProducts.checkDescending(dataTable);
					if(actual) {
						ExcelUtils.setCellData(i, COL_RESULT, Result.PASSED.toString());
					} else {
						ExcelUtils.setCellData(i, COL_RESULT, Result.FAILED.toString());
					}
				} else {
					boolean actual = objViewListProducts.checkAscending(dataTable);
					if(actual) {
						ExcelUtils.setCellData(i, COL_RESULT, Result.PASSED.toString());
					} else {
						ExcelUtils.setCellData(i, COL_RESULT, Result.FAILED.toString());
					}
				}
				driver.get(url);
			}
		}
		driver.close();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	/**
	 * Test requirement: TR-DMX-VLP-03. Test case ID: TC-DMX-VLP-05
	 * @throws Exception 
	 */
	@Test (priority = 5)
	@Parameters({ "url" })
	public void ViewList_Quantity(String url) throws Exception {
		objViewListProducts = new ViewListProductsPage();
		elementService = new Element();
		iTestCaseRow = ExcelUtils.getRowUsed();
		for(int i = START_ROW; i <= iTestCaseRow; i++) {
			caseValue = ExcelUtils.getCellData(i, COL_CASE);
			typeValue = ExcelUtils.getCellData(i, COL_TYPE);
			if(caseValue.equals(Case.SUCCESSFULLY.toString()) && typeValue.equals(Type.QUANTITY.toString())) {
				
				// 2. Nhấn chọn danh sách sản phẩm trong danh mục
				category = ExcelUtils.getCellData(i, COL_INPUT_CATEGORY);
				objViewListProducts.chooseCategory(category);
				
				objViewListProducts.waitForProductsLoad();
				elementService.waitForPageLoad();
				int quantityBreadcrumb = objViewListProducts.getQuantityBreadcrumb();
				int quantityProducts = objViewListProducts.getQuantityProducts();
				int quantityTotal = objViewListProducts.getQuantityTotal();
				if(quantityBreadcrumb == quantityProducts && quantityBreadcrumb == quantityTotal)
					ExcelUtils.setCellData(i, COL_RESULT, Result.PASSED.toString());
				else
					ExcelUtils.setCellData(i, COL_RESULT, Result.FAILED.toString());
				
				Assert.assertEquals(quantityBreadcrumb, quantityProducts);
				Assert.assertEquals(quantityTotal, quantityProducts);
				
				driver.close();
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.get(url);
			}
		}
	}
	

}
