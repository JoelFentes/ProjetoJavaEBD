package Main.View;

import Main.Model.Receita;

import java.util.Scanner;

public class ReceitaView {
    private static Scanner scanner = new Scanner(System.in);

    public static void showReceitaCadastro() {
        System.out.println("\n------------ Cadastro de Receita ------------\n");
    }

    public static String getDataReceita() {
        System.out.print("Data da Receita (dd/MM/yyyy): ");
        return scanner.nextLine().trim();
    }

    public static String getDescricaoReceita() {
        String descricao;
        while (true) {
            System.out.print("Descrição da Receita: ");
            descricao = scanner.nextLine().trim();

            if (descricao.matches("[a-zA-Z\\p{L} \\-'’]+")) {
                break;
            } else {
                System.out.println("Descrição inválida. Por favor, insira apenas letras e espaços.");
            }
        }
        return descricao;
    }

    public static float getValorReceita() {
        System.out.print("Valor da Receita (R$): ");
        return scanner.nextFloat();
    }

    public static String getSalaNome() {
        System.out.print("Nome da Sala: ");
        scanner.nextLine();
        return scanner.nextLine();
    }

    public static void showReceitaCadastroSucesso(Receita receita) {
        System.out.println(String.format("\n------------ Receita cadastrada com sucesso ------------\nData: %s\nDescrição: %s\nValor: %.2f\nSala: %s",
                receita.getData(), receita.getDescricao(), receita.getValor(), receita.getSalaNome()));
    }

    public void closeScanner() {
        scanner.close();
    }
}
