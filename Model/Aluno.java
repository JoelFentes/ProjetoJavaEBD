package Main.Model;

public class Aluno extends Pessoa{
    private int idade;
    private Sala sala;
    private String contato;
    private String endereco;

    // Construtor
    public Aluno(String nome, String cpf, int idade, Sala sala, String contato, String endereco) {
        super(nome, cpf);
        this.idade = idade;
        this.sala = sala;
        this.contato = contato;
        this.endereco = endereco;
    }

    // Getters e Setters

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }


    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }


    @Override
    public String toString() {
       return String.format("Nome: %s, CPF: %s, Idade: %d, Sala: %s, Contato: %s, Endereço: %s%n",
                getNome(), getCpf(), idade, sala.getDescricao(), contato, endereco);
    }

}
