package Main.Model;

import java.io.*;

public class Professor {
    private static int proximoId = 1;
    private int id;
    private String nome;
    private String cpf;
    private String senha;

    public Professor(int i, String nome, String cpf, String senha) {
        this.id = proximoId++;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

 /*   public static List<String> carregarNomesProfessores() {
        List<String> nomesProfessores = new ArrayList<>();
        String fileName = "C:\\Users\\joelf\\IdeaProjects\\ProjetoJavaEBD\\BD_USER.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                String nome = data[2];
                nomesProfessores.add(nome);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar os nomes dos professores do arquivo.");
        }

        return nomesProfessores;
    }*/

    public void salvarEmArquivo() {
        String fileName = "C:\\Users\\joelf\\IdeaProjects\\ProjetoJavaEBD\\BD_USER.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(String.format("%d;%s;%s;%s\n", id, nome, cpf, senha));
        } catch (IOException e) {
            System.out.println("Erro ao salvar professor em arquivo: " + e.getMessage());
        }
    }
}
