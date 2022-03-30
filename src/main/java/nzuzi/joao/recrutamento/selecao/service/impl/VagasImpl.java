package nzuzi.joao.recrutamento.selecao.service.impl;

import nzuzi.joao.recrutamento.selecao.entity.Candidatos;
import nzuzi.joao.recrutamento.selecao.entity.Vagas;
import nzuzi.joao.recrutamento.selecao.repository.VagasRepository;
import nzuzi.joao.recrutamento.selecao.service.VagaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VagasImpl implements VagaService {

    private static final Integer ERROR_CODE = 404;

    @Autowired
    private VagasRepository repository;

    Logger logger = LoggerFactory.getLogger(VagasImpl.class);

    @Override
    public ResponseEntity<?> create(Vagas vaga) {
        try {
            repository.save(vaga);
            return new ResponseEntity<>("Vaga Cadastrada Com Sucesso!", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Erro ao cadastrar a vaga ", e.getCause());
        }
        return null;
    }

    @Override
    public ResponseEntity<?> findAll() {
        try {
            Optional<List<Vagas>> allVagas = Optional.ofNullable(repository.findAll());
            return allVagas.isPresent()
                    ? ResponseEntity.ok().body(allVagas)
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não Existe(m) Vaga(s) Cadastradas!");
        } catch (Exception e) {
            logger.error("Ocorreu uma excepção ao pesquisar as vagas", e.getCause());
        }
        return null;
    }

    @Override
    public ResponseEntity<?> findTitulo(String titulo) {
        try {
            return repository.getVagasPorTitulo(titulo).isPresent()
                    ? ResponseEntity.ok().body(repository.getVagasPorTitulo(titulo))
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga ".concat(titulo).concat(" Não Encontrada"));
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao pesquisar a vaga ".concat(titulo), e.getCause());
        }
        return null;
    }

    @Override
    public ResponseEntity<?> findTipoVaga(String tipoVaga) {
        try {
            return repository.getVagasPorTipoVaga(tipoVaga).isPresent()
                    ? ResponseEntity.ok().body(repository.getVagasPorTipoVaga(tipoVaga))
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de Vaga ".concat(tipoVaga).concat(" Não Encontrada"));
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao pesquisar o tipo de vaga ".concat(tipoVaga), e.getCause());
        }
        return null;
    }

    @Override
    public ResponseEntity<?> update(Long id, Vagas vaga) {
        try{
            Vagas vagaInDB = repository.findById(id).get();
            if(isVagaExists(id).getStatusCodeValue() != HttpStatus.NOT_FOUND.value()){
                vagaInDB.setDescricao(vaga.getDescricao());
                vagaInDB.setSalario(vaga.getSalario());
                vagaInDB.setTitulo(vaga.getTitulo());
                vagaInDB.setTipoVaga(vaga.getTipoVaga());
                repository.save(vagaInDB);
                return new ResponseEntity<>("Atualização feita com sucesso! ", HttpStatus.CREATED);
            }
        }catch (Exception e){
            logger.error("Ocorreu um erro ao atualizar a vaga ", e.getCause());
        }
        return null;
    }

    private ResponseEntity<?> isVagaExists(Long id){
        try{
            Optional<Optional<Vagas>> vagaExistente = Optional.ofNullable(repository.findById(id));
            return vagaExistente.isPresent()
                    ? ResponseEntity.status(HttpStatus.OK).body(vagaExistente)
                    : new ResponseEntity<>("Vaga não encontrada ", HttpStatus.NOT_FOUND);
        }catch (Exception e){
            logger.error("Ocorreu uma exceção ", e.getCause());
        }
        return null;
    }
}
