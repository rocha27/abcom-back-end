package br.gov.mapa.abcom.infra.persistencia.documento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdiomaRepository extends JpaRepository<IdiomaEntity, Long> {
}
