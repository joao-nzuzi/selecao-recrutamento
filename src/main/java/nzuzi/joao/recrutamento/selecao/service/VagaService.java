package nzuzi.joao.recrutamento.selecao.service;

import nzuzi.joao.recrutamento.selecao.entity.Vagas;
import org.springframework.http.ResponseEntity;

public interface VagaService {

    ResponseEntity<?> create(Vagas vaga);
    ResponseEntity<?> findAll();
    ResponseEntity<?> findTitulo(String titulo);
    ResponseEntity<?> findTipoVaga(String tipoVaga);
    ResponseEntity<?> update(Long id, Vagas vaga);
}
