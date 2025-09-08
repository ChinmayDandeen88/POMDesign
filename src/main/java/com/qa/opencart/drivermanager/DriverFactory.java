package com.qa.opencart.drivermanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	
	public static String highlightElement;
	
	public static ThreadLocal<WebDriver> tldriver= new ThreadLocal<WebDriver>();
	
	private static final Logger log = LogManager.getLogger(DriverFactory.class);
	
	/**
	 * This method is initializing the driver on basis of browser
	 * @param browserName
	 * @return this returns driver instance 
	 */
	public WebDriver initDriver(Properties prop) {
		
		String browserName = prop.getProperty("browser");
		//System.out.println("browser name is "+ browserName);
		log.info("browserName is "+ browserName);
		
		
		highlightElement = prop.getProperty("highlight");
		
		switch (browserName.trim().toLowerCase()) {
		
		case "chrome":
			//driver = new ChromeDriver();
			tldriver.set(new ChromeDriver());
			break;

		case "firefox":
			//driver = new FirefoxDriver();
			tldriver.set(new FirefoxDriver());
			break;
			
		case "safari":
			//driver = new SafariDriver();
			tldriver.set(new SafariDriver());
			break;
			
		case "edge":
			//driver = new EdgeDriver();
			tldriver.set(new EdgeDriver());
			break;
			
		default:
			//System.out.println(AppError.INVALID_BROWSER_MSG + ":"+ browserName );
			log.error("AppError.INVALID_BROWSER_MSG + :" + browserName);
			throw new FrameworkException("======== INVALID BROWSER NAME GIVEN ============");
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
	}
	
	  public static WebDriver getDriver() {
		  return tldriver.get();
	  }
	
	/**
	 * This method is init prop with property file..
	 * @return
	 */
	
	
	public Properties initProp() {
		
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resourcess/config/config.properties");
			try {
				prop.load(ip);
			} 
			catch (IOException e) {	
				e.printStackTrace();
			}
		} 
		catch (FileNotFoundException e) {	
			e.printStackTrace();
		}
		return prop;
	}
	
	public static File getscreenshotFile() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		return srcFile;
	}
	
	public static byte[] getscreenshotByte() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);	
	}
	
	public static String getscreenshotBase64() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);		
	}
	
	
	
	
	
}
