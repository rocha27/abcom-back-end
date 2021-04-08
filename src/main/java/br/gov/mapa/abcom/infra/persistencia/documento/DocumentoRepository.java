package br.gov.mapa.abcom.infra.persistencia.documento;

import br.gov.mapa.abcom.infra.persistencia.documento.dto.DocumentoFiltroDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentoRepository extends JpaRepository<DocumentoEntity, Long> {

    @Query("select distinct documento from DocumentoEntity documento " +
            "where (:#{#filtro.nome} is null or lower(documento.edicao)  = lower(:#{#filtro.nome}))" +
            "order by documento.id desc")
    List<DocumentoEntity> filtro(@Param("filtro") DocumentoFiltroDTO filtro);

}
