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

        System.out.print("Idade de Entrada: ");
        int idadeMinima = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha pendente

        System.out.print("Idade Limite: ");
        int idadeMaxima = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha pendente

        Sala sala = new Sala(descricao, professorResponsavel, idadeMinima, idadeMaxima);
        sala.salvarEmArquivo(); // Salva a sala no arquivo BD_SALAS.txt

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

        Aluno aluno = new Aluno(id, nome, cpf);
        aluno.salvarEmArquivo();
        System.out.println("Aluno cadastrado com sucesso: " + aluno.getMatricula() + " - " + aluno.getNome());
    }




}
