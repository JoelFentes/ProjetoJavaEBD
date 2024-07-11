package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Professor {
    private static int proximoId = 0;
    private int id;
    private String nome;
    private String cpf;
    private String senha;

    public Professor(int id, String nome, String cpf, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void cadastrarSala(Scanner scanner) {
        System.out.println("\n------------ Cadastro de Sala ------------\n");

        System.out.print("Nome da Sala: ");
        String descricao = scanner.nextLine();

        List<String> nomesProfessores = carregarNomesProfessores();
        String professorResponsavel = null;

        while (professorResponsavel == null) {
            System.out.print("Professor Responsável: ");
            professorResponsavel = scanner.nextLine();

            if (!nomesProfessores.contains(professorResponsavel)) {
                System.out.println("\nProfessor não encontrado no banco de dados. Digite um nome válido.\n");
                professorResponsavel = null;
            }
        }

        int idadeMinima = 0;
        do {
            System.out.print("Idade de Entrada: ");
            idadeMinima = scanner.nextInt();
            scanner.nextLine();

            if (idadeMinima < 0) {
                System.out.println("Idade de entrada não pode ser menor que 0. Digite novamente.");
            }
        } while (idadeMinima < 0);

        int idadeMaxima = 0;
        do {
            System.out.print("Idade Limite: ");
            idadeMaxima = scanner.nextInt();
            scanner.nextLine();

            if (idadeMaxima < idadeMinima) {
                System.out.println("Idade limite não pode ser menor que a idade de entrada. Digite novamente.");
            }
        } while (idadeMaxima < idadeMinima);


        Sala sala = new Sala(descricao, professorResponsavel, idadeMinima, idadeMaxima);
        sala.salvarEmArquivo();

        System.out.println(String.format("\n------------ Sala cadastrada com sucesso ------------\nID: %d\nDescrição: %s\nProfessor Responsável: %s\nIdade de Entrada: %d\nIdade Limite: %d", sala.getId(), sala.getDescricao(), professorResponsavel, sala.getIdadeMinima(), sala.getIdadeMaxima()));
    }


    public static List<String> carregarNomesProfessores() {
        List<String> nomesProfessores = new ArrayList<>();
        String fileName = "C:\\Users\\UPE SURUBIM\\IdeaProjects\\ProjetoJavaEBD\\BD_USER.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                String nome = data[2];
                nomesProfessores.add(nome);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar os nomes dos professores do arquivo.");
        }

        return nomesProfessores;
    }

    public void cadastrarAluno(Scanner scanner) {
        System.out.println("\nCadastro de Aluno");

        System.out.print("Nome do Aluno: ");
        String nome = scanner.nextLine();

        System.out.print("CPF do Aluno: ");
        String cpf = scanner.nextLine();

        System.out.print("Whatsapp ou Número para contato: ");
        String contato = scanner.nextLine();

        System.out.print("Idade: ");
        int idade = scanner.nextInt();

        System.out.print("Sala: ");
        String salaAluno = scanner.nextLine();

        Aluno aluno = new Aluno(id, nome, cpf, contato, idade, salaAluno);
        aluno.salvarEmArquivo();

        System.out.println(String.format("\n------------ Aluno cadastrado com sucesso ------------\nMatrícula: %d\nNome: %s\nSala: %s", aluno.getMatricula(), aluno.getNome(), aluno.getSalaAluno()));
    }




}
