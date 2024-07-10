package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Sala {
    private static final String ARQUIVO_BANCO_DE_DADOS = "C:\\Users\\UPE SURUBIM\\IdeaProjects\\ProjetoJavaEBD\\BD_SALAS.txt";

    private static int proximoId = lerUltimoID(); // Inicializa com o último ID lido do banco de dados

    private int id;
    private String descricao;
    private String professorResponsavel;
    private int idadeMinima;
    private int idadeMaxima;

    public Sala(String descricao, String professorResponsavel, int idadeMinima, int idadeMaxima) {
        this.id = proximoId++; // Usa o próximo ID disponível e depois incrementa
        this.descricao = descricao;
        this.professorResponsavel = professorResponsavel;
        this.idadeMinima = idadeMinima;
        this.idadeMaxima = idadeMaxima;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getProfessorResponsavel() {
        return professorResponsavel;
    }

    public int getIdadeMinima() {
        return idadeMinima;
    }

    public int getIdadeMaxima() {
        return idadeMaxima;
    }

    public void salvarEmArquivo() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_BANCO_DE_DADOS, true))) {
            writer.println(id + ";" + descricao + ";" + professorResponsavel + ";" + idadeMinima + ";" + idadeMaxima);
        } catch (IOException e) {
            System.out.println("Erro ao salvar a sala em arquivo.");
        }
    }

    private static int lerUltimoID() {
        int ultimoID = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_BANCO_DE_DADOS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                ultimoID = Integer.parseInt(dados[0]);
            }
        } catch (IOException | NumberFormatException e) {
            // Em caso de erro ao ler o arquivo ou converter o ID, retorna 0
            System.out.println("Erro ao ler o último ID do banco de dados das salas.");
        }

        return ultimoID + 1; // Retorna o próximo ID disponível
    }


}
