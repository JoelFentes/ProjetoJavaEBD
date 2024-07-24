package Main.View;

import Main.Model.NotificacaoEvento;

import java.util.Scanner;

public class NotificacaoEventoView {
    private static Scanner scanner = new Scanner(System.in);

    public static void showNotificacaoEventoEnviada(NotificacaoEvento notificacao) {
        System.out.println("\n------------ Notificação de Evento Enviada ------------\n");
        System.out.println(notificacao);
    }

    public String getEventoDescricao() {
        System.out.print("Descrição do Evento: ");
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

    public String getNomeProfessor() {
        System.out.print("Nome do Professor: ");
        return scanner.nextLine().trim();
    }

    public void closeScanner() {
        scanner.close();
    }
}
