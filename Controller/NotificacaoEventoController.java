package Main.Controller;

import Main.Model.NotificacaoEvento;
import Main.View.NotificacaoEventoView;

public class NotificacaoEventoController {
    private NotificacaoEventoView view;
    private NotificacaoEvento model;

    public NotificacaoEventoController(NotificacaoEventoView view, NotificacaoEvento model) {
        this.view = view;
        this.model = model;
    }

    public void enviarNotificacao() {
        String eventoDescricao = view.getEventoDescricao();
        String data = view.getDataNotificacao();
        String mensagem = view.getMensagemNotificacao();
        int professorId = view.getProfessorId();

        model.setComunicacao(0, data, mensagem, professorId);
        model.setEventoDescricao(eventoDescricao);
        view.showNotificacaoEventoEnviada(model);
    }
}
