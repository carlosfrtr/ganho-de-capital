package ganho.capital.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Operacao {

	@JsonProperty("operation")
	private TipoDeOperacaoEnum tipo;
	
	@JsonProperty("unit-cost")
	private BigDecimal custoUnitario;
	
	@JsonProperty("quantity")
	private Integer quantidade;
	
	public Operacao() {
	}

	public Operacao(TipoDeOperacaoEnum tipo, BigDecimal custoUnitario, Integer quantidade) {
		this.tipo = tipo;
		this.custoUnitario = custoUnitario;
		this.quantidade = quantidade;
	}

	public BigDecimal getValor() {
		return this.custoUnitario
				.multiply(BigDecimal.valueOf(this.quantidade));
	}

	public Boolean isCompra() {
		return tipo.equals(TipoDeOperacaoEnum.COMPRA);
	}
	public Boolean isVenda() {
		return tipo.equals(TipoDeOperacaoEnum.VENDA);
	}

	public BigDecimal getCustoUnitario() {
		return custoUnitario;
	}

	public Integer getQuantidade() {
		return quantidade;
	}
}