package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AbstractAnnotation.*;

public class DMXViewProductThroughList extends AbstractClass {
	By menu = By.id("menu2017");
	By manufactureName = By.xpath("//aside[@class='filter bannerFilter']//child::div[@class='test manufacture show-10']");
	By table = By.id("product-list");
	By allProduct = By.xpath("//div[contains(@class,'prdItem')]");
	By productName = By.tagName("span");
	
	By titleProductName = By.xpath("//section[@id='main-container']//child::h1");

	By listCate = By.tagName("li");
	By eachItem = By.tagName("a");
	
	public List<WebElement> getListCategory() {
		return waitForElement(menu).findElements(listCate);
	}
	public List<WebElement> getManufactureList(){
		return waitForElement(manufactureName).findElements(eachItem);
	}

	public List<WebElement> getAllProduct(){
		return driver.findElement(table).findElements(allProduct);
	}
	public void chooseCategory(String category) {
		chooseCate: {
			for (int i = 0; i < getListCategory().size(); i++) {
				List<WebElement> cate = getListCategory().get(i).findElements(eachItem);
				for (int j = 0; j < cate.size(); j++) {
					if (cate.get(j).getText().contentEquals(category)) {
						cate.get(j).click();
						break chooseCate;
					}
				}
			}
		}
	}

	public void chooseManufacture(String manufac) {
		scrollToElement(waitForElement(manufactureName));
		for (int i = 0; i < getManufactureList().size(); i++) {
			if (getManufactureList().get(i).getAttribute("title").contentEquals(manufac) && !getManufactureList().get(i).isSelected()) {
				getManufactureList().get(i).click();
				break;
			}
		}
	}

	public WebElement[][] getTable() {
		WebElement[][] dataTable = new WebElement[(getAllProduct().size() / 4) + 1][4];
		int countRow = 0;
		int countCol = 0;
		for (int i = 0; i < getAllProduct().size(); i++) {
			if (countRow < 4) {
				dataTable[countCol][countRow] = getAllProduct().get(i);
				countRow++;
			} else {
				countCol++;
				countRow = 0;
				dataTable[countCol][countRow] = getAllProduct().get(i);
				countRow++;
			}
		}
		return dataTable;
	}

	public void getProduct(String nameProduct) {
		WebElement[][] dataTable = getTable();
		getProduct: {
			for (int i = 0; i < dataTable.length; i++) {
				for (int j = 0; j < dataTable[i].length; j++) {
					if (dataTable[i][j] != null) {
						WebElement product = dataTable[i][j];
						String getProductName = product.findElement(productName).getText().trim();
						if (getProductName.contentEquals(nameProduct)) {
							WebElement productFound = product.findElement(productName);
							scrollToElement(productFound);
							productFound.click();
							break getProduct;
						}
					}
				}
			}
		}
	}

	public void scrollToElement(WebElement elm) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = elm;
		js.executeScript("arguments[0].scrollIntoView();", element);
		js.executeScript("window.scrollBy(0,-185)");
	}

	public String compareTitle(String expected) {
		String actual = driver.findElement(titleProductName).getText();
		if (actual.equals(expected))
			return "Hệ thống hiển thị đúng thông tin chi tiết sản phẩm đó";
		else
			return "Hệ thống hiển thị không đúng thông tin chi tiết sản phẩm đó";
	}

	public WebElement waitForElement(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

}
