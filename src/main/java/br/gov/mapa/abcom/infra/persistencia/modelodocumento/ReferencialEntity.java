package br.gov.mapa.abcom.infra.persistencia.modelodocumento;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(schema = "ABCOM_MAPA", name = "S_REFERENCIAL")
public class ReferencialEntity implements Serializable {

    @Id
    @Column(name = "ID_REFERENCIAL")
    @SequenceGenerator(name = "SQ_REFERENCIAL", sequenceName = "ABCOM_MAPA.SQ_REFERENCIAL", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_REFERENCIAL")
    private Long id;

    @Column(name = "NM_REFERENCIAL")
    private String nome;

    @Column(name = "ST_REFERENCIAL")
    private boolean ativo;

}
