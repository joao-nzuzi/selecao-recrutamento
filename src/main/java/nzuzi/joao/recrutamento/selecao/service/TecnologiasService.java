package nzuzi.joao.recrutamento.selecao.service;

import nzuzi.joao.recrutamento.selecao.entity.Tecnologias;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TecnologiasService {

    Tecnologias create(Tecnologias tecnologia);
    List<Tecnologias> findAll();
    Tecnologias update(Tecnologias tecnologia, Long id);
    Optional<Tecnologias> findByName(String nome);
}
