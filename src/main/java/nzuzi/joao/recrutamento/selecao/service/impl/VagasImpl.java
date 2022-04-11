package nzuzi.joao.recrutamento.selecao.service.impl;

import nzuzi.joao.recrutamento.selecao.entity.Vagas;
import nzuzi.joao.recrutamento.selecao.repository.VagasRepository;
import nzuzi.joao.recrutamento.selecao.service.VagaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VagasImpl implements VagaService {

    @Autowired
    private VagasRepository repository;

    Logger logger = LoggerFactory.getLogger(VagasImpl.class);

    @Override
    public Vagas create(Vagas vaga) {
        return repository.save(vaga);
    }

    @Override
    public List<Vagas> findAll() {
        return repository.findAll() ;
    }

    @Override
    public Optional<Vagas> findTitulo(String titulo) {
        return repository.getVagasPorTitulo(titulo);
    }

    @Override
    public Optional<Vagas> findTipoVaga(String tipoVaga) {
        return repository.getVagasPorTipoVaga(tipoVaga);
    }

    @Override
    public Vagas update(Long id, Vagas vaga) {
        Vagas vagaInDB = repository.findById(id).get();
        if(isVagaExists(id).isPresent()){
            vagaInDB.setDescricao(vaga.getDescricao());
            vagaInDB.setSalario(vaga.getSalario());
            vagaInDB.setTitulo(vaga.getTitulo());
            vagaInDB.setTipoVaga(vaga.getTipoVaga());
            repository.save(vagaInDB);
        }
        return vagaInDB;
    }

    private Optional<Vagas> isVagaExists(Long id){
           return repository.findById(id);
    }
}
