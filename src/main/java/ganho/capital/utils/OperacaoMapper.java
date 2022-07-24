package ganho.capital.utils;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ganho.capital.model.Operacao;

public class OperacaoMapper {
	private static final ObjectMapper MAPPER = new ObjectMapper();

	public static List<Operacao> obterOperacoesFromJson(String json) {
		try {
			return Arrays.asList(MAPPER.readValue(json, Operacao[].class));
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
