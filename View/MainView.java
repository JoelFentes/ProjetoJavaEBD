package Main.View;

import Main.Controller.ProfessorController;

import java.util.Scanner;

public class MainView {
    private Scanner scanner;
    private ProfessorController professorController;

    public MainView(ProfessorController professorController) {
        this.professorController = professorController;
        scanner = new Scanner(System.in);
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
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        professorController.registerUser(nome, cpf, senha);
    }

    private void loginUser() {
        System.out.println("------------ Login de Usuário ------------");
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        boolean loginSuccess = professorController.loginUser(cpf, senha);

        if (loginSuccess) {
            System.out.println("Login bem-sucedido!");
            showProfessorOptions();
        } else {
            System.out.println("Usuário ou senha incorretos. Tente novamente.");
        }
    }

    private void showProfessorOptions() {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n------------ Escolha uma opção ------------");
            System.out.println("1 - Cadastrar Sala");
            System.out.println("2 - Cadastrar Aluno");
            System.out.println("3 - Cadastrar Aula");
            System.out.println("0 - Sair");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após o nextInt()

            switch (choice) {
                case 1:
                    professorController.cadastrarSala();
                    break;
                case 2:
                    professorController.cadastrarAluno();
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
}
