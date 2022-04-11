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
    public Tecnologias create(Tecnologias tecnologia) {
        return repository.save(tecnologia);
    }

    @Override
    public List<Tecnologias> findAll() {
        return repository.findAll();
    }


    @Override
    public Tecnologias update(Tecnologias tecnologia, Long id) {

            Tecnologias tecnologiaInDB = repository.findById(id).get();
            if(isTecnologiaExists(id).isPresent()){
                tecnologiaInDB.setDescricao(tecnologia.getDescricao());
                repository.save(tecnologiaInDB);
            }

            return tecnologiaInDB;
    }

    private Optional<Tecnologias> isTecnologiaExists(Long id){
        return repository.findById(id);

    }

    @Override
    public Optional<Tecnologias> findByName(String nome) {
        return repository.getTecnologiasByDescricaoLike(nome);

    }
}
