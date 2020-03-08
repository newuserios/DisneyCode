package com.disney.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class CustomWebDriver {
	private static final CustomWebDriver INSTANCE = new CustomWebDriver();

	private WebDriver driver = null;

	public CustomWebDriver() {

	}

	public static CustomWebDriver getInstance() {
		return INSTANCE;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void startFireFoxDriver() {
		String geckoPath = System.getProperty("user.dir") + "/src/test/resources/geckodriver";
		System.setProperty("webdriver.gecko.driver", geckoPath);
		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "false");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	public void startChromeDriver() {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/src/test/resources/chromedriver");
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("--test-type");
		 options.addArguments("--always-authorize-plugins");
		options.addArguments("--silent");
		driver = new ChromeDriver(options);
	}

	public void startIEDriver() {
		System.setProperty("webdriver.ie.driver",
				System.getProperty("user.dir") + "/src/test/resources/IEDriverServer.exe");
		driver = new InternetExplorerDriver();
	}
}
