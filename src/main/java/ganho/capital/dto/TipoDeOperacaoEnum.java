package ganho.capital.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TipoDeOperacaoEnum {

	@JsonProperty("buy")
	COMPRA,
	@JsonProperty("sell")
	VENDA;
	
}
