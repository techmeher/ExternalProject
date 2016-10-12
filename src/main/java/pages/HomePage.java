package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import framework.constants.SideMenuItem;

public class HomePage extends AbstractPage {

	@FindBy(how = How.XPATH, using = "//*[@id='container']/div[2]/div[3]/div[1]/header/h2")
	public WebElement latestNewsHeader;

	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='navigationMenuWrapper']//ul/li/a") })
	public List<WebElement> sideLinks;

	@FindBy(how = How.XPATH, using = "//*[@id='navigation-toggle-button']/div/div/div[1]/i")
	public WebElement sideMenuBtn;

	@FindBy(how = How.XPATH, using = "//*[@id='navigation-toggle-button']/div/div/div[2]/i")
	public WebElement sideMenuCloseBtn;

	@FindBy(how = How.XPATH, using = "//*[@id='contacticon']/div/div/div[1]/i")
	public WebElement officeLink;

	@FindBy(how = How.XPATH, using = "//*[@id='contacticon']/div/div/div[2]/i")
	public WebElement officeLinkCloseBtn;

	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='contactbox']/div/div/ul/li/a[2]") })
	public List<WebElement> officeLocations;

	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='container']//header/h2") })
	public List<WebElement> articleHeaders;

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void selectSideMenuItem(SideMenuItem menuItem) {
		sideMenuBtn.click();
		for (WebElement sideLink : sideLinks) {
			if (sideLink.getText().trim().equals(menuItem.getItem())) {
				sideLink.click();
				break;
			}
		}
	}

	public boolean isLinkPresent(String linkText) {
		boolean isPresent = false;
		List<String> mainPageLinks = new ArrayList<String>();

		for (WebElement link : articleHeaders) {
			if (!link.getText().trim().equals("")) {
				mainPageLinks.add(link.getText().trim());
			}
		}

		isPresent = mainPageLinks.contains(linkText);
		return isPresent;
	}
}
