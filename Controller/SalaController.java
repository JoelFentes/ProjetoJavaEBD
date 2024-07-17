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
        carregarSalasDoArquivo();
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
                    deletarSala();
                    break;
                case 0:
                    System.out.println("Obrigado por usar o sistema EBD!");
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
        salvarSalasNoArquivo();

        salaview.showSalaCadastroSucesso(sala);
        System.out.println("Sala salva com sucesso!");
    }


    public static void alterarSala() {
        System.out.print("Digite o nome da sala que deseja alterar: ");
        String nomeSala = scanner.nextLine();

        Sala salaEncontrada = null;
        for (Sala sala : salas) {
            if (sala.getDescricao().equalsIgnoreCase(nomeSala)) {
                salaEncontrada = sala;
                break;
            }
        }

        if (salaEncontrada != null) {
            SalaView.showSalaAlteracao(salaEncontrada); // Mostra os detalhes da sala encontrada

            System.out.println("\n------------ Escolha o que deseja alterar ------------\n" +
                    "1 - Descrição\n" +
                    "2 - Professor Responsável\n" +
                    "3 - Idade de Entrada\n" +
                    "4 - Idade Limite\n" +
                    "0 - Cancelar");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nova descrição: ");
                    String novaDescricao = scanner.nextLine();
                    salaEncontrada.setDescricao(novaDescricao);
                    break;
                case 2:
                    List<String> nomesProfessores = professorController.carregarNomesProfessores();
                    String novoProfessor;
                    do {
                        System.out.print("Novo professor responsável: ");
                        novoProfessor = scanner.nextLine();
                        if (!nomesProfessores.contains(novoProfessor)) {
                            System.out.println("Professor não encontrado. Por favor, insira um nome válido.");
                        }
                    } while (!nomesProfessores.contains(novoProfessor));
                    salaEncontrada.setProfessorResponsavel(novoProfessor);
                    break;
                case 3:
                    int novaIdadeMinima = salaview.getIdadeMinimaSala();
                    salaEncontrada.setIdadeMinimaSala(novaIdadeMinima);
                    int novaIdadeMaxima = salaview.getIdadeMaximaSala(novaIdadeMinima);
                    salaEncontrada.setIdadeMaximaSala(novaIdadeMaxima);
                    break;
                case 4:
                    novaIdadeMaxima = salaview.getIdadeMaximaSala(salaEncontrada.getIdadeMinimaSala());
                    salaEncontrada.setIdadeMaximaSala(novaIdadeMaxima);
                    break;
                case 0:
                    System.out.println("Operação de alteração cancelada.");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }

            salvarSalasNoArquivo(); // Salva as salas atualizadas no arquivo

            System.out.println("Sala alterada com sucesso!");
        } else {
            System.out.println("Sala não encontrada.");
        }
    }



    public static void deletarSala() {
        SalaView.showSalaDelecaoConfirmacao(salaview.getDescricaoSala());

        System.out.print("Digite o nome da sala que deseja deletar: ");
        String nomeSala = scanner.nextLine();

        Sala salaEncontrada = null;
        for (Sala sala : salas) {
            if (sala.getDescricao().equalsIgnoreCase(nomeSala)) {
                salaEncontrada = sala;
                break;
            }
        }

        if (salaEncontrada != null) {
            salaview.showSalaDelecaoConfirmacao(salaEncontrada.getDescricao());
            String confirmacao = scanner.nextLine().trim();

            if (confirmacao.equalsIgnoreCase("S")) {
                salas.remove(salaEncontrada);
                salvarSalasNoArquivo(); // Salva as salas atualizadas no arquivo
                System.out.println("Sala deletada com sucesso!");
            } else {
                System.out.println("Operação de deleção cancelada.");
            }
        } else {
            System.out.println("Sala não encontrada.");
        }
    }

    private static void salvarSalasNoArquivo() {
        String fileName = "C:\\Users\\joelf\\IdeaProjects\\ProjetoJavaEBD\\BD_SALAS.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Sala sala : salas) {
                String salaData = String.format("%d;%s;%s;%d;%d", sala.getId(), sala.getDescricao(), sala.getProfessorResponsavel(),
                        sala.getIdadeMinimaSala(), sala.getIdadeMaximaSala());
                writer.write(salaData);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados das salas.");
            e.printStackTrace(); // Mostra detalhes do erro no console para diagnóstico
        }
    }


    private static List<Sala> carregarSalasDoArquivo() {
        List<Sala> salas = new ArrayList<>();
        String fileName = "C:\\Users\\joelf\\IdeaProjects\\ProjetoJavaEBD\\BD_SALAS.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] data = linha.split(";");
                int id = Integer.parseInt(data[0]);
                String descricao = data[1];
                String professorResponsavel = data[2];
                int idadeMinima = Integer.parseInt(data[3]);
                int idadeMaxima = Integer.parseInt(data[4]);
                Sala sala = new Sala(descricao, professorResponsavel, idadeMinima, idadeMaxima);
                sala.setId(id);
                salas.add(sala);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao ler as salas do arquivo.");
        }
        return salas;
    }


}
