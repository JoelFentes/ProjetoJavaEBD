package Main.Controller;

import Main.Model.NotificacaoAluno;
import Main.View.NotificacaoAlunoView;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class NotificacaoAlunoController {
    private static final String fileName = "C:\\Users\\joser\\IdeaProjects\\ProjetoJavaEBD\\BD_NOTIFICACOES.txt";
    private static NotificacaoAlunoView view;
    private static NotificacaoAluno model;

    public NotificacaoAlunoController(NotificacaoAlunoView view, NotificacaoAluno model) {
        this.view = view;
        this.model = model;
    }

    public static void enviarNotificacao() {
        String alunoNome = view.getAlunoNome();
        String data = view.getDataNotificacao();
        String mensagem = view.getMensagemNotificacao();
        String nomeProfessor = view.getNomeProfessor();

        model.setComunicacao(0, data, mensagem, nomeProfessor);
        model.setAlunoNome(alunoNome);
        view.showNotificacaoAlunoEnviada(model);
        salvarNotificacaoAluno();
    }


    private static void salvarNotificacaoAluno() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String notificacaoData = String.format("Notificação para Aluno: %s\nData: %s\nMensagem: %s\nProfessor: %s\n\n",
                    model.getAlunoNome(), model.getData(), model.getMensagem(), model.getProfessorNome());
            writer.write(notificacaoData);
            System.out.println("Notificação de aluno salva com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar a notificação de aluno.");
        }
    }
}
