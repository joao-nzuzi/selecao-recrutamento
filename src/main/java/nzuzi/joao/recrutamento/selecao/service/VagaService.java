package nzuzi.joao.recrutamento.selecao.service;

import nzuzi.joao.recrutamento.selecao.entity.Vagas;

import java.util.List;
import java.util.Optional;

public interface VagaService {

    Vagas create(Vagas vaga);
    List<Vagas> findAll();
    Optional<Vagas> findTitulo(String titulo);
    Optional<Vagas> findTipoVaga(String tipoVaga);
    Vagas update(Long id, Vagas vaga);
}
