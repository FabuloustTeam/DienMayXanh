package com.dienmayxanh.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.dienmayxanh.abstractclass.*;
import com.dienmayxanh.service.Element;

public class LinkPage extends AbstractAnnotation {

	private Element elementService;

	public LinkPage() {
		elementService = new Element();
	}

	public WebElement getLink(String data) {
		return elementService.waitForElementClickable(By.xpath("//li//a[@href='" + data + "']"));
	}

}