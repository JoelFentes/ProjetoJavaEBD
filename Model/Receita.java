package Main.Model;

public class Receita extends FinanceiroModel {
    public Receita(String data, String descricao, float valor, String salaNome) {
        super(data, descricao, valor, salaNome);
    }

    @Override
    public String toString() {
        return String.format("Receita - %s", super.toString());
    }
}
