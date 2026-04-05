package br.com.gabriel.aluno_online.dto;

import br.com.gabriel.aluno_online.MatriculaStatusEnum;
import lombok.Data;

@Data
public class DisciplinasAlunoResponseDTO {
    private String nomeDisciplina;
    private String nomeProfessor;
    private Double nota1;
    private Double nota2;
    private Double media;
    private MatriculaStatusEnum status;
}
