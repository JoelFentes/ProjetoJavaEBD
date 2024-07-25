package Main.View;

import Main.Controller.*;

import java.util.Scanner;

public class MainView {
    private ProfessorView professorView;
    private ProfessorController professorController;
    private SalaController salaController;
    private AlunoController alunoController;
    private AulaController aulaController;
    private ReceitaController receitaController;
    private DespesaController despesaController;
    private RelatorioFinanceiroController relatorioController;
    private NotificacaoAlunoController notificacaoAlunoController;
    private NotificacaoEventoController notificacaoEventoController;

    private Scanner scanner = new Scanner(System.in);

    public MainView(ProfessorView professorView, ProfessorController professorController,
                    SalaController salaController, AlunoController alunoController,
                    AulaController aulaController, ReceitaController receitaController,
                    DespesaController despesaController, RelatorioFinanceiroController relatorioController,
                    NotificacaoAlunoController notificacaoAlunoController,
                    NotificacaoEventoController notificacaoEventoController) {
        this.professorView = professorView;
        this.professorController = professorController;
        this.salaController = salaController;
        this.alunoController = alunoController;
        this.aulaController = aulaController;
        this.receitaController = receitaController;
        this.despesaController = despesaController;
        this.relatorioController = relatorioController;
        this.notificacaoAlunoController = notificacaoAlunoController;
        this.notificacaoEventoController = notificacaoEventoController;
    }


    public void showMenu() {
        boolean running = true;
        while (running) {
            System.out.printf("\n\t\t------- Seja Bem-vindo ao App de Gestão EBD!------- \n%d - Criar Conta\n%d - Já possui uma conta? Fazer Login\n%d - Sair%n", 1, 2, 3);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após o nextInt()

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    System.out.println("Obrigado por usar o sistema EBD!");
                    running = false;
                    break;
                default:
                    System.out.print("Opção inválida, Tente um valor válido.");
                    break;
            }
        }
    }

    private void registerUser() {
        System.out.println("------------ Cadastro de Usuário ------------");
        String nome = professorView.getNomeProfessor();
        String cpf = professorView.getCpfProfessor();
        String senha = professorView.getSenhaProfessor();

        professorController.registerUser(nome, cpf, senha);
    }

    private void loginUser() {
        System.out.println("------------ Login de Usuário ------------");
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        System.out.print("Senha: ");
        String senha = scanner.nextLine().trim();

        boolean loginSuccess = ProfessorController.loginUser(nome, senha);

        if (loginSuccess) {
            System.out.println("Login bem-sucedido!");
            professorController.showProfessorOptions();
        } else {
            System.out.println("Usuário ou senha incorretos. Tente novamente.");
        }
    }
}
