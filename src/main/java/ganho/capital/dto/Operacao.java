package ganho.capital.dto;

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

	public TipoDeOperacaoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoDeOperacaoEnum tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getCustoUnitario() {
		return custoUnitario;
	}

	public void setCustoUnitario(BigDecimal custoUnitario) {
		this.custoUnitario = custoUnitario;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public BigDecimal getValor() {
		return this.custoUnitario
		.multiply(BigDecimal.valueOf(this.quantidade));
	}
	

}
