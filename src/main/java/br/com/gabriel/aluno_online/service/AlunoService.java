package br.com.gabriel.aluno_online.service;

import br.com.gabriel.aluno_online.model.Aluno;
import br.com.gabriel.aluno_online.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service  // "Esta classe é um Service do Spring!"
public class AlunoService {

    @Autowired // Injeção de dependência automática!
    AlunoRepository alunoRepository;

    // CREATE — salvar novo aluno
    public void criarAluno(Aluno aluno) {
        alunoRepository.save(aluno);  // INSERT INTO aluno ...
    }

    // READ — listar todos
    public List<Aluno> listarTodosAlunos() {
        return alunoRepository.findAll();  // SELECT * FROM aluno
    }

    // READ — buscar por ID
    public Optional<Aluno> buscarAlunoPorId(Long id) {
        return alunoRepository.findById(id);  // SELECT * FROM aluno WHERE id = ?
    }

    // UPDATE — atualizar por ID
    public void atualizarAlunoPorId(Long id, Aluno aluno) {
        Optional<Aluno> alunoDoBanco = buscarAlunoPorId(id);

        if (alunoDoBanco.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Aluno não encontrado");
        }

        Aluno alunoParaEditar = alunoDoBanco.get();
        alunoParaEditar.setNome(aluno.getNome());
        alunoParaEditar.setCpf(aluno.getCpf());
        alunoParaEditar.setEmail(aluno.getEmail());


        alunoRepository.save(alunoParaEditar);  // UPDATE aluno SET ...
    }
    public void deletarAluno(Long id){
        Optional<Aluno> alunoDoBanco = buscarAlunoPorId(id);

        if (alunoDoBanco.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Aluno não encontrado para exclusão");
        }
        // 2. Deletamos usando o ID
        alunoRepository.deleteById(id);
    }
}
