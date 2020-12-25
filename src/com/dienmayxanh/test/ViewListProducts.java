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
	
	/**
	 * Test requirement: TR-DMX-VLP-01 Test Case ID: TC-DMX-VLP-01
	 */
	@Test(priority = 1)
	@Parameters({ "url" })
	public void testShowAllProduct(String url) {
		objViewListProducts = new ViewListProductsPage();
		elementService = new Element();
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		
		// 2. Nhấn ch�?n danh sách sản phẩm trong danh mục
		WebElement locnuoc = elementService.waitForElementClickable(By.xpath("//a[@href='/may-loc-nuoc']"));
		locnuoc.click();

		boolean isAllProductTrueName = true;
		List<WebElement> products = objViewListProducts.getAllProducts();
		for (int i = 0; i < products.size(); i ++) {
			WebElement spanName = products.get(i).findElement(By.xpath("//div[@class='prdName']//span"));
			if(!spanName.getText().toLowerCase().contains("máy l�?c nước")) {
				isAllProductTrueName = false;
				break;
			}
		}
		Assert.assertEquals(isAllProductTrueName, true);
	}
	
	/**
	 * Test requirement: TR-DMX-VLP-01 Test Case ID: TC-DMX-VLP-02
	 */
	@Test(priority = 2)
	@Parameters({ "url" })
	private void testShowAllProductOfBrand(String url) {
		objViewListProducts = new ViewListProductsPage();
		elementService = new Element();
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		
		WebElement locnuoc = elementService.waitForElementClickable(By.xpath("//a[@href='/may-loc-nuoc']"));
		locnuoc.click();
		// 3. Chọn hãng
		List<WebElement> listBrand = driver.findElements(By.xpath("//div[@class=\"test manufacture show-10\"]/a"));
		JavascriptExecutor jsEx = (JavascriptExecutor) driver;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < listBrand.size() - 1; i++) {
			if (listBrand.get(i).getAttribute("title").equalsIgnoreCase("kangaroo")) {
				listBrand.get(i).findElement(By.tagName("i")).click();
				break;
			}
		}
		elementService.waitForPageLoad();
		
		boolean isOnlyBrandProducts = true;
		List<WebElement> allProducts = objViewListProducts.getAllProducts();
		for (int i = 0; i < allProducts.size(); i++) {
			WebElement spanName = allProducts.get(i).findElement(By.xpath("//div[@class='prdName']//span"));
			if(!spanName.getText().toLowerCase().contains("kangaroo")) {
				isOnlyBrandProducts = false;
				break;
			}
		}
		
		Assert.assertEquals(isOnlyBrandProducts, true);
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
		
		int rowData = ExcelUtils.getRowContains("ViewList_Quantity", COL_TESTNAME);
		ITestResult result = Reporter.getCurrentTestResult();
		result.setAttribute("testname", "ViewList_Quantity");
		
		// 2. Nhấn chọn danh sách sản phẩm trong danh mục
		category = ExcelUtils.getCellData(rowData, COL_INPUT_CATEGORY);
		objViewListProducts.chooseCategory(category);
		
		objViewListProducts.waitForProductsLoad();
		elementService.waitForPageLoad();
		int quantityBreadcrumb = objViewListProducts.getQuantityBreadcrumb();
		int quantityProducts = objViewListProducts.getQuantityProducts();
		int quantityTotal = objViewListProducts.getQuantityTotal();
		if(quantityBreadcrumb == quantityProducts && quantityBreadcrumb == quantityTotal)
			ExcelUtils.setCellData(rowData, COL_RESULT, Result.PASSED.toString());
		else
			ExcelUtils.setCellData(rowData, COL_RESULT, Result.FAILED.toString());
		Assert.assertEquals(quantityBreadcrumb, quantityProducts);
		Assert.assertEquals(quantityTotal, quantityProducts);
	}
	

}
