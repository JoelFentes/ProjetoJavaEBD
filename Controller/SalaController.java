package Main.Controller;

import Main.Model.Sala;
import Main.View.SalaView;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SalaController {
    private static List<Sala> salas;
    private static ProfessorController professorController;
    private static SalaView salaview;

    public SalaController(List<Sala> salas, ProfessorController professorController, SalaView salaview) {
        SalaController.salas = salas;
        SalaController.professorController = professorController;
        SalaController.salaview = salaview;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void showSalaOptions() {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.printf(
                    "\n------------ Escolha uma opção ------------\n" +
                            "%d - Cadastrar Salas\n" +
                            "%d - Alterar Salas\n" +
                            "%d - Deletar Salas\n" +
                            "%d - Voltar ao Menu%n",
                    1, 2, 3, 0
            );

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    cadastrarSala();
                    break;
                case 2:
                    alterarSala();
                    break;
                case 3:
                    // Implementar método deletarSala se necessário
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

    public static void cadastrarSala() {
        SalaView.showSalaCadastro();

        String descricao = salaview.getDescricaoSala();
        String professorResponsavel;
        List<String> nomesProfessores = professorController.carregarNomesProfessores();

        do {
            professorResponsavel = salaview.getProfessorResponsavel();
            if (!nomesProfessores.contains(professorResponsavel)) {
                System.out.println("Professor não encontrado. Por favor, insira um nome válido.");
            }
        } while (!nomesProfessores.contains(professorResponsavel));

        int idadeMinima = salaview.getIdadeMinimaSala();
        int idadeMaxima = salaview.getIdadeMaximaSala(idadeMinima);

        Sala sala = new Sala(descricao, professorResponsavel, idadeMinima, idadeMaxima);
        salas.add(sala);
        salvarSalasNoArquivo(salas);

        salaview.showSalaCadastroSucesso(sala);
        System.out.println("Sala salva com sucesso!");
    }

    public static void ListarSalas() {
        System.out.println("\n----------- Lista de Salas -----------");

        List<Sala> salas = carregarSalasDoArquivo();
        if (salas.isEmpty()) {
            System.out.println("Nenhuma sala encontrada.");
            return;
        }
        for (Sala sala : salas) {
            System.out.println(sala);
        }
    }

    // Método para alterar sala
    public static void alterarSala() {
        List<Sala> salas = carregarSalasDoArquivo();

        if (salas.isEmpty()) {
            System.out.println("Nenhuma sala encontrada para alteração.");
            return;
        }

        // Listar salas
        ListarSalas();

        // Solicitar ID da sala a ser alterada
        System.out.print("Informe o ID da sala que deseja alterar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        Sala salaSelecionada = null;
        for (Sala sala : salas) {
            if (sala.getId() == id) {
                salaSelecionada = sala;
                break;
            }
        }

        if (salaSelecionada == null) {
            System.out.println("Sala com ID " + id + " não encontrada.");
            return;
        }

        boolean flag = true;
        while (flag) {
            System.out.printf(
                    "\n------------ O que deseja alterar ------------\n" +
                            "1 - Descrição\n" +
                            "2 - Professor responsável\n" +
                            "3 - Idade mínima\n" +
                            "4 - Idade máxima\n" +
                            "5 - Sair\n" +
                            "Escolha uma opção: "
            );

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Novo nome da sala: ");
                    String novoNome = scanner.nextLine();
                    salaSelecionada.setDescricao(novoNome);
                    break;
                case 2:
                    System.out.print("Novo professor responsável: ");
                    String novoProfessor = scanner.nextLine();
                    salaSelecionada.setProfessorResponsavel(novoProfessor);
                    break;
                case 3:
                    System.out.print("Nova idade mínima: ");
                    int novaIdadeMinima = scanner.nextInt();
                    scanner.nextLine();
                    salaSelecionada.setIdadeMinimaSala(novaIdadeMinima);
                    break;
                case 4:
                    System.out.print("Nova idade máxima: ");
                    int novaIdadeMaxima = scanner.nextInt();
                    scanner.nextLine();
                    salaSelecionada.setIdadeMaximaSala(novaIdadeMaxima);
                    break;
                case 5:

                    salvarSalasNoArquivo(salas);
                    flag = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    private static void salvarSalasNoArquivo(List<Sala> salas) {
        String fileName = "C:\\Users\\joelf\\IdeaProjects\\ProjetoJavaEBD\\BD_SALAS.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (Sala sala : salas) {
                String salaData = String.format("%d;%s;%s;%d;%d", sala.getId(), sala.getDescricao(), sala.getProfessorResponsavel(),
                        sala.getIdadeMinimaSala(), sala.getIdadeMaximaSala());
                writer.write(salaData);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados das salas.");
            e.printStackTrace();
        }
    }

    public static List<Sala> carregarSalasDoArquivo() {
        return carregarSalasDoArquivo("C:\\Users\\joelf\\IdeaProjects\\ProjetoJavaEBD\\BD_SALAS.txt");
    }

    public static List<Sala> carregarSalasDoArquivo(String fileName) {
        List<Sala> salas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] data = linha.split(";");

                if (data.length != 5) {
                    System.out.println("Linha inválida: " + linha);
                    continue;
                }

                try {
                    int id = Integer.parseInt(data[0].trim());
                    String descricao = data[1].trim();
                    String professorResponsavel = data[2].trim();
                    int idadeMinima = Integer.parseInt(data[3].trim());
                    int idadeMaxima = Integer.parseInt(data[4].trim());

                    Sala sala = new Sala(descricao, professorResponsavel, idadeMinima, idadeMaxima);
                    sala.setId(id);
                    salas.add(sala);

                } catch (NumberFormatException e) {
                    System.out.println("Erro ao formatar os números na linha: " + linha);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return salas;
    }
}
