package Main.Controller;

import Main.Model.Aluno;
import Main.Model.Professor;
import Main.Model.Sala;
import Main.View.ProfessorView;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorController {
    private List<Professor> professores;
    private List<Sala> salas;
    private List<Aluno> alunos;
    private ProfessorView view;

    public ProfessorController(List<Professor> professores, List<Sala> salas, List<Aluno> alunos, ProfessorView view) {
        this.professores = professores;
        this.salas = salas;
        this.alunos = alunos;
        this.view = view;
    }

    public void cadastrarSala() {
        view.showSalaCadastro();

        String descricao = view.getDescricaoSala();
        List<String> nomesProfessores = carregarNomesProfessores();
        String professorResponsavel = view.getProfessorResponsavel(nomesProfessores);
        int idadeMinima = view.getIdadeMinima();
        int idadeMaxima = view.getIdadeMaxima(idadeMinima);

        Sala sala = new Sala(descricao, professorResponsavel, idadeMinima, idadeMaxima);
        salas.add(sala); // Adiciona a sala à lista de salas

        salvarSalasNoArquivo(); // Salva as salas no arquivo

        view.showSalaCadastroSucesso(sala);
    }

    public void cadastrarAluno() {
        view.showAlunoCadastro();

        String nome = view.getNomeAluno();
        String cpf = view.getCpfAluno();
        String contato = view.getContatoAluno();
        int idade = view.getIdadeAluno();
        String salaAluno = view.getSalaAluno();


        Aluno aluno = new Aluno(nome, cpf, contato, idade, salaAluno);
        alunos.add(aluno);

        salvarAlunosNoArquivo();
        view.showAlunoCadastroSucesso(aluno);
    }

    public boolean loginUser(String nome, String senha) {
        for (Professor professor : professores) {
            if (professor.getNome().equalsIgnoreCase(nome) && professor.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    public void registerUser(String nome, String cpf, String senha) {
        Professor professor = new Professor(professores.size() + 1, nome, cpf, senha);
        professores.add(professor);
        salvarProfessoresNoArquivo();
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private void salvarSalasNoArquivo() {
        String fileName = "C:\\Users\\joelf\\IdeaProjects\\ProjetoJavaEBD\\BD_SALAS.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Sala sala : salas) {
                String salaData = String.format("%s;%s;%d;%d", sala.getDescricao(), sala.getProfessorResponsavel(),
                        sala.getIdadeMinima(), sala.getIdadeMaxima());
                writer.write(salaData);
                writer.newLine();
            }
            System.out.println("Sala cadastrada com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados das salas.");
        }
    }

    private void salvarAlunosNoArquivo() {
        String fileName = "C:\\Users\\joelf\\IdeaProjects\\ProjetoJavaEBD\\BD_ALUNOS.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Aluno aluno : alunos) {
                String alunoData = String.format("%s;%s;%s;%d;%s", aluno.getNome(), aluno.getCpf(),
                        aluno.getContato(), aluno.getIdade(), aluno.getSalaAluno());
                writer.write(alunoData);
                writer.newLine();
            }
            System.out.println("Aluno cadastrado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados dos alunos.");
        }
    }

    private void salvarProfessoresNoArquivo() {
        String fileName = "C:\\Users\\joelf\\IdeaProjects\\ProjetoJavaEBD\\BD_PROFESSORES.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Professor professor : professores) {
                String professorData = String.format("%d;%s;%s;%s", professor.getId(), professor.getNome(), professor.getCpf(),
                        professor.getSenha());
                writer.write(professorData);
                writer.newLine();
            }
            System.out.println("Professor cadastrado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados dos professores.");
        }
    }

    private List<String> carregarNomesProfessores() {
        List<String> nomesProfessores = new ArrayList<>();
        for (Professor professor : professores) {
            nomesProfessores.add(professor.getNome());
        }
        return nomesProfessores;
    }
}
