package com.qa.opencart.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private final By searchResult = By.cssSelector("div.product-thumb");
	private final By searchHeader = By.cssSelector("div#content h1"); 
	
	private static final Logger log = LogManager.getLogger(SearchResultPage.class);
	
	// * ---------------------------------------------------------------------------------- *// 
	public int getSearchResultsCount() {
		int resultsCount = eleUtil.waitForElementsPresence(searchResult, AppConstants.DEFAULT_MEDIUM_WAIT).size();
		//System.out.println("results count is ---> "+ resultsCount);
		log.info("results count is ---> "+ resultsCount);
		return resultsCount;
	}
	
	public String getResultsHeaderValue() {
		String headerText = eleUtil.doElementGetText(searchHeader);
		//System.out.println("search text header is --> "+ headerText);
		log.info("search text header is --> "+ headerText);
		return headerText;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		//System.out.println("product name is ---> "+ productName);
		log.info("product name is ---> "+ productName);
		eleUtil.doClick(By.linkText(productName));
		return new ProductInfoPage(driver); 
	}
}
