package ganho.capital.operacoes;

import java.math.BigDecimal;
import java.math.RoundingMode;

import ganho.capital.dto.Operacao;
import ganho.capital.dto.PosicaoEmAberto;
import ganho.capital.dto.TipoDeOperacaoEnum;

public class OperacaoDeCompra extends OperacaoFinanceira {

	public OperacaoDeCompra(PosicaoEmAberto posicaoEmAberto, Operacao operacao) {
		super(posicaoEmAberto, operacao);
	}

	@Override
	public void calcular() {
		this.posicaoEmAberto.setPrecoMedio(this.posicaoEmAberto.getValor().add(this.operacao.getValor())
				.divide(BigDecimal.valueOf(this.operacao.getQuantidade() + this.posicaoEmAberto.getQuantidadeDeAcoes()))
				.setScale(2, RoundingMode.HALF_UP));
		this.posicaoEmAberto.computarQuantidadeDeAcoesAtual(this.operacao.getQuantidade());
		this.calcularImposto();
	}

	@Override
	public boolean podeCalcular() {
		return this.operacao.getTipo().equals(TipoDeOperacaoEnum.COMPRA);
	}

}
