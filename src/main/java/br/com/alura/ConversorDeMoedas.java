package br.com.alura;

import java.util.Scanner;

public class ConversorDeMoedas {
    private final ApiConversor apiConversor;

    public ConversorDeMoedas(ApiConversor apiConversor) {
        this.apiConversor = apiConversor;
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);

        String[] moedasOrigem = {"USD", "BRL", "EUR", "BRL", "GBP", "BRL"};
        String[] moedasDestino = {"BRL", "USD", "BRL", "EUR", "BRL", "GBP"};

        while (true) {
            mostrarOpcoes();
            int opcao = scanner.nextInt();

            if (opcao == 0) {
                System.out.println("Saindo...");
                break;
            }

            if (opcao < 1 || opcao > 6) {
                System.out.println("Opção inválida. Tente novamente.");
                continue;
            }

            System.out.print("Digite o valor a ser convertido: ");
            double valor = scanner.nextDouble();

            String moedaOrigem = moedasOrigem[opcao - 1];
            String moedaDestino = moedasDestino[opcao - 1];

            double valorConvertido = apiConversor.converter(new Moeda(moedaOrigem), new Moeda(moedaDestino), valor);
            if (valorConvertido != 0) {
                System.out.printf("O valor convertido de %.2f %s para %s é: %.2f%n",
                        valor, moedaOrigem, moedaDestino, valorConvertido);
            } else {
                System.out.println("Erro ao realizar a conversão. Tente novamente mais tarde.");
            }
        }
        scanner.close();
    }

    private void mostrarOpcoes() {
        System.out.println("\nEscolha uma opção de conversão:");
        System.out.println("1. Dólar para Real");
        System.out.println("2. Real para Dólar");
        System.out.println("3. Euro para Real");
        System.out.println("4. Real para Euro");
        System.out.println("5. Libra para Real");
        System.out.println("6. Real para Libra");
        System.out.println("0. Sair");
    }
}
