package com.qa.opencart.base;

import java.util.Properties;
import io.qameta.allure.testng.AllureTestNg; // or the correct class name


import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.drivermanager.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultPage;
import com.qa.opencart.listeners.TestAllureListener;


import io.qameta.allure.Description;

import com.qa.opencart.pages.ProductInfoPage;



//@Listeners(ChainTestListener.class) // for chain test reporting - add this 
//@Listeners({ChainTestListener.class, TestAllureListener.class})
public class BaseTest {

	protected WebDriver driver;
	protected Properties prop;
	DriverFactory df;
	
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected SearchResultPage searchResultPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterPage registerPage;

	@Description("launch te browser {0} and the url ")
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(String browserName) {
		df = new DriverFactory();
		prop = df.initProp();
		
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
	}
	
	@Description("taking the screenshot of each test method..")
	@AfterMethod
	public void attachScreenshot(ITestResult result) {
		if(!result.isSuccess()) {
			ChainTestListener.embed(DriverFactory.getscreenshotFile(), "image/png");
		}
		ChainTestListener.embed(DriverFactory.getscreenshotFile(), "image/png");
	}

	@Description("closing the browser..")
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
