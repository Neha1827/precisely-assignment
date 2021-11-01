package com.infogix.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
	
	public WebDriver driver;
	public Properties prop;

	@SuppressWarnings("deprecation")
	public WebDriver initializeBrowserDriver() throws IOException {
		prop = new Properties();
		InputStream is = getClass().getClassLoader().getResourceAsStream("data.properties");
		prop.load(is);
		String browserName = prop.getProperty("browser");
		String driverlocation = prop.getProperty("driverlocation");

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", driverlocation);
			driver = new ChromeDriver();
		} else if (browserName == "firefox") {
			System.setProperty("webdriver.gecko.driver", driverlocation);
			driver = new FirefoxDriver();

		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;

	}

}
