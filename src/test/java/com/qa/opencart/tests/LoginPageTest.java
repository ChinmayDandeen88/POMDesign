package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;



@Epic("epic : 100, design the open cart application page....")
@Feature("login page design of open cart")
@Story("develop login core features,such as login, title,forget password")
public class LoginPageTest extends BaseTest {
	
	
	@Description("login page title test")
	@Owner("chinmay")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void LoginPageTitleTest() {
		String actualTitle = loginPage.getLoginPageTitle();
		ChainTestListener.log("Actual title is "+ actualTitle);
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	
	@Description("login page url test")
	@Owner("chinmay")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void getLoginPageURLTest() {
		String actualURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actualURL.contains(AppConstants.ACC_PAGE_FRACTION_URL));
	}
	
	
	@Description("login page password exist test")
	@Owner("chinmay")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void isPasswordLinkExistTest() {
		Assert.assertTrue(loginPage.isPasswordLinkExist());
	}
	
	
	@Description("login page is header exist test")
	@Owner("chinmay")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void isHeaderExistTest() {
		Assert.assertTrue(loginPage.isHeaderExist());
	}
	
	
	@Description("login page test")
	@Owner("chinmay")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void zLoginTest() {
		 accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		 Assert.assertTrue(accPage.islogoutLinkExist());
		 //Assert.assertEquals(actualAccountPageTitle, "My Account");
	}
	
}

