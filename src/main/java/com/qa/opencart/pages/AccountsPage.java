package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private final By headers = By.cssSelector("div#content h2");
	private final By logoutLink = By.linkText("Logout");
	private final By searchPlaceholder = By.xpath("//input[@name=\"search\"]");
	private final By searchBttn = By.xpath("//button[@class=\"btn btn-default btn-lg\"]");

	private static final Logger log = LogManager.getLogger(AccountsPage.class);

	public List<String> AccountPageHeaders() {
		List<WebElement> headerList = eleUtil.waitForElementsPresence(headers, AppConstants.DEFAULT_SHORT_WAIT);
		//List<WebElement> headerList= driver.findElements(headers);
		//System.out.println("size of the headerlist is :"+ headerList.size());
		log.info("size of the headerlist is :"+ headerList.size());
		List<String> headersFullList = new ArrayList<String>();
		for(WebElement e : headerList) {
			String headerListText = e.getText();
			headersFullList.add(headerListText);
		}
		return headersFullList;	
	}

	public boolean islogoutLinkExist() {
		WebElement logoutEle = eleUtil.waitForElementVisible(logoutLink, AppConstants.DEFAULT_MEDIUM_WAIT);
	    return eleUtil.isElementDisplayed(logoutEle);
	}

	public SearchResultPage dosearch(String productName) {
		System.out.println("you are searching the product ---> "+ productName);
		WebElement searchEle = eleUtil.waitForElementVisible(searchPlaceholder, AppConstants.DEFAULT_SHORT_WAIT);
		searchEle.clear();
		searchEle.sendKeys(productName);
		eleUtil.doClick(searchBttn);
		return new SearchResultPage(driver);
			
		//driver.findElement(searchPlaceholder).sendKeys(productName);
		//driver.findElement(searchBttn).click();
	}
}
