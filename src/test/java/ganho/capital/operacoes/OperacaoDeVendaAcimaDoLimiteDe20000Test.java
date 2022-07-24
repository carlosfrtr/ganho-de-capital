package ganho.capital.operacoes;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import ganho.capital.model.Operacao;
import ganho.capital.model.PosicaoEmAberto;
import ganho.capital.model.TipoDeOperacaoEnum;

public class OperacaoDeVendaAcimaDoLimiteDe20000Test {

	@Test
	public void deveCalcularTaxaDeComLucroDe80000() {
		final PosicaoEmAberto posicaoEmAberto = new PosicaoEmAberto();
		final Operacao compra = new Operacao(TipoDeOperacaoEnum.COMPRA, BigDecimal.valueOf(10.00), 10000);
		final Operacao venda = new Operacao(TipoDeOperacaoEnum.VENDA, BigDecimal.valueOf(50.00), 10000);
		
		new OperacaoDeCompra(posicaoEmAberto, compra).calcular();
		new OperacaoDeVendaAcimaDoLimiteDe20000(posicaoEmAberto, venda).calcular();
		
		Assert.assertEquals(BigDecimal.ZERO.setScale(2), posicaoEmAberto.getImpostos().get(0).getTax());
		Assert.assertEquals(BigDecimal.valueOf(80000.00).setScale(2), posicaoEmAberto.getImpostos().get(1).getTax());

	}
	
}
