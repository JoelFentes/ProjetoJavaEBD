package Main.View;

import Main.Model.Sala;

import java.util.Scanner;

public class SalaView {
    private static Scanner scanner = new Scanner(System.in);

    public static void showSalaCadastro() {
        System.out.println("\n------------ Cadastro de Sala ------------\n");
    }

    public String getDescricaoSala() {
        String descricao;
        while (true) {
            System.out.print("Nome da Sala: ");
            descricao = scanner.nextLine().trim();

            if (descricao.matches("[a-zA-Z\\p{L} \\-'’]+")) {
                break;
            } else {
                System.out.println("Nome inválido. Por favor, insira apenas letras e espaços.");
            }
        }
        return descricao;

    }

    public static int getIdadeMinimaSala() {
        while (true) {
            System.out.print("Idade de Entrada: ");
            String idade = scanner.nextLine().trim();

            if (idade.matches("\\d+")) {
                int idadeMinima = Integer.parseInt(idade);
                if (idadeMinima >= 0) {
                    return idadeMinima;
                } else {
                    System.out.println("Idade de entrada não pode ser menor que 0. Digite novamente.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, insira apenas números.");
            }
        }
    }


    public String getProfessorResponsavel() {
        System.out.print("Professor Responsável: ");
        return scanner.nextLine();
    }

    public static int getIdadeMaximaSala(int idadeMinima) {
        while (true) {
            System.out.print("Idade Limite: ");
            String idade = scanner.nextLine().trim();

            if (idade.matches("\\d+")) {
                int idadeMaxima = Integer.parseInt(idade);
                if (idadeMaxima >= idadeMinima) {
                    return idadeMaxima;
                } else {
                    System.out.println("Idade limite não pode ser menor que a idade de entrada. Digite novamente.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, insira apenas números.");
            }
        }
    }

    public void showSalaCadastroSucesso(Sala sala) {
        System.out.println(String.format("\n------------ Sala cadastrada com sucesso ------------\nID: %d\nDescrição: %s\nIdade de Entrada: %d\nIdade Limite: %d",
                sala.getId(), sala.getDescricao(), sala.getIdadeMinimaSala(), sala.getIdadeMaximaSala()));
    }



    public void closeScanner() {
        scanner.close();
    }
}
