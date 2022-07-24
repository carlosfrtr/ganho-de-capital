package ganho.capital.utils;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ganho.capital.model.Imposto;

public class ImpostoMapper {
	private static final ObjectMapper MAPPER = new ObjectMapper();

	public static String toJson(List<Imposto> impostos) {
		try {
			return MAPPER.writeValueAsString(impostos);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
