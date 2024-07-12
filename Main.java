package Main;

import Main.Controller.ProfessorController;
import Main.Model.Aluno;
import Main.Model.Professor;
import Main.Model.Sala;
import Main.View.MainView;
import Main.View.ProfessorView;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProfessorView professorView = new ProfessorView();
        List<Professor> professores = new ArrayList<>();
        List<Sala> salas = new ArrayList<>();
        List<Aluno> alunos = new ArrayList<>();

        ProfessorController professorController = new ProfessorController(professores, salas, alunos, professorView);
        MainView mainView = new MainView(professorController);

        mainView.showMenu();
    }
}
