package ganho.capital.operacoes;

import java.math.BigDecimal;

import ganho.capital.model.Imposto;
import ganho.capital.model.Operacao;
import ganho.capital.model.PosicaoEmAberto;

public class OperacaoDeVendaAcimaDoLimiteDe20000 extends OperacaoDeVenda {

	private static final Integer LIMITE_LUCRO = 20000;

	public OperacaoDeVendaAcimaDoLimiteDe20000(PosicaoEmAberto posicaoEmAberto, Operacao operacao) {
		super(posicaoEmAberto, operacao);
	}

	@Override
	public void calcularImposto() {
		this.posicaoEmAberto.adicionarImposto(new Imposto(this.lucro.multiply(BigDecimal.valueOf(0.2))));
	}

	@Override
	public boolean podeCalcular() {
		return super.podeCalcular() && this.operacao.getValor().compareTo(BigDecimal.valueOf(LIMITE_LUCRO)) > 0;
	}

}
