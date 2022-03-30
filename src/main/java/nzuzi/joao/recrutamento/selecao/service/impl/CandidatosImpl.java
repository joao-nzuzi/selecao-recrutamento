package nzuzi.joao.recrutamento.selecao.service.impl;

import nzuzi.joao.recrutamento.selecao.entity.Candidatos;
import nzuzi.joao.recrutamento.selecao.entity.Tecnologias;
import nzuzi.joao.recrutamento.selecao.repository.CandidatosRepository;
import nzuzi.joao.recrutamento.selecao.service.CandidatoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatosImpl implements CandidatoService{

    @Autowired
    private CandidatosRepository repository;

    Logger logger = LoggerFactory.getLogger(CandidatosImpl.class);

    @Override
    public ResponseEntity<?> create(Candidatos candidatos) {
        try{
            repository.save(candidatos);
            return new ResponseEntity<>("Cadastro Feito Com Sucesso!", HttpStatus.CREATED);
        }catch (Exception e){
            logger.error("Ocorreu um erro no cadastro do candidato", e.getCause());
        }
        return null;
    }

    @Override
    public ResponseEntity<?> update(Candidatos candidatos, Long id){
        try{
            Candidatos candidatoInDB = repository.findById(id).get();
            if(isCandidatoExists(id).getStatusCodeValue() != HttpStatus.NOT_FOUND.value()){
                candidatoInDB.setCurriculumVitae(candidatos.getCurriculumVitae());
                candidatoInDB.setNome(candidatos.getNome());
                candidatoInDB.setEmail(candidatos.getEmail());
                candidatoInDB.setPerfil(candidatos.getPerfil());
                candidatoInDB.setPretensaoSalarial(candidatos.getPretensaoSalarial());
                candidatoInDB.setTecnologias(candidatos.getTecnologias());
                repository.save(candidatoInDB);
                return new ResponseEntity<>("Atualização feita com sucesso! ", HttpStatus.CREATED);
            }
        }catch (Exception e){
            logger.error("Ocorreu uma excepção ao atualizar o candidato ", e.getCause());;
        }
        return null;
    }

    @Override
    public ResponseEntity<?> findAll() {
        try{
            Optional<List<Candidatos>> todosCandidatos = Optional.ofNullable(repository.findAll());
            return todosCandidatos.isPresent()
                    ? ResponseEntity.ok().body(todosCandidatos)
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não Existe(m) Candidato(s) Cadastrado(s)");
        }catch (Exception e){
            logger.error("Ocorreu um erro ao pesquisar os candidatos", e.getCause());
        }
        return null;
    }

    @Override
    public ResponseEntity<?> findCandidatoByName(String nome) {
        try{
            return repository.getCandidatoPorNome(nome).isPresent()
                    ? ResponseEntity.ok().body(repository.getCandidatoPorNome(nome))
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não Existe(m) Candidato(s) Com Nome ".concat(nome));
        }catch (Exception e){
            logger.error("Ocorreu um erro ao pesquisar os candidatos", e.getCause());
        }
        return null;
    }

    @Override
    public ResponseEntity<?> findCandidatoByPerfil(String perfil) {
        try{
            return repository.getCandidatosPorPerfil(perfil).isPresent()
                    ? ResponseEntity.ok().body(repository.getCandidatosPorPerfil(perfil))
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não Existe(m) Candidato(s) Com Perfil ".concat(perfil));
        }catch (Exception e){
            logger.error("Ocorreu um erro ao pesquisar os candidatos", e.getCause());
        }
        return null;
    }

    @Override
    public ResponseEntity<?> findCandidatoPorFaixaSalarial(Double faixaSalarialMenor, Double faixaSalarialMaior) {
        try{
            return repository.getCandidatosPorPretensaoSalarial(faixaSalarialMenor, faixaSalarialMaior).isPresent()
                    ? ResponseEntity.ok().body(repository.getCandidatosPorPretensaoSalarial(faixaSalarialMenor, faixaSalarialMaior))
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não Existe(m) Candidato(s) Com a Pretensão Salarial na Faixa de "
                        .concat(String.valueOf(faixaSalarialMenor))
                        .concat(", ")
                        .concat(String.valueOf(faixaSalarialMaior)));
        }catch (Exception e){
            logger.error("Ocorreu um erro ao pesquisar os candidatos", e.getCause());
        }
        return null;
    }

    private ResponseEntity<?> isCandidatoExists(Long id){
        try{
            Optional<Optional<Candidatos>> candidatoExistente = Optional.ofNullable(repository.findById(id));
            return candidatoExistente.isPresent()
                    ? ResponseEntity.status(HttpStatus.OK).body(candidatoExistente)
                    : new ResponseEntity<>("Candidato Não Encontrado ", HttpStatus.NOT_FOUND);
        }catch (Exception e){
            logger.error("Ocorreu uma exceção ", e.getCause());
        }
        return null;
    }
}