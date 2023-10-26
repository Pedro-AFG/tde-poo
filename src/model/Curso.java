package model;

import java.util.HashSet;

public class Curso {
    private String codigo;
    private String nomeCurso;
    private int cargaHoraria;
    public HashSet<Aluno> listaAlunos = new HashSet<>();

    public Curso(String codigo, String nomeCurso, int cargaHoraria) {
        this.codigo = codigo;
        this.nomeCurso = nomeCurso;
        this.cargaHoraria = cargaHoraria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public void inserirAluno (Aluno aluno){
        listaAlunos.add(aluno);
    }

    public void removerAluno (Aluno aluno){
        listaAlunos.remove(aluno);
    }
}
