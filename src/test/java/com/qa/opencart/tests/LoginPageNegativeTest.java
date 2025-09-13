package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class LoginPageNegativeTest extends BaseTest{

	@DataProvider()
	public Object[][] getNegativeLoginData() {
		return new Object[][] {
			{"testchinmay@231","password"},
			{"march2024@open.com","@@##$#"},
			{"","qwerty"},
			{"",""}
		};
	}
	
	
	@Test (dataProvider="getNegativeLoginData")
	public void negativeLoginTest(String invalidUserName,String invalidPassword) {
		Assert.assertTrue(loginPage.doLoginWithInvalidCredentials(invalidUserName, invalidPassword));
	}
}
