package Main;

import java.io.*;
import java.util.*;

public class LoginUsuario {
    private static final String bdUser = "C:\\Users\\UPE SURUBIM\\IdeaProjects\\ProjetoJavaEBD\\BD_USER.txt";
    private static final ArrayList<String> loggedInUser = new ArrayList<>();
    private static Professor loggedInProfessor = null;

    public static void loginUser(Scanner scanner) {
        System.out.print("Nome ou CPF: ");
        String user = scanner.nextLine();
        System.out.print("Senha: ");
        String password = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(bdUser))) {
            String line;
            boolean loginSuccess = false;

            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(";");
                if ((userData[1].equals(user) || userData[2].equals(user)) && userData[3].equals(password)) {
                    String welcomeMessage = String.format("\nLogin bem-sucedido! Bem-vindo Professor %s!", userData[2]);
                    System.out.println(welcomeMessage);

                    loggedInUser.clear();
                    loggedInUser.addAll(Arrays.asList(userData));

                    int id = Integer.parseInt(userData[0]);
                    String nome = userData[1];
                    String cpf = userData[2];
                    String senha = userData[3];
                    loggedInProfessor = new Professor(id, nome, cpf, senha);

                    loginSuccess = true;
                    break;
                }
            }

            if (!loginSuccess) {
                System.out.println("\nUsuário ou senha incorretos.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler os dados dos usuários.");
        }
    }

    public static ArrayList<String> getLoggedInUser() {
        return loggedInUser;
    }

    public static Professor getLoggedInProfessor() {
        return loggedInProfessor;
    }

    
    static boolean flag = true;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Professor professor = getLoggedInProfessor();
        if (professor != null) {

           
            while (flag) {
                System.out.println(String.format("\n------------ Escolha uma opção:  ------------\n%d - Cadastrar Sala\n%d - Cadastrar Aluno\n%d - Cadastrar Aula\n%d - Sair",
                        1, 2, 3, 0));

                int opcao = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcao) {
                    case 1:
                        professor.cadastrarSala(scanner);
                        break;
                    case 2:
                        professor.cadastrarAluno(scanner);
                        break;
                    case 3:
                    	System.out.println("Produção...");
                        break;
                    case 0:
                        System.out.println("Obrigado por usar o sistema EBD!");
                        flag = false;
                        return;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }

             }
        } else {
            System.out.println("Nenhum usuário logado.");

        }
        
        scanner.close();
    }
}
