package Main;

import java.io.*;
import java.util.*;

public class Aluno {
    private static int proximaMatricula = 1; 
    private int matricula;
    private int idProfessor;
    private String nome;
    private String cpf;
    private int idade;
    private String contato;
    private String salaAluno;


    public Aluno(int idProfessor, String nome, String cpf, String contato, int idade, String salaAluno) {
        this.matricula = proximaMatricula++;
        this.idProfessor = idProfessor;
        this.nome = nome;
        this.cpf = cpf;
        this.contato = contato;
        this.idade = idade;
        this.salaAluno = salaAluno;
    }

    public String getSalaAluno() {
        return salaAluno;
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

    public int getIdade() {
        return idade;
    }

    public String getContato() {
        return contato;
    }

    public void salvarEmArquivo() {
        String fileName = "C:\\Users\\joelf\\eclipse-workspace\\BD_ALUNOS.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.println(idProfessor + ";" + matricula + ";" + nome + ";" + cpf + ";" + idade + ";" + contato + ";" + salaAluno );
        } catch (IOException e) {
            System.out.println("Erro ao salvar o aluno em arquivo.");
        }
    }


}
