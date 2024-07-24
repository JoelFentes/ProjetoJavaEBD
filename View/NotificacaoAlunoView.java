package Main.View;

import Main.Model.NotificacaoAluno;

import java.util.Scanner;

public class NotificacaoAlunoView {
    private static Scanner scanner = new Scanner(System.in);

    public static void showNotificacaoAlunoEnviada(NotificacaoAluno notificacao) {
        System.out.println("\n------------ Notificação para Aluno Enviada ------------\n");
        System.out.println(notificacao);
    }

    public String getAlunoNome() {
        System.out.print("Nome do Aluno: ");
        return scanner.nextLine().trim();
    }

    public String getDataNotificacao() {
        System.out.print("Data da Notificação (dd/MM/yyyy): ");
        return scanner.nextLine().trim();
    }

    public String getMensagemNotificacao() {
        System.out.print("Mensagem da Notificação: ");
        return scanner.nextLine().trim();
    }

    public int getProfessorId() {
        System.out.print("ID do Professor: ");
        return scanner.nextInt();
    }

    public void closeScanner() {
        scanner.close();
    }
}
