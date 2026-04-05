package br.com.gabriel.aluno_online;

public enum MatriculaStatusEnum {

    MATRICULADO("matriculado"),
    APROVADO("aprovado"),
    REPROVADO("reprovado"),
    TRANCADO("trancado");

    private String situacao;

    MatriculaStatusEnum (String situacao){
        this.situacao = situacao;
    }

    public String getSituacao(){
        return situacao;
    }
}
