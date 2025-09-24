package com.qa.opencart.drivermanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;

	public static String highlightElement;

	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();

	private static final Logger log = LogManager.getLogger(DriverFactory.class);

	public OptionsManager optionsManger;


	/**
	 * This method is initializing the driver on basis of browser
	 * 
	 * @param browserName
	 * @return this returns driver instance
	 */
	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");
		// System.out.println("browser name is "+ browserName);
		log.info("browserName is " + browserName);

	highlightElement = prop.getProperty("highlight");
		optionsManger = new OptionsManager(prop);

		boolean remoteExecution = Boolean.parseBoolean(prop.getProperty("remote"));

		switch (browserName.trim().toLowerCase()) {

		case "chrome":
			if(remoteExecution) {
				init_remoteDriver("chrome"); //run the test cases on selenium grid
			}
			else {
				tldriver.set(new ChromeDriver(optionsManger.getChromeOptions()));
			}
			break;
		case "firefox":
			if(remoteExecution) {
				init_remoteDriver("firefox");//run the test cases on selenium grid
			}
			else {
				tldriver.set(new FirefoxDriver(optionsManger.getFirefoxOptions())); // run test cases in local 
		}
			break;
		case "safari":
			// driver = new SafariDriver();
			tldriver.set(new SafariDriver());
			break;

		case "edge":
			if(remoteExecution) {
				init_remoteDriver("edge");//run the test cases on selenium grid
			}
			else {
				tldriver.set(new EdgeDriver(optionsManger.getEdgeOptions())); // run test cases in local 
			}
			break;
		default:
			//System.out.println(AppError.INVALID_BROWSER_MSG + ":"+ browserName );
			log.error(AppError.INVALID_BROWSER_MSG + ":" + browserName);
			FrameworkException fe = new FrameworkException(AppError.INVALID_BROWSER_MSG + ":" + browserName);
			log.error("Exception occured while initialising the driver:",fe);
			throw new FrameworkException("======== INVALID BROWSER NAME GIVEN ============");
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

	return getDriver();
	}

	// this is used to initialise the remote webdriver using selenium grid 
	private void init_remoteDriver(String browserName) {

		try {
			switch(browserName) {
//
			case "chrome":
				tldriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManger.getChromeOptions()));
				break;

		    case "firefox":
			    tldriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManger.getFirefoxOptions()));
			   break;
				
			case "edge":
				tldriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManger.getEdgeOptions()));
				break;

			default :
				log.error("pass the right browser name for the selenium grid...");
				FrameworkException fe = new FrameworkException(AppError.INVALID_BROWSER_MSG + " : " + browserName);
				log.error("Exception occured while initializing the driver...",fe);
				throw new FrameworkException("======== INVALID BROWSER NAME GIVEN ============");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	public static WebDriver getDriver() {
		return tldriver.get();
	}



//	/**
//	 * This method is init prop with property file..
//	 * @return
//	 */
	public Properties initProp() {

		prop = new Properties();
		FileInputStream ip = null;

		String envName = System.getProperty("env");
		log.info("envirnoment name is ==== > ...." + envName); // this stmt is not getting printed 

		try {
			if(envName == null) {
				log.info("no envirnoment is passed, hence executing Test cases on QA Envirnoment..");
				ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
		}
			else {
			switch(envName.trim().toLowerCase()) {
				case "qa": 
					ip = new FileInputStream("./src/test/resources/config/config.qa.properties");	
					break;
				case "dev": 
					ip = new FileInputStream("./src/test/resources/config/config.dev.properties");	
					break;
				case "stage": 
					ip = new FileInputStream("./src/test/resources/config/config.stage.properties");	
					break;
				case "uat": 
					ip = new FileInputStream("./src/test/resources/config/config.uat.properties");	
					break;
				case "prod": 
					ip = new FileInputStream("./src/test/resources/config/config.properties");	
					break;	
				default: 
					log.error("envirnoment value is invalid,please pass correct envirnoment" );
					throw new FrameworkException("=========Invalid ENVIRNOMENT======== ?");
				}
			}
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
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

