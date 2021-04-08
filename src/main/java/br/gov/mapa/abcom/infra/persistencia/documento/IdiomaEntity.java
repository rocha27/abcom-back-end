package br.gov.mapa.abcom.infra.persistencia.documento;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(schema = "CORPORATIVO_MAPA", name = "VW_IDIOMA")
public class IdiomaEntity implements Serializable {

    @Id
    @Column(name = "ID_IDIOMA")
    private Long id;

    @Column(name = "DS_IDIOMA")
    private String nome;

}
