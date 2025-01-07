package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class GoogleResultsPage {

	@FindBy(tagName = "h3")
	public WebElement resultTitle;

	private WebDriverWait wait;

	public GoogleResultsPage(WebDriver driver) {
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	public boolean tituloEsVisible() {
		wait.until(ExpectedConditions.visibilityOf(resultTitle));
		return this.resultTitle.isDisplayed();
	}
}
