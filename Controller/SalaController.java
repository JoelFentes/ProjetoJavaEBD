package Main.Controller;

import Main.Model.Sala;
import Main.View.SalaView;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class SalaController {
    private static List<Sala> salas;
    private ProfessorController professorController;
    private static SalaView salaview;

    public SalaController(List<Sala> salas, ProfessorController professorController, SalaView salaview) {
        this.salas = salas;
        this.professorController = professorController;
        this.salaview = salaview;
    }
    private static Scanner scanner = new Scanner(System.in);


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
            scanner.nextLine(); // Consumir a quebra de linha após o nextInt()

            switch (choice) {
                case 1:
                    SalaView.showSalaCadastro();
                    cadastrarSala();
                    break;
                case 2:
                    // Alterar Salas - implementação futura
                    break;
                case 3:
                    System.out.println("Em desenvolvimento...");
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
        salaview.showSalaCadastro();

        String descricao = salaview.getDescricaoSala();
        String professorResponsavel = salaview.getProfessorResponsavel();
        int idadeMinima = salaview.getIdadeMinimaSala();
        int idadeMaxima = salaview.getIdadeMaximaSala(idadeMinima);

        Sala sala = new Sala(descricao, professorResponsavel, idadeMinima, idadeMaxima);
        salas.add(sala); // Adiciona a sala à lista de salas

        salvarSalasNoArquivo(); // Salva as salas no arquivo

        salaview.showSalaCadastroSucesso(sala);
    }

    private static void salvarSalasNoArquivo() {
        String fileName = "C:\\Users\\hfent\\IdeaProjects\\ProjetoJavaEBD\\BD_SALAS.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Sala sala : salas) {
                String salaData = String.format("%s;%s;%d;%d", sala.getDescricao(), sala.getProfessorResponsavel(),
                        sala.getIdadeMinimaSala(), sala.getIdadeMaximaSala());
                writer.write(salaData);
                writer.newLine();
            }
            System.out.println("Sala cadastrada com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados das salas.");
        }
    }
}
