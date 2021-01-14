package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AbstractClass.abstractLink;
import BaseClass.ExcelUtils;

public class LinkPage extends abstractLink {
	//By link = By.xpath("//li//a[@href='"+ExcelUtils.getCellData(i, COL_LINK)+"']"));
	
public WebElement getLink(String data) {
	return driver.findElement(By.xpath("//li//a[@href='"+data+"']"));
}
public void clickLink (String data) {
	this.getLink(data);
}
}
