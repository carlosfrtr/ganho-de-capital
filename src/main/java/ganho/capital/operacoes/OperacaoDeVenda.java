package ganho.capital.operacoes;

import java.math.BigDecimal;
import java.math.RoundingMode;

import ganho.capital.dto.Operacao;
import ganho.capital.dto.PosicaoEmAberto;
import ganho.capital.dto.TipoDeOperacaoEnum;

public class OperacaoDeVenda extends OperacaoFinanceira {

	protected BigDecimal lucro;

	public OperacaoDeVenda(PosicaoEmAberto posicaoEmAberto, Operacao operacao) {
		super(posicaoEmAberto, operacao);
	}

	@Override
	public void calcular() {
		this.posicaoEmAberto.computarQuantidadeDeAcoesAtual(-this.operacao.getQuantidade());

		lucro = operacao.getCustoUnitario().subtract(posicaoEmAberto.getPrecoMedio())
				.multiply(BigDecimal.valueOf(operacao.getQuantidade())).setScale(2, RoundingMode.HALF_UP);

		BigDecimal prejuizo = lucro.add(posicaoEmAberto.getPrejuizo()).compareTo(BigDecimal.ZERO) < 0
				? posicaoEmAberto.getPrejuizo().add(lucro)
				: BigDecimal.ZERO;
		lucro = lucro.add(posicaoEmAberto.getPrejuizo()).compareTo(BigDecimal.ZERO) > 0
				? lucro.add(posicaoEmAberto.getPrejuizo())
				: BigDecimal.ZERO;

		this.posicaoEmAberto.setPrejuizo(prejuizo);
		this.calcularImposto();
	}
	
	@Override
	public boolean podeCalcular() {
		return this.operacao.getTipo().equals(TipoDeOperacaoEnum.VENDA);
	}

}
