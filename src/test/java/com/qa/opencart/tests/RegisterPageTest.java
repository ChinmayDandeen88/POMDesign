package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.CsvUtil;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void goToRegister() {

		registerPage = loginPage.navigateToRegistrationPage();
	}

	@DataProvider
	public Object[][] getRegData() {
		return new Object[][] {
			{"test_04","uiautomation","9182883487","chin@123","yes"},
			{"test_02","uiautomation","9182883488","chin@123","yes"},
			{"test_03","uiautomation","9182883489","chin@123","yes"},
		};
	}
	
	/**
	 * this method works with excel sheet 
	 * returns object array of two dimensional 
	 */
	@DataProvider
	public Object[][] getregSheetData() {
		return ExcelUtil.getTestData("register");
	}
	
	
	/**
	 * this method works with csv file 
	 * returns object array of two dimensional 
	 */
	@DataProvider
	public Object[][] getregCSVdata(){
		return CsvUtil.csvData("register");
	}

	@Test(dataProvider="getregCSVdata")
	public void goToUserRegistrationTest(String firstname,String lastname,String telephone,String password,String subscribe) {
		Assert.assertTrue
		(registerPage.userRegister(firstname, lastname, StringUtil.getRandomEmail(), telephone, password, subscribe));
	}

}

