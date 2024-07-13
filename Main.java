package Main;

import Main.Controller.AlunoController;
import Main.Controller.ProfessorController;
import Main.Controller.SalaController;
import Main.Model.Aluno;
import Main.Model.Professor;
import Main.Model.Sala;
import Main.View.MainView;
import Main.View.ProfessorView;
import Main.View.AlunoView;
import Main.View.SalaView;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Inicializando as visualizações
        ProfessorView professorView = new ProfessorView();
        SalaView salaView = new SalaView();
        AlunoView alunoView = new AlunoView();

        // Inicializando listas de dados
        List<Professor> professores = new ArrayList<>();
        List<Sala> salas = new ArrayList<>();
        List<Aluno> alunos = new ArrayList<>();

        // Inicializando os controladores
        ProfessorController professorController = new ProfessorController(professores, professorView);
        AlunoController alunoController = new AlunoController(alunos, alunoView);
        SalaController salaController = new SalaController(salas, professorController, salaView);

        // Inicializando a visão principal com as dependências necessárias
        MainView mainView = new MainView(professorView, professorController, salaController);

        // Exibindo o menu principal
        mainView.showMenu();
    }
}
