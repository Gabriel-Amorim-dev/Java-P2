package br.com.gabriel.aluno_online.model;

import br.com.gabriel.aluno_online.MatriculaStatusEnum;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "matricula_aluno")
@Entity
public class MatriculaAluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    private Double nota1;
    private Double nota2;

    @Enumerated(EnumType.STRING)  // Salva "MATRICULADO" como texto no banco
    private MatriculaStatusEnum situacao;

}
