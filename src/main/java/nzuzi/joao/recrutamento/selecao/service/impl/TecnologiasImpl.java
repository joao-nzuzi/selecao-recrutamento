package nzuzi.joao.recrutamento.selecao.service.impl;

import nzuzi.joao.recrutamento.selecao.entity.Tecnologias;
import nzuzi.joao.recrutamento.selecao.repository.TecnologiasRepository;
import nzuzi.joao.recrutamento.selecao.service.TecnologiasService;
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
public class TecnologiasImpl implements TecnologiasService {

    @Autowired
    private TecnologiasRepository repository;

    Logger logger = LoggerFactory.getLogger(TecnologiasImpl.class);

    @Override
    public ResponseEntity<?> create(Tecnologias tecnologia) {
        try{
            repository.save(tecnologia);
            return new ResponseEntity<>("Cadastro Feito Com Sucesso!", HttpStatus.CREATED);
        }catch (Exception e){
            logger.error("Ocorreu um erro ao cadastrar a Tecnologia");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ocorreu um erro ao cadastrar a Tecnologia!");
        }
    }

    @Override
    public ResponseEntity<?> findAll() {
        try {
            Optional<List<Tecnologias>> todasTecnologias = Optional.ofNullable(repository.findAll());
            return todasTecnologias.isPresent()
                    ? ResponseEntity.ok().body(todasTecnologias)
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não Existe(m) Tecnologias(s) Cadastrado(s)");
        }catch (Exception e){
            logger.error("Ocorreu um erro ao pesquisar as Tecnologias", e.getCause());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ocorreu um erro ao pesquisar as Tecnologias!");
        }
    }


    @Override
    public ResponseEntity<?> update(Tecnologias tecnologia, Long id) {
        try{
            Tecnologias tecnologiaInDB = repository.findById(id).get();
            if(isTecnologiaExists(id).getStatusCodeValue() != HttpStatus.NOT_FOUND.value()){
                tecnologiaInDB.setDescricao(tecnologia.getDescricao());
                repository.save(tecnologiaInDB);
                return ResponseEntity.status(HttpStatus.CREATED).body("Atualização feita com sucesso!");
            }
        }catch (Exception e){
            logger.error("Ocorreu uma exceção ao atualizar a tecnologia ", e.getCause());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ocorreu uma exceção ao atualizar a tecnologia!");
        }
        return null;
    }

    private ResponseEntity<?> isTecnologiaExists(Long id){
        try{
            Optional<Optional<Tecnologias>> tecnologiaExistente = Optional.ofNullable(repository.findById(id));
            return tecnologiaExistente.isPresent()
                    ? ResponseEntity.ok().body(tecnologiaExistente)
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tecnologia Não Encontrada!");
        }catch (Exception e){
            logger.error("Ocorreu uma exceção ", e.getCause());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ocorreu uma exceção!");
        }
    }

    @Override
    public ResponseEntity<?> findByName(String nome) {
        try{
            return repository.getTecnologiasByDescricaoLike(nome).isPresent()
                    ? ResponseEntity.ok().body(repository.getTecnologiasByDescricaoLike(nome))
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não Existe(m) Tecnologia(s) Com Nome ".concat(nome));
        }catch (Exception e){
            logger.error("Ocorreu um erro ao pesquisar a(s) Tecnologia(s)", e.getCause());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ocorreu um erro ao pesquisar a(s) Tecnologia(s)!");
        }
    }
}
