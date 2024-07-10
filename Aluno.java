package Main;

import java.io.*;
import java.util.*;

public class Aluno {
    private int teste;
    private static int proximaMatricula = 1; 
    private int matricula;
    private int idProfessor;
    private String nome;
    private String cpf;

    public Aluno(int idProfessor, String nome, String cpf) {
        this.matricula = proximaMatricula++;
        this.idProfessor = idProfessor;
        this.nome = nome;
        this.cpf = cpf;
    }

    public int getMatricula() {
        return matricula;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

   
    public void salvarEmArquivo() {
        String fileName = "C:\\Users\\joelf\\eclipse-workspace\\BD_ALUNOS.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.println(idProfessor + ";" + matricula + ";" + nome + ";" + cpf);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o aluno em arquivo.");
        }
    }


}
