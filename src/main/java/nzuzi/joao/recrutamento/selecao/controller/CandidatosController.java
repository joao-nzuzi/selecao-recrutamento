package nzuzi.joao.recrutamento.selecao.controller;

import nzuzi.joao.recrutamento.selecao.entity.Candidatos;
import nzuzi.joao.recrutamento.selecao.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("api/v1/selecao/recrutamento/candidatos")
public class CandidatosController {

    @Autowired
    private CandidatoService service;
    ResponseEntity<String> retorno;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody Candidatos candidato) {
        Candidatos candidatos = service.create(candidato);
        return new ResponseEntity<>(candidatos, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody  Candidatos candidato, @RequestParam("id") Long id){
        Candidatos candidatoAtualizado = service.update(candidato, id);
        return new ResponseEntity<>(candidatoAtualizado, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        return service.findAll().isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não Existe(m) Candidato(s) Cadastrado(s) ")
                : ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/find_by_name/")
    public ResponseEntity<? extends Object> getCandidatoByName(@RequestParam(defaultValue = "") String nome){
        if(nome.isEmpty()){
            retorno = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Por favor, informa o nome do candidato que pretende");
        }else{
            Optional<Candidatos> candidaPorNome = service.findCandidatoByName(nome);
            return candidaPorNome.isPresent() ? ResponseEntity.ok().body(service.findCandidatoByName(nome))
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidato ".concat(nome).concat(" Não Encontrada"));
        }
        return retorno;
    }

    @GetMapping("/find_by_perfil/")
    public ResponseEntity getCandidatoByPerfil(@RequestParam(defaultValue = "") String perfil){
        if(perfil.isEmpty()){
            retorno = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Por favor, informa o perfil do candidato que pretende");
        }else{
            Optional<Candidatos> perfilCandidato = service.findCandidatoByPerfil(perfil);
            return perfilCandidato.isPresent() ? ResponseEntity.ok().body(service.findCandidatoByPerfil(perfil))
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidato ".concat(perfil).concat(" Não Encontrada"));
        }
        return retorno;
    }

    @GetMapping("/find_by_faixa_salarial/")
    public ResponseEntity getCandidatoByFaixaSalarial(@RequestParam(defaultValue = "") Double faixaSalarialMenor, @RequestParam(defaultValue = "") Double faixaSalarialMaior){
        if (String.valueOf(faixaSalarialMenor).isEmpty() || String.valueOf(faixaSalarialMaior).isEmpty()){
            retorno = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Por favor, informa a faixa salarial que pretende");
        }else{
            Optional<Candidatos> faixaSalarial = service.findCandidatoPorFaixaSalarial(faixaSalarialMenor, faixaSalarialMaior);
            return faixaSalarial.isPresent() ? ResponseEntity.ok().body(service.findCandidatoPorFaixaSalarial(faixaSalarialMenor, faixaSalarialMaior))
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não Existe Informação Para a Faixa Salarial informada");
        }
        return retorno;
    }
}
