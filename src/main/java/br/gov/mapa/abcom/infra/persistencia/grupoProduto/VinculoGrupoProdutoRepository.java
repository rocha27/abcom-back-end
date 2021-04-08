package br.gov.mapa.abcom.infra.persistencia.grupoProduto;

import br.gov.mapa.abcom.infra.persistencia.grupoProduto.dto.FiltroGrupoProdutoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VinculoGrupoProdutoRepository extends JpaRepository<VinculoGrupoProdutoEntity, Long>{

    @Query("select vinculoGrupoProduto from VinculoGrupoProdutoEntity vinculoGrupoProduto " +
            "where (:#{#id} is null or vinculoGrupoProduto.id = :#{#id}) ")
    VinculoGrupoProdutoEntity buscaVinculoPeloGrupo(@Param("id") Long id);
}
