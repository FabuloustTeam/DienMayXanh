package com.dienmayxanh.test;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.dienmayxanh.abstractclass.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import com.dienmayxanh.service.ExcelUtils;
//@Listeners(com.dienmayxanh.listener.ListenerTest.class)

public class ViewListProducts extends AbstractAnnotation {
	
	/**
	 * Test requirement: TR-DMX-VLP-01 Test Case ID: TC-DMX-VLP-01
	 */
	@Test(priority = 1)
	public void testShowAllProduct() {
		// 2. Nhấn ch�?n danh sách sản phẩm trong danh mục
		WebElement locnuoc = waitForElementClickable(By.xpath("//a[@href='/may-loc-nuoc']"));
		locnuoc.click();

		boolean isAllProductTrueName = true;
		List<WebElement> products = getAllProducts();
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
	private void testShowAllProductOfBrand() {
		WebElement locnuoc = waitForElementClickable(By.xpath("//a[@href='/may-loc-nuoc']"));
		locnuoc.click();
		// 3. Chọn hãng
		List<WebElement> listBrand = driver.findElements(By.xpath("//div[@class=\"test manufacture show-10\"]/a"));
		JavascriptExecutor jsEx = (JavascriptExecutor) driver;
//		jsEx.executeScript(
//				"var el = document.querySelectorAll('div.test.manufacture.show-10')[0]; el.scrollIntoView(true)");
//		
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
		waitForPageLoad();
		
		boolean isOnlyBrandProducts = true;
		List<WebElement> allProducts = getAllProducts();
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
	public void testViewWithSortPriceDescending() throws Exception {
		int rowData = ExcelUtils.getRowContains("TC-DMX-VLP-03", 2);
		ITestResult result = Reporter.getCurrentTestResult();
		result.setAttribute("id", "TC-DMX-VLP-03");
		
		// 2. Nhấn chọn danh sách sản phẩm trong danh mục
		String category = ExcelUtils.getCellData(rowData + 1, 7);
		chooseCategory(category);

		waitForPageLoad();
		
		// 3. Chọn sắp xếp
		String sortType = ExcelUtils.getCellData(rowData + 2, 7);
		chooseSort(sortType);

		WebElement[][] dataTable = getTable();
		boolean actual = checkDescending(dataTable);
		String actualResult = "";
		if(actual)
			actualResult = "Hệ thống hiển thị toàn bộ sản phẩm lọc nước hiện có với sắp xếp theo giá tiền từ cao đến thấp";
		else
			actualResult = "Hệ thống không hiển thị đúng";
		
		result.setAttribute("actualResult", actualResult);
		
		Assert.assertEquals(actual, true);
	}
	
	/**
	 * Test requirement: TR-DMX-VLP-02. Test case ID: TC-DMX-VLP-04
	 * @throws Exception 
	 */
	@Test (priority = 4)
	public void testViewWithSortPriceAscending() throws Exception {
		int rowData = ExcelUtils.getRowContains("TC-DMX-VLP-04", 2);
		ITestResult result = Reporter.getCurrentTestResult();
		result.setAttribute("id", "TC-DMX-VLP-04");
		
		// 2. Nhấn chọn danh sách sản phẩm trong danh mục
		String category = ExcelUtils.getCellData(rowData + 1, 7);
		chooseCategory(category);

		waitForPageLoad();
		
		// 3. Chọn sắp xếp
		String sortType = ExcelUtils.getCellData(rowData + 2, 7);
		chooseSort(sortType);

		WebElement[][] dataTable = getTable();
		boolean actual = checkAscending(dataTable);
		
		String actualResult = "";
		if(actual)
			actualResult = "Hệ thống hiển thị toàn bộ sản phẩm lọc nước hiện có với sắp xếp theo giá tiền từ thấp đến cao";
		else
			actualResult = "Hệ thống không hiển thị đúng";
		
		result.setAttribute("actualResult", actualResult);
		
		Assert.assertEquals(actual, true);
	}
	
	/**
	 * Test requirement: TR-DMX-VLP-03. Test case ID: TC-DMX-VLP-05
	 * @throws Exception 
	 */
	@Test (priority = 5)
	public void testQuantity() throws Exception {
		int rowData = ExcelUtils.getRowContains("TC-DMX-VLP-05", 2);
		ITestResult result = Reporter.getCurrentTestResult();
		result.setAttribute("id", "TC-DMX-VLP-05");
		
		// 2. Nhấn chọn danh sách sản phẩm trong danh mục
		String category = ExcelUtils.getCellData(rowData + 1, 7);
		chooseCategory(category);
		
		waitForProductsLoad();
		waitForPageLoad();
		int quantityBreadcrumb = getQuantityBreadcrumb();
		int quantityProducts = getQuantityProducts();
		int quantityTotal = getQuantityTotal();
		String actualResult = "";
		if(quantityBreadcrumb == quantityProducts && quantityBreadcrumb == quantityTotal)
			actualResult = "Hệ thống hiển thị breadcrumb và ở trước filter có số lượng bằng số lượng sản phẩm đang hiển thị";
		else if(quantityBreadcrumb != quantityProducts)
			actualResult = "Hệ thống hiển thị breadcrumb và số lượng sản phẩm đang hiển thị khác nhau";
		else if(quantityTotal != quantityProducts)
			actualResult = "Hệ thống hiển thị ở trước filter và số lượng sản phẩm đang hiển thị khác nhau";
		else if(quantityBreadcrumb != quantityTotal)
			actualResult = "Hệ thống hiển thị ở trước filter và breadcrumb khác nhau";
		else
			actualResult = "Hệ thống hiển thị breadcrumb, ở trước filter và số lượng sản phẩm đang hiển thị khác nhau";
		
		result.setAttribute("actualResult", actualResult);
		Assert.assertEquals(quantityBreadcrumb, quantityProducts);
		Assert.assertEquals(quantityTotal, quantityProducts);
//		Assert.assertEquals(false, true);
	}
	
	private int getQuantityTotal() {
		WebElement total = waitForElementVisibility(By.xpath("//div[contains(@class,'totalfilter')]//child::span"));
		return Integer.parseInt(total.getText().trim());
	}

	private int getQuantityProducts() {
		int quantityProducts = 0;
		WebElement[][] dataTable = getTable();
		for (int i = 0; i < dataTable.length; i++) {
			for (int j = 0; j < dataTable[i].length; j++) {
				if(dataTable[i][j] != null)
					quantityProducts++;
			}
		}
		return quantityProducts;
	}

	private int getQuantityBreadcrumb() {
		WebElement quantityBreadcrumb = waitForElementVisibility(By.id("breadcrumb_total"));
		return Integer.parseInt(quantityBreadcrumb.getText().trim());
	}

	private boolean checkAscending(WebElement[][] dataTable) {
		int minPrice = getPrice(dataTable[0][0]);
		for (int i = 0; i < dataTable.length; i++) {
			for (int j = 0; j < dataTable[i].length; j++) {
				if (dataTable[i][j] != null) {
					int tempPrice = getPrice(dataTable[i][j]);
					if (minPrice > tempPrice)
						return false;
					else
						minPrice = tempPrice;
				}
			}
		}
		return true;
	}

	private boolean checkDescending(WebElement[][] dataTable) {
		int maxPrice = getPrice(dataTable[0][0]);
		for (int i = 0; i < dataTable.length; i++) {
			for (int j = 0; j < dataTable[i].length; j++) {
				if (dataTable[i][j] != null) {
					int tempPrice = getPrice(dataTable[i][j]);
					if (maxPrice < tempPrice)
						return false;
					else
						maxPrice = tempPrice;
				}
			}
		}
		return true;
	}

	private int getPrice(WebElement cellTable) {
		List<WebElement> prices = cellTable.findElements(By.tagName("strong"));
		WebElement price;
		if (prices.size() > 1) {
			price = prices.get(prices.size() - 1);
		} else {
			price = prices.get(0);
		}
		String priceValue = price.getText().replace("₫", "").replace(".", "");
		return Integer.parseInt(priceValue);
	}

	private WebElement[][] getTable() {
		WebElement table = waitForElementVisibility(By.id("product-list"));
		List<WebElement> allProducts = table.findElements(By.xpath("//div[contains(@class,'prdItem')]"));
		WebElement[][] dataTable = new WebElement[(allProducts.size() / 4) + 1][4];
		int countRow = 0;
		int countCol = 0;
		for (int i = 0; i < allProducts.size(); i++) {
			if (countRow < 4) {
				dataTable[countCol][countRow] = allProducts.get(i);
				countRow++;
			} else {
				countCol++;
				countRow = 0;
				dataTable[countCol][countRow] = allProducts.get(i);
				countRow++;
			}
		}
		return dataTable;
	}
	
	private List<WebElement> getAllProducts() {
		WebElement table = waitForElementVisibility(By.id("product-list"));
		List<WebElement> allProducts = table.findElements(By.xpath("//div[contains(@class,'prdItem')]"));
		WebElement[][] dataTable = new WebElement[(allProducts.size() / 4) + 1][4];
		int countRow = 0;
		int countCol = 0;
		for (int i = 0; i < allProducts.size(); i++) {
			if (countRow < 4) {
				dataTable[countCol][countRow] = allProducts.get(i);
				countRow++;
			} else {
				countCol++;
				countRow = 0;
				dataTable[countCol][countRow] = allProducts.get(i);
				countRow++;
			}
		}
		return allProducts;
	}

	private void chooseSort(String sortType) {
		WebElement orderType = waitForElementVisibility(By.xpath("//div[@class='ordertype twonum']"));
		List<WebElement> orderTypes = orderType.findElements(By.tagName("a"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		for (int i = 0; i < orderTypes.size(); i++) {
			wait.until(ExpectedConditions.elementToBeClickable(orderTypes.get(i)));
			if(orderTypes.get(i).getText().trim().equalsIgnoreCase(sortType.toLowerCase())) {
				orderTypes.get(i).click();
				int centerWidth = orderTypes.get(i).getSize().getWidth()/2;
				int centerHeight = orderTypes.get(i).getSize().getHeight()/2;
				Actions builder = new Actions(driver);
				builder.moveToElement(orderTypes.get(i), centerWidth, centerHeight).click().build().perform();
				break;
			}
		}
		
		waitForPageLoad();
//		WebElement sortPriceDescending = waitForElementClickable(
//				By.xpath("//div[@class='ordertype twonum']//child::a[@data-text='"+sortType+"']"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		sortPriceDescending.click();

		waitForElementInvisible(By.id("dlding"));

		waitForProductsLoad();
	}

	private void waitForProductsLoad() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		while (true) {
			try {
				WebElement loadMore = waitForElementClickable(By.xpath("//a[@class='viewmore']"));
				js.executeScript("arguments[0].scrollIntoView();", loadMore);
				js.executeScript("window.scrollBy(0,-130)");
				loadMore.click();
				waitForElementInvisible(By.id("dlding"));
			} catch (Exception e) {
				System.out.println(e.toString());
				break;
			}
		}
	}

	private void chooseCategory(String category) {
		WebElement uListCategories = waitForElementClickable(By.xpath("//span[@id='dmsp']//following::nav//child::ul"));
		List<WebElement> liCategories = uListCategories.findElements(By.tagName("li"));
		chooseCategory: {
			for (int i = 0; i < liCategories.size(); i++) {
				List<WebElement> categories = liCategories.get(i).findElements(By.tagName("a"));
				for (int j = 0; j < categories.size(); j++) {
					if (categories.get(j).getText().contentEquals(category)) {
						categories.get(j).click();
						break chooseCategory;
					}
				}
			}
		}
	}

	private void waitForPageLoad() {
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	private WebElement waitForElementClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	private void waitForElementInvisible(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	private WebElement waitForElementVisibility(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

}
