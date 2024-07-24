package Main.Model;

import java.util.Map;

public class Aula {
    private int id;
    private String descricao;
    private String data;
    private String professorResponsavel;
    private Sala sala;
    private float oferta;
    private Map<String, Boolean> presencas;

    private static int idCounter = 0; // Contador estático para gerar IDs únicos

    public Aula(String descricao, String data, String professorResponsavel, Sala sala , float oferta, Map<String, Boolean> presencas ) {
        this.id = ++idCounter; // Atribui um ID único a cada aula
        this.descricao = descricao;
        this.data = data;
        this.professorResponsavel = professorResponsavel;
        this.sala = sala;
        this.oferta = oferta;
        this.presencas = presencas;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getProfessorResponsavel() {
        return professorResponsavel;
    }

    public void setProfessorResponsavel(String professorResponsavel) {
        this.professorResponsavel = professorResponsavel;
    }

    public Map<String, Boolean> getPresencas() {
        return presencas;
    }

    public void setPresencas(Map<String, Boolean> presencas) {
        this.presencas = presencas;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public float getOferta() {
        return oferta;
    }

    public void setOferta(float oferta) {
        this.oferta = oferta;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Descrição: %s, Data: %s, Professor: %s, Sala: %s, Oferta: %.2f, Presenças: %s",
                id, descricao, data, professorResponsavel, sala.getDescricao(), oferta, presencas);
    }
}
