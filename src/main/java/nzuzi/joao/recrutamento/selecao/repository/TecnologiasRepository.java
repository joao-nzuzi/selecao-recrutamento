package nzuzi.joao.recrutamento.selecao.repository;

import nzuzi.joao.recrutamento.selecao.entity.Tecnologias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TecnologiasRepository extends JpaRepository<Tecnologias, Long> {

    Optional<Tecnologias> getTecnologiasByDescricao(@Param("descricao") String descricao);
}
