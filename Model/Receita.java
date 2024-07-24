package Main.Model;

public class Receita {
    private int id;
    private String data;
    private String descricao;
    private float valor;
    private String salaNome; // Alterado para referenciar o nome da sala

    public Receita(String data, String descricao, float valor, String salaNome) {
        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
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

    public String getSalaNome() {
        return salaNome;
    }

    @Override
    public String toString() {
        return String.format("Data: %s | Descrição: %s | Valor: %.2f | Sala: %s",
                data, descricao, valor, salaNome);
    }
}
