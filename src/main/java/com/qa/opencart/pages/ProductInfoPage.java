package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String,String> productMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private final By productNameText = By.tagName("h1");
	private final By productImages = By.cssSelector("ul.thumbnails img");
	private final By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private final By productPriceMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	
	public String productHeader() {
		String productHeaderText = eleUtil
									.waitForElementVisible(productNameText, AppConstants.DEFAULT_SHORT_WAIT).getText();
		System.out.println("product header name is --> "+ productHeaderText);
		return productHeaderText;
	}
	
	public int getProductImages() {
		int imagesCount = eleUtil.waitForElementsVisible(productImages, AppConstants.DEFAULT_MEDIUM_WAIT).size();
		System.out.println("Images count is "+ imagesCount);
		return imagesCount;	
	}
	
	public Map<String, String> getProductData() {
		
		//productMap = new HashMap<String,String>(); // create the object of hashmap & it is pointing to null, random order
		
		// productMap = new LinkedHashMap<String,String>();// Exact insertion order
		
		productMap = new TreeMap<String,String>(); // sorted key order
		
		productMap.put("productheader", productHeader());
		productMap.put("productimages", String.valueOf(getProductImages()));
		getProductMetaData();
		getProductPriceMetaData();
		System.out.println("========== product data ============\n"+ productMap);
		return productMap;
		
	}
	
	private void getProductMetaData() {
		List<WebElement> listProductMetaData = eleUtil
				                              .waitForElementsVisible(productMetaData, AppConstants.DEFAULT_MEDIUM_WAIT);
		System.out.println("Meta data Product list is"+ listProductMetaData.size());
			
		for(WebElement e : listProductMetaData) {
			String metadata = e.getText();
			String meta[] = metadata.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productMap.put(metaKey, metaValue);
		}	
	}
	
	private void getProductPriceMetaData() {
		List<WebElement> listProductPriceMetaData = eleUtil
				                              .waitForElementsVisible(productPriceMetaData, AppConstants.DEFAULT_MEDIUM_WAIT);
		System.out.println("total price meta data"+ listProductPriceMetaData.size());
			
		//productMap = new HashMap<String,String>(); // create the object of hashmap & it is pointing to null 
		String taxValue = listProductPriceMetaData.get(0).getText();
		String extTaxValue = listProductPriceMetaData.get(1).getText().split(":")[1].trim();
		
		productMap.put("productprice", taxValue);
		productMap.put("extproductprice", extTaxValue);
		}
		
		
	}
	

