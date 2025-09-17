package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;


//package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	private final By firstName = By.id("input-firstname");
	private final By lastName = By.id("input-lastname");
	private final By email = By.id("input-email");
	private final By telephone = By.id("input-telephone");
	private final By password = By.id("input-password");
	private final By confirmpassword = By.id("input-confirm");

	private final By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private final By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");

	private final By agreeCheckBox = By.name("agree");
	private final By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

	private final By successMessg = By.cssSelector("div#content h1");

	private final By logoutLink = By.linkText("Logout");
	private final By registerLink = By.linkText("Register");

	public boolean userRegister(String firstName, String lastName, String email, String telephone, String password,
			String subscribe) {

		eleUtil.waitForElementVisible(this.firstName, AppConstants.DEFAULT_SHORT_WAIT).sendKeys(firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmpassword, password);

		if (subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		} else {
			eleUtil.doClick(subscribeNo);
		}

		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueButton);

		String successMesg = eleUtil.waitForElementVisible(successMessg, AppConstants.DEFAULT_MEDIUM_WAIT).getText();
		System.out.println(successMesg);

		if (successMesg.contains(AppConstants.USER_REGISTER_SUCCESS_MSG)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		} else {
			return false;
		}

	}

}
//public class RegisterPage {
//
//	private WebDriver driver;
//	private ElementUtil eleUtil;
//
//	public RegisterPage(WebDriver driver) {
//		this.driver = driver;
//		eleUtil = new ElementUtil(driver);
//	}
//
//	private final By firstName = By.xpath("//input[@id=\"input-firstname\"]");// reverted the changed
//	private final By lastName = By.xpath("//input[@id=\"input-lastname\"]");
//	private final By emailId = By.xpath("//input[@id=\"input-email\"]");
//	private final By telephoneNumber = By.xpath("//input[@id=\"input-telephone\"]");
//	private final By password = By.xpath("//input[@id=\"input-password\"]");
//	private final By confirmPassword = By.xpath("//input[@id=\"input-confirm\"]");
//	private final By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']"); //By.xpath("(//input[@type=\"radio\"])[2]");
//	private final By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']"); // By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");
//	private final By checkPrivacy = By.name("agree"); //.xpath("//input[@name=\"agree\"]");
//	private final By continueBttn = By.xpath("//input[@type='submit' and @value='Continue']"); //By.xpath("//input[@type='submit' and @value='Continue']");
//	//private final By continueBttn = By.xpath("//input[@value=\"Continue\"]");
//	private final By successMsg = By.tagName("h1");
//	private final By logoutBttn = By.linkText("Logout");
//	private final By registerLink = By.linkText("Register");
//
//	private static final Logger log = LogManager.getLogger(RegisterPage.class);
//
//
//	public boolean userRegister(String firstname,String lastname,String emailid,String telephonenum, 
//			String passwordf,String subscribe) {
//
//		eleUtil.doSendKeys(firstName, firstname);
//		eleUtil.doSendKeys(lastName, lastname);
//		eleUtil.doSendKeys(emailId, emailid);
//		eleUtil.doSendKeys(telephoneNumber, telephonenum);
//		eleUtil.doSendKeys(password, passwordf);
//		eleUtil.doSendKeys(confirmPassword, passwordf);
//		eleUtil.doSendKeys(subscribeNo, subscribe);		
//
//		
//		if(subscribe.equalsIgnoreCase("yes")) {
//			eleUtil.doClick(subscribeYes);
//		}
//		else {
//			eleUtil.doClick(subscribeNo);
//		}
//		
//		eleUtil.doClick(checkPrivacy);
//		eleUtil.doClick(continueBttn);
//		
//		String RegistersuccessMsg = eleUtil.waitForElementVisible(successMsg,AppConstants.DEFAULT_MEDIUM_WAIT).getText();
//		System.out.println(RegistersuccessMsg);
//		log.info(RegistersuccessMsg);
//		if(RegistersuccessMsg.contains(AppConstants.USER_REGISTER_SUCCESS_MSG)) {
//			eleUtil.doClick(logoutBttn);
//			eleUtil.doClick(registerLink);	
//			return true;
//		} else {
//			return false;
//		}
//		
//	}
//}