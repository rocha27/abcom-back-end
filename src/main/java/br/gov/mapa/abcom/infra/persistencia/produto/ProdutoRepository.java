package br.gov.mapa.abcom.infra.persistencia.produto;

import br.gov.mapa.abcom.infra.persistencia.produto.dto.FiltroProdutoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    @Query("select distinct produto from Produto produto " +
            "where (:#{#filtro.nome} is null or lower(produto.nome)  = lower(:#{#filtro.nome}))" +
            "order by produto.id desc")
    List<Produto> filtro(@Param("filtro") FiltroProdutoDTO filtro);
}
