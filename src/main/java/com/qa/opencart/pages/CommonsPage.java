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

public class CommonsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public CommonsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private final By logo = By.xpath("//img[@class=\"img-responsive\"]");
	private final By searchSpace = By.xpath("//input[@name=\"search\"]");
	private final By footerLinks = By.cssSelector("footer li a");
	
	private static final Logger log = LogManager.getLogger(CommonsPage.class);
	
	public boolean isLogoExits() {
		return  eleUtil.isElementDisplayed(logo);
	}
	
	public boolean isSearchSpaceExists() {
		return eleUtil.isElementDisplayed(searchSpace);
	}
	
	public List<String> isFooterLinksExist() {
		List<WebElement> footerLists = eleUtil.waitForElementsVisible(footerLinks, AppConstants.DEFAULT_MEDIUM_WAIT);
		List<String> footerValueList = new ArrayList<String>();
		for(WebElement e : footerLists) {
			String text = e.getText();
			footerValueList.add(text);
		}
		return footerValueList;
	}
	
}
