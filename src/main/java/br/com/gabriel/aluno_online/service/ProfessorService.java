package br.com.gabriel.aluno_online.service;

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

    // CREATE — salva um novo professor no banco
    public void criarProfessor(Professor professor) {
        // INSERT INTO professor ...
        professorRepository.save(professor);
    }

    // READ — busca um professor pelo ID, retorna Optional (pode ser vazio)
    public Optional<Professor> buscarProfessorPorId(Long id) {
        // SELECT * FROM professor WHERE id = ?
        return professorRepository.findById(id);
    }

    // READ — retorna todos os professores cadastrados
    public List<Professor> listarTodosOsProfessores() {
        // SELECT * FROM professor
        return professorRepository.findAll();
    }

    // UPDATE — atualiza os dados de um professor existente pelo ID
    public void atualizarProfessorPorId(Long id, Professor professor) {
        // 1. Verifica se o professor existe antes de tentar editar
        Optional<Professor> professorDoBanco = buscarProfessorPorId(id);
        if (professorDoBanco.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Professor não encontrado para a alteração");
        }

        // 2. Pega o objeto gerenciado pelo JPA e atualiza os campos
        Professor professorParaEditar = professorDoBanco.get();
        professorParaEditar.setNome(professor.getNome());
        professorParaEditar.setCpf(professor.getCpf());
        professorParaEditar.setEmail(professor.getEmail());

        // 3. Salva as alterações — UPDATE INTO professor ...
        professorRepository.save(professorParaEditar);
    }

    // DELETE — remove um professor pelo ID
    public void deletarProfessor(Long id) {
        // 1. Verifica se o professor existe antes de tentar deletar
        Optional<Professor> professorDoBanco = buscarProfessorPorId(id);
        if (professorDoBanco.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Professor não encontrado para exclusão");
        }

        // 2. Deleta usando o ID — DELETE FROM professor WHERE id = ?
        professorRepository.deleteById(id);
    }
}
