package pageObjects;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.selenium.driver.BaseTestWiki;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WikiResultsPage extends BaseTestWiki {

	@FindBy(id = "firstHeading")
	WebElement titulo;

	private WebDriverWait wait;

	public WikiResultsPage(WebDriver driver) {
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	public boolean tituloEsVisible() {
		wait.until(ExpectedConditions.visibilityOf(titulo));

		return this.titulo.isDisplayed();
	}

	public WebElement getTituloElemento() {
		return titulo;
	}

	public void waitForElementVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
}
