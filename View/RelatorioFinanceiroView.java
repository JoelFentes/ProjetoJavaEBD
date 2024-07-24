package Main.View;

import Main.Model.RelatorioFinanceiro;

import java.util.Scanner;

public class RelatorioFinanceiroView {
    private Scanner scanner = new Scanner(System.in);

    public void showGerarRelatorio() {
        System.out.println("\n------------ Gerar Relatório Financeiro ------------");
    }

    public String getDataInicio() {
        System.out.print("Data de Início (dd/MM/yyyy): ");
        return scanner.nextLine().trim();
    }

    public String getDataFim() {
        System.out.print("Data de Fim (dd/MM/yyyy): ");
        return scanner.nextLine().trim();
    }

    public void showRelatorio(RelatorioFinanceiro relatorio) {
        System.out.println("\n------------ Relatório Financeiro ------------");
        System.out.printf("Período: %s a %s%n", relatorio.getDataInicio(), relatorio.getDataFim());
        System.out.printf("Total de Receitas: R$ %.2f%n", relatorio.getTotalReceitas());
        System.out.printf("Total de Despesas: R$ %.2f%n", relatorio.getTotalDespesas());
        System.out.printf("Saldo: R$ %.2f%n", relatorio.getSaldo());
    }
}
