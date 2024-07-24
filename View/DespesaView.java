package Main.View;

import Main.Model.Despesa;

import java.util.Scanner;

public class DespesaView {
    private static Scanner scanner = new Scanner(System.in);

    public static void showDespesaCadastro() {
        System.out.println("\n------------ Cadastro de Despesa ------------\n");
    }

    public String getDataDespesa() {
        System.out.print("Data da Despesa (dd/MM/yyyy): ");
        return scanner.nextLine().trim();
    }

    public String getDescricaoDespesa() {
        String descricao;
        while (true) {
            System.out.print("Descrição da Despesa: ");
            descricao = scanner.nextLine().trim();

            if (descricao.matches("[a-zA-Z\\p{L} \\-'’]+")) {
                break;
            } else {
                System.out.println("Descrição inválida. Por favor, insira apenas letras e espaços.");
            }
        }
        return descricao;
    }

    public float getValorDespesa() {
        System.out.print("Valor da Despesa (R$): ");
        return scanner.nextFloat();
    }

    public String getProfessorNome() {
        System.out.print("Nome do Professor: ");
        scanner.nextLine();  // Consumir a linha pendente
        return scanner.nextLine();
    }

    public String getSalaNome() {
        System.out.print("Nome da Sala: ");
        return scanner.nextLine();
    }

    public void showDespesaCadastroSucesso(Despesa despesa) {
        System.out.println(String.format("\n------------ Despesa cadastrada com sucesso ------------\nData: %s\nDescrição: %s\nValor: %.2f\nProfessor: %s\nSala: %s",
                despesa.getData(), despesa.getDescricao(), despesa.getValor(), despesa.getProfessorNome(), despesa.getSalaNome()));
    }

    public void closeScanner() {
        scanner.close();
    }
}
