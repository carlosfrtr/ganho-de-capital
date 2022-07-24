package ganho.capital;

import java.util.List;
import java.util.Scanner;

import ganho.capital.model.Operacao;
import ganho.capital.model.PosicaoEmAberto;
import ganho.capital.operacoes.OperacaoDeCompra;
import ganho.capital.operacoes.OperacaoDeVenda;
import ganho.capital.operacoes.OperacaoDeVendaAcimaDoLimiteDe20000;
import ganho.capital.operacoes.OperacaoFinanceira;
import ganho.capital.utils.ImpostoMapper;
import ganho.capital.utils.OperacaoMapper;

public class App {

	public static void main(String[] args) {

		final Scanner scanner = new Scanner(System.in);

		try {
			while (scanner.hasNextLine()) {
				final String nextLine = scanner.nextLine();
				if (nextLine.isBlank())
					break;

				final List<Operacao> operacoes = OperacaoMapper.obterOperacoesFromJson(nextLine);

				final PosicaoEmAberto posicaoEmAberto = new PosicaoEmAberto();

				for (Operacao operacao : operacoes) {
					OperacaoFinanceira.processar(new OperacaoDeCompra(posicaoEmAberto, operacao),
							new OperacaoDeVendaAcimaDoLimiteDe20000(posicaoEmAberto, operacao),
							new OperacaoDeVenda(posicaoEmAberto, operacao));
				}

				System.out.println(ImpostoMapper.toJson(posicaoEmAberto.getImpostos()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}

	}
}