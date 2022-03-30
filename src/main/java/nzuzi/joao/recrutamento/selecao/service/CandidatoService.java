package nzuzi.joao.recrutamento.selecao.service;

import nzuzi.joao.recrutamento.selecao.entity.Candidatos;
import org.springframework.http.ResponseEntity;

public interface CandidatoService {

    ResponseEntity<?> create(Candidatos candidatos);
    ResponseEntity<?> update(Candidatos candidatos, Long id);
    ResponseEntity<?> findAll();
    ResponseEntity<?> findCandidatoByName(String nome);
    ResponseEntity<?> findCandidatoByPerfil(String perfil);
    ResponseEntity<?> findCandidatoPorFaixaSalarial(Double faixaSalarialMenor, Double faixaSalarialMaior);
}
