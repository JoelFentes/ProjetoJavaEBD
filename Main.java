package Main;

import Main.Controller.*;
import Main.Model.Aluno;
import Main.Model.Aula;
import Main.Model.Professor;
import Main.Model.Sala;
import Main.Model.Receita; // Adicionando importação
import Main.Model.Despesa; // Adicionando importação
import Main.Model.RelatorioFinanceiro; // Adicionando importação
import Main.View.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Inicializando as visualizações
        ProfessorView professorView = new ProfessorView();
        SalaView salaView = new SalaView();
        AlunoView alunoView = new AlunoView();
        AulaView aulaView = new AulaView();
        ReceitaView receitaView = new ReceitaView();
        DespesaView despesaView = new DespesaView();
        RelatorioFinanceiroView relatorioView = new RelatorioFinanceiroView(); // Inicializando a visualização de relatório

        // Inicializando listas de dados
        List<Professor> professores = new ArrayList<>();
        List<Sala> salas = new ArrayList<>();
        List<Aluno> alunos = new ArrayList<>();
        List<Aula> aulas = new ArrayList<>();
        List<Receita> receitas = new ArrayList<>(); // Inicializando lista de receitas
        List<Despesa> despesas = new ArrayList<>(); // Inicializando lista de despesas

        // Inicializando os controladores
        ProfessorController professorController = new ProfessorController(professores, professorView);
        SalaController salaController = new SalaController(salas, professorController, salaView);
        AlunoController alunoController = new AlunoController(alunos, salaController, alunoView);
        AulaController aulaController = new AulaController(aulas, salaController, aulaView);

        RelatorioFinanceiroController relatorioController = new RelatorioFinanceiroController(relatorioView); // Inicializando o controlador de relatórios

        // Inicializando o controlador de Receitas e Despesas
        ReceitaController receitaController = new ReceitaController(receitas, receitaView);
        DespesaController despesaController = new DespesaController(despesas, despesaView);

        // Inicializando a visão principal com as dependências necessárias
        MainView mainView = new MainView(professorView, professorController, salaController, alunoController, aulaController, receitaController, despesaController, relatorioController);

        // Exibindo o menu principal
        mainView.showMenu();
    }
}
