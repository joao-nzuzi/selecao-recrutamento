package nzuzi.joao.recrutamento.selecao.repository;

import nzuzi.joao.recrutamento.selecao.entity.Vagas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VagasRepository extends JpaRepository<Vagas, Long> {

    @Query(value = "select * from vagas where tipo_vaga like %:tipo_vaga", nativeQuery = true)
    Optional<Vagas> getVagasPorTipoVaga(@Param("tipo_vaga") String tipoVaga);

    @Query(value = "select * from vagas where titulo like %:titulo", nativeQuery = true)
    Optional<Vagas> getVagasPorTitulo(@Param("titulo") String titulo);
}
