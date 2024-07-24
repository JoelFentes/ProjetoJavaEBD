package Main.Controller;

import Main.Model.Despesa;
import Main.Model.Receita;
import Main.Model.RelatorioFinanceiro;
import Main.View.RelatorioFinanceiroView;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RelatorioFinanceiroController {
    private static RelatorioFinanceiroView relatorioFinanceiroView;

    public RelatorioFinanceiroController(RelatorioFinanceiroView relatorioFinanceiroView) {
        this.relatorioFinanceiroView = relatorioFinanceiroView;
    }

    public static void gerarRelatorio(List<Receita> receitas, List<Despesa> despesas) {
        relatorioFinanceiroView.showGerarRelatorio();

        String dataInicio = relatorioFinanceiroView.getDataInicio();
        String dataFim = relatorioFinanceiroView.getDataFim();

        float totalReceitas = 0;
        float totalDespesas = 0;

        for (Receita receita : receitas) {
            if (isBetweenDates(receita.getData(), dataInicio, dataFim)) {
                totalReceitas += receita.getValor();
            }
        }

        for (Despesa despesa : despesas) {
            if (isBetweenDates(despesa.getData(), dataInicio, dataFim)) {
                totalDespesas += despesa.getValor();
            }
        }

        float saldo = totalReceitas - totalDespesas;

        RelatorioFinanceiro relatorio = new RelatorioFinanceiro(dataInicio, dataFim, totalReceitas, totalDespesas, saldo);
        relatorioFinanceiroView.showRelatorio(relatorio);

        saveRelatorio(relatorio);
    }

    private static boolean isBetweenDates(String data, String dataInicio, String dataFim) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataAComparar = LocalDate.parse(data, formatter);
        LocalDate inicio = LocalDate.parse(dataInicio, formatter);
        LocalDate fim = LocalDate.parse(dataFim, formatter);

        return (dataAComparar.isEqual(inicio) || dataAComparar.isAfter(inicio)) &&
                (dataAComparar.isEqual(fim) || dataAComparar.isBefore(fim));
    }

    public static void saveRelatorio(RelatorioFinanceiro relatorio) {
        String fileName = "C:\\Users\\joelf\\IdeaProjects\\ProjetoJavaEBD\\BD_RELATORIO_FINANCEIRO.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String relatorioData = String.format("%s;%s;%.2f;%.2f;%.2f",
                    relatorio.getDataInicio(), relatorio.getDataFim(),
                    relatorio.getTotalReceitas(), relatorio.getTotalDespesas(), relatorio.getSaldo());
            writer.write(relatorioData);
            writer.newLine();
            System.out.println("\nRelatório salvo com sucesso\n");
        } catch (IOException e) {
            System.err.println("Erro ao salvar relatório: " + e.getMessage());
        }
    }
}
