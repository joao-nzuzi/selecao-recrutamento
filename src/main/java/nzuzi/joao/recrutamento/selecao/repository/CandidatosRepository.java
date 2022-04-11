package nzuzi.joao.recrutamento.selecao.repository;

import nzuzi.joao.recrutamento.selecao.entity.Candidatos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CandidatosRepository extends JpaRepository<Candidatos, Long> {
    @Query(value = "select * from candidatos where nome like %:nome", nativeQuery = true)
    Optional<Candidatos> getCandidatoPorNome(@Param("nome") String nome);

    @Query(value = "select * from candidatos where perfil like %:perfil", nativeQuery = true)
    Optional<Candidatos> getCandidatosPorPerfil(@Param("perfil") String perfil);

    @Query(value = "select * from candidatos where pretensao_salarial between :faixa_salarial_menor and :faixa_salarial_maior", nativeQuery = true)
    Optional<Candidatos> getCandidatosPorPretensaoSalarial(@Param("faixa_salarial_menor") Double faixa_salarial_menor, @Param("faixa_salarial_maior") Double faixa_salarial_maior);
}
