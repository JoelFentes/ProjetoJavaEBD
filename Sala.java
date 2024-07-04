package Main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Sala {
    private int id;
    private String descricao;
    private String professorResponsavel;

    public Sala(int id, String descricao, String professorResponsavel) {
        this.id = id;
        this.descricao = descricao;
        this.professorResponsavel = professorResponsavel;
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

    public void salvarEmArquivo() {
        String fileName = "C:\\Users\\joelf\\eclipse-workspace\\BD_SALAS.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.println(id + ";" + descricao + ";" + professorResponsavel);
        } catch (IOException e) {
            System.out.println("Erro ao salvar a sala em arquivo.");
        }
    }
}
