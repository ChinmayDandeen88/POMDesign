package com.qa.opencart.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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

	private final By firstName = By.xpath("//input[@id=\"input-firstname\"]");// reverted the changed
	private final By lastName = By.xpath("//input[@id=\"input-lastname\"]");
	private final By emailId = By.xpath("//input[@id=\"input-email\"]");
	private final By telephoneNumber = By.xpath("//input[@id=\"input-telephone\"]");
	private final By password = By.xpath("//input[@id=\"input-password\"]");
	private final By confirmPassword = By.xpath("//input[@id=\"input-confirm\"]");
	private final By subscribeYes = By.xpath("(//input[@type=\"radio\"])[2]");
	private final By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");
	private final By checkPrivacy = By.xpath("//input[@name=\"agree\"]");
	private final By continueBttn = By.xpath("//input[@value=\"Continue\"]");
	private final By successMsg = By.tagName("h1");
	private final By logoutBttn = By.linkText("Logout");
	private final By registerLink = By.linkText("Register");

	private static final Logger log = LogManager.getLogger(RegisterPage.class);


	public boolean userRegister(String firstname,String lastname,String emailid,String telephonenum, 
			String passwordf,String subscribe) {

		eleUtil.doSendKeys(firstName, firstname);
		eleUtil.doSendKeys(lastName, lastname);
		eleUtil.doSendKeys(emailId, emailid);
		eleUtil.doSendKeys(telephoneNumber, telephonenum);
		eleUtil.doSendKeys(password, passwordf);
		eleUtil.doSendKeys(confirmPassword, passwordf);
		eleUtil.doSendKeys(subscribeNo, subscribe);		

		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		}
		else {
			eleUtil.doClick(subscribeNo);
		}
		eleUtil.doClick(checkPrivacy);
		eleUtil.doClick(continueBttn);
		
		String RegistersuccessMsg = eleUtil.waitForElementVisible(successMsg,AppConstants.DEFAULT_MEDIUM_WAIT).getText();
		System.out.println(RegistersuccessMsg);
		log.info(RegistersuccessMsg);
		if(RegistersuccessMsg.contains(AppConstants.USER_REGISTER_SUCCESS_MSG)) {
			eleUtil.doClick(logoutBttn);
			eleUtil.doClick(registerLink);	
			return true;
		} else {
			return false;
		}
		
	}
}