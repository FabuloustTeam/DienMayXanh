package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AbstractClasses.AbstractBOT;

public class ViewListProductsPage extends AbstractBOT{
	By web = By.xpath("//*[@href='/may-loc-nuoc']");
	By clickbrand = By.xpath("//*[@href='may-loc-nuoc-kangaroo']");
	

	
	public void showAllProduct () {
		waitForElementClickable(web);
	}
	public void showAllProductOfBrand() {
		waitForElementClickable(clickbrand);
	}
	
	public void viewListProducts() {
		this.showAllProduct();
	}
	public void viewListProductOfBrand() {
		this.showAllProductOfBrand();
	}
	
	public WebElement waitForElementClickable(final By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));

	}
}
