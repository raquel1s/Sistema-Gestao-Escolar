package com.weg.gestao_escola.controller;

import com.weg.gestao_escola.dto.aluno.AlunoRequisicaoDTO;
import com.weg.gestao_escola.dto.aluno.AlunoRespostaDTO;
import com.weg.gestao_escola.service.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AlunoRespostaDTO>> listarAlunos(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.listarAlunos());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoRespostaDTO> buscarAlunoPorId(@PathVariable int id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarAlunoPorId(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<AlunoRespostaDTO> criar(@RequestBody AlunoRequisicaoDTO requisicaoDTO){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(requisicaoDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoRespostaDTO> atualizar(@PathVariable int id,
                                                      @RequestBody AlunoRequisicaoDTO requisicaoDTO){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.atualizar(id, requisicaoDTO));
        }catch (Exception e){
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
