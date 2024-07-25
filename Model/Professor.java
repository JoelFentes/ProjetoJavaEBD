package Main.Model;

import java.io.*;

public class Professor extends Pessoa{
    private static int proximoId = 1;
    private int id;
    private String senha;

    public Professor(int i, String nome, String cpf, String senha) {
        super(nome, cpf);
        this.id = proximoId++;
        this.senha = senha;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}

