package Main.Model;

public class Aluno {
    private String cpf;
    private String nome;
    private int idade;
    private Sala sala;
    private String contato;
    private String endereco;

    // Construtor
    public Aluno(String cpf, String nome, int idade, Sala sala, String contato, String endereco) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
        this.sala = sala;
        this.contato = contato;
        this.endereco = endereco;
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

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
        return String.format("CPF: %s, Nome: %s, Idade: %d, Sala: %s, Contato: %s, Endereço: %s",
                cpf, nome, idade, sala.getDescricao(), contato, endereco);
    }
}
