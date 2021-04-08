package br.gov.mapa.abcom.infra.persistencia.modelodocumento;

import br.gov.mapa.abcom.infra.persistencia.modelodocumento.dto.ReferencialFiltroDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReferencialRepository extends JpaRepository<ReferencialEntity, Long>{

    @Query("select distinct referencial from ReferencialEntity referencial " +
            "where (:#{#filtro.nome} is null or lower(referencial.nome) = lower(:#{#filtro.nome})) " +
            "order by referencial.id desc")
    List<ReferencialEntity> filtro(@Param("filtro") ReferencialFiltroDTO filtro);
}
