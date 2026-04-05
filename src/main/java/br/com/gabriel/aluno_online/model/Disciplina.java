package br.com.gabriel.aluno_online.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "disciplina")
@Entity
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Integer cargaHoraria;

    @ManyToOne  // "Muitas disciplinas → um professor"
    @JoinColumn(name = "professor_id")  // Nome da FK no banco
    private Professor professor;
}