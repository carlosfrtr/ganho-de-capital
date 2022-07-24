<p align="center">
  <i>Code Challenge: Ganho de Capital</i> programa de linha de comando (CLI) que calcula o imposto a ser pago sobre lucros ou prejuízos de operações no mercado financeiro de ações.
</p>

<p align="center">
  <a href="#overview">Overview</a> •
  <a href="#roles">Roles</a> •
  <a href="#example">Example</a> •
  <a href="#installing">Install</a> •
  <a href="#running">Run</a> •
</p>

---

# Overview

O programa recebe listas, uma por linha, de operações do mercado financeiro de ações em formato
json através da entrada padrão ( stdin ). Cada operação desta lista contém os seguintes campos:

┏━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃    Nome    ┃ Significado                                                      ┃
┡━━━━━━━━━━━━╇━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┩
┃ operation  ┃ Se a operação é uma operação de compra ( buy ) ou venda ( sell ) ┃
┃ unit-cost  ┃ Preço unitário da ação em uma moeda com duas casas decimais      ┃
┃ quantity   ┃ Quantidade de ações negociadas                                   ┃
┡━━━━━━━━━━━━╇━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┩

Este é um exemplo da entrada:
```JSON
[{"operation":"buy", "unit-cost":10.00, "quantity": 10000},
{"operation":"sell", "unit-cost":20.00, "quantity": 5000}]
[{"operation":"buy", "unit-cost":20.00, "quantity": 10000},
{"operation":"sell", "unit-cost":10.00, "quantity": 5000}]
```

As operações estarão na ordem em que elas ocorreram, ou seja, a segunda operação na lista aconteceu
depois da primeira e assim por diante.
Cada linha é uma simulação independente, seu programa não deve manter o estado obtido em uma linha
para as outras.
A última linha da entrada será uma linha vazia.

Para cada linha da entrada, o programa deve retornar uma lista contendo o imposto pago para cada
operação recebida. Os elementos desta lista devem estar codificados em formato json e a saída deve ser
retornada através da saída padrão ( stdout ). O retorno é composto pelo seguinte campo:

┏━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃    Nome    ┃ Significado                              ┃
┡━━━━━━━━━━━━╇━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┩
┃ tax        ┃ O valor do imposto pago em uma operação  ┃
┡━━━━━━━━━━━━╇━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┩

Este é um exemplo da saída:
```JSON
[{"tax":0.00}, {"tax":10000.00}]
[{"tax":0.00}, {"tax":0.00}]
```

A lista retornada pelo programa deve ter o mesmo tamanho da lista de operações processadas na entrada.
Por exemplo, se foram processadas três operações (buy, buy, sell), o retorno do programa deve ser uma lista
com três valores que representam o imposto pago em cada operação.

# Roles

O programa deve lidar com dois tipos de operações ( buy e sell ) e ele deve seguir as seguintes regras:
- O percentual de imposto pago é de 20% sobre o lucro obtido na operação. Ou seja, o imposto vai ser pago quando há uma operação de venda cujo preço é superior ao preço médio ponderado de compra.
- Para determinar se a operação resultou em lucro ou prejuízo, você pode calcular o preço médio ponderado, então quando você compra ações você deve recalcular o preço médio ponderado utilizando essa fórmula: nova-media-ponderada = ((quantidade-de-acoes-atual * media-ponderada-atual) + (quantidade-de-acoes * valor-de-compra)) / (quantidade-de-acoes-atual + quantidade-de-acoes-compradas) . Por exemplo, se você comprou 10 ações por R$ 20,00, vendeu 5, depois comprou outras 5 por R$ 10,00, a média ponderada é ((5 x 20.00) + (5 x 10.00)) / (5 + 5) = 15.00 .
- Você deve usar o prejuízo passado para deduzir múltiplos lucros futuros, até que todo prejuízo seja deduzido.
- Prejuízos acontecem quando você vende ações a um valor menor do que o preço médio ponderado de compra. Neste caso, nenhum imposto deve ser pago e você deve subtrair o prejuízo dos lucros seguintes, antes de calcular o imposto.
- Você não paga nenhum imposto se o valor total da operação (custo unitário da ação x quantidade) for menor ou igual a R$ 20000,00. Use o valor total da operação e não o lucro obtido para determinar se o imposto deve ou não ser pago. E não se esqueça de deduzir o prejuízo dos lucros seguintes.
- Nenhum imposto é pago em operações de compra.

Você pode assumir que nenhuma operação vai vender mais ações do que você tem naquele momento.

# Example

Quando a aplicação recebe duas linhas, elas devem ser lidadas como duas simulações independentes. O
programa não deve carregar o estado obtido do processamento da primeira entrada para as outras
execuções.

Input:
```JSON
[{"operation":"buy", "unit-cost":10.00, "quantity": 100}, {"operation":"sell", "unit-cost":15.00, "quantity": 50}, {"operation":"sell", "unit-cost":15.00, "quantity": 50}]
[{"operation":"buy", "unit-cost":10.00, "quantity": 10000}, {"operation":"sell", "unit-cost":20.00, "quantity": 5000}, {"operation":"sell", "unit-cost":5.00, "quantity": 5000}]

```
```JSON
Output:
[{"tax": 0.00},{"tax": 0.00},{"tax": 0.00}]
[{"tax": 0.00},{"tax": 10000.00},{"tax": 0.00}]
```

## Requisitos para executar projeto

# installing

* Necessário JDK 8 ou superior instalado.

Gerar o arquivo .jar da aplicação:
```SHELL
./gradlew build
```
# running

Para executar basta colocar no terminal:
```SHELL
java -jar ganho-capital.jar
```

A aplicação vai aguardar o input das informações e vai devolver o resultado quando receber uma linha vazia.

# running2

Outra forma de executar informando uma arquivo via Input Redirection.:

'''SHELL
java -jar ganho-capital.jar < input.txt
'''

Onde o arquivo input.txt contém as informações da entrada. 