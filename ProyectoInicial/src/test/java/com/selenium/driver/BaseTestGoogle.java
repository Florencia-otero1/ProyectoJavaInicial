package com.selenium.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTestGoogle {

	public static void goToMainPage(WebDriver driver) {
		driver.get("http://google.com");
	}

	private enum browsers {
		CHROME, FIREFOX, EDGE
	}

	public static WebDriver getDriver(WebDriver driver, String browser) {
		switch (browsers.valueOf(browser.toUpperCase())) {
		case CHROME:
			System.setProperty("webdriver.chrome.driver", "src/resources/drivers/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case FIREFOX:
			System.setProperty("webdriver.gecko.driver", "src/resources/drivers/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case EDGE:
			System.setProperty("webdriver.edge.driver", "src/resources/drivers/msedgedriver.exe");
			driver = new EdgeDriver();
			break;
		default:
			throw new IllegalArgumentException("Invalid browser value");
		}

		return driver;
	}

}
