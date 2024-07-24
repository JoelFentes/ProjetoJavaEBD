package Main.Controller;

import Main.Model.Professor;
import Main.View.ProfessorView;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProfessorController {
    private static List<Professor> professores;
    private ProfessorView professorview;
    private int proximoIdProfessor;

    public ProfessorController(List<Professor> professores, ProfessorView professorview) {
        this.professores = professores;
        this.professorview = professorview;
        this.proximoIdProfessor = 1; // Inicializa com 1 ou o valor adequado conforme sua lógica
        carregarProfessoresDoArquivo();
    }

    Scanner scanner = new Scanner(System.in);

    public void showProfessorOptions() {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.printf(
                    "\n------------ Escolha uma opção ------------\n" +
                            "%d - Gerir Salas\n" +
                            "%d - Gerir Alunos\n" +
                            "%d - Gerir Aulas\n" +
                            "%d - Sair%n",
                    1, 2, 3, 0
            );

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    SalaController.showSalaOptions();
                    break;
                case 2:
                    AlunoController.showAlunoOptions();
                    break;
                case 3:
                    AulaController.showAulaOptions();
                    break;
                case 0:
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    public static boolean loginUser(String nome, String senha) {
       for (Professor professor : professores) {
            if (professor.getNomeProfessor().equalsIgnoreCase(nome) && professor.getSenhaProfessor().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    public void registerUser(String nome, String cpf, String senha) {
        Professor professor = new Professor(proximoIdProfessor, nome, cpf, senha);
        professores.add(professor);
        salvarProfessoresNoArquivo(); // Salva os professores no arquivo
        proximoIdProfessor++; // Incrementa o próximo ID disponível
    }

    private void carregarProfessoresDoArquivo() {
        String fileName = "C:\\Users\\joelf\\IdeaProjects\\ProjetoJavaEBD\\BD_PROFESSORES.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] dados = line.split(";");
                if (dados.length == 4) {
                    int id = Integer.parseInt(dados[0].trim());
                    String nome = dados[1].trim();
                    String cpf = dados[2].trim();
                    String senha = dados[3].trim();

                    Professor professor = new Professor(id, nome, cpf, senha);
                    professores.add(professor);

                    // Atualizar o contador de ID se necessário
                    if (id >= proximoIdProfessor) {
                        proximoIdProfessor = id + 1;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar os dados dos professores.");
        }
    }

    private void salvarProfessoresNoArquivo() {
        String fileName = "C:\\Users\\joelf\\IdeaProjects\\ProjetoJavaEBD\\BD_PROFESSORES.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {
            for (Professor professor : professores) {
                String professorData = String.format("%d;%s;%s;%s", professor.getId(), professor.getNomeProfessor(), professor.getCpfProfessor(), professor.getSenhaProfessor());
                writer.write(professorData);
                writer.newLine();
            }
            System.out.println("Professores cadastrados com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados dos professores.");
        }
    }

    public static List<String> carregarNomesProfessores() {
        List<String> nomesProfessores = new ArrayList<>();
        for (Professor professor : professores) {
            nomesProfessores.add(professor.getNomeProfessor());
        }
        return nomesProfessores;
    }
}
