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
import pageObjects.WikiHomePage;
import pageObjects.WikiResultsPage;
import com.selenium.driver.BaseTestWiki;
import com.selenium.utils.Utils;

public class WikiTest extends BaseTestWiki {
	WebDriver driver;

	@DataProvider(name = "datos-búsqueda")
	public Object[][] getData() throws IOException {
		List<String[]> csvData = Utils.readCSV("src/resources/Excel/ExcelWiki.csv");

		Object[][] data = new Object[csvData.size()][];
		for (int i = 0; i < csvData.size(); i++) {
			data[i] = csvData.get(i);
		}
		return data;
	}

	@BeforeMethod
	public void precondiciones(ITestContext context) {
		String navegadorTestSuite = context.getCurrentXmlTest().getParameter("Navegador");
		driver = BaseTestWiki.getDriver(driver, navegadorTestSuite);
		BaseTestWiki.goToMainPage(driver);
	}

	@AfterMethod
	public void postcondiciones() {
		driver.close();
	}

	@Test(dataProvider = "datos-búsqueda", description = "Validar la búsqueda en Wikipedia")
	public void ValidarBusqueda(String palabra, String resultado) throws InterruptedException {
		WikiHomePage homePage = new WikiHomePage(driver);

		Utils.escribir("Iniciando la búsqueda para la palabra: " + palabra);

		Thread.sleep(1000);

		WikiResultsPage resultPage = homePage.searchText(palabra);

		Thread.sleep(2000);

		Assert.assertTrue(resultPage.tituloEsVisible(), "El título no es visible");

		String textoObtenido = resultPage.getTituloElemento().getText().replaceAll("[,;]", "").trim();
		String textoEsperado = resultado.replaceAll("[,;]", "").trim();

		Utils.escribir("Texto Obtenido: '" + textoObtenido + "'");
		Utils.escribir("Texto Esperado: '" + textoEsperado + "'");

		Assert.assertTrue(textoObtenido.equalsIgnoreCase(textoEsperado), "No coincide la palabra y el resultado");

		Utils.escribir("La búsqueda fue exitosa: " + (textoObtenido.equalsIgnoreCase(textoEsperado) ? "Sí" : "No"));
	}

}
