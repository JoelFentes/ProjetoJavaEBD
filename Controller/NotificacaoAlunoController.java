package Main.Controller;

import Main.Model.NotificacaoAluno;
import Main.View.NotificacaoAlunoView;

public class NotificacaoAlunoController {
    private NotificacaoAlunoView view;
    private NotificacaoAluno model;

    public NotificacaoAlunoController(NotificacaoAlunoView view, NotificacaoAluno model) {
        this.view = view;
        this.model = model;
    }

    public void enviarNotificacao() {
        String alunoNome = view.getAlunoNome();
        String data = view.getDataNotificacao();
        String mensagem = view.getMensagemNotificacao();
        int professorId = view.getProfessorId();

        model.setComunicacao(0, data, mensagem, professorId);
        model.setAlunoNome(alunoNome);
        view.showNotificacaoAlunoEnviada(model);
    }
}
