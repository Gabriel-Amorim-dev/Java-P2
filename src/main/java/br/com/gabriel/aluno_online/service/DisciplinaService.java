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

    public void criarDisciplina(Disciplina disciplina){
        disciplinaRepository.save(disciplina);
    }
    public List<Disciplina> listDisciplinaDoProf(Long professorId){
        return disciplinaRepository.findByProfessorId(professorId);
    }
}
