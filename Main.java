package Main;

import Main.Controller.AlunoController;
import Main.Controller.AulaController;
import Main.Controller.ProfessorController;
import Main.Controller.SalaController;
import Main.Model.Aluno;
import Main.Model.Aula;
import Main.Model.Professor;
import Main.Model.Sala;
import Main.View.MainView;
import Main.View.ProfessorView;
import Main.View.AlunoView;
import Main.View.AulaView;
import Main.View.SalaView;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Inicializando as visualizações
        ProfessorView professorView = new ProfessorView();
        SalaView salaView = new SalaView();
        AlunoView alunoView = new AlunoView();
        AulaView aulaView = new AulaView();

        // Inicializando listas de dados
        List<Professor> professores = new ArrayList<>();
        List<Sala> salas = new ArrayList<>();
        List<Aluno> alunos = new ArrayList<>();
        List<Aula> aulas = new ArrayList<>();

        // Inicializando os controladores
        ProfessorController professorController = new ProfessorController(professores, professorView);
        SalaController salaController = new SalaController(salas, professorController, salaView);
        AlunoController alunoController = new AlunoController(alunos, salaController, alunoView);
        AulaController aulaController = new AulaController(aulas, salaController, aulaView);

        // Inicializando a visão principal com as dependências necessárias
        MainView mainView = new MainView(professorView, professorController, salaController);

        // Exibindo o menu principal
        mainView.showMenu();
    }
}
