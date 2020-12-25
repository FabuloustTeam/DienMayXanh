package com.dienmayxanh.page;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.dienmayxanh.abstractclass.*;
import com.dienmayxanh.service.Element;

public class ViewListProductsPage extends AbstractAnnotation {

	By totalLocator = By.xpath("//div[contains(@class,'totalfilter')]//child::span");
	By quantityBreadcrumbLocator = By.id("breadcrumb_total");
	By pricesLocator = By.tagName("strong");
	By tableLocator = By.id("product-list");
	By allProductsLocator = By.xpath("//div[contains(@class,'prdItem')]");
	By orderTypeLocator = By.xpath("//div[@class='ordertype twonum']");
	By loadMoreLocator = By.xpath("//a[@class='viewmore']");
	By loadingLocator = By.id("dlding");
	By uListCategoriesLocator = By.xpath("//span[@id='dmsp']//following::nav//child::ul");
	By liCategoriesLocator = By.tagName("li");
	By categoriesLocator = By.tagName("a");
	By orderTypesLocator = By.tagName("a");
	private Element elementService;
	
	public ViewListProductsPage() {
		elementService = new Element();
	}
	
	public int getQuantityTotal() {
		WebElement total = elementService.waitForElementVisibility(totalLocator);
		return Integer.parseInt(total.getText().trim());
	}

	public int getQuantityProducts() {
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

	public int getQuantityBreadcrumb() {
		WebElement quantityBreadcrumb = elementService.waitForElementVisibility(quantityBreadcrumbLocator);
		return Integer.parseInt(quantityBreadcrumb.getText().trim());
	}

	public boolean checkAscending(WebElement[][] dataTable) {
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

	public boolean checkDescending(WebElement[][] dataTable) {
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

	public int getPrice(WebElement cellTable) {
		List<WebElement> prices = cellTable.findElements(pricesLocator);
		WebElement price;
		if (prices.size() > 1) {
			price = prices.get(prices.size() - 1);
		} else {
			price = prices.get(0);
		}
		String priceValue = price.getText().replace("₫", "").replace(".", "");
		return Integer.parseInt(priceValue);
	}

	public WebElement[][] getTable() {
		WebElement table = elementService.waitForElementVisibility(tableLocator);
		List<WebElement> allProducts = table.findElements(allProductsLocator);
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
	
	public List<WebElement> getAllProducts() {
		WebElement table = elementService.waitForElementVisibility(tableLocator); 
		List<WebElement> allProducts = table.findElements(allProductsLocator);
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

	public void chooseSort(String sortType) {
		WebElement orderType = elementService.waitForElementVisibility(orderTypeLocator);
		List<WebElement> orderTypes = orderType.findElements(orderTypesLocator);
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
		
		elementService.waitForPageLoad();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		elementService.waitForElementInvisible(loadingLocator);

		waitForProductsLoad();
	}

	public void waitForProductsLoad() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		while (true) {
			try {
				WebElement loadMore = elementService.waitForElementClickable(loadMoreLocator);
				js.executeScript("arguments[0].scrollIntoView();", loadMore);
				js.executeScript("window.scrollBy(0,-130)");
				loadMore.click();
				elementService.waitForElementInvisible(loadingLocator);
			} catch (Exception e) {
				System.out.println(e.toString());
				break;
			}
		}
	}

	public void chooseCategory(String category) {
		WebElement uListCategories = elementService.waitForElementClickable(uListCategoriesLocator);
		List<WebElement> liCategories = uListCategories.findElements(liCategoriesLocator);
		chooseCategory: {
			for (int i = 0; i < liCategories.size(); i++) {
				List<WebElement> categories = liCategories.get(i).findElements(categoriesLocator);
				for (int j = 0; j < categories.size(); j++) {
					if (categories.get(j).getText().contentEquals(category)) {
						categories.get(j).click();
						break chooseCategory;
					}
				}
			}
		}
	}

}
