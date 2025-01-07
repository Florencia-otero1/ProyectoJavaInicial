package com.selenium.test;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.GoogleHomePage;
import pageObjects.GoogleResultsPage;
import com.selenium.driver.BaseTestGoogle;
import com.selenium.utils.Utils;

public class GoogleTest extends BaseTestGoogle {
	WebDriver driver;

	@DataProvider(name = "datos-búsqueda")
	public Object[][] getData() throws IOException {
		List<String[]> csvData = Utils.readCSV("src/resources/Excel/ExcelGoogle.csv");

		Object[][] data = new Object[csvData.size()][];
		for (int i = 0; i < csvData.size(); i++) {
			data[i] = csvData.get(i);
		}
		return data;
	}

	@BeforeMethod
	public void precondiciones(ITestContext context) {
		String navegadorTestSuite = context.getCurrentXmlTest().getParameter("Navegador");
		driver = BaseTestGoogle.getDriver(driver, navegadorTestSuite);
		BaseTestGoogle.goToMainPage(driver);
	}

	@AfterMethod
	public void postcondiciones() {
		driver.close();
	}

	@Test(dataProvider = "datos-búsqueda", description = "Validar que las búsquedas en Google funcionan")
	public void validarBusquedaGoogle(String varBuscar, String resultado) throws Exception {
		GoogleHomePage homePage = new GoogleHomePage(driver);

		Assert.assertTrue(homePage.searchInputEsVisible(), "El input no está visible");
		Utils.escribir("El input de búsqueda es visible.");

		GoogleResultsPage resultsPage = homePage.searchText(varBuscar);
		Utils.escribir("Se realizó la búsqueda para: " + varBuscar);

		Assert.assertTrue(resultsPage.tituloEsVisible(), "El título no está visible");
		Utils.escribir("El título de la página de resultados es visible.");

		String textoObtenido = resultsPage.resultTitle.getText().trim();
		String textoEsperado = resultado.trim();
		Utils.escribir("Texto Obtenido: '" + textoObtenido + "'");
		Utils.escribir("Texto Esperado: '" + textoEsperado + "'");

		Assert.assertTrue(textoObtenido.equalsIgnoreCase(textoEsperado), "No coincide la palabra y el resultado");
		Utils.escribir("La búsqueda fue exitosa: " + (textoObtenido.equalsIgnoreCase(textoEsperado) ? "Sí" : "No"));
	}

}
