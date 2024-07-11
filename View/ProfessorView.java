package Main.View;

import Main.Model.Aluno;
import Main.Model.Sala;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ProfessorView {
    private Scanner scanner = new Scanner(System.in);

    public void showSalaCadastro() {
        System.out.println("\n------------ Cadastro de Sala ------------\n");
    }

    public String getDescricaoSala() {
        System.out.print("Nome da Sala: ");
        return scanner.nextLine();
    }

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

    public int getIdadeMinima() {
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

    public int getIdadeMaxima(int idadeMinima) {
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
        System.out.println(String.format("\n------------ Sala cadastrada com sucesso ------------\nID: %d\nDescrição: %s\nProfessor Responsável: %s\nIdade de Entrada: %d\nIdade Limite: %d",
                sala.getId(), sala.getDescricao(), sala.getProfessorResponsavel(), sala.getIdadeMinima(), sala.getIdadeMaxima()));
    }

    public void showAlunoCadastro() {
        System.out.println("\nCadastro de Aluno");
    }

    public String getNomeAluno() {
        System.out.print("Nome do Aluno: ");
        return scanner.nextLine();
    }

    public String getCpfAluno() {
        String cpf;
        boolean cpfValido = false;

        do {
            System.out.print("CPF do Aluno (apenas números): ");
            cpf = scanner.nextLine();

            // Verifica se o CPF tem exatamente 11 dígitos.
            if (cpf.matches("\\d{11}")) {
                // Verifica se o CPF já existe no banco de dados.
                if (cpfDisponivel(cpf)) {
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

    public boolean cpfDisponivel(String cpf) {
        try (BufferedReader reader = new BufferedReader(new FileReader("BD_USER.txt"))) {
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

    public String getCpf() {
        System.out.print("CPF: ");
        return scanner.nextLine();
    }

    public String getSenha() {
        System.out.print("Senha: ");
        return scanner.nextLine();
    }

    public String getNome() {
        System.out.print("Nome: ");
        return scanner.nextLine();
    }
}
