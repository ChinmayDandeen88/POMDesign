package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.drivermanager.DriverFactory;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;


public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	// private By locators : page objects
	
	private final By emailID = By.xpath("//input[@id='input-email']");
	
	                           // By.xpath("//input[@id='password']");
	private final By password = By.id("input-password");
	
	private final By loginBttn = By.xpath("//input[@value='Login']");
	
	private final By forgotpasswordLink = //By.linkText("Forgotten Password");
			                              By.xpath("(//a[text()='Forgotten Password'])[1]");
	
	private final By headerName =  By.tagName("h2");
			                       //By.xpath("//h2[text()='New Customer']");
	
	private final By RegistarLink = By.linkText("Register");
	private final By shop = By.linkText("shop"); // dummy xpath 
	
	private static final Logger log = LogManager.getLogger(LoginPage.class);
	
	// public constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	// public page methods / actions 
	@Step("getting login page title...")
	public String getLoginPageTitle() {	
		String loginpagetitle = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_SHORT_WAIT);
		//System.out.println("Login Page Title is :" + loginpagetitle);
		log.info("Login Page Title is :" + loginpagetitle);
		return loginpagetitle;
	}
	
	@Step("getting login page URL...")
	public String getLoginPageURL() {
		String loginPageURL = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_FRACTION_URL,AppConstants.DEFAULT_SHORT_WAIT);
		//String loginPageURL = driver.getCurrentUrl();
		//System.out.println("Login Page URL is :"+loginPageURL);
		log.info("Login Page URL is :" +loginPageURL);
		return loginPageURL;
	}
	
	@Step("getting login page password link...")
	public boolean isPasswordLinkExist() {
		 return eleUtil.isElementDisplayed(forgotpasswordLink);
		 
	}
	
	@Step("getting login page header exist...")
	public boolean isHeaderExist() {
		return eleUtil.isElementDisplayed(headerName);
	}
	
	@Step("getting login page login inputs...")
	public AccountsPage doLogin(String Appusername,String Apppwd) {
		//System.out.println("username is :"+ Appusername + " password is :"+ Apppwd);
		log.info("username is :"+ Appusername + " password is :"+ Apppwd);
		eleUtil.waitForElementVisible(emailID, AppConstants.DEFAULT_MEDIUM_WAIT).sendKeys(Appusername);
		eleUtil.doSendKeys(password, Apppwd);
		eleUtil.doClick(loginBttn);
		// driver.findElement(emailID).sendKeys(Appusername);
		//driver.findElement(password).sendKeys(Apppwd);
		//driver.findElement(loginBttn).click();
		return new AccountsPage(driver);
	}
	
	@Step("navigating to registration page ...")
	public RegisterPage navigateToRegistrationPage() {
		eleUtil.waitForElementVisible(RegistarLink, AppConstants.DEFAULT_SHORT_WAIT);
		eleUtil.doClick(RegistarLink);
		return new RegisterPage(driver);
	}
}
