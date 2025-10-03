package com.weg.gestao_escola.controller;

import com.weg.gestao_escola.dto.curso.CursoRequisicaoDTO;
import com.weg.gestao_escola.dto.curso.CursoRespostaDTO;
import com.weg.gestao_escola.dto.professor.ProfessorRequisicaoDTO;
import com.weg.gestao_escola.dto.professor.ProfessorRespostaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class Curso {

    @GetMapping
    public ResponseEntity<List<CursoRespostaDTO>> listarCursos(){

    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoRespostaDTO> buscarCursoPorId(@PathVariable int id){

    }

    @PostMapping
    public ResponseEntity<CursoRespostaDTO> criar(@RequestBody CursoRequisicaoDTO requisicaoDTO){

    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoRespostaDTO> atualizar(@PathVariable int id,
                                                          @RequestBody CursoRequisicaoDTO requisicaoDTO){

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable int id){

    }
}
