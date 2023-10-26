package control;

import dao.Conexao;
import model.Aluno;
import model.Curso;

import java.sql.SQLException;
import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {

        Conexao conexao = new Conexao();
        Scanner scannerMenu = new Scanner(System.in);
        Scanner scannerAtrib = new Scanner(System.in);

        int menu = 0;
        while(menu != 5) {
            System.out.println("Cadastrar Curso = 1");
            System.out.println("Cadastrar Aluno = 2");
            System.out.println("Consultar curso de um dado aluno = 3");
            System.out.println("Listar alunos de um curso = 4");
            System.out.println("Sair = 5");
            menu = scannerMenu.nextInt();

            switch (menu){
                case 1:
                    try {
                        System.out.println("Insira o nome do Curso: ");
                        String nomeCurso = scannerAtrib.nextLine();
                        System.out.println("Insira o código do Curso: ");
                        String codCurso = scannerAtrib.nextLine();
                        System.out.println("Insira a carga horária do Curso: ");
                        int chCurso = scannerAtrib.nextInt();
                        conexao.cadastrarCurso(Conexao.connect(), codCurso, nomeCurso, chCurso);
                    }catch (Exception cadastroCurso) {
                        System.out.println("Erro no cadastro do Curso: " + cadastroCurso);
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Insira o nome do Aluno: ");
                        String nomeAluno = scannerAtrib.nextLine();
                        System.out.println("Insira a matrícula do Aluno: ");
                        String matrAluno = scannerAtrib.nextLine();
                        System.out.println("Insira o codigo do curso do Aluno: ");
                        String cursoAluno = scannerAtrib.nextLine();
                        Curso cursoP = conexao.retornaCurso(Conexao.connect(), cursoAluno);
                        System.out.println(cursoP.getCodigo());
                        conexao.cadastrarAluno(Conexao.connect(), matrAluno, nomeAluno, cursoP);
                    }catch (Exception cadastroAluno) {
                        System.out.println("Erro no cadastro do Aluno: " + cadastroAluno);
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Digite a matrícula do Aluno: ");
                        String matrAluno = scannerAtrib.nextLine();
                        Aluno alunoP = conexao.retornaAluno(Conexao.connect(), matrAluno);
                        conexao.consultarCursoDoAluno(Conexao.connect(), alunoP);
                    }catch (Exception consultaCurso) {
                        System.out.println("Erro na busca do Aluno: " + consultaCurso);
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Digite o código do Curso: ");
                        String codCurso = scannerAtrib.nextLine();
                        Curso cursoP = conexao.retornaCurso(Conexao.connect(), codCurso);
                        conexao.listarAlunosDoCurso(Conexao.connect(), cursoP);
                    }catch (Exception listarAlunos) {
                        System.out.println("Erro na listagem dos Aluno: " + listarAlunos);
                    }
                    break;
                case 5:
                    break;
            }
        }
    }
}
