package ganho.capital.operacoes;

import java.math.BigDecimal;

import ganho.capital.dto.Imposto;
import ganho.capital.dto.Operacao;
import ganho.capital.dto.PosicaoEmAberto;

public class OperacaoDeVendaAcimaDoLimiteDe20000 extends OperacaoDeVenda {

	private static final Integer LIMITE_LUCRO = 20000;

	public OperacaoDeVendaAcimaDoLimiteDe20000(PosicaoEmAberto posicaoEmAberto, Operacao operacao) {
		super(posicaoEmAberto, operacao);
	}

	@Override
	public void calcularImposto() {
		this.posicaoEmAberto.adicionarImposto(new Imposto(lucro.multiply(BigDecimal.valueOf(0.2))));
	}

	@Override
	public boolean podeCalcular() {
		return super.podeCalcular() && this.operacao.getValor().compareTo(BigDecimal.valueOf(LIMITE_LUCRO)) > 0;
	}

}
