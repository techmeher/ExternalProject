package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ServicesPage extends AbstractPage {

	@FindBy(how = How.XPATH, using = "//*[@id='container']/section/header/h1")
	public WebElement pageHeader;

	public ServicesPage(WebDriver driver) {
		super(driver);
	}

}
