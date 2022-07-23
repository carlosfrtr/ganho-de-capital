package ganho.capital;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ganho.capital.dto.Operacao;
import ganho.capital.dto.PosicaoEmAberto;
import ganho.capital.operacoes.OperacaoDeCompra;
import ganho.capital.operacoes.OperacaoDeVenda;
import ganho.capital.operacoes.OperacaoDeVendaAcimaDoLimiteDe20000;
import ganho.capital.operacoes.OperacaoFinanceira;

public class App {
	static final ObjectMapper MAPPER = new ObjectMapper();

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		while (scanner.hasNextLine()) {
			String nextLine = scanner.nextLine();
			if (nextLine.isBlank())
				break;

			try {
				List<Operacao> operacoes = Arrays.asList(MAPPER.readValue(nextLine, Operacao[].class));

				final PosicaoEmAberto posicaoEmAberto = new PosicaoEmAberto();

				for (Operacao operacao : operacoes) {
					OperacaoFinanceira.processar(new OperacaoDeCompra(posicaoEmAberto, operacao),
							new OperacaoDeVendaAcimaDoLimiteDe20000(posicaoEmAberto, operacao),
							new OperacaoDeVenda(posicaoEmAberto, operacao)
							);
				}

				System.out.println(MAPPER.writeValueAsString(posicaoEmAberto.getImpostos()));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

		scanner.close();

	}
}
