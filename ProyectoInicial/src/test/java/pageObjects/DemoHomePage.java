package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DemoHomePage {
	WebDriver driver;

	public DemoHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@id='contcont']//a[2]")
	private WebElement categoriaPhones;

	@FindBy(xpath = "//a[@id='login2']")
	private WebElement LogIn;

	@FindBy(xpath = "//a[normalize-space()='Log out']")
	private WebElement logOut;

	public WebElement getCategoriaPhones() {
		return categoriaPhones;
	}

	public WebElement getLogIn() {
		return LogIn;
	}

	public WebElement getLogOut() {
		return logOut;
	}

}
