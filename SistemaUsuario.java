/*
package Main;

import java.util.Scanner;


public class SistemaUsuario {

	static boolean flag = true;

    public static void menuOptions() {
        Scanner scanner = new Scanner(System.in);

        while (flag) {
            System.out.printf("\n\t\t------- Seja Bem-vindo ao App de Gestão EBD!------- \n%d - Criar Conta\n%d - Já possui uma conta? Fazer Login\n%d - Sair%n", 1, 2, 3);

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:

                    System.out.print("------------ Cadastro de Usuário ------------\n");
                    RegistrarUsuario.registerUser(scanner);
                    break;
                case 2:

                    System.out.print("------------ Login de Usuário ------------\n");

                    LoginUsuario.loginUser(scanner);
                    LoginUsuario.main(null);
                    break;
                case 3:

                    System.out.println("Obrigado por usar o sistema EBD!");
                    flag = false;
                    return;
                default:
                    System.out.print("Opção inválida, Tente um valor válido.");
                    break;

            }
        }
    }
}
*/
