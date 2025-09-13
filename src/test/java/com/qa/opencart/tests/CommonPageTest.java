package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class CommonPageTest extends BaseTest{

	@Test
	public void checkCommonElementsOnLoginPageTest() {

		SoftAssert softassert = new SoftAssert();

		softassert.assertTrue(commonPage.isLogoExits());
		softassert.assertTrue(commonPage.isSearchSpaceExists());

		List<String>footerList = commonPage.isFooterLinksExist();
		softassert.assertEquals(footerList.size(), AppConstants.DEFAULT_FOOTER_COUNT);

		System.out.println("Total footer links present is "+ footerList.size());
		softassert.assertAll();
	}

	@Test
	public void checkCommonElementsOnAccountsPageTest() {

		loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		SoftAssert softassert = new SoftAssert();

		softassert.assertTrue(commonPage.isLogoExits());
		softassert.assertTrue(commonPage.isSearchSpaceExists());

		List<String>footerList = commonPage.isFooterLinksExist();
		softassert.assertEquals(footerList.size(), AppConstants.DEFAULT_FOOTER_COUNT);

		System.out.println("total footer links present is "+ footerList.size());
		softassert.assertAll();
	}

}
