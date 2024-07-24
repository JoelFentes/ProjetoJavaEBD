package Main.Controller;

import Main.Model.NotificacaoEvento;
import Main.View.NotificacaoEventoView;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class NotificacaoEventoController {
    private static NotificacaoEventoView view;
    private static NotificacaoEvento model;

    public NotificacaoEventoController(NotificacaoEventoView view, NotificacaoEvento model) {
        this.view = view;
        this.model = model;
    }

    public static void enviarNotificacao() {
        String eventoDescricao = view.getEventoDescricao();
        String data = view.getDataNotificacao();
        String mensagem = view.getMensagemNotificacao();
        String nomeProfessor = view.getNomeProfessor();

        model.setComunicacao(0, data, mensagem, nomeProfessor);
        model.setEventoDescricao(eventoDescricao);
        view.showNotificacaoEventoEnviada(model);
        salvarNotificacaoEvento();
    }

    private static void salvarNotificacaoEvento() {
        String fileName = "C:\\Users\\joelf\\IdeaProjects\\ProjetoJavaEBD\\BD_NOTIFICACOES.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String notificacaoData = String.format("Notificação sobre o Evento: %s\nData: %s\nMensagem: %s\nProfessor: %s\n\n",
                    model.getEventoDescricao(), model.getData(), model.getMensagem(), model.getProfessorNome());
            writer.write(notificacaoData);
            System.out.println("Notificação de evento salva com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar a notificação de evento.");
        }
    }
}
