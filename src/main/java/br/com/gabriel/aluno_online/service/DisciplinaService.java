package br.com.gabriel.aluno_online.service;

import br.com.gabriel.aluno_online.model.Disciplina;
import br.com.gabriel.aluno_online.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

    @Autowired
    DisciplinaRepository disciplinaRepository;

    // CREATE — salva uma nova disciplina no banco
    public void criarDisciplina(Disciplina disciplina) {
        // INSERT INTO disciplina ...
        disciplinaRepository.save(disciplina);
    }

    // READ — busca todas as disciplinas vinculadas a um professor específico
    public List<Disciplina> listDisciplinaDoProf(Long professorId) {
        // SELECT * FROM disciplina WHERE professor_id = ?
        return disciplinaRepository.findByProfessorId(professorId);
    }
}
