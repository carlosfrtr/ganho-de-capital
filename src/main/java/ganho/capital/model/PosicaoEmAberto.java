package ganho.capital.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PosicaoEmAberto {

	private BigDecimal precoMedio;
	private Integer quantidadeDeAcoes;
	private BigDecimal prejuizo;
	private List<Imposto> impostos;

	public PosicaoEmAberto() {
		this.quantidadeDeAcoes = 0;
		this.precoMedio = BigDecimal.ZERO;
		this.prejuizo = BigDecimal.ZERO;

		this.impostos = new ArrayList<Imposto>();
	}

	public void computarQuantidadeDeAcoesAtual(Integer quantidade) {
		this.quantidadeDeAcoes += quantidade;

	}

	public void adicionarImposto(Imposto imposto) {
		this.impostos.add(imposto);
	}

	public BigDecimal getValor() {
		return this.precoMedio.multiply(BigDecimal.valueOf(this.quantidadeDeAcoes));
	}

	public List<Imposto> getImpostos() {
		return impostos;
	}

	public Integer getQuantidadeDeAcoes() {
		return quantidadeDeAcoes;
	}

	public BigDecimal getPrecoMedio() {
		return precoMedio;
	}

	public void setPrecoMedio(BigDecimal precoMedio) {
		this.precoMedio = precoMedio;
	}

	public BigDecimal getPrejuizo() {
		return prejuizo;
	}

	public void setPrejuizo(BigDecimal prejuizo) {
		this.prejuizo = prejuizo;
	}

}