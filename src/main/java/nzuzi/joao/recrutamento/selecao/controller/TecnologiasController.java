package nzuzi.joao.recrutamento.selecao.controller;

import nzuzi.joao.recrutamento.selecao.entity.Tecnologias;
import nzuzi.joao.recrutamento.selecao.service.TecnologiasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/selecao/recrutamento/tecnologias")
public class TecnologiasController {

    @Autowired
    TecnologiasService service;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody Tecnologias tecnologia){
        return service.create(tecnologia);
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody Tecnologias tecnologia, @RequestParam Long id){
        return service.update(tecnologia, id);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        return service.findAll();
    }

    @GetMapping("/find_by_name/")
    public ResponseEntity getTegnologiaByName(@RequestParam String nome){
        return service.findByName(nome);
    }
}
