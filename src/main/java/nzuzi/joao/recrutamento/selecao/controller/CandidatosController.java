package nzuzi.joao.recrutamento.selecao.controller;

import nzuzi.joao.recrutamento.selecao.entity.Candidatos;
import nzuzi.joao.recrutamento.selecao.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/selecao/recrutamento/candidatos")
public class CandidatosController {

    @Autowired
    private CandidatoService service;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody Candidatos candidato){
        return service.create(candidato);
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody  Candidatos candidato, @RequestParam Long id){
        return service.update(candidato, id);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        return service.findAll();
    }

    @GetMapping("/find_by_name/")
    public ResponseEntity getCandidatoByName(@RequestParam String nome){
        return service.findCandidatoByName(nome);
    }

    @GetMapping("/find_by_perfil/")
    public ResponseEntity getCandidatoByPerfil(@RequestParam String perfil){
        return service.findCandidatoByPerfil(perfil);
    }

    @GetMapping("/find_by_faixa_salarial/")
    public ResponseEntity getCandidatoByFaixaSalarial(@RequestParam Double faixaSalarialMenor, @RequestParam Double faixaSalarialMaior){
        return service.findCandidatoPorFaixaSalarial(faixaSalarialMenor, faixaSalarialMaior);
    }
}
