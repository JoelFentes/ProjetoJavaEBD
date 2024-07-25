package Main.Controller;


import Main.Model.Professor;
import Main.View.ProfessorView;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Main.Controller.NotificacaoEventoController;
import Main.Controller.NotificacaoAlunoController;

import static Main.Controller.DespesaController.despesas;
import static Main.Controller.ReceitaController.receitas;


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
                            "\n------- Gestão EBD -------\n" +
                            "%d - Gerir Salas\n" +
                            "%d - Gerir Alunos\n" +
                            "%d - Gerir Aulas\n" +
                            "\n------- Secretaria -------\n" +
                            "%d - Gerir Despesas\n" +
                            "%d - Gerir Receitas\n" +
                            "%d - Gerar Relatório\n" +
                            "%d - Excluir Conta\n" +
                            "%d - Enviar Notificação para Aluno\n" +
                            "%d - Enviar Notificação de Evento\n" +
                            "%d - Sair%n",
                    1, 2, 3, 4, 5, 6, 7, 8, 9, 0
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
                case 4:
                    DespesaController.showDespesaOptions();
                    break;
                case 5:
                    ReceitaController.showReceitaOptions();
                    break;
                case 6:
                    RelatorioFinanceiroController.gerarRelatorio(receitas, despesas);
                    break;
                case 7:
                    deletarProfessor();
                    break;
                case 8:
                    NotificacaoAlunoController.enviarNotificacao();
                    break;
                case 9:
                    NotificacaoEventoController.enviarNotificacao();
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

    public void deletarProfessor() {
        if (professores.isEmpty()) {
            System.out.println("Nenhum professor encontrado para deletar.");
            return;
        }

        // Listar professores
        System.out.println("\n----------- Lista de Professores -----------");
        for (Professor professor : professores) {
            System.out.printf("ID: %d, Nome: %s%n", professor.getId(), professor.getNomeProfessor());
        }

        System.out.print("Informe o ID do professor que deseja deletar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Professor professorParaDeletar = null;
        for (Professor professor : professores) {
            if (professor.getId() == id) {
                professorParaDeletar = professor;
                break;
            }
        }

        if (professorParaDeletar == null) {
            System.out.println("Professor com ID " + id + " não encontrado.");
            return;
        }

        System.out.print("Informe a senha do professor: ");
        String senha = scanner.nextLine();

        if (!professorParaDeletar.getSenhaProfessor().equals(senha)) {
            System.out.println("Senha incorreta. Operação cancelada.");
            return;
        }

        professores.remove(professorParaDeletar);
        salvarProfessoresNoArquivo();
        System.out.println("Professor deletado com sucesso!");
    }


    private void carregarProfessoresDoArquivo() {
        String fileName = "C:\\Users\\joser\\IdeaProjects\\ProjetoJavaEBD\\BD_PROFESSORES.txt";
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
        String fileName = "C:\\Users\\joser\\IdeaProjects\\ProjetoJavaEBD\\BD_PROFESSORES.txt";
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
