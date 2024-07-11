package Main.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Sala {
    private static final String bdSalas = "C:\\Users\\joelf\\IdeaProjects\\ProjetoJavaEBD\\BD_SALAS.txt";
    private static int proximoId = lerUltimoID();
    private int id;
    private String descricao;
    private String professorResponsavel;
    private int idadeMinima;
    private int idadeMaxima;

    public Sala(String descricao, String professorResponsavel, int idadeMinima, int idadeMaxima) {
        this.id = proximoId++;
        this.descricao = descricao;
        this.professorResponsavel = professorResponsavel;
        this.idadeMinima = idadeMinima;
        this.idadeMaxima = idadeMaxima;
    }

    // Getters

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
        try (PrintWriter writer = new PrintWriter(new FileWriter(bdSalas, true))) {
            writer.println(id + ";" + descricao + ";" + professorResponsavel + ";" + idadeMinima + ";" + idadeMaxima);
        } catch (IOException e) {
            System.out.println("Erro ao salvar a sala em arquivo.");
        }
    }

    private static int lerUltimoID() {
        int ultimoID = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(bdSalas))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] data = linha.split(";");
                ultimoID = Integer.parseInt(data[0]);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao ler o último ID do banco de dados das salas.");
        }
        return ultimoID + 1;
    }
}