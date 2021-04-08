package br.gov.mapa.abcom.infra.persistencia.grupoProduto;

import br.gov.mapa.abcom.infra.persistencia.grupoProduto.dto.FiltroGrupoProdutoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoProdutoRepository extends JpaRepository<GrupoProdutoEntity, Long>{
    @Query("select distinct grupoProduto from GrupoProdutoEntity grupoProduto " +
            "where (:#{#filtro.nome} is null or lower(grupoProduto.nome)  = lower(:#{#filtro.nome}))" +
            "order by grupoProduto.id desc")
    List<GrupoProdutoEntity> filtro(@Param("filtro") FiltroGrupoProdutoDTO filtro);
}
