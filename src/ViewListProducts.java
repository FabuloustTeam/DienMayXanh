import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class ViewListProducts {
	WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {
		// 1. Truy cập trang chủ điện máy xanh
		accessWebsite("https://www.dienmayxanh.com/");
	}
	
	@AfterMethod
	public void closeBrowser() {
		this.driver.close();
	}
	
	/**
	 * Test requirement: TR-DMX-VLP-01 Test Case ID: TC-DMX-VLP-01
	 */
	@Test(priority = 1)
	public void ShowAllProduct() {
		// 2. Nhấn chọn danh sách sản phẩm trong danh mục
		WebElement locnuoc = driver.findElement(By.xpath("//a[@href='/may-loc-nuoc']"));
		locnuoc.click();

		boolean isAllProductTrueName = true;
		List<WebElement> products = getAllProducts();
		for (int i = 0; i < products.size(); i ++) {
			WebElement spanName = products.get(i).findElement(By.xpath("//div[@class='prdName']//span"));
			if(!spanName.getText().toLowerCase().contains("máy lọc nước")) {
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
	private void ShowAllProductOfBrand() {
		WebElement locnuoc = driver.findElement(By.xpath("//a[@href='/may-loc-nuoc']"));
		locnuoc.click();
		// 3. Chọn hãng
		List<WebElement> listBrand = driver.findElements(By.xpath("//div[@class=\"test manufacture show-10\"]/a"));
		JavascriptExecutor jsEx = (JavascriptExecutor) driver;
		jsEx.executeScript(
				"var el = document.querySelectorAll('div.test.manufacture.show-10')[0]; el.scrollIntoView(true)");
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
	 */
	@Test (priority = 3)
	public void testViewWithSortPriceDescending() {
		// 2. Nhấn chọn danh sách sản phẩm trong danh mục
		chooseCategory("Lọc nước");

		// 3. Chọn sắp xếp
		chooseSort(" Giá cao đến thấp ");

		WebElement[][] dataTable = getTable();
		boolean actual = checkDescending(dataTable);
		Assert.assertEquals(actual, true);
	}
	
	/**
	 * Test requirement: TR-DMX-VLP-02. Test case ID: TC-DMX-VLP-04
	 */
	@Test (priority = 4)
	public void testViewWithSortPriceAscending() {
		// 2. Nhấn chọn danh sách sản phẩm trong danh mục
		chooseCategory("Lọc nước");

		// 3. Chọn sắp xếp
		chooseSort(" Giá thấp đến cao ");

		WebElement[][] dataTable = getTable();
		boolean actual = checkAscending(dataTable);
		Assert.assertEquals(actual, true);
	}
	
	/**
	 * Test requirement: TR-DMX-VLP-03. Test case ID: TC-DMX-VLP-05
	 */
	@Test (priority = 5)
	public void testQuantity() {
		// 2. Nhấn chọn danh sách sản phẩm trong danh mục
		chooseCategory("Lọc nước");
		
		waitForProductsLoad();
		int quantityBreadcrumb = getQuantityBreadcrumb();
		int quantityProducts = getQuantityProducts();
		int quantityTotal = getQuantityTotal();
		Assert.assertEquals(quantityBreadcrumb, quantityProducts);
		Assert.assertEquals(quantityTotal, quantityProducts);
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
		WebElement sortPriceDescending = waitForElementClickable(
				By.xpath("//aside[@id='scroll-MLN']//child::a[text()='" + sortType + "']"));
		sortPriceDescending.click();
		waitForElementInvisible(By.id("dlding"));

		waitForProductsLoad();
	}

	private void waitForProductsLoad() {
		JavascriptExecutor js = (JavascriptExecutor) this.driver;
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

	private void accessWebsite(String url) {
		System.setProperty("webdriver.chrome.silentOutput", "true");
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		this.driver.get(url);
	}

	private WebElement waitForElementClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(this.driver, 10);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	private void waitForElementInvisible(By locator) {
		WebDriverWait wait = new WebDriverWait(this.driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	private WebElement waitForElementVisibility(By locator) {
		WebDriverWait wait = new WebDriverWait(this.driver, 10);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

}
