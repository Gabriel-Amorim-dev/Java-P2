package br.com.gabriel.aluno_online.service;

import br.com.gabriel.aluno_online.MatriculaStatusEnum;
import br.com.gabriel.aluno_online.dto.AtualizarNotasRequestDTO;
import br.com.gabriel.aluno_online.dto.DisciplinasAlunoResponseDTO;
import br.com.gabriel.aluno_online.dto.HistoricoAlunoResponseDTO;

import br.com.gabriel.aluno_online.model.MatriculaAluno;
import br.com.gabriel.aluno_online.repository.MatriculaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MatriculaAlunoService {
    private static final double MEDIA_PARA_APROVACAO = 7.0;

    @Autowired
    MatriculaAlunoRepository matriculaAlunoRepository;


    public void criarMatricula(MatriculaAluno matriculaAluno){
        matriculaAluno.setSituacao(
                MatriculaStatusEnum.MATRICULADO
        );
        matriculaAlunoRepository.save(matriculaAluno);
    }
    public void trancarMatricula(Long Id){
        MatriculaAluno matricula = matriculaAlunoRepository.findById(Id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Matricula não encontrada"));
        if (matricula.getSituacao().equals(MatriculaStatusEnum.MATRICULADO)) {
            matricula.setSituacao(
                    MatriculaStatusEnum.TRANCADO
            );
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Só é possivel trancar com o status MATRICULADO");

        }
    }
    public void atualizarNotas(Long id, AtualizarNotasRequestDTO dto) {
        // Busca no repositório correto e trata o Optional
        MatriculaAluno matricula = matriculaAlunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Matrícula não encontrada"));

        if (dto.getNota1() != null) matricula.setNota1(dto.getNota1());
        if (dto.getNota2() != null) matricula.setNota2(dto.getNota2());

        if (matricula.getNota1() != null && matricula.getNota2() != null) {
            double media = (matricula.getNota1() + matricula.getNota2()) / 2;
            matricula.setSituacao(media >= MEDIA_PARA_APROVACAO
                    ? MatriculaStatusEnum.APROVADO
                    : MatriculaStatusEnum.REPROVADO);
        }

        matriculaAlunoRepository.save(matricula);
    }

    public HistoricoAlunoResponseDTO emitirHistorico(Long alunoId) {
        // 1. Busca a lista de todas as matrículas desse aluno específico
        List<MatriculaAluno> matriculasDoAluno = matriculaAlunoRepository.findAlunoById(alunoId);

        if (matriculasDoAluno.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma matrícula encontrada para este aluno");
        }

        // 2. Criar o objeto DTO que será retornado
        // ... preencher a lista de disciplinas e notas ...

        return getHistoricoAlunoResponseDTO(matriculasDoAluno);
    }

    private static HistoricoAlunoResponseDTO getHistoricoAlunoResponseDTO(List<MatriculaAluno> matriculasDoAluno) {
        HistoricoAlunoResponseDTO historico = new HistoricoAlunoResponseDTO();
        historico.setNomeAluno(matriculasDoAluno.getFirst().getAluno().getNome());
        historico.setCpfAluno(matriculasDoAluno.getFirst().getAluno().getCpf());
        historico.setEmailAluno(matriculasDoAluno.getFirst().getAluno().getEmail());
        List<DisciplinasAlunoResponseDTO> listaDisciplinas = matriculasDoAluno.stream()
                .map(matricula -> {
                    DisciplinasAlunoResponseDTO item = new DisciplinasAlunoResponseDTO();
                    item.setNomeDisciplina(matricula.getDisciplina().getNome());
                    item.setNota1(matricula.getNota1());
                    item.setNota2(matricula.getNota2());
                    item.setMedia((matricula.getNota1() + matricula.getNota2()) / 2); // Opcional
                    item.setStatus(matricula.getSituacao());
                    return item;
                }).toList();

        historico.setDisciplinas(listaDisciplinas);

        return historico;
    }
}
