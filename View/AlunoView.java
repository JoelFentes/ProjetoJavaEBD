package Main.View;

import Main.Model.Aluno;

import java.util.Scanner;

public class AlunoView {
    private static final Scanner scanner = new Scanner(System.in);

    public static void showAlunoCadastro() {
        System.out.println("\n----------- Cadastro de Aluno -----------");
    }

    public String getCpf() {
        String cpf;
        while (true) {
            System.out.print("CPF do aluno (somente números): ");
            cpf = scanner.nextLine().trim();

            if (cpf.matches("\\d{11}")) {
                break;
            } else {
                System.out.println("CPF inválido. Por favor, insira apenas números.");
            }
        }
        return cpf;
    }

    public String getNome() {
        String nome;
        while (true) {
            System.out.print("Nome do aluno: ");
            nome = scanner.nextLine().trim();

            if (nome.matches("[a-zA-Z\\p{L} \\-'’]+")) {
                break;
            } else {
                System.out.println("Nome inválido. Por favor, insira apenas letras e espaços.");
            }
        }
        return nome;
    }

    public int getIdade() {
        String idade;
        while (true) {
            System.out.print("Idade: ");
            idade = scanner.nextLine().trim();
            if (idade.matches("\\d+")) {
                return Integer.parseInt(idade);
            } else {
                System.out.println("Idade inválida. Por favor, insira apenas números.");
            }
        }
    }

    public String getContato() {
        System.out.print("Contato do aluno: ");
        return scanner.nextLine();
    }

    public String getEndereco() {
        System.out.print("Endereço do aluno: ");
        return scanner.nextLine();
    }

    public String getSala() {
        System.out.print("Nome da sala para o aluno: ");
        return scanner.nextLine();
    }


    public void ShowAlunoCadastroSucesso(Aluno aluno) {
        System.out.println(String.format("\n------------ Aluno Cadastrado Com Sucesso ------------\nCPF: %s\nNome: %s\nIdade: %d\nSala: %s \nContato: %s \nEndereço: %s",
                aluno.getCpf(), aluno.getNome(), aluno.getIdade(), aluno.getSala(), aluno.getContato(), aluno.getEndereco()));
    }

}
