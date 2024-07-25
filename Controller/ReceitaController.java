package Main.Controller;

import Main.Model.Receita;
import Main.View.ReceitaView;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReceitaController {
    private static final String fileName = "C:\\Users\\joelf\\IdeaProjects\\ProjetoJavaEBD\\BD_RECEITAS.txt";
    static List<Receita> receitas;
    private static ReceitaView receitaView;

    public ReceitaController(List<Receita> receitas, ReceitaView receitaView) {
        this.receitas = receitas;
        this.receitaView = receitaView;
        carregarReceitasDoArquivo();
    }

    public static void showReceitaOptions() {
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("\n------------ Gerenciar Receitas ------------");
            System.out.println("1 - Cadastrar Receita");
            System.out.println("2 - Listar Receitas");
            System.out.println("0 - Voltar");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    cadastrarReceita();
                    break;
                case 2:
                    listarReceitas();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void cadastrarReceita() {
        receitaView.showReceitaCadastro();
        String data = receitaView.getDataReceita();
        String descricao = receitaView.getDescricaoReceita();
        float valor = receitaView.getValorReceita();
        String salaNome = receitaView.getSalaNome();

        Receita receita = new Receita(data, descricao, valor, salaNome);
        receitas.add(receita);
        salvarReceitasNoArquivo();

        receitaView.showReceitaCadastroSucesso(receita);
    }

    public static void listarReceitas() {
        System.out.println("\n------------ Lista de Receitas ------------");
        List<Receita> receitas = carregarReceitasDoArquivo(fileName);
        if (receitas.isEmpty()) {
            System.out.println("Nenhuma receita encontrada.");
            return;
        }
        for (Receita receita : receitas) {
            System.out.println(receita);
        }
    }

    public static List<Receita> carregarReceitasDoArquivo() {
        return carregarReceitasDoArquivo(fileName);
    }



    public static List<Receita> carregarReceitasDoArquivo(String fileName) {
        List<Receita> receitas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] dados = line.split(";");
                if (dados.length == 4) {
                    String data = dados[0].trim();
                    String descricao = dados[1].trim();
                    float valor = Float.parseFloat(dados[2].trim().replace(',', '.'));
                    String salaNome = dados[3].trim();

                    Receita receita = new Receita(data, descricao, valor, salaNome);
                    receitas.add(receita);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar os dados das receitas.");
        }
        return receitas;
    }


    private static void salvarReceitasNoArquivo() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (Receita receita : receitas) {
                String receitaData = String.format("%s;%s;%.2f;%s",
                        receita.getData(), receita.getDescricao(), receita.getValor(), receita.getSalaNome());
                writer.write(receitaData);
                writer.newLine();
            }
            System.out.println("Receitas cadastradas com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados das receitas.");
        }
    }
}
