package model;

public class Aluno {
    private String matricula;
    private String nomeAluno;

    public Aluno(String matricula, String nomeAluno) {
        this.matricula = matricula;
        this.nomeAluno = nomeAluno;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }
}
