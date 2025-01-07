package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DemoResultsPage {
	WebDriver driver;

	public DemoResultsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[normalize-space()='Samsung galaxy s6']")
	private WebElement firstProduct;

	@FindBy(xpath = "//a[normalize-space()='Add to cart']")
	private WebElement addToCartButton;

	@FindBy(xpath = "//a[normalize-space()='Cart']")
	private WebElement cart;

	@FindBy(xpath = "//input[@id='loginusername']")
	private WebElement usernameField;

	@FindBy(xpath = "//input[@id='loginpassword']")
	private WebElement passwordField;

	@FindBy(xpath = "//button[normalize-space()='Log in']")
	private WebElement logInButton;

	@FindBy(xpath = "//tr[@class='success']")
	private WebElement cartContents;

	public WebElement getFirstProduct() {
		return firstProduct;
	}

	public WebElement getAddToCartButton() {
		return addToCartButton;
	}

	public WebElement getCart() {
		return cart;
	}

	public WebElement getPasswordField() {
		return passwordField;
	}

	public WebElement getLogInButton() {
		return logInButton;
	}

	public WebElement getUsernameField() {
		return usernameField;
	}

	public WebElement getCartContents() {
		return cartContents;
	}
}
