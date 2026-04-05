package br.com.gabriel.aluno_online.controller;

import br.com.gabriel.aluno_online.dto.AtualizarNotasRequestDTO;
import br.com.gabriel.aluno_online.dto.HistoricoAlunoResponseDTO;
import br.com.gabriel.aluno_online.model.MatriculaAluno;
import br.com.gabriel.aluno_online.service.MatriculaAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matriculas")
public class MatriculaAlunoController {
    @Autowired
    MatriculaAlunoService matriculaAlunoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarMatricula(@RequestBody MatriculaAluno m) {
        matriculaAlunoService.criarMatricula(m);
    }

    @PostMapping("/trancar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void trancarMatricula(@PathVariable Long id){
        matriculaAlunoService.trancarMatricula(id);
    }
    @PatchMapping("/atualizar-notas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarNotas(@PathVariable Long id,
                               @RequestBody AtualizarNotasRequestDTO dto) {
        matriculaAlunoService.atualizarNotas(id, dto);
    }

    @GetMapping("/emitir-historico/{alunoId}")
    @ResponseStatus(HttpStatus.OK)
    public HistoricoAlunoResponseDTO emitirHistorico(
            @PathVariable Long alunoId) {
        return matriculaAlunoService.emitirHistorico(alunoId);
    }

}