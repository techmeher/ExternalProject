package framework.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public interface DriverOperations {

	public WebDriver getWebDriver(DesiredCapabilities capabilities);

	public DesiredCapabilities getCapabilities();
}
