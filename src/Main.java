import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CurrencyConverter converter = new CurrencyConverter();
        boolean running = true;

        while (running) {
            int choice = getConversionChoice(scanner);
            if (choice == 8) {
                running = false;
                System.out.println("Encerrando o programa.");
                break;
            }

            double amount = getAmount(scanner);
            String[] currencies = getCurrencies(scanner, choice);
            String baseCurrency = currencies[0];
            String targetCurrency = currencies[1];

            double conversionRate = converter.getConversionRate(baseCurrency, targetCurrency);
            if (conversionRate != -1) {
                double convertedAmount = converter.convert(amount, conversionRate);
                System.out.printf("A taxa de conversão de %s para %s é: %f\n", baseCurrency, targetCurrency, conversionRate);
                System.out.printf("%f %s é igual a %f %s\n", amount, baseCurrency, convertedAmount, targetCurrency);
                converter.logConversion(baseCurrency, targetCurrency, amount, convertedAmount);
            } else {
                System.out.println("Falha na conversão.");
            }
        }
    }

    private static int getConversionChoice(Scanner scanner) {
        System.out.println("Escolha a conversão de moeda:");
        System.out.println("1 - USD/AED");
        System.out.println("2 - USD/BRL");
        System.out.println("3 - BRL/EUR");
        System.out.println("4 - BRL/USD");
        System.out.println("5 - EUR/BRL");
        System.out.println("6 - EUR/USD");
        System.out.println("7 - Custom");
        System.out.println("8 - Sair");

        while (true) {
            try {
                int choice = scanner.nextInt();
                if (choice < 1 || choice > 8) {
                    System.out.println("Escolha inválida, por favor insira um número entre 1 e 8.");
                } else {
                    return choice;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida, por favor insira um número.");
                scanner.next(); // Limpa a entrada inválida
            }
        }
    }

    private static double getAmount(Scanner scanner) {
        System.out.println("Digite o valor a ser convertido:");
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida, por favor insira um número válido.");
                scanner.next(); // Limpa a entrada inválida
            }
        }
    }

    private static String[] getCurrencies(Scanner scanner, int choice) {
        String baseCurrency = "";
        String targetCurrency = "";

        if (choice == 7) {
            System.out.println("Digite a moeda base e a moeda de destino no formato MOEDA_BASE/MOEDA_ALVO:");
            String input = scanner.next();
            String[] currencies = input.split("/");
            if (currencies.length != 2) {
                System.out.println("Formato inválido. Por favor insira no formato MOEDA_BASE/MOEDA_ALVO.");
                return getCurrencies(scanner, choice);
            }
            baseCurrency = currencies[0];
            targetCurrency = currencies[1];
        } else {
            switch (choice) {
                case 1:
                    baseCurrency = "USD";
                    targetCurrency = "AED";
                    break;
                case 2:
                    baseCurrency = "USD";
                    targetCurrency = "BRL";
                    break;
                case 3:
                    baseCurrency = "BRL";
                    targetCurrency = "EUR";
                    break;
                case 4:
                    baseCurrency = "BRL";
                    targetCurrency = "USD";
                    break;
                case 5:
                    baseCurrency = "EUR";
                    targetCurrency = "BRL";
                    break;
                case 6:
                    baseCurrency = "EUR";
                    targetCurrency = "USD";
                    break;
            }
        }
        return new String[]{baseCurrency, targetCurrency};
    }
}
