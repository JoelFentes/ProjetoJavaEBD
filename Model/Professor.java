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

    public String getNomeProfessor() {
        return nome;
    }

    public String getCpfProfessor() {
        return cpf;
    }

    public String getSenhaProfessor() {
        return senha;
    }


}

