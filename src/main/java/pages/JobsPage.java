package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class JobsPage extends AbstractPage {

	@FindBy(how = How.XPATH, using = "//*[@id='container']/div[1]/h1")
	public WebElement pageHeader;

	public JobsPage(WebDriver driver) {
		super(driver);
	}

}
