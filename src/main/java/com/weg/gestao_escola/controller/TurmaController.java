package com.weg.gestao_escola.controller;

import com.weg.gestao_escola.dto.curso.CursoRequisicaoDTO;
import com.weg.gestao_escola.dto.curso.CursoRespostaDTO;
import com.weg.gestao_escola.dto.turma.TurmaRequisicaoDTO;
import com.weg.gestao_escola.dto.turma.TurmaRespostaDTO;
import com.weg.gestao_escola.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private TurmaService service;

    public TurmaController(TurmaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TurmaRespostaDTO>> listarAlunos(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.listarTurmas());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaRespostaDTO> buscarAlunoPorId(@PathVariable int id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarTurmaPorId(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<TurmaRespostaDTO> criar(@Valid @RequestBody TurmaRequisicaoDTO requisicaoDTO){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(requisicaoDTO));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaRespostaDTO> atualizar(@Valid @PathVariable int id,
                                                      @RequestBody TurmaRequisicaoDTO requisicaoDTO){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.atualizar(id, requisicaoDTO));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable int id){
        try{
            service.excluir(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
