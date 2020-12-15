package TestCases;

import java.util.List;
import org.openqa.selenium.*;
import org.testng.Reporter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import AbstractAnnotation.AbstractClass;
import ActionsForITestListener.ExcelUtils;

public class ViewProductThroughList extends AbstractClass {

	/**
	 * Test requirement: TR-DMX-VPBLP-01 Test case: TC-DMX-VPBLP-01
	 * @throws Exception 
	 */
	@Test(groups = { "viewByName" })
	public void testSuccessViewProductByList() throws Exception {
		int rowData = ExcelUtils.getRowContains("TC-DMX-VPBLP-01", 2);
		// Step 2 Nhấn chọn loại sản phẩm muốn xem trong danh mục
		String category = ExcelUtils.getCellData(rowData+1, 7);
		chooseCategory(category);
		// Step 3 Nhấn chọn vào hãng muốn xem của sản phẩm đó
		String manufacture = ExcelUtils.getCellData(rowData+2, 7);
		chooseManufacture(manufacture);
		// Step 4 Nhấn chọn vào sản phẩm muốn xem
		String product = ExcelUtils.getCellData(rowData+3, 7);
		getProduct(product);

		//compareTitle("Máy lọc nước RO hydrogen ion kiềm Kangaroo KG100EO 7 lõi");
		String expectedResult = ExcelUtils.getCellData(rowData+3, 8);
		String actualResult = compareTitle("Máy lọc nước RO hydrogen ion kiềm Kangaroo KG100EO 7 lõi");
		ITestResult result = Reporter.getCurrentTestResult();
		result.setAttribute("actualResult", actualResult);
		Assert.assertEquals(actualResult, expectedResult);
	}

	private void chooseCategory(String category) {
		WebElement menu = waitForElement(By.id("menu2017"));
		List<WebElement> listCate = menu.findElements(By.tagName("li"));
		chooseCate: {
			for (int i = 0; i < listCate.size(); i++) {
				List<WebElement> cate = listCate.get(i).findElements(By.tagName("a"));
				for (int j = 0; j < cate.size(); j++) {
					if (cate.get(j).getText().contentEquals(category)) {
						cate.get(j).click();
						break chooseCate;
					}
				}
			}
		}
	}

	private void chooseManufacture(String manufac) {
		WebElement manufacture = waitForElement(
				By.xpath("//aside[@class='filter bannerFilter']//child::div[@class='test manufacture show-10']"));
		scrollToElement(manufacture);
		List<WebElement> listManufac = manufacture.findElements(By.tagName("a"));
		for (int i = 0; i < listManufac.size(); i++) {
			if (listManufac.get(i).getAttribute("title").contentEquals(manufac) && !listManufac.get(i).isSelected()) {
				listManufac.get(i).click();
				break;
			}
		}
	}

	private WebElement[][] getTable() {
		WebElement table = driver.findElement(By.id("product-list"));
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

	private void getProduct(String nameProduct) {
		WebElement[][] dataTable = getTable();
		getProduct: {
			for (int i = 0; i < dataTable.length; i++) {
				for (int j = 0; j < dataTable[i].length; j++) {
					if (dataTable[i][j] != null) {
						WebElement product = dataTable[i][j];
						String getProductName = product.findElement(By.tagName("span")).getText().trim();
						if (getProductName.contentEquals(nameProduct)) {
							WebElement productFound = product.findElement(By.tagName("a"));
							scrollToElement(productFound);
							productFound.click();
							break getProduct;
						}
					}
				}
			}
		}
	}

	private void scrollToElement(WebElement elm) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = elm;
		js.executeScript("arguments[0].scrollIntoView();", element);
		js.executeScript("window.scrollBy(0,-185)");
	}

	private String compareTitle(String expected) {
		String actual = driver.findElement(By.xpath("//section[@id='main-container']//child::h1")).getText();
		if(actual.equals(expected))
			return "Hệ thống hiển thị đúng thông tin chi tiết sản phẩm đó";
		else
			return "Hệ thống hiển thị không đúng thông tin chi tiết sản phẩm đó";
	}

	private WebElement waitForElement(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
}
