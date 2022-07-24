package ganho.capital.operacoes;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import ganho.capital.model.Operacao;
import ganho.capital.model.PosicaoEmAberto;
import ganho.capital.model.TipoDeOperacaoEnum;

public class OperacaoDeCompraTest {

	private final Operacao compra = new Operacao(TipoDeOperacaoEnum.COMPRA, BigDecimal.valueOf(10.00), 100);
	

	@Test
	public void deveCalcularTaxaDeUmaOperacaoERetornarZero() {
		final PosicaoEmAberto posicaoEmAberto = new PosicaoEmAberto();
		final OperacaoDeCompra operacaoDeCompra = new OperacaoDeCompra(posicaoEmAberto, this.compra);
		
		Assert.assertTrue(operacaoDeCompra.podeCalcular());

		operacaoDeCompra.calcular();

		Assert.assertEquals(BigDecimal.ZERO.setScale(2), posicaoEmAberto.getImpostos().get(0).getTax());

	}
	
	@Test
	public void deveCalcularTaxaDeVariasOperacoesETodasRetornarZero() {
		final Operacao compra1 = new Operacao(TipoDeOperacaoEnum.COMPRA, BigDecimal.valueOf(30.00), 140);
		final Operacao compra2 = new Operacao(TipoDeOperacaoEnum.COMPRA, BigDecimal.valueOf(15.00), 120);
		final PosicaoEmAberto posicaoEmAberto = new PosicaoEmAberto();
		
		Arrays.asList(compra1, compra2).forEach(compra -> {
			OperacaoDeCompra operacaoDeCompra = new OperacaoDeCompra(posicaoEmAberto, compra);
			operacaoDeCompra.calcular();
		});
		
		
		Assert.assertEquals(BigDecimal.ZERO.setScale(2), posicaoEmAberto.getImpostos().get(0).getTax());
		Assert.assertEquals(BigDecimal.ZERO.setScale(2), posicaoEmAberto.getImpostos().get(1).getTax());
	}
	
}
