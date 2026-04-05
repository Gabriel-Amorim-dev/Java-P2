package br.com.gabriel.aluno_online.repository;

import br.com.gabriel.aluno_online.model.Disciplina;
import br.com.gabriel.aluno_online.model.MatriculaAluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculaAlunoRepository extends JpaRepository<MatriculaAluno, Long> {
    List<MatriculaAluno> findAlunoById(Long Aluno_Id);
}
