package nzuzi.joao.recrutamento.selecao.service;

import nzuzi.joao.recrutamento.selecao.entity.Candidatos;

import java.util.List;
import java.util.Optional;

public interface CandidatoService {

    Candidatos create(Candidatos candidatos);
    Candidatos update(Candidatos candidatos, Long id);
    List<Candidatos> findAll();
    Optional<Candidatos> findCandidatoByName(String nome);
    Optional<Candidatos> findCandidatoByPerfil(String perfil);
    Optional<Candidatos> findCandidatoPorFaixaSalarial(Double faixaSalarialMenor, Double faixaSalarialMaior);
}
