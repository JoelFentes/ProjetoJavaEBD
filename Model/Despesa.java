package Main.Model;

public class Despesa {
    private int id;
    private String data;
    private String descricao;
    private float valor;
    private String professorNome;
    private String salaNome;

    public Despesa(String data, String descricao, float valor, String professorNome, String salaNome) {
        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.professorNome = professorNome;
        this.salaNome = salaNome;
    }

    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public float getValor() {
        return valor;
    }

    public String getProfessorNome() {
        return professorNome;
    }

    public String getSalaNome() {
        return salaNome;
    }

    @Override
    public String toString() {
        return String.format("Data: %s | Descrição: %s | Valor: %.2f | Professor: %s | Sala: %s",
                data, descricao, valor, professorNome, salaNome);
    }
}
