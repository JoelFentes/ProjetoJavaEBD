package Main.View;

import Main.Model.Aula;
import Main.Model.Sala;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AulaView {
    private static Scanner scanner = new Scanner(System.in);

    public static void showAulaCadastro() {
        System.out.println("\n------------ Cadastro de Aula ------------\n");
    }

    public String getDescricaoAula() {
        String descricao;
        while (true) {
            System.out.print("Descrição da Aula: ");
            descricao = scanner.nextLine().trim();

            if (descricao.matches("[a-zA-Z\\p{L} \\-'’]+")) {
                break;
            } else {
                System.out.println("Descrição inválida. Por favor, insira apenas letras e espaços.");
            }
        }
        return descricao;
    }

    public String getDataAula() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return now.format(formatter);
    }

    public String getProfessorResponsavel() {
        System.out.print("Professor Responsável: ");
        return scanner.nextLine();
    }

    public String getAulaSala() {
        System.out.print("Nome da Sala: ");
        return scanner.nextLine();
    }

    public float getOfertaAula() {
        System.out.print("Ofertas (R$): ");
        return scanner.nextFloat();
    }

    public void showRegistrarPresenca() {
        System.out.println("Registro de Presença da Aula:");
    }

    public String getPresencaAluno(String nome) {
        System.out.printf("Aluno %s está presente? (P/F): ", nome);
        return scanner.nextLine().trim().toUpperCase();
    }

    public void showAulaCadastroSucesso(Aula aula) {
        System.out.println(String.format("\n------------ Aula cadastrada com sucesso ------------\nDescrição: %s\nData: %s\nSala: %s\nProfessor: %s\nOfertas: %s",
                aula.getDescricao(), aula.getData(), aula.getSala(), aula.getProfessorResponsavel(), aula.getOferta()));
    }

    public void closeScanner() {
        scanner.close();
    }
}
