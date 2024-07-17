package Main.View;

import Main.Model.Aluno;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class AlunoView {
    private final Scanner scanner = new Scanner(System.in);

    public void showAlunoCadastro() {
        System.out.println("\n------------ Cadastro de Aluno ------------");
    }

    public String getNomeAluno() {
        System.out.print("Nome do Aluno: ");
        return scanner.nextLine();
    }

    public String getCpfAluno() {
        String cpf;
        boolean cpfValido = false;

        do {
            System.out.print("CPF (apenas números): ");
            cpf = scanner.nextLine();

            // Verifica se o CPF tem exatamente 11 dígitos.
            if (cpf.matches("\\d{11}")) {
                // Verifica se o CPF já existe no banco de dados.
                if (!cpfDisponivel(cpf)) {
                    cpfValido = true;
                } else {
                    System.out.println("CPF já cadastrado. Digite um CPF válido.");
                }
            } else {
                System.out.println("CPF inválido. Digite um CPF com exatamente 11 dígitos numéricos.");
            }
        } while (!cpfValido);

        return cpf;
    }

    public static boolean cpfDisponivel(String cpf) {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\joelf\\IdeaProjects\\ProjetoJavaEBD\\BD_ALUNOS.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length >= 2 && data[1].equals(cpf)) {
                    return true; // CPF encontrado no banco de dados
                }
            }
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
        return false;
    }

    public String getContatoAluno() {
        System.out.print("Whatsapp ou Número para contato: ");
        return scanner.nextLine();
    }

    public int getIdadeAluno() {
        System.out.print("Idade: ");
        return scanner.nextInt();
    }

    public String getSalaAluno() {
        System.out.print("Sala: ");
        return scanner.nextLine();
    }

    public void showAlunoCadastroSucesso(Aluno aluno) {
        System.out.println(String.format("\n------------ Aluno cadastrado com sucesso ------------\nMatrícula: %d\nNome: %s\nSala: %s",
                aluno.getMatricula(), aluno.getNome(), aluno.getSalaAluno()));
    }

}
