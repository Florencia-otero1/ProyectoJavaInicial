package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleHomePage {

	@FindBy(name = "q")
	WebElement searchInput;

	private WebDriver driver;

	public GoogleHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean searchInputEsVisible() {
		return this.searchInput.isDisplayed();
	}

	public GoogleResultsPage searchText(String text) {
		this.searchInput.sendKeys(text);
		searchInput.submit();
		return new GoogleResultsPage(this.driver);
	}
}
