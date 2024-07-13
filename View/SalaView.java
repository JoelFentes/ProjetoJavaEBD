package Main.View;

import Main.Controller.SalaController;
import Main.Model.Sala;

import java.util.Scanner;

public class SalaView {
    private static Scanner scanner = new Scanner(System.in);

    public static void showSalaCadastro() {
        System.out.println("\n------------ Cadastro de Sala ------------\n");
    }

    public String getDescricaoSala() {
        System.out.print("Nome da Sala: ");
        return scanner.nextLine();
    }

    public int getIdadeMinimaSala() {
        int idadeMinima;
        do {
            System.out.print("Idade de Entrada: ");
            idadeMinima = scanner.nextInt();
            scanner.nextLine();

            if (idadeMinima < 0) {
                System.out.println("Idade de entrada não pode ser menor que 0. Digite novamente.");
            }
        } while (idadeMinima < 0);
        return idadeMinima;
    }

    public String getProfessorResponsavel() {
        System.out.print("Professor Responsável: ");
        return scanner.nextLine();
    }

    public int getIdadeMaximaSala(int idadeMinima) {
        int idadeMaxima;
        do {
            System.out.print("Idade Limite: ");
            idadeMaxima = scanner.nextInt();
            scanner.nextLine();

            if (idadeMaxima < idadeMinima) {
                System.out.println("Idade limite não pode ser menor que a idade de entrada. Digite novamente.");
            }
        } while (idadeMaxima < idadeMinima);
        return idadeMaxima;
    }

    public void showSalaCadastroSucesso(Sala sala) {
        System.out.println(String.format("\n------------ Sala cadastrada com sucesso ------------\nID: %d\nDescrição: %s\nIdade de Entrada: %d\nIdade Limite: %d",
                sala.getId(), sala.getDescricao(), sala.getIdadeMinimaSala(), sala.getIdadeMaximaSala()));
    }

    public void closeScanner() {
        scanner.close();
    }
}
