package br.com.gabriel.aluno_online.service;

import br.com.gabriel.aluno_online.model.Aluno;
import br.com.gabriel.aluno_online.model.Professor;
import br.com.gabriel.aluno_online.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    public void criarProfessor(Professor professor){
        professorRepository.save(professor);
    }

    public Optional<Professor> buscarProfessorPorId(Long id) {
        return professorRepository.findById(id);
    }

    public List<Professor> listarTodosOsProfessores(){
        return professorRepository.findAll();
    }

    public void atualizarProfessorPorId(Long id, Professor professor) {
        Optional<Professor> professorDoBanco = buscarProfessorPorId(id);

        if (professorDoBanco.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Professor não encontrado para a alteração");
        }
        Professor professorParaEditar = professorDoBanco.get();
        professorParaEditar.setNome(professor.getNome());
        professorParaEditar.setCpf(professor.getCpf());
        professorParaEditar.setEmail(professor.getEmail());

        professorRepository.save(professorParaEditar);
    }

    public void deletarProfessor(Long id){
        Optional<Professor> professorDoBanco = buscarProfessorPorId(id);

        if (professorDoBanco.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Professor não encontrado para exclusão");
        }
        // 2. Deletamos usando o ID
        professorRepository.deleteById(id);
    }
}
