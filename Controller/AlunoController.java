package Main.Controller;

import Main.Model.Aluno;
import Main.Model.Sala;
import Main.View.AlunoView;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlunoController {
    private static final String fileName = "C:\\Users\\joelf\\IdeaProjects\\ProjetoJavaEBD\\BD_ALUNOS.txt"; // Caminho do arquivo

    private static List<Aluno> alunos = new ArrayList<>();
    private static SalaController salaController;
    private static AlunoView alunoView;

    private static final Scanner scanner = new Scanner(System.in);

    public AlunoController(List<Aluno> alunos, SalaController salaController, AlunoView alunoView) {
        AlunoController.alunos = alunos;
        AlunoController.salaController = salaController;
        AlunoController.alunoView = alunoView;
    }

    public static void showAlunoOptions() {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.printf(
                    "\n------------ Escolha uma opção ------------\n" +
                            "%d - Cadastrar Aluno\n" +
                            "%d - Alterar Aluno\n" +
                            "%d - Listar Alunos\n" +
                            "%d - Deletar Aluno\n" +
                            "%d - Voltar ao Menu%n",
                    1, 2, 3, 4, 0
            );

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    cadastrarAluno();
                    break;
                case 2:
                    alterarAluno();
                    break;
                case 3:
                    listarAlunos();
                    break;
                case 4:
                    deletarAluno();
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

    public static void cadastrarAluno() {
        AlunoView.showAlunoCadastro();

        String cpf;
        do {
            cpf = alunoView.getCpf();
            if (isCpfExistente(cpf)) {
                System.out.println("CPF já cadastrado. Por favor, insira um CPF válido.");
            }
        } while (isCpfExistente(cpf));

        String nome = alunoView.getNome();

        int idade;
        Sala sala;
        List<Sala> salas = SalaController.carregarSalasDoArquivo();

        String nomeSala = alunoView.getSala();
        do {
            idade = alunoView.getIdade();


            sala = null;
            for (Sala s : salas) {
                if (s.getDescricao().equalsIgnoreCase(nomeSala)) {
                    sala = s;
                    break;
                }
            }

            if (sala == null) {
                System.out.println("Sala não encontrada. Por favor, insira um nome válido.");
            } else if (idade < sala.getIdadeMinimaSala() || idade > sala.getIdadeMaximaSala()) {
                System.out.printf("Idade do aluno não está dentro do range permitido para a sala. As idades permitidas são de %d até %d anos.%n",
                        sala.getIdadeMinimaSala(), sala.getIdadeMaximaSala());
            }

        } while (sala == null || idade < sala.getIdadeMinimaSala() || idade > sala.getIdadeMaximaSala());

        String contato = alunoView.getContato();
        String endereco = alunoView.getEndereco();

        Aluno aluno = new Aluno(cpf, nome, idade, sala, contato, endereco);
        alunos.add(aluno);
        salvarAlunosNoArquivo(alunos);

        alunoView.ShowAlunoCadastroSucesso(aluno);
    }

    public static void alterarAluno() {
        List<Aluno> alunos = carregarAlunosDoArquivo();

        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno encontrado para alteração.");
            return;
        }

        // Listar alunos
        listarAlunos();

        // Solicitar CPF do aluno a ser alterado
        System.out.print("Informe o CPF do aluno que deseja alterar: ");
        String cpf = scanner.nextLine();

        Aluno alunoSelecionado = null;
        for (Aluno aluno : alunos) {
            if (aluno.getCpf().equals(cpf)) {
                alunoSelecionado = aluno;
                break;
            }
        }

        if (alunoSelecionado == null) {
            System.out.println("Aluno com CPF " + cpf + " não encontrado.");
            return;
        }

        boolean flag = true;
        while (flag) {
            System.out.printf(
                    "\n------------ O que deseja alterar ------------\n" +
                            "1 - Nome\n" +
                            "2 - Idade\n" +
                            "3 - Contato\n" +
                            "4 - Endereço\n" +
                            "5 - Sala\n" +
                            "6 - Sair\n" +
                            "Escolha uma opção: "
            );

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Novo nome do aluno: ");
                    String novoNome = scanner.nextLine();
                    alunoSelecionado.setNome(novoNome);
                    break;
                case 2:
                    System.out.print("Nova idade do aluno: ");
                    int novaIdade = scanner.nextInt();
                    scanner.nextLine();
                    alunoSelecionado.setIdade(novaIdade);
                    break;
                case 3:
                    System.out.print("Novo contato do aluno: ");
                    String novoContato = scanner.nextLine();
                    alunoSelecionado.setContato(novoContato);
                    break;
                case 4:
                    System.out.print("Novo endereço do aluno: ");
                    String novoEndereco = scanner.nextLine();
                    alunoSelecionado.setEndereco(novoEndereco);
                    break;
                case 5:
                    String novaSalaNome;
                    Sala novaSala = null;
                    List<Sala> salas = SalaController.carregarSalasDoArquivo();
                    do {
                        novaSalaNome = alunoView.getSala();
                        for (Sala s : salas) {
                            if (s.getDescricao().equals(novaSalaNome)) {
                                novaSala = s;
                                break;
                            }
                        }
                        if (novaSala == null) {
                            System.out.println("Sala não encontrada. Por favor, insira um nome válido.");
                        }
                    } while (novaSala == null);
                    alunoSelecionado.setSala(novaSala);
                    break;
                case 6:
                    salvarAlunosNoArquivoSobrescrever(alunos);
                    flag = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    public static void listarAlunos() {
        System.out.println("\n----------- Lista de Alunos -----------");

        List<Aluno> alunos = carregarAlunosDoArquivo();
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno encontrado.");
            return;
        }
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }
    }

    public static void deletarAluno() {
        List<Aluno> alunos = carregarAlunosDoArquivo();

        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno encontrado para deletar.");
            return;
        }

        listarAlunos();

        System.out.print("Informe o CPF do aluno que deseja deletar: ");
        String cpf = scanner.nextLine();

        Aluno alunoParaDeletar = null;
        for (Aluno aluno : alunos) {
            if (aluno.getCpf().equals(cpf)) {
                alunoParaDeletar = aluno;
                break;
            }
        }

        if (alunoParaDeletar == null) {
            System.out.println("Aluno com CPF " + cpf + " não encontrado.");
            return;
        }

        alunos.remove(alunoParaDeletar);
        salvarAlunosNoArquivoSobrescrever(alunos);
        System.out.println("Aluno deletado com sucesso!");
    }

    public static List<Aluno> carregarAlunosPorSala(Sala sala) {
        List<Aluno> alunosPorSala = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] data = linha.split(";");
                if (data.length >= 6) { // Verifica se a linha tem pelo menos 6 campos
                    String nomeSala = data[3].trim(); // Obtém o nome da sala

                    // Verifica se o nome da sala corresponde à sala especificada
                    if (nomeSala.equalsIgnoreCase(sala.getDescricao())) {
                        String cpf = data[0].trim();
                        String nome = data[1].trim();
                        int idade = Integer.parseInt(data[2].trim());
                        String contato = data[4].trim();
                        String endereco = data[5].trim();

                        // Cria um objeto Aluno e adiciona à lista
                        Aluno aluno = new Aluno(cpf, nome, idade, sala, contato, endereco);
                        alunosPorSala.add(aluno);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return alunosPorSala;
    }

    private static boolean isCpfExistente(String cpf) {
        List<Aluno> alunos = carregarAlunosDoArquivo();
        for (Aluno aluno : alunos) {
            if (aluno.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    private static void salvarAlunosNoArquivo(List<Aluno> alunos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (Aluno aluno : alunos) {
                String alunoData = String.format("%s;%s;%d;%s;%s;%s",
                        aluno.getCpf(), aluno.getNome(), aluno.getIdade(),
                        aluno.getSala().getDescricao(), aluno.getContato(), aluno.getEndereco());
                writer.write(alunoData);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados dos alunos.");
            e.printStackTrace();
        }
    }

    private static void salvarAlunosNoArquivoSobrescrever(List<Aluno> alunos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Aluno aluno : alunos) {
                String alunoData = String.format("%s;%s;%d;%s;%s;%s",
                        aluno.getCpf(), aluno.getNome(), aluno.getIdade(),
                        aluno.getSala().getDescricao(), aluno.getContato(), aluno.getEndereco());
                writer.write(alunoData);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados dos alunos.");
            e.printStackTrace();
        }
    }

    public static List<Aluno> carregarAlunosDoArquivo() {
        List<Aluno> alunos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] data = linha.split(";");

                if (data.length != 6) {
                    System.out.println("Linha inválida: " + linha);
                    continue;
                }

                try {
                    String cpf = data[0].trim();
                    String nome = data[1].trim();
                    int idade = Integer.parseInt(data[2].trim());
                    String nomeSala = data[3].trim();
                    String contato = data[4].trim();
                    String endereco = data[5].trim();

                    Sala sala = null;
                    for (Sala s : SalaController.carregarSalasDoArquivo()) {
                        if (s.getDescricao().equals(nomeSala)) {
                            sala = s;
                            break;
                        }
                    }

                    if (sala != null) {
                        Aluno aluno = new Aluno(cpf, nome, idade, sala, contato, endereco);
                        alunos.add(aluno);
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Erro ao formatar dados na linha: " + linha);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return alunos;
    }
}
