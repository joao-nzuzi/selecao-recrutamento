package nzuzi.joao.recrutamento.selecao.controller;

import nzuzi.joao.recrutamento.selecao.entity.Candidatos;
import nzuzi.joao.recrutamento.selecao.service.CandidatoService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("api/v1/selecao/recrutamento/candidatos")
public class CandidatosController {

    @Autowired
    private CandidatoService service;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody Candidatos candidato) throws IOException {
        Candidatos candidatos = new Candidatos();
        candidatos.setCurriculumVitae(candidato.getCurriculumVitae());
        candidatos.setNome(candidato.getNome());
        candidatos.setPerfil(candidato.getPerfil());
        candidatos.setEmail(candidato.getEmail());
        candidatos.setPretensaoSalarial(candidato.getPretensaoSalarial());
        candidatos.setTecnologias(candidato.getTecnologias());
        candidatos.setDataRegisto(candidato.getDataRegisto());
        return service.create(candidatos);
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody  Candidatos candidato, @RequestParam("id") Long id){
        return service.update(candidato, id);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        return service.findAll();
    }

    @GetMapping("/find_by_name/")
    public ResponseEntity getCandidatoByName(@RequestParam("nome") String nome){
        return service.findCandidatoByName(nome);
    }

    @GetMapping("/find_by_perfil/")
    public ResponseEntity getCandidatoByPerfil(@RequestParam("perfil") String perfil){
        return service.findCandidatoByPerfil(perfil);
    }

    @GetMapping("/find_by_faixa_salarial/")
    public ResponseEntity getCandidatoByFaixaSalarial(@RequestParam("faixaSalarialMenor") Double faixaSalarialMenor, @RequestParam("faixaSalarialMaior") Double faixaSalarialMaior){
        return service.findCandidatoPorFaixaSalarial(faixaSalarialMenor, faixaSalarialMaior);
    }
}
