package nzuzi.joao.recrutamento.selecao.controller;

import nzuzi.joao.recrutamento.selecao.entity.Vagas;
import nzuzi.joao.recrutamento.selecao.service.VagaService;
import nzuzi.joao.recrutamento.selecao.service.impl.VagasImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/selecao/recrutamento/vagas")
public class VagasController {

    Logger logger = LoggerFactory.getLogger(VagasImpl.class);
    ResponseEntity<String> retorno;

    @Autowired
    private VagaService service;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody Vagas vaga){
        service.create(vaga);
        return new ResponseEntity<>("Vaga Cadastrada com Sucesso!", HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody Vagas vaga, @RequestParam Long id){
        Vagas vagaAtualizada = service.update(id, vaga);
        return new ResponseEntity<>(vagaAtualizada, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        return service.findAll().isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem vagas cadastradas")
        : ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/find_by_titulo/")
    public ResponseEntity<? extends Object> getVagaByTitulo(@RequestParam(defaultValue = "") String titulo){
        if(titulo.isEmpty()){
           retorno = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Por favor, informa o título da vaga que pretende");
        }else {
            Optional<Vagas> vagaByTitulo = service.findTitulo(titulo);
            return vagaByTitulo.isPresent() ? ResponseEntity.ok().body(service.findTitulo(titulo))
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga ".concat(titulo).concat(" Não Encontrada"));
        }
        return retorno;
    }

    @GetMapping("/find_by_tipo_vaga/")
    public HttpEntity<? extends Object> getVagaByTipoVaga(@RequestParam(defaultValue = "") String tipo){
        if(tipo.isEmpty()){
           retorno = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Por favor, informa o tipo de vaga que pretende");
        }else{
            Optional<Vagas> vagaByTipoVaga = service.findTipoVaga(tipo);
            return vagaByTipoVaga.isPresent() ? ResponseEntity.ok().body(service.findTipoVaga(tipo))
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga ".concat(tipo).concat(" Não Encontrada"));
        }
        return retorno;
    }
}
