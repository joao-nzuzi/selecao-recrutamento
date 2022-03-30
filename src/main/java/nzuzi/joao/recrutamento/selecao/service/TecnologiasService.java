package nzuzi.joao.recrutamento.selecao.service;

import nzuzi.joao.recrutamento.selecao.entity.Tecnologias;
import org.springframework.http.ResponseEntity;

public interface TecnologiasService {

    ResponseEntity<?> create(Tecnologias tecnologia);
    ResponseEntity<?> findAll();
    ResponseEntity<?> update(Tecnologias tecnologia, Long id);
    ResponseEntity<?> findByName(String nome);
}
