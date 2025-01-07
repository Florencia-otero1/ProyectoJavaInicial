package com.selenium.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.DemoHomePage;
import pageObjects.DemoResultsPage;
import com.selenium.driver.BaseTestDemo;
import com.selenium.utils.Utils;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class DemoTest extends BaseTestDemo {
	WebDriver driver;
	WebDriverWait wait;

	DemoHomePage demoHomePage;
	DemoResultsPage demoResultsPage;

	@DataProvider(name = "datos-logIn")
	public Object[][] getData() throws IOException {
		List<String[]> csvData = Utils.readCSV("src/resources/Excel/ExcelDemo.csv");

		Object[][] data = new Object[csvData.size()][];
		for (int i = 0; i < csvData.size(); i++) {
			data[i] = csvData.get(i);
		}
		return data;
	}

	@BeforeMethod
	public void precondiciones(ITestContext context) {
		String navegadorTestSuite = context.getCurrentXmlTest().getParameter("Navegador");
		driver = BaseTestDemo.getDriver(driver, navegadorTestSuite);

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		demoHomePage = new DemoHomePage(driver);
		demoResultsPage = new DemoResultsPage(driver);

		BaseTestDemo.goToMainPage(driver);
	}

	@AfterMethod
	public void postcondiciones() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test(description = "Validar que las adiciones al carrito en DemoBlaze funcionan")
	public void ValidarAdicionCarrito() throws Exception {
		Utils.escribir("Abrimos la página web www.demoblaze.com");

		Utils.escribir("Entramos a la categoría Phones");
		WebElement categoriaPhones = wait.until(ExpectedConditions.visibilityOf(demoHomePage.getCategoriaPhones()));

		Assert.assertTrue(categoriaPhones.isDisplayed(), "La categoría Phones no está visible.");
		categoriaPhones.click();
		Utils.escribir("Hemos clickeado en la categoría Phones.");

		WebElement firstProduct = wait.until(ExpectedConditions.visibilityOf(demoResultsPage.getFirstProduct()));
		firstProduct.click();
		Utils.escribir("Hemos clickeado en el producto Samsung Galaxy S6.");

		WebElement addToCart = wait.until(ExpectedConditions.visibilityOf(demoResultsPage.getAddToCartButton()));
		addToCart.click();
		Utils.escribir("Hemos clickeado en el botón 'Agregar al carrito'.");

		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		Utils.escribir("Se ha aceptado el alert de confirmación.");

		WebElement cart = wait.until(ExpectedConditions.visibilityOf(demoResultsPage.getCart()));
		Assert.assertTrue(cart.isDisplayed(), "El carrito está vacío.");
		cart.click();
		Utils.escribir("Hemos clickeado en el carrito para verificar los productos.");

		WebElement cartContents = wait.until(ExpectedConditions.visibilityOf(demoResultsPage.getCartContents()));
		Assert.assertTrue(cartContents.isDisplayed(), "El contenido del carrito no es visible.");
		Utils.escribir("El contenido del carrito es visible.");

		Thread.sleep(2000);
	}

	@Test(dataProvider = "datos-logIn", description = "Validar Log In")
	public void ValidarLogIn(String varBuscar, String resultado) throws Exception {
		Utils.escribir("Abrimos la página web www.demoblaze.com");

		Utils.escribir("Seleccionamos Log In");
		WebElement logIn = wait.until(ExpectedConditions.visibilityOf(demoHomePage.getLogIn()));
		logIn.click();
		Utils.escribir("Hemos clickeado en la opción Log In.");

		Utils.escribir("Llenamos el campo de usuario con: " + varBuscar);
		wait.until(ExpectedConditions.visibilityOf(demoResultsPage.getUsernameField())).sendKeys(varBuscar);

		Utils.escribir("Llenamos el campo de contraseña.");
		wait.until(ExpectedConditions.visibilityOf(demoResultsPage.getPasswordField())).sendKeys("123456");

		Utils.escribir("Hacemos clic en el botón de Log In.");
		wait.until(ExpectedConditions.elementToBeClickable(demoResultsPage.getLogInButton())).click();

		wait.until(ExpectedConditions.visibilityOf(demoHomePage.getLogOut()));
		Assert.assertTrue(demoHomePage.getLogOut().isDisplayed(), "El Log In no se ha realizado correctamente.");
		Utils.escribir("El Log In ha sido exitoso.");

		Thread.sleep(2000);
	}

}