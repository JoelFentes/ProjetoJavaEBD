package Main.Model;

public class NotificacaoAluno implements Comunicacao {
    private int id;
    private String data;
    private String mensagem;
    private int professorId;
    private String alunoNome;

    public NotificacaoAluno(String alunoNome) {
        this.alunoNome = alunoNome;
    }

    @Override
    public void setComunicacao(int id, String data, String mensagem, int professorId) {
        this.id = id;
        this.data = data;
        this.mensagem = mensagem;
        this.professorId = professorId;
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
    public int getProfessorId() {
        return professorId;
    }

    @Override
    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public String getAlunoNome() {
        return alunoNome;
    }

    public void setAlunoNome(String alunoNome) {
        this.alunoNome = alunoNome;
    }

    @Override
    public String toString() {
        return "NotificacaoAluno{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", mensagem='" + mensagem + '\'' +
                ", professorId=" + professorId +
                ", alunoNome='" + alunoNome + '\'' +
                '}';
    }
}
