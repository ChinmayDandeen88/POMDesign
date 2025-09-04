package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest{

	@BeforeClass
	public void accPageSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));	
	}
	
	@Test
	public void cisLogoutLinkExistTest() {
		Assert.assertTrue(accPage.islogoutLinkExist());
	}
	
	@Test
	public void accountPageHeadersTest() {
		List<String> actualHeaderList = accPage.AccountPageHeaders();
		Assert.assertEquals(actualHeaderList.size(),AppConstants.ACC_PAGE_HEADER_COUNT);
		Assert.assertEquals(actualHeaderList,AppConstants.expected_ACC_Page_Header_List);
	}
	
	@Test
	public void doSearchTest() {
		accPage.dosearch("Macbook Pro");
	}
}
