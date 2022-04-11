package nzuzi.joao.recrutamento.selecao.controller;

import nzuzi.joao.recrutamento.selecao.entity.Tecnologias;
import nzuzi.joao.recrutamento.selecao.service.TecnologiasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/selecao/recrutamento/tecnologias")
public class TecnologiasController {

    @Autowired
    TecnologiasService service;
    ResponseEntity<String> retorno;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody Tecnologias tecnologia){
        service.create(tecnologia);
        return new ResponseEntity<>("Tecnologia Cadastrada com Sucesso!", HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody Tecnologias tecnologia, @RequestParam Long id){
        Tecnologias tecnologiaActualizada = service.update(tecnologia, id);
        return new ResponseEntity<>(tecnologiaActualizada, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        return service.findAll().isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem tecnologias cadastradas")
                : ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/find_by_name/")
    public ResponseEntity<?> getTecnologiaByName(@RequestParam(defaultValue = "") String nome){
        if(nome.isEmpty()){
            retorno = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Por favor, informa o título da vaga que pretende");
        }else {
            Optional<Tecnologias> vagaByTitulo = service.findByName(nome);
            return vagaByTitulo.isPresent() ? ResponseEntity.ok().body(service.findByName(nome))
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tecnologia ".concat(nome).concat(" Não Encontrada"));
        }
        return retorno;
    }
}
