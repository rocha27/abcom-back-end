package br.gov.mapa.abcom.infra.persistencia.modelodocumento;

import br.gov.mapa.abcom.infra.persistencia.modelodocumento.dto.ModeloDocumentoFiltroDTO;
import br.gov.mapa.abcom.infra.persistencia.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReferencialModeloDocumentoRepository extends JpaRepository<ReferencialModeloDocumentoEntity, Long> {

      @Query("select distinct documento from ReferencialModeloDocumentoEntity documento " +
              "where (:#{#filtro.idModeloDocumento} is null or documento.id = :#{#filtro.idModeloDocumento})" +
              "and (:#{#filtro.idReferencial} is null or documento.referencial.id = :#{#filtro.idReferencial})" +
              "order by documento.id desc")
      List<ReferencialModeloDocumentoEntity> filtro(@Param("filtro") ModeloDocumentoFiltroDTO filtro);

      ReferencialModeloDocumentoEntity findByNome(String nomeModelo);
}
