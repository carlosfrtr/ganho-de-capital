package ganho.capital.operacoes;

import java.math.BigDecimal;
import java.math.RoundingMode;

import ganho.capital.model.Operacao;
import ganho.capital.model.PosicaoEmAberto;

public class OperacaoDeVenda extends OperacaoFinanceira {

	protected BigDecimal lucro;

	public OperacaoDeVenda(PosicaoEmAberto posicaoEmAberto, Operacao operacao) {
		super(posicaoEmAberto, operacao);
	}

	@Override
	public void calcular() {
		this.posicaoEmAberto.computarQuantidadeDeAcoesAtual(-this.operacao.getQuantidade());

		this.lucro = this.operacao.getCustoUnitario().subtract(this.posicaoEmAberto.getPrecoMedio())
				.multiply(BigDecimal.valueOf(this.operacao.getQuantidade())).setScale(2, RoundingMode.HALF_UP);

		final BigDecimal prejuizo = this.lucro.add(this.posicaoEmAberto.getPrejuizo()).compareTo(BigDecimal.ZERO) < 0
				? this.posicaoEmAberto.getPrejuizo().add(this.lucro)
				: BigDecimal.ZERO;
		this.lucro = this.lucro.add(this.posicaoEmAberto.getPrejuizo()).compareTo(BigDecimal.ZERO) > 0
				? this.lucro.add(this.posicaoEmAberto.getPrejuizo())
				: BigDecimal.ZERO;

		this.posicaoEmAberto.setPrejuizo(prejuizo);
		this.calcularImposto();
	}

	@Override
	public boolean podeCalcular() {
		return this.operacao.isVenda();
	}

}
