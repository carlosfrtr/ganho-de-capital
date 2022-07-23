package ganho.capital.operacoes;

import java.util.stream.Stream;

import ganho.capital.dto.Imposto;
import ganho.capital.dto.Operacao;
import ganho.capital.dto.PosicaoEmAberto;

public abstract class OperacaoFinanceira {
	
	protected PosicaoEmAberto posicaoEmAberto;
	protected Operacao operacao;
	
	public OperacaoFinanceira(PosicaoEmAberto posicaoEmAberto, Operacao operacao) {
		this.posicaoEmAberto = posicaoEmAberto;
		this.operacao = operacao;
	}
	
	public static void processar(OperacaoFinanceira... operacoesFinanceiras) {
		Stream.of(operacoesFinanceiras).filter(OperacaoFinanceira::podeCalcular).findFirst().ifPresent(OperacaoFinanceira::calcular);;
	}

	public abstract void calcular();

	public void calcularImposto() {
		this.posicaoEmAberto.adicionarImposto(new Imposto());
	}
	
	public abstract boolean podeCalcular();
	
}
