package framework.constants;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import framework.utils.DriverOperations;

public enum BrowserType implements DriverOperations {

	FireFox() {

		public WebDriver getWebDriver(DesiredCapabilities capabilities) {
			FirefoxProfile profile = new FirefoxProfile();
			profile.setAcceptUntrustedCertificates(true);
			profile.setEnableNativeEvents(false);
			profile.setAssumeUntrustedCertificateIssuer(false);
			return new FirefoxDriver(capabilities);
		}

		public DesiredCapabilities getCapabilities() {
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();

			return capabilities;
		}

	},
	InternetExplorer() {

		public WebDriver getWebDriver(DesiredCapabilities capabilities) {
			return new InternetExplorerDriver(capabilities);
		}

		public DesiredCapabilities getCapabilities() {
			DesiredCapabilities capabilities = DesiredCapabilities
					.internetExplorer();
			capabilities.setCapability(
					InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			capabilities.setCapability("nativeEvents", false);
			capabilities.setCapability("unexpectedAlertBehaviour", "accept");
			capabilities.setCapability("ignoreProtectedModeSettings", true);
			capabilities.setCapability("disable-popup-blocking", true);
			capabilities.setCapability("enablePresistentHover", true);
			capabilities.setCapability("iedriver-version", "x64_2.41.0");
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
			return capabilities;
		}

	},
	Chrome() {

		public WebDriver getWebDriver(DesiredCapabilities capabilities) {
			return new ChromeDriver(capabilities);
		}

		public DesiredCapabilities getCapabilities() {
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			return capabilities;
		}

	};

}
