package tests.functional;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.JobsPage;
import framework.constants.SideMenuItem;
import framework.testcases.AbstractTestCase;

public class MainTest extends AbstractTestCase {

	HomePage _mainPage;

	@BeforeTest
	public void setUp() {
		_logger.info("Setting up Test Environment");
		setUpWebEnv();
		_logger.info("Loading Base URL");
		_mainPage = new HomePage(_driver);
		_mainPage.loadPage(baseUrl, 60);
		_driver.manage().window().maximize();
	}

	@Test(description = "Verifying Latest News Header")
	public void verifyLatestNewsHeader() {
		Assert.assertTrue(
				_mainPage.isLinkPresent(_mainPage.latestNewsHeader.getText()),
				"Latest News Link is not present");
	}

	@Test(description = "Verifying No. of office locations", dependsOnMethods = { "verifyLatestNewsHeader" })
	public void verifyNoOfOfficeLocations() {
		_mainPage.officeLink.click();
		System.out.println("No of Valtech office locations : "
				+ _mainPage.officeLocations.size());
	}

	@Test(description = "Verifying Jobs page Header", dependsOnMethods = { "verifyLatestNewsHeader" })
	public void verifyJobsPageHeader() {
		String expectedHeader = "Jobs";
		_mainPage.selectSideMenuItem(SideMenuItem.JOBS);
		Assert.assertEquals(new JobsPage(_driver).pageHeader.getText(),
				expectedHeader, "Incorrect Jobs Page header");
	}

	@AfterMethod
	public void takesScreenShotIfFailed(ITestResult result) {
		takeScreenshotInCaseOfFailure(result);
	}

	@AfterTest
	public void tearDown() {
		_logger.info("Closing Browser");
		closeBrowser();
	}

}
