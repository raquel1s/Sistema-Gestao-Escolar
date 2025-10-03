package com.weg.gestao_escola.controller;

import com.weg.gestao_escola.dto.aluno.AlunoRequisicaoDTO;
import com.weg.gestao_escola.dto.aluno.AlunoRespostaDTO;
import com.weg.gestao_escola.dto.professor.ProfessorRequisicaoDTO;
import com.weg.gestao_escola.dto.professor.ProfessorRespostaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @GetMapping
    public ResponseEntity<List<ProfessorRespostaDTO>> listarProfessores(){

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorRespostaDTO> buscarProfessorPorId(@PathVariable int id){

    }

    @PostMapping
    public ResponseEntity<ProfessorRespostaDTO> criar(@RequestBody ProfessorRequisicaoDTO requisicaoDTO){

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorRespostaDTO> atualizar(@PathVariable int id,
                                                      @RequestBody ProfessorRequisicaoDTO requisicaoDTO){

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable int id){

    }
}
