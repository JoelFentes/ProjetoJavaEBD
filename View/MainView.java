package Main.View;

import Main.Controller.ProfessorController;
import Main.Controller.SalaController;
import Main.View.ProfessorView;
import Main.View.SalaView;

import java.util.Scanner;

public class MainView {
    private ProfessorController professorController;
    private ProfessorView professorView;
    private Scanner scanner;

    public MainView(ProfessorView professorView, ProfessorController professorController, SalaController salaController) {
        this.professorView = professorView;
        this.professorController = professorController;
        this.scanner = new Scanner(System.in);
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

        boolean loginSuccess = professorController.loginUser(nome, senha);

        if (loginSuccess) {
            System.out.println("Login bem-sucedido!");
            professorController.showProfessorOptions();
        } else {
            System.out.println("Usuário ou senha incorretos. Tente novamente.");
        }
    }
}
