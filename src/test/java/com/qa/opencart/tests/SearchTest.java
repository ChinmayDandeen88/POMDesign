package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchTest extends BaseTest{

	@BeforeClass
	public void searchSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}

	@Test
	public void bsearchTest() {
		searchResultPage = accPage.dosearch("MacBook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		String productActualName= productInfoPage.productHeader();
		Assert.assertEquals(productActualName,"MacBook Pro" );
	}
	
	
	public void asearchResultCount() {
		int searchcount = searchResultPage.getSearchResultsCount();
		System.out.println("total searches appeared is "+ searchcount);
		//return searchcount;
	}

}
