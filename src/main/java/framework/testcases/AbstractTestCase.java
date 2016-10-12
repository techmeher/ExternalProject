package framework.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import pages.AbstractPage;
import framework.constants.BrowserType;
import framework.utils.CsvReader;

public class AbstractTestCase {

	protected String baseUrl;
	protected WebDriver _driver;
	private AbstractPage _page;
	private String location = "";
	protected Logger _logger = Logger.getLogger("TestCase_Logs");
	private Properties prop;
	private String browser;
	private String PROPERTYFILE_PATH;
	private CsvReader _csvReader;
	protected final int IMPLICITWAITSECONDS = 60;
	protected String screenShotPath;

	protected void setUpWebEnv() {

		_logger.log(Level.INFO, "Attempting to Read Property file");
		prop = new Properties();
		PROPERTYFILE_PATH = System.getProperty("user.dir")
				+ "/src/test/resources/properties/Env.properties";
		try {
			prop.load(new FileInputStream(new File(PROPERTYFILE_PATH)));

			browser = prop.getProperty("browser");
			selectBrowser(browser);
			baseUrl = prop.getProperty("BASEURL",
					"file:///Users/meher/Desktop/QATest/index.html");
			_logger.info("Current URL under Test : " + baseUrl);
			screenShotPath = System.getProperty("user.dir")
					+ prop.getProperty("SCREENSHOTLOCATION");
		} catch (FileNotFoundException e) {
			_logger.log(Level.SEVERE, "Error locating property file : ");
			e.printStackTrace();
		} catch (IOException e) {
			_logger.log(Level.SEVERE, "Error reading property file : ");
			e.printStackTrace();
		}

	}

	private void selectBrowser(String browserName) {
		_logger.log(Level.INFO, "Attempting to Instantiate Browser");
		switch (browserName) {
		case "firefox":
			this._driver = BrowserType.FireFox.getWebDriver(BrowserType.FireFox
					.getCapabilities());
			_page = new AbstractPage(_driver);
			_logger.log(Level.INFO, "Started Firefox Browser");
			break;

		default:
			this._driver = BrowserType.FireFox.getWebDriver(BrowserType.FireFox
					.getCapabilities());
			_page = new AbstractPage(_driver);
			break;
		}
	}

	protected void closeBrowser() {

		_logger.log(Level.INFO, "Attempting to Close Browser");
		if (_driver != null) {
			_driver.quit();
		}
	}

	public void takeScreenshotInCaseOfFailure(ITestResult result) {
		if (!result.isSuccess()) {
			_logger.info("Capturing Screenshot");
			_page.takeScreenShot(screenShotPath);
			closeBrowser();
		}
	}

	protected Object[][] getCsvData(String path) {
		try {
			_csvReader = new CsvReader(path);
		} catch (FileNotFoundException e) {
			_logger.log(Level.SEVERE, "Error reading CSV File :");
			e.printStackTrace();
		}
		return _csvReader.getData();

	}
}
