package br.com.gabriel.aluno_online.repository;

import br.com.gabriel.aluno_online.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  AlunoRepository extends JpaRepository<Aluno, Long> {
}
