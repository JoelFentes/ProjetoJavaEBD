package Main.Controller;

import Main.Model.Aluno;
import Main.View.AlunoView;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class AlunoController {
    private List<Aluno> alunos;
    private AlunoView alunoview;

    public AlunoController(List<Aluno> alunos, AlunoView alunoview) {
        this.alunos = alunos;
        this.alunoview = alunoview;
    }

    public void cadastrarAluno() {
        alunoview.showAlunoCadastro();

        String nome = alunoview.getNomeAluno();
        String cpf = alunoview.getCpfAluno();
        String contato = alunoview.getContatoAluno();
        int idade = alunoview.getIdadeAluno();
        String salaAluno = alunoview.getSalaAluno();

        Aluno aluno = new Aluno(nome, cpf, contato, idade, salaAluno);
        alunos.add(aluno);

        salvarAlunosNoArquivo();
        alunoview.showAlunoCadastroSucesso(aluno);
    }

    private void salvarAlunosNoArquivo() {
        String fileName = "C:\\Users\\hfent\\IdeaProjects\\ProjetoJavaEBD\\BD_ALUNOS.txt";
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
}
