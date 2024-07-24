package Main.Model;

public class RelatorioFinanceiro {
    private String dataInicio;
    private String dataFim;
    private float totalReceitas;
    private float totalDespesas;
    private float saldo;

    public RelatorioFinanceiro(String dataInicio, String dataFim, float totalReceitas, float totalDespesas, float saldo) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.totalReceitas = totalReceitas;
        this.totalDespesas = totalDespesas;
        this.saldo = saldo;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public float getTotalReceitas() {
        return totalReceitas;
    }

    public void setTotalReceitas(float totalReceitas) {
        this.totalReceitas = totalReceitas;
    }

    public float getTotalDespesas() {
        return totalDespesas;
    }

    public void setTotalDespesas(float totalDespesas) {
        this.totalDespesas = totalDespesas;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Relatório Financeiro: \n" +
                "Período: " + dataInicio + " a " + dataFim + "\n" +
                "Total de Receitas: R$ " + totalReceitas + "\n" +
                "Total de Despesas: R$ " + totalDespesas + "\n" +
                "Saldo: R$ " + saldo;
    }
}
