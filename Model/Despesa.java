package Main.Model;

public class Despesa extends FinanceiroModel {
    private String professorNome;

    public Despesa(String data, String descricao, float valor, String professorNome, String salaNome) {
        super(data, descricao, valor, salaNome);
        this.professorNome = professorNome;
    }

    public String getProfessorNome() {
        return professorNome;
    }

    @Override
    public String toString() {
        return String.format("Despesa - %s | Professor: %s",
                super.toString(), professorNome);
    }
}
