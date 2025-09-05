package com.qa.opencart.pages;

import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class InvoicePage { // dummycode has no meaning 

	private WebDriver driver;
	private ElementUtil eleUtil;

	public void SearchResultPage1(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
}
	