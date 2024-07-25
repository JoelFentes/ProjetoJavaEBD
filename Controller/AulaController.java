package Main.Controller;

import Main.Model.Aluno;
import Main.Model.Aula;
import Main.Model.Sala;
import Main.View.AulaView;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class AulaController {
    private static final String fileName = "C:\\Users\\joser\\IdeaProjects\\ProjetoJavaEBD\\BD_AULAS.txt";
    private static List<Aula> aulas;
    private static SalaController salaController;
    private static AulaView aulaView;

    public AulaController(List<Aula> aulas, SalaController salaController, AulaView aulaView) {
        AulaController.aulas = aulas;
        AulaController.salaController = salaController;
        AulaController.aulaView = aulaView;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void showAulaOptions() {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.printf(
                    "\n------------ Escolha uma opção ------------\n" +
                            "%d - Cadastrar Aulas\n" +
                            "%d - Alterar Aulas\n" +
                            "%d - Listar Aulas\n" +
                            "%d - Adicionar Presença\n" +
                            "%d - Deletar Aula\n" +
                            "%d - Voltar ao Menu%n",
                    1, 2, 3, 4, 5, 0
            );

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    cadastrarAula();
                    break;
                case 2:
                    alterarAula();
                    break;
                case 3:
                    listarAulas();
                    break;
                case 4:
                    adicionarPresenca();
                    break;
                case 5:
                    deletarAula();
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

    public static void cadastrarAula() {
        AulaView.showAulaCadastro();

        int novoId = obterUltimoId() + 1;

        String descricao = aulaView.getDescricaoAula();
        String data = aulaView.getDataAula();
        String professorResponsavel;
        List<String> nomesProfessores = ProfessorController.carregarNomesProfessores();

        do {
            professorResponsavel = aulaView.getProfessorResponsavel();
            if (!nomesProfessores.contains(professorResponsavel)) {
                System.out.println("Professor não encontrado. Por favor, insira um nome válido.");
            }
        } while (!nomesProfessores.contains(professorResponsavel));

        String nomeSala;
        Sala sala = null;
        List<Sala> salas = SalaController.carregarSalasDoArquivo();

        do {
            nomeSala = aulaView.getAulaSala();
            for (Sala s : salas) {
                if (s.getDescricao().equalsIgnoreCase(nomeSala)) {
                    sala = s;
                    break;
                }
            }
            if (sala == null) {
                System.out.println("Sala não encontrada. Por favor, insira um nome válido.");
            }
        } while (sala == null);

        float oferta = aulaView.getOfertaAula();

        Map<String, Boolean> presencas = new HashMap<>();

        Aula aula = new Aula(descricao, data, professorResponsavel, sala, oferta, presencas);
        aula.setId(novoId);
        aulas.add(aula);
        salvarAulasNoArquivo(aulas);

        aulaView.showAulaCadastroSucesso(aula);
        System.out.println("Aula salva com sucesso!");
    }

    public static void listarAulas() {
        System.out.println("\n----------- Lista de Aulas -----------");

        List<Aula> aulas = carregarAulasDoArquivo();
        if (aulas.isEmpty()) {
            System.out.println("Nenhuma aula encontrada.");
            return;
        }
        for (Aula aula : aulas) {
            System.out.println(aula);
        }
    }

    public static void alterarAula() {
        List<Aula> aulas = carregarAulasDoArquivo();
        AulaView.showAulaCadastro();

        if (aulas.isEmpty()) {
            System.out.println("Nenhuma aula encontrada para alteração.");
            return;
        }

        listarAulas();

        System.out.print("Informe o ID da aula que deseja alterar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Aula aulaSelecionada = null;
        for (Aula aula : aulas) {
            if (aula.getId() == id) {
                aulaSelecionada = aula;
                break;
            }
        }

        if (aulaSelecionada == null) {
            System.out.println("Aula com ID " + id + " não encontrada.");
            return;
        }

        boolean flag = true;
        while (flag) {
            System.out.printf(
                    "\n------------ O que deseja alterar ------------\n" +
                            "1 - Descrição\n" +
                            "2 - Data\n" +
                            "3 - Professor responsável\n" +
                            "4 - Sala\n" +
                            "5 - Sair\n" +
                            "Escolha uma opção: "
            );

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nova descrição da aula: ");
                    String novaDescricao = scanner.nextLine();
                    aulaSelecionada.setDescricao(novaDescricao);
                    break;
                case 2:
                    System.out.print("Nova data da aula (dd/MM/yyyy): ");
                    String novaData = scanner.nextLine();
                    aulaSelecionada.setData(novaData);
                    break;
                case 3:
                    String novoProfessor;
                    do {
                        novoProfessor = aulaView.getProfessorResponsavel();
                        if (!ProfessorController.carregarNomesProfessores().contains(novoProfessor)) {
                            System.out.println("Professor não encontrado. Por favor, insira um nome válido.");
                        }
                    } while (!ProfessorController.carregarNomesProfessores().contains(novoProfessor));
                    aulaSelecionada.setProfessorResponsavel(novoProfessor);
                    break;
                case 4:
                    String novaSalaNome;
                    Sala novaSala = null;
                    do {
                        novaSalaNome = aulaView.getAulaSala();
                        for (Sala s : SalaController.carregarSalasDoArquivo()) {
                            if (s.getDescricao().equals(novaSalaNome)) {
                                novaSala = s;
                                break;
                            }
                        }
                        if (novaSala == null) {
                            System.out.println("Sala não encontrada. Por favor, insira um nome válido.");
                        }
                    } while (novaSala == null);
                    aulaSelecionada.setSala(novaSala);
                    break;
                case 5:
                    flag = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
        salvarAulasNoArquivoSobrescrever(aulas);
        aulaView.showAulaCadastroSucesso(aulaSelecionada);
        System.out.println("Alteração realizada com sucesso!");
    }

    public static void adicionarPresenca() {
        List<Aula> aulas = carregarAulasDoArquivo();

        if (aulas.isEmpty()) {
            System.out.println("Nenhuma aula encontrada.");
            return;
        }

        listarAulas();

        System.out.print("Informe o ID da aula para adicionar/atualizar presença: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Aula aulaSelecionada = null;
        for (Aula aula : aulas) {
            if (aula.getId() == id) {
                aulaSelecionada = aula;
                break;
            }
        }

        if (aulaSelecionada == null) {
            System.out.println("Aula com ID " + id + " não encontrada.");
            return;
        }

        List<Aluno> alunosNaSala = AlunoController.carregarAlunosPorSala(aulaSelecionada.getSala());
        System.out.println("\n----------- Alunos na Sala " + aulaSelecionada.getSala().getDescricao() + " -----------");
        for (Aluno aluno : alunosNaSala) {
            System.out.println("Nome: " + aluno.getNome() + ", CPF: " + aluno.getCpf());
        }

        System.out.print("Informe o nome do aluno: ");
        String nomeAluno = scanner.nextLine();

        Aluno alunoSelecionado = null;
        for (Aluno aluno : alunosNaSala) {
            if (aluno.getNome().equalsIgnoreCase(nomeAluno)) {
                alunoSelecionado = aluno;
                break;
            }
        }

        if (alunoSelecionado == null) {
            System.out.println("Aluno com nome " + nomeAluno + " não encontrado na sala " + aulaSelecionada.getSala().getDescricao() + ".");
            return;
        }

        System.out.print("Informe o status de presença (Presente/Ausente): ");
        String statusPresenca = scanner.nextLine();

        boolean presenca = statusPresenca.equalsIgnoreCase("Presente");
        aulaSelecionada.getPresencas().put(alunoSelecionado.getNome(), presenca);

        List<Aula> aulasAtualizadas = carregarAulasDoArquivo();
        for (int i = 0; i < aulasAtualizadas.size(); i++) {
            if (aulasAtualizadas.get(i).getId() == id) {
                aulasAtualizadas.set(i, aulaSelecionada);
                break;
            }
        }

        salvarAulasNoArquivoSobrescrever(aulasAtualizadas);
        System.out.println("Presença adicionada/atualizada com sucesso!");
    }

    public static void deletarAula() {
        List<Aula> aulas = carregarAulasDoArquivo();

        if (aulas.isEmpty()) {
            System.out.println("Nenhuma aula encontrada para deletar.");
            return;
        }

        listarAulas();

        System.out.print("Informe o ID da aula que deseja deletar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Aula aulaParaDeletar = null;
        for (Aula aula : aulas) {
            if (aula.getId() == id) {
                aulaParaDeletar = aula;
                break;
            }
        }

        if (aulaParaDeletar == null) {
            System.out.println("Aula com ID " + id + " não encontrada.");
            return;
        }

        aulas.remove(aulaParaDeletar);
        salvarAulasNoArquivoSobrescrever(aulas);
        System.out.println("Aula deletada com sucesso!");
    }

    private static int obterUltimoId() {
        int ultimoId = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length > 0) {
                    try {
                        int id = Integer.parseInt(parts[0]);
                        if (id > ultimoId) {
                            ultimoId = id;
                        }
                    } catch (NumberFormatException e) {
                        // Ignorar IDs inválidos
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de aulas.");
            e.printStackTrace();
        }

        return ultimoId;
    }

    private static List<Aula> carregarAulasDoArquivo() {
        List<Aula> aulas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length < 6) continue;

                int id = Integer.parseInt(parts[0]);
                String descricao = parts[1];
                String data = parts[2];
                String professorResponsavel = parts[3];
                String salaDescricao = parts[4];
                float oferta = Float.parseFloat(parts[5].replace(',', '.'));

                Map<String, Boolean> presencas = new HashMap<>();
                if (parts.length > 6 && !parts[6].isEmpty()) {
                    String[] presencaParts = parts[6].split(",");
                    for (String presenca : presencaParts) {
                        String[] presencaDados = presenca.split(":");
                        if (presencaDados.length == 2) {
                            presencas.put(presencaDados[0], Boolean.parseBoolean(presencaDados[1]));
                        }
                    }
                }

                Sala sala = SalaController.carregarSalasDoArquivo().stream()
                        .filter(s -> s.getDescricao().equalsIgnoreCase(salaDescricao))
                        .findFirst()
                        .orElse(null);
                if (sala == null) {
                    System.out.println("Sala não encontrada: " + salaDescricao);
                    continue;
                }

                Aula aula = new Aula(descricao, data, professorResponsavel, sala, oferta, presencas);
                aula.setId(id);
                aulas.add(aula);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar os dados das aulas.");
            e.printStackTrace();
        }

        return aulas;
    }

    private static void salvarAulasNoArquivo(List<Aula> aulas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (Aula aula : aulas) {
                String aulaData = String.format("%d;%s;%s;%s;%s;%.2f",
                        aula.getId(), aula.getDescricao(), aula.getData(), aula.getProfessorResponsavel(),
                        aula.getSala().getDescricao(), aula.getOferta());

                String presencasString = aula.getPresencas().entrySet().stream()
                        .map(entry -> entry.getKey() + ":" + entry.getValue())
                        .collect(Collectors.joining(","));

                writer.write(aulaData + (presencasString.isEmpty() ? "" : ";" + presencasString));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados das aulas.");
            e.printStackTrace();
        }
    }

    private static void salvarAulasNoArquivoSobrescrever(List<Aula> aulas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Aula aula : aulas) {
                String aulaData = String.format("%d;%s;%s;%s;%s;%.2f",
                        aula.getId(), aula.getDescricao(), aula.getData(), aula.getProfessorResponsavel(),
                        aula.getSala().getDescricao(), aula.getOferta());

                String presencasString = aula.getPresencas().entrySet().stream()
                        .map(entry -> entry.getKey() + ":" + entry.getValue())
                        .collect(Collectors.joining(","));

                writer.write(aulaData + (presencasString.isEmpty() ? "" : ";" + presencasString));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados das aulas.");
            e.printStackTrace();
        }
    }
}
