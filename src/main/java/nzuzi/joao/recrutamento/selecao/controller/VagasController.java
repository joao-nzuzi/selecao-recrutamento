package nzuzi.joao.recrutamento.selecao.controller;

import nzuzi.joao.recrutamento.selecao.entity.Vagas;
import nzuzi.joao.recrutamento.selecao.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/selecao/recrutamento/vagas")
public class VagasController {

    @Autowired
    private VagaService service;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody Vagas vaga){
        return service.create(vaga);
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody Vagas vaga, @RequestParam Long id){
        return service.update(id, vaga);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        return service.findAll();
    }

    @GetMapping("/find_by_titulo/")
    public ResponseEntity getVagaByTitulo(@RequestParam String titulo){
        return service.findTitulo(titulo);
    }

    @GetMapping("/find_by_tipo_vaga/")
    public ResponseEntity getVagaByTipoVaga(@RequestParam String tipo){
        return service.findTipoVaga(tipo);
    }
}
