package Main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;


public class RegistrarUsuario {
    private static final String bdUser = "C:\\Users\\joelf\\eclipse-workspace\\BD_USER.txt";
    private static int id = 0; //

    
    static {
        
        try (BufferedReader reader = new BufferedReader(new FileReader(bdUser))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(";");
                id = Integer.parseInt(userData[0]);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de usuários.");
        }
    }
    
    
    public static void registerUser(Scanner scanner) {

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        
        String cpf;
        boolean cpfValido = false;
        do {
            System.out.print("CPF: ");
            cpf = scanner.nextLine();
            if (cpf.matches("\\d{11}")) {
                cpfValido = true;
            } else {
                System.out.println("CPF inválido. Digite um CPF com exatamente 11 dígitos numéricos.");
            }
        } while (!cpfValido);

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(bdUser, true))) {
            id++;
            String userData = String.format("%d;%s;%s;%s", id, cpf, nome, senha);
            writer.write(userData);
            writer.newLine();
            System.out.println(String.format("\nUsuário cadastrado com sucesso:\nID: %d\nNome: %s\nCPF: %s", id, nome, cpf));
        
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados do usuário.");
        }
        
        
        System.out.print("\nDeseja logar na sua conta?\n- Sim | Não: ");
        String loginQuestion = scanner.nextLine();
        
        if (loginQuestion.equalsIgnoreCase("sim")) {
            LoginUsuario.loginUser(scanner);
        }
        else {
        	SistemaUsuario.menuOptions();
           
        }
    
        
    }
}
