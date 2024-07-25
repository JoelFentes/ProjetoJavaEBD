package Main.Controller;

import Main.Model.Despesa;
import Main.View.DespesaView;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DespesaController {
    static List<Despesa> despesas;
    private static DespesaView despesaView;
    private static Scanner scanner = new Scanner(System.in);

    public DespesaController(List<Despesa> despesas, DespesaView despesaView) {
        this.despesas = despesas;
        this.despesaView = despesaView;
        carregarDespesasDoArquivo();
    }

    public static void showDespesaOptions() {
        boolean running = true;
        while (running) {
            System.out.println("\n------------ Gerenciar Despesas ------------");
            System.out.println("1 - Cadastrar Despesa");
            System.out.println("2 - Listar Despesas");
            System.out.println("0 - Voltar");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    cadastrarDespesa();
                    break;
                case 2:
                    listarDespesas();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void cadastrarDespesa() {
        despesaView.showDespesaCadastro();
        String data = despesaView.getDataDespesa();
        String descricao = despesaView.getDescricaoDespesa();
        float valor = despesaView.getValorDespesa();
        String professorNome = despesaView.getProfessorNome();
        String salaNome = despesaView.getSalaNome();

        Despesa despesa = new Despesa(data, descricao, valor, professorNome, salaNome);
        despesas.add(despesa);
        salvarDespesasNoArquivo();

        despesaView.showDespesaCadastroSucesso(despesa);
    }

    public static void listarDespesas() {
        System.out.println("\n------------ Lista de Despesas ------------");
        List<Despesa> despesas = carregarDespesasDoArquivo("C:\\Users\\joser\\IdeaProjects\\ProjetoJavaEBD\\BD_DESPESAS.txt");
        if (despesas.isEmpty()) {
            System.out.println("Nenhuma despesa encontrada.");
            return;
        }
        for (Despesa despesa : despesas) {
            System.out.println(despesa);
        }
    }


    public static List<Despesa> carregarDespesasDoArquivo() {
        return carregarDespesasDoArquivo("C:\\Users\\joser\\IdeaProjects\\ProjetoJavaEBD\\BD_DESPESAS.txt");
    }


    public static List<Despesa> carregarDespesasDoArquivo(String fileName) {
        List<Despesa> despesas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] dados = line.split(";");
                if (dados.length == 5) {
                    String data = dados[0].trim();
                    String descricao = dados[1].trim();
                    float valor = Float.parseFloat(dados[2].trim().replace(',', '.'));
                    String professorNome = dados[3].trim();
                    String salaNome = dados[4].trim();

                    Despesa despesa = new Despesa(data, descricao, valor, professorNome, salaNome);
                    despesas.add(despesa);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar os dados das despesas.");
        }
        return despesas;
    }


    private static void salvarDespesasNoArquivo() {
        String fileName = "C:\\Users\\joser\\IdeaProjects\\ProjetoJavaEBD\\BD_DESPESAS.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (Despesa despesa : despesas) {
                String despesaData = String.format("%s;%s;%.2f;%s;%s",
                        despesa.getData(), despesa.getDescricao(), despesa.getValor(),
                        despesa.getProfessorNome(), despesa.getSalaNome());
                writer.write(despesaData);
                writer.newLine();
            }
            System.out.println("Despesas cadastradas com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados das despesas.");
        }
    }
}
