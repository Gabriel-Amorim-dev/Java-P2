package br.com.gabriel.aluno_online.dto;

import lombok.Data;

import java.util.List;

@Data
public class HistoricoAlunoResponseDTO {
    private String nomeAluno;
    private String emailAluno;
    private String cpfAluno;
    private List<DisciplinasAlunoResponseDTO> disciplinas;
}
