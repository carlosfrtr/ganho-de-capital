package ganho.capital.operacoes;

import java.math.BigDecimal;
import java.math.RoundingMode;

import ganho.capital.model.Operacao;
import ganho.capital.model.PosicaoEmAberto;

public class OperacaoDeCompra extends OperacaoFinanceira {

	public OperacaoDeCompra(PosicaoEmAberto posicaoEmAberto, Operacao operacao) {
		super(posicaoEmAberto, operacao);
	}

	@Override
	public void calcular() {
		this.posicaoEmAberto.setPrecoMedio(this.posicaoEmAberto.getValor().add(this.operacao.getValor()).divide(
				BigDecimal.valueOf(this.operacao.getQuantidade() + this.posicaoEmAberto.getQuantidadeDeAcoes()), 2,
				RoundingMode.HALF_UP));
		this.posicaoEmAberto.computarQuantidadeDeAcoesAtual(this.operacao.getQuantidade());
		this.calcularImposto();
	}

	@Override
	public boolean podeCalcular() {
		return this.operacao.isCompra();
	}

}
