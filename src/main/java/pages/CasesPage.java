package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CasesPage extends AbstractPage {

	@FindBy(how = How.XPATH, using = "//*[@id='container']/header/h1")
	public WebElement pageHeader;

	public CasesPage(WebDriver driver) {
		super(driver);
	}

}
