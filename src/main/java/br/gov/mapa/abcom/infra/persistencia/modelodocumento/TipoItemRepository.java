package br.gov.mapa.abcom.infra.persistencia.modelodocumento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoItemRepository extends JpaRepository<TipoItemEntity, Long> {

}
