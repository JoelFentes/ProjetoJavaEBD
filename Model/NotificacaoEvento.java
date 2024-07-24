package Main.Model;

public class NotificacaoEvento implements Comunicacao {
    private int id;
    private String data;
    private String mensagem;
    private String nomeProfessor;
    private String eventoDescricao;

    @Override
    public void setComunicacao(int id, String data, String mensagem, String professorId) {
        this.id = id;
        this.data = data;
        this.mensagem = mensagem;
        this.nomeProfessor = professorId;
    }

    @Override
    public void setComunicacao(String data, String mensagem) {
        this.data = data;
        this.mensagem = mensagem;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getData() {
        return data;
    }

    @Override
    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String getMensagem() {
        return mensagem;
    }

    @Override
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String getProfessorNome() {
        return nomeProfessor;
    }

    @Override
    public void setProfessorNome(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public void setEventoDescricao(String eventoDescricao) {
        this.eventoDescricao = eventoDescricao;
    }

    public String getEventoDescricao() {
        return eventoDescricao;
    }

    @Override
    public String toString() {
        return String.format("Notificação de Evento:\nDescrição do Evento: %s\nData: %s\nMensagem: %s\nNome do Professor: %s",
                eventoDescricao, data, mensagem, nomeProfessor);
    }
}
