package Main.View;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ProfessorView {
    private Scanner scanner = new Scanner(System.in);

    public String getProfessorResponsavel(List<String> nomesProfessores) {
        String professorResponsavel = null;
        while (professorResponsavel == null) {
            System.out.print("Professor Responsável: ");
            professorResponsavel = scanner.nextLine();

            if (!nomesProfessores.contains(professorResponsavel)) {
                System.out.println("\nProfessor não encontrado no banco de dados. Digite um nome válido.\n");
                professorResponsavel = null;
            }
        }
        return professorResponsavel;
    }


    public String getCpfProfessor() {
        String cpf;
        boolean cpfValido = false;

        do {
            System.out.print("CPF (apenas números): ");
            cpf = scanner.nextLine();

            if (cpf.matches("\\d{11}") && !cpfDisponivel(cpf)) {
                cpfValido = true;
            } else {
                System.out.println("CPF inválido ou já cadastrado. Digite um CPF válido.");
            }
        } while (!cpfValido);

        return cpf;
    }

    public static boolean cpfDisponivel(String cpf) {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\hfent\\IdeaProjects\\ProjetoJavaEBD\\BD_PROFESSORES.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length >= 2 && data[2].equals(cpf)) {
                    return true; // CPF encontrado no banco de dados
                }
            }
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
        return false;
    }

    public String getSenhaProfessor() {
        System.out.print("Senha: ");
        return scanner.nextLine();
    }

    public String getNomeProfessor() {
        System.out.print("Nome: ");
        return scanner.nextLine();
    }
}
