# Currency Converter - by Angelim

Este é um projeto de conversor de moedas que utiliza a API de taxas de câmbio `ExchangeRate-API` para obter taxas de conversão atualizadas. O programa permite aos usuários selecionar conversões predefinidas ou inserir suas próprias combinações de moedas para obter a taxa de câmbio e converter valores. Além disso, o programa registra cada conversão realizada em um arquivo `.txt`, incluindo a data e hora da conversão.

## Funcionalidades

- **Conversões Predefinidas**: Seis opções de conversão predefinidas entre as moedas USD, AED, BRL, EUR.
- **Conversão Personalizada**: Permite que o usuário insira moedas base e de destino personalizadas no formato `MOEDA_BASE/MOEDA_ALVO`.
- **Registro de Conversões**: Cada conversão é registrada em um arquivo `conversion_log.txt` com o valor convertido e a data e hora da conversão.
- **Loop de Execução**: O programa continua em execução até que o usuário escolha a opção de sair.
- **Tratamento de Exceções**: Validação de entradas e tratamento de erros para entradas inválidas e problemas de conexão com a API.

## Como Utilizar

1. **Clone o repositório:**:

    ```bash
    git clone https://github.com/webangelim/currency-converter.git
    cd currency-converter
2. **Compile o projeto:**
    ```bash
    javac Main.java CurrencyConverter.java
3. **Execute o programa:**
    ```bash
    java Main
4. **Escolha a conversão:**
   - O programa exibirá um menu com as opções de conversão.
   - Digite o número correspondente à conversão desejada.
   - Para inserir uma conversão personalizada, escolha a opção 7 e forneça as moedas no formato MOEDA_BASE/MOEDA_ALVO.

5. **Digite o valor a ser convertido:**
   - Após escolher a conversão, insira o valor que deseja converter.

6. **Ver resultados:**
   - O programa exibirá a taxa de conversão e o valor convertido.
   - A conversão será registrada no arquivo conversion_log.txt.

7. **Sair:**
   - Para encerrar o programa, escolha a opção 8 no menu.

## Autor
- Gustavo Angelim de Souza<br>
- [GitHub](https://github.com/webangelim)
