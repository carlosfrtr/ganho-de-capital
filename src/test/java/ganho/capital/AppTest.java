/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ganho.capital;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AppTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void restoreStreams() {
		System.setOut(System.out);
	}

	@Test
	public void deveCalcularTaxasComUmaOperacaoDeCompraEDuasDeVenda() {
		String input = "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 100}, {\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 50}, {\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 50}]";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		App.main(null);

		Assert.assertEquals("[{\"tax\":0.00},{\"tax\":0.00},{\"tax\":0.00}]\n", outContent.toString());
	}

	@Test
	public void deveCalcularTaxasComUmaOperacaoDeCompraEDuasDeVendaComPagamentoDeTaxas() {
		String input = "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}, {\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 5000}, {\"operation\":\"sell\", \"unit-cost\":5.00, \"quantity\": 5000}]";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		App.main(null);

		Assert.assertEquals("[{\"tax\":0.00},{\"tax\":10000.00},{\"tax\":0.00}]\n", outContent.toString());
	}

	@Test
	public void deveCalcularDuasLinhasDeOperacaoSeparadas() {
		String input = "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 100}, {\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 50}, {\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 50}]\n"
				+ "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}, {\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 5000}, {\"operation\":\"sell\", \"unit-cost\":5.00, \"quantity\": 5000}]";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		App.main(null);
		
		Assert.assertEquals("[{\"tax\":0.00},{\"tax\":0.00},{\"tax\":0.00}]\n"
				+ "[{\"tax\":0.00},{\"tax\":10000.00},{\"tax\":0.00}]\n", outContent.toString());
	}

	@Test
	public void deveCalcularTaxasComUmaOperacaoDeCompraEDuasDeVendaComTaxaDeMil() {
		String input = "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}, {\"operation\":\"sell\", \"unit-cost\":5.00, \"quantity\": 5000}, {\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 3000}]";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		App.main(null);

		Assert.assertEquals("[{\"tax\":0.00},{\"tax\":0.00},{\"tax\":1000.00}]\n", outContent.toString());
	}

	@Test
	public void deveCalcularTaxasComDuasOperacoesDeCompraEUmaDeVenda() {
		String input = "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}, {\"operation\":\"buy\", \"unit-cost\":25.00, \"quantity\": 5000}, {\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 10000}]";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		App.main(null);

		Assert.assertEquals("[{\"tax\":0.00},{\"tax\":0.00},{\"tax\":0.00}]\n", outContent.toString());
	}

	@Test
	public void deveCalcularTaxasComDuasOperacoesDeCompraEDuasDeVenda() {
		String input = "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}, {\"operation\":\"buy\", \"unit-cost\":25.00, \"quantity\": 5000}, {\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 10000}, {\"operation\":\"sell\", \"unit-cost\":25.00, \"quantity\": 5000}]";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		App.main(null);

		Assert.assertEquals("[{\"tax\":0.00},{\"tax\":0.00},{\"tax\":0.00},{\"tax\":10000.00}]\n", outContent.toString());
	}

	@Test
	public void deveCalcularTaxasComUmaOperacaoDeCompraEQuatroDeVenda() {
		String input = "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}, {\"operation\":\"sell\", \"unit-cost\":2.00, \"quantity\": 5000}, {\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 2000}, {\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 2000}, {\"operation\":\"sell\", \"unit-cost\":25.00, \"quantity\": 1000}]";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		App.main(null);

		Assert.assertEquals("[{\"tax\":0.00},{\"tax\":0.00},{\"tax\":0.00},{\"tax\":0.00},{\"tax\":3000.00}]\n", outContent.toString());
	}
	
	@Test
	public void deveCalcularTaxasComOperacoesIntercaladasDeCompraEVenda() {
		String input = "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}, {\"operation\":\"sell\", \"unit-cost\":2.00, \"quantity\": 5000}, {\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 2000}, {\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 2000}, {\"operation\":\"sell\", \"unit-cost\":25.00, \"quantity\": 1000}, {\"operation\":\"buy\", \"unit-cost\":20.00, \"quantity\": 10000}, {\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 5000}, {\"operation\":\"sell\", \"unit-cost\":30.00, \"quantity\": 4350}, {\"operation\":\"sell\", \"unit-cost\":30.00, \"quantity\": 650}]";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		App.main(null);

		Assert.assertEquals("[{\"tax\":0.00},{\"tax\":0.00},{\"tax\":0.00},{\"tax\":0.00},{\"tax\":3000.00},{\"tax\":0.00},{\"tax\":0.00},{\"tax\":3700.00},{\"tax\":0.00}]\n", outContent.toString());
	}
	
	@Test
	public void deveCalcularTaxasComQuatroOperacoesIntercaladasDeCompraEVenda() {
		String input = "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}, {\"operation\":\"sell\", \"unit-cost\":50.00, \"quantity\": 10000}, {\"operation\":\"buy\", \"unit-cost\":20.00, \"quantity\": 10000}, {\"operation\":\"sell\", \"unit-cost\":50.00, \"quantity\": 10000}]";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		App.main(null);

		Assert.assertEquals("[{\"tax\":0.00},{\"tax\":80000.00},{\"tax\":0.00},{\"tax\":60000.00}]\n", outContent.toString());
	}
}
