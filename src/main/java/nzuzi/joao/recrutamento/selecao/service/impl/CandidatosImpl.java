package nzuzi.joao.recrutamento.selecao.service.impl;

import nzuzi.joao.recrutamento.selecao.entity.Candidatos;
import nzuzi.joao.recrutamento.selecao.repository.CandidatosRepository;
import nzuzi.joao.recrutamento.selecao.service.CandidatoService;
import nzuzi.joao.recrutamento.selecao.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatosImpl implements CandidatoService {

    @Autowired
    private CandidatosRepository repository;

    Logger logger = LoggerFactory.getLogger(CandidatosImpl.class);

    @Override
    public Candidatos create(Candidatos candidatos) {
        return repository.save(candidatos);
    }

    @Override
    public Candidatos update(Candidatos candidatos, Long id) {

        Candidatos candidatoInDB = repository.findById(id).get();
        if (isCandidatoExists(id).isPresent()) {
            candidatoInDB.setCurriculumVitae(candidatos.getCurriculumVitae());
            candidatoInDB.setNome(candidatos.getNome());
            candidatoInDB.setEmail(candidatos.getEmail());
            candidatoInDB.setPerfil(candidatos.getPerfil());
            candidatoInDB.setPretensaoSalarial(candidatos.getPretensaoSalarial());
            candidatoInDB.setTecnologias(candidatos.getTecnologias());
            repository.save(candidatoInDB);
        }
        return candidatoInDB;
    }

    @Override
    public List<Candidatos> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Candidatos> findCandidatoByName(String nome) {
        return repository.getCandidatoPorNome(nome);
    }

    @Override
    public Optional<Candidatos> findCandidatoByPerfil(String perfil) {
        return repository.getCandidatosPorPerfil(perfil);
    }

    @Override
    public Optional<Candidatos> findCandidatoPorFaixaSalarial(Double faixaSalarialMenor, Double faixaSalarialMaior) {
        return repository.getCandidatosPorPretensaoSalarial(faixaSalarialMenor, faixaSalarialMaior);
    }

    private Optional<Candidatos> isCandidatoExists(Long id) {
        return repository.findById(id);
    }
}
