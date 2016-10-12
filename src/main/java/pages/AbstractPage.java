package pages;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.exceptions.WebElementNotFoundException;

public class AbstractPage {

	protected WebDriver driver;
	protected Logger logger = Logger.getLogger("App_Logs");

	public AbstractPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * use this method to get current webdriver instance
	 * 
	 * @return webdriver instance
	 */
	public WebDriver getDriver() {
		return this.driver;
	}

	/**
	 * use this method to create an instance of webdriverwait by specifying the
	 * wait period in seconds
	 * 
	 * @param seconds
	 * @return
	 */
	public WebDriverWait getWebDriverWait(int seconds) {
		return new WebDriverWait(this.driver, seconds);
	}

	/**
	 * Use this method to load a page through webdriver by passing url, by
	 * default implicit wait and page load is set to 60 seconds
	 * 
	 * @param url
	 * @param impliclitWaitSeconds
	 */
	public void loadPage(String url, int impliclitWaitSeconds) {
		logger.log(Level.INFO, "Loading Page : " + url);
		this.driver.get(url);
		if (driver.getTitle().contains("Certificate Error: Navigation Blocked")) {
			driver.navigate()
					.to("javascript:document.getElementById('overridelink').click()");
		}

		this.driver.manage().timeouts()
				.implicitlyWait(impliclitWaitSeconds, TimeUnit.SECONDS);
		this.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		this.driver.manage().window().maximize();

	}

	public void clickBrowserBackButton() {
		this.driver.navigate().back();
	}

	public void clickBrowserForwardButton() {
		this.driver.navigate().forward();
	}

	public void clickBrowserRefreshButton() {
		this.driver.navigate().refresh();
	}

	public String getTitle() {
		String pageTitle = "";
		pageTitle = this.driver.getTitle();
		return pageTitle;
	}

	public String getCurrentUrl() {
		String currentUrl = "";
		currentUrl = this.driver.getCurrentUrl();
		return currentUrl;
	}

	public void takeScreenShot(String location) {
		final String _location = location;
		takeScreenShot(_location, "Custom");
	}

	public boolean isElementDisplayed(WebElement element) {
		boolean displayed = false;
		if (element.isDisplayed()) {
			return true;
		} else {
			try {
				displayed = false;
				throw new WebElementNotFoundException(
						"Element not Found exception occurred");

			} catch (WebElementNotFoundException e) {
				e.printStackTrace();
			}
		}

		return displayed;
	}

	public void takeScreenShot(String location, String methodName) {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yy_M_d_Hms");
		String dates = sdf.format(now);
		String method = "_" + methodName + ".png";
		File screenshot = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File(location + "_" + dates
					+ method));
			logger.log(Level.INFO, "Captured Screenshot successfully");
		} catch (IOException e) {
			logger.log(
					Level.SEVERE,
					"Error occurred while capturing screenshot : "
							+ e.getMessage());
		}

	}
}
