package dao;

import model.Aluno;
import model.Curso;

import java.sql.*;

public class Conexao {
    static String url = "jdbc:postgresql://localhost:5432/escola";
    static String user = "postgres";
    static String password = "postgres";

    public static Connection connect() throws SQLException{
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("Conexão estabelecida!");

        return connection;
    }

    public void cadastrarAluno(Connection connection, String matricula, String nome, Curso curso) throws Exception{
        String insertSql = "INSERT INTO aluno (matricula, nome_aluno, cod_curso) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
        preparedStatement.setString(1, matricula);
        preparedStatement.setString(2, nome);
        preparedStatement.setString(3, curso.getCodigo());
        preparedStatement.executeUpdate();
        preparedStatement.close();

        connection.close();
    }

    public void cadastrarCurso(Connection connection, String codigo, String nome, int cargaHoraria) throws Exception{
        String insertSql = "INSERT INTO curso (cod_curso, nome_curso, carga_horaria) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
        preparedStatement.setString(1, codigo);
        preparedStatement.setString(2, nome);
        preparedStatement.setInt(3, cargaHoraria);
        preparedStatement.executeUpdate();
        preparedStatement.close();

        connection.close();
    }

    public void consultarCursoDoAluno(Connection connection, Aluno aluno) throws Exception{
        String selectSql = "SELECT a.matricula, a.nome_aluno, c.nome_curso FROM aluno a, curso c WHERE a.matricula = ? AND a.cod_curso = c.cod_curso";
        PreparedStatement pstmt = connection.prepareStatement(selectSql);
        pstmt.setString(1, aluno.getMatricula());
        ResultSet rs = pstmt.executeQuery();

        System.out.println("Curso do aluno: " + aluno.getNomeAluno());
        while (rs.next()) {
            String matricula = rs.getString("matricula");
            String nomeAluno = rs.getString("nome_aluno");
            String nomeCurso = rs.getString("nome_curso");
            System.out.println("Matrícula: " + matricula + ", Nome: " + nomeAluno + ", Curso: " + nomeCurso);
        }
        rs.close();
        pstmt.close();
        connection.close();
    }

    public void listarAlunosDoCurso(Connection connection, Curso curso) throws Exception{
        String selectSql = "SELECT a.matricula, a.nome_aluno, c.nome_curso FROM aluno a, curso c WHERE c.cod_curso = ? AND a.cod_curso = c.cod_curso";
        PreparedStatement pstmt = connection.prepareStatement(selectSql);
        pstmt.setString(1, curso.getCodigo());
        ResultSet rs = pstmt.executeQuery();

        System.out.println("Alunos presentes no curso: " + curso.getNomeCurso());
        while (rs.next()) {
            String codCurso = rs.getString("matricula");
            String nomeAluno = rs.getString("nome_aluno");
            String nomeCurso = rs.getString("nome_curso");
            System.out.println("Matrícula: " + codCurso + ", Nome do Aluno: " + nomeAluno + ", Descrição do curso: " + nomeCurso);
        }

        rs.close();
        pstmt.close();
        connection.close();
    }

    public Curso retornaCurso(Connection connection, String codCurso) throws Exception{
        String selectSql = "SELECT * FROM curso WHERE cod_curso = ?";
        PreparedStatement pstmt = connection.prepareStatement(selectSql);
        pstmt.setString(1, codCurso);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            String nomeCurso = rs.getString("nome_curso");
            String codigoCurso = rs.getString("cod_curso");
            int cargaHoraria = rs.getInt("carga_horaria");
            System.out.println("Nome: " + nomeCurso + ", Código: " + codigoCurso + ", Carga Horária: " + cargaHoraria);

            return new Curso(codigoCurso, nomeCurso, cargaHoraria);
        }
        rs.close();
        pstmt.close();
        connection.close();

        return null;
    }

    public Aluno retornaAluno(Connection connection, String matriculaBusca) throws Exception{
        String selectSql = "SELECT * FROM aluno WHERE matricula = ?";
        PreparedStatement pstmt = connection.prepareStatement(selectSql);
        pstmt.setString(1, matriculaBusca);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            String nomeAluno = rs.getString("nome_aluno");
            String matriculaAluno = rs.getString("matricula");
            String cod_curso = rs.getString("cod_curso");
            System.out.println("Nome: " + nomeAluno + ", Matrícula: " + matriculaAluno + ", Código do Curso: " + cod_curso);

            return new Aluno(matriculaAluno, nomeAluno);
        }
        rs.close();
        pstmt.close();
        connection.close();

        return null;
    }
}
