package com.weg.gestao_escola.controller;

import com.weg.gestao_escola.dto.aula.AulaRequisicaoDTO;
import com.weg.gestao_escola.dto.aula.AulaRespostaDTO;
import com.weg.gestao_escola.service.AulaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aulas")
public class AulaController {

    private AulaService service;

    public AulaController(AulaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AulaRespostaDTO>> listarAulas(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.listarAulas());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AulaRespostaDTO> buscarAulaPorId(@PathVariable int id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarAulaPorId(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<AulaRespostaDTO> criar(@RequestBody AulaRequisicaoDTO requisicaoDTO){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(requisicaoDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AulaRespostaDTO> atualizar(@PathVariable int id,
                                                     @RequestBody AulaRequisicaoDTO requisicaoDTO){
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
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
